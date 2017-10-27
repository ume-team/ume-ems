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
 * UME资源管理表:UME_RESOURCE<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeResourceCrudServiceImpl extends BaseDBComponent implements UmeResourceCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeResourceDto entity) {
        validate(entity);
        return getDao().update(UmeResourceDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeResourceDto entity) {
        UmeResourceDto existed = getDao().queryForObject(UmeResourceDto.SQLID.FIND, entity, UmeResourceDto.class);
        if (existed == null) {
            return getDao().update(UmeResourceDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeResourceDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeResourceDto entity) {
        validate(entity);
        return getDao().update(UmeResourceDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeResourceDto entity) {
        validate(entity);
        return getDao().update(UmeResourceDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeResourceDto entity) {
        return getDao().update(UmeResourceDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeResourceDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeResourceDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public UmeResourceDto find(UmeResourceDto queryParam) {
        return getDao().queryForObject(UmeResourceDto.SQLID.FIND, queryParam, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public List<UmeResourceDto> search(UmeResourceDto condition) {
        return getDao().queryForObjectList(UmeResourceDto.SQLID.SEARCH, condition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public List<UmeResourceDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeResourceDto.SQLID.LIKE_SEARCH, condition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public List<UmeResourceDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeResourceDto.SQLID.DYNA_SEARCH, condition, UmeResourceDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeResourceCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeResourceDto.SQLID.COUNT, condition);
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
