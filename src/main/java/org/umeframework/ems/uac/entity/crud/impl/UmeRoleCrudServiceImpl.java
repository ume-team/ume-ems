package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.ems.uac.entity.UmeRoleDto;
import org.umeframework.ems.uac.entity.crud.UmeRoleCrudService;
import org.umeframework.dora.service.BaseDBComponent;

/**
 * UME角色定义表:UME_ROLE<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeRoleCrudServiceImpl extends BaseDBComponent implements UmeRoleCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeRoleDto entity) {
        validate(entity);
        return getDao().update(UmeRoleDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeRoleDto entity) {
        UmeRoleDto existed = getDao().queryForObject(UmeRoleDto.SQLID.FIND, entity, UmeRoleDto.class);
        if (existed == null) {
            return getDao().update(UmeRoleDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeRoleDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeRoleDto entity) {
        validate(entity);
        return getDao().update(UmeRoleDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeRoleDto entity) {
        validate(entity);
        return getDao().update(UmeRoleDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeRoleDto entity) {
        return getDao().update(UmeRoleDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public UmeRoleDto find(UmeRoleDto queryParam) {
        return getDao().queryForObject(UmeRoleDto.SQLID.FIND, queryParam, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public List<UmeRoleDto> search(UmeRoleDto condition) {
        return getDao().queryForObjectList(UmeRoleDto.SQLID.SEARCH, condition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public List<UmeRoleDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeRoleDto.SQLID.LIKE_SEARCH, condition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public List<UmeRoleDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeRoleDto.SQLID.DYNA_SEARCH, condition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeRoleDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeRoleDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
