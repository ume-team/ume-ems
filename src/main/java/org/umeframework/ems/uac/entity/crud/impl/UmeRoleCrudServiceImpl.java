package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.uac.entity.UmeRoleDto;
import org.umeframework.ems.uac.entity.crud.UmeRoleCrudService;

/**
 * UME角色定义表:UME_ROLE CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class UmeRoleCrudServiceImpl extends BaseDBComponent implements UmeRoleCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(UmeRoleDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeRoleDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeRoleDto entity) {
        UmeRoleDto existed = super.getDao().queryForObject(UmeRoleDto.SQLID.FIND, entity, UmeRoleDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(UmeRoleDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeRoleDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeRoleDto entity) {
        validate(entity);
        int result = super.getDao().update(UmeRoleDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeRoleDto entity) {
        int result = super.getDao().update(UmeRoleDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#find
     */
    @Override
    public UmeRoleDto find(UmeRoleDto queryParam) {
        return super.getDao().queryForObject(UmeRoleDto.SQLID.FIND, queryParam, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#findList
     */
    @Override
    public List<UmeRoleDto> findList(UmeRoleDto condition) {
        return super.getDao().queryForObjectList(UmeRoleDto.SQLID.FIND_LIST, condition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#findListLike
     */
    @Override
    public List<UmeRoleDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(UmeRoleDto.SQLID.FIND_LIST_LIKE, condition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService#findListMatch
     */
    @Override
    public List<UmeRoleDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(UmeRoleDto.SQLID.FIND_LIST_MATCH, dynaCondition, UmeRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(UmeRoleDto.SQLID.COUNT, condition);
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
