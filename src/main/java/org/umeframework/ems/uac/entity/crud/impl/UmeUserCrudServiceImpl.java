package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.ems.uac.entity.UmeUserDto;
import org.umeframework.ems.uac.entity.crud.UmeUserCrudService;

/**
 * UME用户表:UME_USER CRUD service implementation.<br>
 *
 * @author UME-Generator
 */
@org.springframework.stereotype.Service
public class UmeUserCrudServiceImpl extends BaseDBComponent implements UmeUserCrudService {

    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#create
     */
    @Override
    @TransactionRequired
    public Integer create(UmeUserDto entity) {
        validate(entity);
        if (entity.getCreateAuthor() == null) {
            entity.setCreateAuthor(super.getUid());
        }
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeUserDto.SQLID.INSERT, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#createList
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(this.create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#createOrUpdate
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeUserDto entity) {
        UmeUserDto existed = super.getDao().queryForObject(UmeUserDto.SQLID.FIND, entity, UmeUserDto.class);
        if (existed == null) {
            return this.create(entity);
        } else {
            validate(entity);
            return this.update(entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#createOrUpdateList
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(this.createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#update
     */
    @Override
    @TransactionRequired
    public Integer update(UmeUserDto entity) {
        validate(entity);
        if (entity.getUpdateAuthor() == null) {
            entity.setUpdateAuthor(super.getUid());
        }
        int result = super.getDao().update(UmeUserDto.SQLID.SMART_UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#updateList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(this.update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#updateFully
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeUserDto entity) {
        validate(entity);
        int result = super.getDao().update(UmeUserDto.SQLID.UPDATE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#updateFullyList
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(this.updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#delete
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeUserDto entity) {
        int result = super.getDao().update(UmeUserDto.SQLID.DELETE, entity);
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#deleteList
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(this.delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#find
     */
    @Override
    public UmeUserDto find(UmeUserDto queryParam) {
        return super.getDao().queryForObject(UmeUserDto.SQLID.FIND, queryParam, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#findList
     */
    @Override
    public List<UmeUserDto> findList(UmeUserDto condition) {
        return super.getDao().queryForObjectList(UmeUserDto.SQLID.FIND_LIST, condition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#findListLike
     */
    @Override
    public List<UmeUserDto> findListLike(Map<String, String> condition) {
        return super.getDao().queryForObjectList(UmeUserDto.SQLID.FIND_LIST_LIKE, condition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService#findListMatch
     */
    @Override
    public List<UmeUserDto> findListMatch(Map<String, String> dynaCondition) {
        return super.getDao().queryForObjectList(UmeUserDto.SQLID.FIND_LIST_MATCH, dynaCondition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return super.getDao().count(UmeUserDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeUserDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
