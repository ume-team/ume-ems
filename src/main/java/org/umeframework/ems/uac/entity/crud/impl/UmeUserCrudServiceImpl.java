package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.ems.uac.entity.UmeUserDto;
import org.umeframework.ems.uac.entity.crud.UmeUserCrudService;
import org.umeframework.dora.service.BaseDBComponent;

/**
 * UME用户表:UME_USER<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeUserCrudServiceImpl extends BaseDBComponent implements UmeUserCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeUserDto entity) {
        validate(entity);
        return getDao().update(UmeUserDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeUserDto entity) {
        UmeUserDto existed = getDao().queryForObject(UmeUserDto.SQLID.FIND, entity, UmeUserDto.class);
        if (existed == null) {
            return getDao().update(UmeUserDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeUserDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeUserDto entity) {
        validate(entity);
        return getDao().update(UmeUserDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeUserDto entity) {
        validate(entity);
        return getDao().update(UmeUserDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeUserDto entity) {
        return getDao().update(UmeUserDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeUserDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeUserDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public UmeUserDto find(UmeUserDto queryParam) {
        return getDao().queryForObject(UmeUserDto.SQLID.FIND, queryParam, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public List<UmeUserDto> search(UmeUserDto condition) {
        return getDao().queryForObjectList(UmeUserDto.SQLID.SEARCH, condition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public List<UmeUserDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeUserDto.SQLID.LIKE_SEARCH, condition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public List<UmeUserDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeUserDto.SQLID.DYNA_SEARCH, condition, UmeUserDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeUserCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeUserDto.SQLID.COUNT, condition);
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
