package org.umeframework.ems.uac.entity.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.umeframework.dora.bean.BeanValidator;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.ems.uac.entity.UmeSeqDto;
import org.umeframework.ems.uac.entity.crud.UmeSeqCrudService;
import org.umeframework.dora.service.BaseDBComponent;

/**
 * UME自增长序列管理表:UME_SEQ<br>
 * Crud service implementation class.<br>
 *
 * @author DORA.Generator
 */
@org.springframework.stereotype.Service
public class UmeSeqCrudServiceImpl extends BaseDBComponent implements UmeSeqCrudService {
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public Integer create(UmeSeqDto entity) {
        validate(entity);
        return getDao().update(UmeSeqDto.SQLID.INSERT, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createList(List<UmeSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeSeqDto entity : entityList) {
            result.add(create(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public Integer createOrUpdate(UmeSeqDto entity) {
        UmeSeqDto existed = getDao().queryForObject(UmeSeqDto.SQLID.FIND, entity, UmeSeqDto.class);
        if (existed == null) {
            return getDao().update(UmeSeqDto.SQLID.INSERT, entity);
        } else {
            validate(entity);
            return getDao().update(UmeSeqDto.SQLID.SMART_UPDATE, entity);
        }
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> createOrUpdateList(List<UmeSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeSeqDto entity : entityList) {
            result.add(createOrUpdate(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public Integer update(UmeSeqDto entity) {
        validate(entity);
        return getDao().update(UmeSeqDto.SQLID.SMART_UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateList(List<UmeSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeSeqDto entity : entityList) {
            result.add(update(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public Integer updateFully(UmeSeqDto entity) {
        validate(entity);
        return getDao().update(UmeSeqDto.SQLID.UPDATE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> updateFullyList(List<UmeSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeSeqDto entity : entityList) {
            result.add(updateFully(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public Integer delete(UmeSeqDto entity) {
        return getDao().update(UmeSeqDto.SQLID.DELETE, entity);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    @TransactionRequired
    public List<Integer> deleteList(List<UmeSeqDto> entityList) {
        List<Integer> result = new ArrayList<Integer>(entityList.size());
        for (UmeSeqDto entity : entityList) {
            result.add(delete(entity));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    public UmeSeqDto find(UmeSeqDto queryParam) {
        return getDao().queryForObject(UmeSeqDto.SQLID.FIND, queryParam, UmeSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    public List<UmeSeqDto> search(UmeSeqDto condition) {
        return getDao().queryForObjectList(UmeSeqDto.SQLID.SEARCH, condition, UmeSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    public List<UmeSeqDto> likeSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeSeqDto.SQLID.LIKE_SEARCH, condition, UmeSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    public List<UmeSeqDto> dynaSearch(Map<String, String> condition) {
        return getDao().queryForObjectList(UmeSeqDto.SQLID.DYNA_SEARCH, condition, UmeSeqDto.class);
    }
    
    /* (non-Javadoc)
     * 
     * @see org.umeframework.ems.uac.entity.crud.impl.UmeSeqCrudService
     */
    @Override
    public Integer count(Map<String, String> condition) {
        return getDao().count(UmeSeqDto.SQLID.COUNT, condition);
    }

    /**
     * Do entity validation before doUpdate
     * 
     * @param entity - Target doUpdate Entity
     */
    protected void validate(UmeSeqDto entity) {
        // Here invoke the default entity check logic
        BeanValidator beanValidator = new BeanValidator();
        // Invoke validation rule
        beanValidator.validate(entity);
    }
}
