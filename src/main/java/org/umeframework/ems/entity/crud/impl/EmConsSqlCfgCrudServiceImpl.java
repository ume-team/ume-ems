package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmConsSqlCfgDto;
import org.umeframework.ems.entity.crud.EmConsSqlCfgCrudService;

/**
 * EM 制约（SQL）配置表:EM_CONS_SQL_CFG CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmConsSqlCfgCrudServiceImpl extends BaseDBComponent implements EmConsSqlCfgCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmConsSqlCfgDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmConsSqlCfgDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmConsSqlCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsSqlCfgDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmConsSqlCfgDto entity) {
        EmConsSqlCfgDto existed = super.getDao().queryForObject(EmConsSqlCfgDto.SQLID.FIND, entity, EmConsSqlCfgDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmConsSqlCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsSqlCfgDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmConsSqlCfgDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmConsSqlCfgDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmConsSqlCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsSqlCfgDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmConsSqlCfgDto entity) {
        validate(entity);
        int result = super.getDao().update(EmConsSqlCfgDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmConsSqlCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsSqlCfgDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmConsSqlCfgDto entity) {
        int result = super.getDao().update(EmConsSqlCfgDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmConsSqlCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsSqlCfgDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#find
     */
    @Override
    public EmConsSqlCfgDto find(EmConsSqlCfgDto queryParam) {
        return super.getDao().queryForObject(EmConsSqlCfgDto.SQLID.FIND, queryParam, EmConsSqlCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#findList
     */
    @Override
    public List<EmConsSqlCfgDto> findList(EmConsSqlCfgDto condition) {
        return super.getDao().queryForObjectList(EmConsSqlCfgDto.SQLID.FIND_LIST, condition, EmConsSqlCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#findListLike
     */
    @Override
    public List<EmConsSqlCfgDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmConsSqlCfgDto.SQLID.FIND_LIST_LIKE, condition, EmConsSqlCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService#findListMatch
     */
    @Override
    public List<EmConsSqlCfgDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmConsSqlCfgDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmConsSqlCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsSqlCfgCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmConsSqlCfgDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmConsSqlCfgDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
