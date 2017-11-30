package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.uac.entity.UmeResourceDto;
import org.umeframework.ems.uac.entity.crud.UmeResourceCrudService;

/**
 * UME资源管理表:UME_RESOURCE CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class UmeResourceCrudServiceImpl extends BaseDBComponent implements UmeResourceCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(UmeResourceDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeResourceDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeResourceDto entity) {
        UmeResourceDto existed = super.getDao().queryForObject(UmeResourceDto.SQLID.FIND, entity, UmeResourceDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(UmeResourceDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeResourceDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeResourceDto entity) {
        validate(entity);
        int result = super.getDao().update(UmeResourceDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeResourceDto entity) {
        int result = super.getDao().update(UmeResourceDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#find
     */
    @Override
    public UmeResourceDto find(UmeResourceDto queryParam) {
        return super.getDao().queryForObject(UmeResourceDto.SQLID.FIND, queryParam, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#findList
     */
    @Override
    public List<UmeResourceDto> findList(UmeResourceDto condition) {
        return super.getDao().queryForObjectList(UmeResourceDto.SQLID.FIND_LIST, condition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#findListLike
     */
    @Override
    public List<UmeResourceDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(UmeResourceDto.SQLID.FIND_LIST_LIKE, condition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService#findListMatch
     */
    @Override
    public List<UmeResourceDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(UmeResourceDto.SQLID.FIND_LIST_MATCH, dynaCondition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(UmeResourceDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeResourceDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
