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
 * UME角色资源访问权限关系表:UME_ROLE_ACL CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class UmeRoleAclCrudServiceImpl extends BaseDBComponent implements UmeRoleAclCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(UmeRoleAclDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeRoleAclDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeRoleAclDto entity) {
        UmeRoleAclDto existed = super.getDao().queryForObject(UmeRoleAclDto.SQLID.FIND, entity, UmeRoleAclDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(UmeRoleAclDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeRoleAclDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeRoleAclDto entity) {
        validate(entity);
        int result = super.getDao().update(UmeRoleAclDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeRoleAclDto entity) {
        int result = super.getDao().update(UmeRoleAclDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeRoleAclDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeRoleAclDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#find
     */
    @Override
    public UmeRoleAclDto find(UmeRoleAclDto queryParam) {
        return super.getDao().queryForObject(UmeRoleAclDto.SQLID.FIND, queryParam, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#findList
     */
    @Override
    public List<UmeRoleAclDto> findList(UmeRoleAclDto condition) {
        return super.getDao().queryForObjectList(UmeRoleAclDto.SQLID.FIND_LIST, condition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#findListLike
     */
    @Override
    public List<UmeRoleAclDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(UmeRoleAclDto.SQLID.FIND_LIST_LIKE, condition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService#findListMatch
     */
    @Override
    public List<UmeRoleAclDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(UmeRoleAclDto.SQLID.FIND_LIST_MATCH, dynaCondition, UmeRoleAclDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeRoleAclCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(UmeRoleAclDto.SQLID.COUNT, condition);
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
