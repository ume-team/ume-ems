package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmDsCfgDto;
import org.umeframework.ems.entity.crud.EmDsCfgCrudService;

/**
 * EM数据源配置表:EM_DS_CFG CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmDsCfgCrudServiceImpl extends BaseDBComponent implements EmDsCfgCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmDsCfgDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmDsCfgDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmDsCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmDsCfgDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmDsCfgDto entity) {
        EmDsCfgDto existed = super.getDao().queryForObject(EmDsCfgDto.SQLID.FIND, entity, EmDsCfgDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmDsCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmDsCfgDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmDsCfgDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmDsCfgDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmDsCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmDsCfgDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmDsCfgDto entity) {
        validate(entity);
        int result = super.getDao().update(EmDsCfgDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmDsCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmDsCfgDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmDsCfgDto entity) {
        int result = super.getDao().update(EmDsCfgDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmDsCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmDsCfgDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#find
     */
    @Override
    public EmDsCfgDto find(EmDsCfgDto queryParam) {
        return super.getDao().queryForObject(EmDsCfgDto.SQLID.FIND, queryParam, EmDsCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#findList
     */
    @Override
    public List<EmDsCfgDto> findList(EmDsCfgDto condition) {
        return super.getDao().queryForObjectList(EmDsCfgDto.SQLID.FIND_LIST, condition, EmDsCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#findListLike
     */
    @Override
    public List<EmDsCfgDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmDsCfgDto.SQLID.FIND_LIST_LIKE, condition, EmDsCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService#findListMatch
     */
    @Override
    public List<EmDsCfgDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmDsCfgDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmDsCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmDsCfgCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmDsCfgDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmDsCfgDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
