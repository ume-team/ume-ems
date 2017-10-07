package org.umeframework.ems.controller;

import org.springframework.stereotype.Service;
import org.umeframework.ems.jdbc.DynaDaoManager;

import javax.annotation.Resource;

/**
 * TransactionalCRUDController
 *
 * @author Yue MA
 *
 */
@Service
public class TransactionalCrudController extends BaseCrudController {
	/**
	 * Data source selector
	 */
	@Resource
	private DynaDaoManager dynaDaoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.data.controller.BaseCRUDController#doBefore(java.lang
	 * .String, java.lang.String, java.lang.Object[])
	 */
	@Override
	protected Object[] doBefore(String category, String entId, Object[] params) {
		// Start transaction
		String dsId = super.getEntityDESCManager().getEmDesc(entId).getEntCfg().getRefTblDatasource();
		dynaDaoManager.beginTransaction(dsId);
		return super.doBefore(category, entId, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.data.controller.BaseCRUDController#doAfter(java.lang.
	 * String, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	protected Object doAfter(String category, String entId, Object[] params, Object result) {
		result = super.doAfter(category, entId, params, result);
		// commit transaction
		String dsId = super.getEntityDESCManager().getEmDesc(entId).getEntCfg().getRefTblDatasource();
		dynaDaoManager.commitTransaction(dsId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.data.controller.BaseCRUDController#doException(java.
	 * lang.String, java.lang.String, java.lang.Object[], java.lang.Throwable)
	 */
	@Override
	protected void doException(String category, String entId, Object[] params, Throwable e) {
		// roll back transaction
		String dsId = super.getEntityDESCManager().getEmDesc(entId).getEntCfg().getRefTblDatasource();
		dynaDaoManager.rollbackTransaction(dsId);
		super.doException(category, entId, params, e);
	}


}
