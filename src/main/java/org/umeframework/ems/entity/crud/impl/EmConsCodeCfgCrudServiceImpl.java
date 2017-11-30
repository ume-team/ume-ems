package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmConsCodeCfgDto;
import org.umeframework.ems.entity.crud.EmConsCodeCfgCrudService;

/**
 * EM 编码配置表:EM_CONS_CODE_CFG CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmConsCodeCfgCrudServiceImpl extends BaseDBComponent implements EmConsCodeCfgCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmConsCodeCfgDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmConsCodeCfgDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmConsCodeCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsCodeCfgDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmConsCodeCfgDto entity) {
        EmConsCodeCfgDto existed = super.getDao().queryForObject(EmConsCodeCfgDto.SQLID.FIND, entity, EmConsCodeCfgDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmConsCodeCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsCodeCfgDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmConsCodeCfgDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmConsCodeCfgDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmConsCodeCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsCodeCfgDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmConsCodeCfgDto entity) {
        validate(entity);
        int result = super.getDao().update(EmConsCodeCfgDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmConsCodeCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsCodeCfgDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmConsCodeCfgDto entity) {
        int result = super.getDao().update(EmConsCodeCfgDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmConsCodeCfgDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmConsCodeCfgDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#find
     */
    @Override
    public EmConsCodeCfgDto find(EmConsCodeCfgDto queryParam) {
        return super.getDao().queryForObject(EmConsCodeCfgDto.SQLID.FIND, queryParam, EmConsCodeCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#findList
     */
    @Override
    public List<EmConsCodeCfgDto> findList(EmConsCodeCfgDto condition) {
        return super.getDao().queryForObjectList(EmConsCodeCfgDto.SQLID.FIND_LIST, condition, EmConsCodeCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#findListLike
     */
    @Override
    public List<EmConsCodeCfgDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmConsCodeCfgDto.SQLID.FIND_LIST_LIKE, condition, EmConsCodeCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService#findListMatch
     */
    @Override
    public List<EmConsCodeCfgDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmConsCodeCfgDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmConsCodeCfgDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmConsCodeCfgCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmConsCodeCfgDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmConsCodeCfgDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
