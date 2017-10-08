package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.ems.uac.entity.UmeUserRoleDto;
import org.umeframework.ems.uac.entity.crud.UmeUserRoleCrudService;
import org.umeframework.dora.service.BaseDBComponent;

/**
 * UME用户角色关系表:UME_USER_ROLE<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeUserRoleCrudServiceImpl extends BaseDBComponent implements UmeUserRoleCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeUserRoleDto entity) {
        validate(entity);
        return getDao().update(UmeUserRoleDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeUserRoleDto entity) {
        UmeUserRoleDto existed = getDao().queryForObject(UmeUserRoleDto.SQLID.FIND, entity, UmeUserRoleDto.class);
        if (existed == null) {
            return getDao().update(UmeUserRoleDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeUserRoleDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeUserRoleDto entity) {
        validate(entity);
        return getDao().update(UmeUserRoleDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeUserRoleDto entity) {
        validate(entity);
        return getDao().update(UmeUserRoleDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeUserRoleDto entity) {
        return getDao().update(UmeUserRoleDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public UmeUserRoleDto find(UmeUserRoleDto queryParam) {
        return getDao().queryForObject(UmeUserRoleDto.SQLID.FIND, queryParam, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public List<UmeUserRoleDto> search(UmeUserRoleDto condition) {
        return getDao().queryForObjectList(UmeUserRoleDto.SQLID.SEARCH, condition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public List<UmeUserRoleDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeUserRoleDto.SQLID.LIKE_SEARCH, condition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public List<UmeUserRoleDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeUserRoleDto.SQLID.DYNA_SEARCH, condition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeUserRoleDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeUserRoleDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
