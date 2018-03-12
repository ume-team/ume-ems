package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.uac.entity.UmeUserRoleDto;
import org.umeframework.ems.uac.entity.crud.UmeUserRoleCrudService;

/**
 * UME用户角色关系表:UME_USER_ROLE CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class UmeUserRoleCrudServiceImpl extends BaseDBComponent implements UmeUserRoleCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(UmeUserRoleDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeUserRoleDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeUserRoleDto entity) {
        UmeUserRoleDto existed = super.getDao().queryForObject(UmeUserRoleDto.SQLID.FIND, entity, UmeUserRoleDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(UmeUserRoleDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeUserRoleDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeUserRoleDto entity) {
        validate(entity);
        int result = super.getDao().update(UmeUserRoleDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeUserRoleDto entity) {
        int result = super.getDao().update(UmeUserRoleDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeUserRoleDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserRoleDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#find
     */
    @Override
    public UmeUserRoleDto find(UmeUserRoleDto queryParam) {
        return super.getDao().queryForObject(UmeUserRoleDto.SQLID.FIND, queryParam, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#findList
     */
    @Override
    public List<UmeUserRoleDto> findList(UmeUserRoleDto condition) {
        return super.getDao().queryForObjectList(UmeUserRoleDto.SQLID.FIND_LIST, condition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#findListLike
     */
    @Override
    public List<UmeUserRoleDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(UmeUserRoleDto.SQLID.FIND_LIST_LIKE, condition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService#findListMatch
     */
    @Override
    public List<UmeUserRoleDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(UmeUserRoleDto.SQLID.FIND_LIST_MATCH, dynaCondition, UmeUserRoleDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserRoleCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(UmeUserRoleDto.SQLID.COUNT, condition);
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
