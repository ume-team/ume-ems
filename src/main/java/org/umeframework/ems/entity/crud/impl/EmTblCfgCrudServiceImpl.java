package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmTblCfgDto;
import org.umeframework.ems.entity.crud.EmTblCfgCrudService;

/**
 * EM实体展示配置表:EM_TBL_CFG CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmTblCfgCrudServiceImpl extends BaseDBComponent implements EmTblCfgCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmTblCfgDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmTblCfgDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmTblCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblCfgDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmTblCfgDto entity) {
        EmTblCfgDto existed = super.getDao().queryForObject(EmTblCfgDto.SQLID.FIND, entity, EmTblCfgDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmTblCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblCfgDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmTblCfgDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmTblCfgDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmTblCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblCfgDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmTblCfgDto entity) {
        validate(entity);
        int result = super.getDao().update(EmTblCfgDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmTblCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblCfgDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmTblCfgDto entity) {
        int result = super.getDao().update(EmTblCfgDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmTblCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmTblCfgDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#find
     */
    @Override
    public EmTblCfgDto find(EmTblCfgDto queryParam) {
        return super.getDao().queryForObject(EmTblCfgDto.SQLID.FIND, queryParam, EmTblCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#findList
     */
    @Override
    public List<EmTblCfgDto> findList(EmTblCfgDto condition) {
        return super.getDao().queryForObjectList(EmTblCfgDto.SQLID.FIND_LIST, condition, EmTblCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#findListLike
     */
    @Override
    public List<EmTblCfgDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmTblCfgDto.SQLID.FIND_LIST_LIKE, condition, EmTblCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService#findListMatch
     */
    @Override
    public List<EmTblCfgDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmTblCfgDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmTblCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmTblCfgCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmTblCfgDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmTblCfgDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
