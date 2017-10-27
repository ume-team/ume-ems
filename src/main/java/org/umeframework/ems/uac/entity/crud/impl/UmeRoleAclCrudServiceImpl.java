package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.uac.entity.UmeRoleAclDto;
import org.umeframework.ems.uac.entity.crud.UmeRoleAclCrudService;

/**
 * UME角色资源访问权限关系表:UME_ROLE_ACL<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeRoleAclCrudServiceImpl extends BaseDBComponent implements UmeRoleAclCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeRoleAclDto entity) {
        validate(entity);
        return getDao().update(UmeRoleAclDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeRoleAclDto entity) {
        UmeRoleAclDto existed = getDao().queryForObject(UmeRoleAclDto.SQLID.FIND, entity, UmeRoleAclDto.class);
        if (existed == null) {
            return getDao().update(UmeRoleAclDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeRoleAclDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeRoleAclDto entity) {
        validate(entity);
        return getDao().update(UmeRoleAclDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeRoleAclDto entity) {
        validate(entity);
        return getDao().update(UmeRoleAclDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeRoleAclDto entity) {
        return getDao().update(UmeRoleAclDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public UmeRoleAclDto find(UmeRoleAclDto queryParam) {
        return getDao().queryForObject(UmeRoleAclDto.SQLID.FIND, queryParam, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public List<UmeRoleAclDto> search(UmeRoleAclDto condition) {
        return getDao().queryForObjectList(UmeRoleAclDto.SQLID.SEARCH, condition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public List<UmeRoleAclDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeRoleAclDto.SQLID.LIKE_SEARCH, condition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public List<UmeRoleAclDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeRoleAclDto.SQLID.DYNA_SEARCH, condition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeRoleAclDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeRoleAclDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
