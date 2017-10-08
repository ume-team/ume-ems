/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.umeframework.dora.ajax.AjaxParser;
import org.umeframework.dora.bean.BeanFactory;
import org.umeframework.dora.context.SessionContext;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.exception.TimeoutException;
import org.umeframework.dora.service.BaseComponent;
import org.umeframework.dora.service.user.UserLoginService;
import org.umeframework.dora.util.ReflectUtil;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.crud.EntityCrudManager;
import org.umeframework.ems.desc.EntityDescManager;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.entity.EmRoleAclDto;
import org.umeframework.ems.uac.user.dto.UserAuthDto;

import javax.annotation.Resource;

/**
 * BaseCRUDController
 *
 * @author Yue MA
 *
 */
@Service
public class BaseCrudController extends BaseComponent {
	/**
	 * BeanFactory instance
	 */
	@Resource(name = "beanFactory")
	private BeanFactory beanFactory;
	/**
	 * EntityDESCManager instance
	 */
	@Resource
	private EntityDescManager entityDESCManager;
	/**
	 * tableCRUDManager
	 */
	@Resource
	private EntityCrudManager<?, ?> entityCRUDManager;
	/**
	 * Login service instance
	 */
	@Resource(name = "userLoginService")
	private UserLoginService userLoginService;
	/**
	 * json data parser for selected entity manager instance
	 */
	@Resource(name = "ajaxParser")
	private AjaxParser<String> ajaxParser;
	/**
	 * ACCESS level list
	 */
	private Map<String, Integer> accessDefine;

	/**
	 * OperateCategory
	 */
	enum OperateCategory {
		count, retrieve, retrieveByPrimaryKey, create, update, delete, createMulti, updateMulti, deleteMulti
	}

	/**
	 * AbstractCRUDController
	 */
	public BaseCrudController() {
		accessDefine = new HashMap<String, Integer>();
		accessDefine.put(OperateCategory.retrieve.toString(), 1);
		accessDefine.put(OperateCategory.retrieveByPrimaryKey.toString(), 1);
		accessDefine.put(OperateCategory.count.toString(), 1);

		accessDefine.put(OperateCategory.update.toString(), 2);
		accessDefine.put(OperateCategory.updateMulti.toString(), 2);

		accessDefine.put(OperateCategory.create.toString(), 4);
		accessDefine.put(OperateCategory.createMulti.toString(), 4);
		accessDefine.put(OperateCategory.delete.toString(), 4);
		accessDefine.put(OperateCategory.deleteMulti.toString(), 4);
	}

	/**
	 * Get defined operate category list
	 * 
	 * @return
	 */
	public String[] getOperateCategory() {
		String[] result = new String[OperateCategory.values().length];
		for (int i = 0; i < OperateCategory.values().length; i++) {
			result[i] = OperateCategory.values()[i].toString();
		}
		return result;
	}

	/**
	 * CRUD execute entry
	 * 
	 * @param category
	 * @param entId
	 * @param jsonInput
	 * @return
	 * @throws TimeoutException 
	 */
	public Object execute(String category, String entId, String jsonInput) throws TimeoutException {
		category = String.valueOf(category.charAt(0)).toLowerCase() + category.substring(1);
		checkAuthorization(entId, category);

		Object[] params = null;
		Object result = null;

		try {
			Method serviceMethod = getServiceMethod(entId, category);
			params = getInputParams(entId, jsonInput, serviceMethod);
			EntityCrudManager<?, ?> entityCrudManager = this.getCRUDManager(entId);
			
			params = doBefore(category, entId, params);
			result = serviceMethod.invoke(entityCrudManager, params);
			result = doAfter(category, entId, params, result);
		} catch (Throwable e) {
			doException(category, entId, params, e);
		} finally {
			doFinally(category, entId, params, result);
		}
		return result;
	}

	/**
	 * doBefore
	 * 
	 * @param params
	 * @return
	 */
	protected Object[] doBefore(String category, String entId, Object[] params) {
		return params;
	}

	/**
	 * doAfter
	 * 
	 * @param params
	 * @param result
	 * @return
	 */
	protected Object doAfter(String category, String entId, Object[] params, Object result) {
		return result;
	}

	/**
	 * doException
	 * 
	 * @param params
	 * @param e
	 * @param options
	 */
	protected void doException(String category, String entId, Object[] params, Throwable e) {
		throw new ApplicationException(e, "APMSG40002", new Object[] { category, entId});
	}

	/**
	 * doFinally
	 * 
	 * @param params
	 * @param result
	 */
	protected void doFinally(String category, String entId, Object[] params, Object result) {
	}

	/**
	 * Do authorization check
	 * 
	 * @param entId
	 * @param category
	 * @throws TimeoutException 
	 */
	protected void checkAuthorization(String entId, String category) throws TimeoutException {
		String token = SessionContext.open().get(SessionContext.Key.Token);
		UserAuthDto auth = (UserAuthDto) userLoginService.getUserObject(token);
		Set<Map<String, Object>> userAclSet = auth.getAccResList();
		for (Map<String, Object> acl : userAclSet) {
			String accResId = (String) acl.get(EmRoleAclDto.Property.accResId);
			Integer accLevel = (Integer) acl.get(EmRoleAclDto.Property.accLevel);
			if (!accResId.equals(entId)) {
				continue;
			}
			if (!accessDefine.containsKey(category)) {
				throw new ApplicationException("Unsupport operation category:" + category);
			}
			if (accLevel < accessDefine.get(category)) {
				throw new ApplicationException(
						"Access level mismatch, require " + category + " but current is " + accLevel);
			}
		}
	}

	/**
	 * getServiceMethod
	 * 
	 * @param entId
	 * @param opCate
	 * @return
	 */
	protected Method getServiceMethod(String entId, String category) {
		String methodName = this.mapCategoryToMethodName(category);
		EntityCrudManager<?, ?> entityCrudManager = (EntityCrudManager<?, ?>) getCRUDManager(entId);
		Method serviceMethod = ReflectUtil.getNonBridgeMethod(entityCrudManager.getClass(), methodName);
		return serviceMethod;
	}

	/**
	 * getInputParams
	 * 
	 * @param entId
	 * @param jsonParam
	 * @param serviceMethod
	 * @return
	 */
	protected Object[] getInputParams(String entId, String jsonParam, Method serviceMethod) {
		Object[] params = null;
		try {
			Annotation[][] serviceMethodParamAnnos = serviceMethod.getParameterAnnotations();
			Class<?>[] inParamClasses = serviceMethod.getParameterTypes();
			Type[] genericParamTypes = serviceMethod.getGenericParameterTypes();
			params = new Object[inParamClasses.length];
			params[0] = entId;
			for (int i = 1; i < inParamClasses.length; i++) {
				params[i] = ajaxParser.parse(jsonParam, inParamClasses[i], genericParamTypes[i],
						serviceMethodParamAnnos[i]);
			}
		} catch (Throwable e) {
			throw new ApplicationException(e, "APMSG40002", new Object[] { entId, serviceMethod.getName(), jsonParam});
		}
		return params;
	}

	/**
	 * Get CRUD manager by entity ID.
	 * 
	 * @param entId
	 * @return
	 */
	protected EntityCrudManager<?, ?> getCRUDManager(String entId) {
		EntityCrudManager<?, ?> em = entityCRUDManager;
		EmDescDto desc = this.entityDESCManager.getEmDesc(entId);
		String emName = desc.getEntCfg().getCusEntityManager();
		if (!StringUtil.isEmpty(emName)) {
			entityCRUDManager = beanFactory.getBean(emName);
		}
		if (em == null) {
			throw new ApplicationException("APMSG30002", new Object[] { emName});
		}
		return em;
	}

	/**
	 * Get service method name accord to input operate category.
	 * 
	 * @param category
	 * @return
	 */
	protected String mapCategoryToMethodName(String category) {
		return category;
	}

	/**
	 * @return the ajaxParser
	 */
	public AjaxParser<String> getAjaxParser() {
		return ajaxParser;
	}

	/**
	 * @param ajaxParser
	 *            the ajaxParser to set
	 */
	public void setAjaxParser(AjaxParser<String> ajaxParser) {
		this.ajaxParser = ajaxParser;
	}

	/**
	 * @return the entityDESCManager
	 */
	public EntityDescManager getEntityDESCManager() {
		return entityDESCManager;
	}

	/**
	 * @param entityDESCManager
	 *            the entityDESCManager to set
	 */
	public void setEntityDESCManager(EntityDescManager entityDESCManager) {
		this.entityDESCManager = entityDESCManager;
	}

	/**
	 * @return the accessDefine
	 */
	public Map<String, Integer> getAccessDefine() {
		return accessDefine;
	}

	/**
	 * @param accessDefine
	 *            the accessDefine to set
	 */
	public void setAccessDefine(Map<String, Integer> accessDefine) {
		this.accessDefine = accessDefine;
	}

}
