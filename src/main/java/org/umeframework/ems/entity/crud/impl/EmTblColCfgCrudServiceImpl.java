package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmTblColCfgDto;
import org.umeframework.ems.entity.crud.EmTblColCfgCrudService;

/**
 * EM实体属性配置表:EM_TBL_COL_CFG CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmTblColCfgCrudServiceImpl extends BaseDBComponent implements EmTblColCfgCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmTblColCfgDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmTblColCfgDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmTblColCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblColCfgDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmTblColCfgDto entity) {
        EmTblColCfgDto existed = super.getDao().queryForObject(EmTblColCfgDto.SQLID.FIND, entity, EmTblColCfgDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmTblColCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblColCfgDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmTblColCfgDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmTblColCfgDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmTblColCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblColCfgDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmTblColCfgDto entity) {
        validate(entity);
        int result = super.getDao().update(EmTblColCfgDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmTblColCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblColCfgDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmTblColCfgDto entity) {
        int result = super.getDao().update(EmTblColCfgDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmTblColCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblColCfgDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#find
     */
    @Override
    public EmTblColCfgDto find(EmTblColCfgDto queryParam) {
        return super.getDao().queryForObject(EmTblColCfgDto.SQLID.FIND, queryParam, EmTblColCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#findList
     */
    @Override
    public List<EmTblColCfgDto> findList(EmTblColCfgDto condition) {
        return super.getDao().queryForObjectList(EmTblColCfgDto.SQLID.FIND_LIST, condition, EmTblColCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#findListLike
     */
    @Override
    public List<EmTblColCfgDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmTblColCfgDto.SQLID.FIND_LIST_LIKE, condition, EmTblColCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService#findListMatch
     */
    @Override
    public List<EmTblColCfgDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmTblColCfgDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmTblColCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblColCfgCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmTblColCfgDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmTblColCfgDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
