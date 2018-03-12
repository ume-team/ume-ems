package org.umeframework.ems.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.entity.EmSeqDto;
import org.umeframework.ems.entity.crud.EmSeqCrudService;

/**
 * 采番表:EM_SEQ CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class EmSeqCrudServiceImpl extends BaseDBComponent implements EmSeqCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(EmSeqDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmSeqDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<EmSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmSeqDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(EmSeqDto entity) {
        EmSeqDto existed = super.getDao().queryForObject(EmSeqDto.SQLID.FIND, entity, EmSeqDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<EmSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmSeqDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(EmSeqDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(EmSeqDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<EmSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmSeqDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(EmSeqDto entity) {
        validate(entity);
        int result = super.getDao().update(EmSeqDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<EmSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmSeqDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(EmSeqDto entity) {
        int result = super.getDao().update(EmSeqDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<EmSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (EmSeqDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#find
     */
    @Override
    public EmSeqDto find(EmSeqDto queryParam) {
        return super.getDao().queryForObject(EmSeqDto.SQLID.FIND, queryParam, EmSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#findList
     */
    @Override
    public List<EmSeqDto> findList(EmSeqDto condition) {
        return super.getDao().queryForObjectList(EmSeqDto.SQLID.FIND_LIST, condition, EmSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#findListLike
     */
    @Override
    public List<EmSeqDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(EmSeqDto.SQLID.FIND_LIST_LIKE, condition, EmSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService#findListMatch
     */
    @Override
    public List<EmSeqDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(EmSeqDto.SQLID.FIND_LIST_MATCH, dynaCondition, EmSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.entity.crud.impl.EmSeqCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(EmSeqDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(EmSeqDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
