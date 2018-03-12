/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.jdbc.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.umeframework.dora.bean.BeanFactory;
import org.umeframework.dora.connection.JdbcDataSourceManager;
import org.umeframework.dora.dao.RdbDao;
import org.umeframework.dora.dao.impl.JdbcDaoImpl;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.exception.SystemException;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.entity.EmDsCfgDto;
import org.umeframework.ems.jdbc.DynaDaoManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Dynamic select Dao instance accord to input data name.
 * 
 * @author Yue MA
 *
 */
@Service
public class DynaDaoManagerImpl extends BaseDBComponent implements DynaDaoManager {
	/**
	 * Default data source ID
	 */
	public static final String DEFAULT_DATA_SOURCE = "DEFAULT";

	/**
	 * Bean factory instance
	 */
	@Resource(name = "beanFactory")
	private BeanFactory beanFactory;

	/**
	 * jdbcDataSourceManager
	 */
	@Resource(name = "jdbcDataSourceManager")
	private JdbcDataSourceManager jdbcDataSourceManager;

	/**
	 * Data source instance managed map
	 */
	private Map<String, RdbDao> daoMap = new java.util.concurrent.ConcurrentHashMap<String, RdbDao>();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.connection.CommonDaoManager#getDatabaseType(java.lang
	 * .String)
	 */
	@Override
	public String getDatebaseType(String dsId) {
		dsId = StringUtil.isEmpty(dsId) ? DEFAULT_DATA_SOURCE : dsId;
		String dsType = this.jdbcDataSourceManager.typeOf(dsId);
		return StringUtil.isEmpty(dsType) ? "MYSQL" : dsType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.DataSourceManager#getDao(java.lang.String)
	 */
	@Override
	public RdbDao getDao(String dsId) {
		dsId = StringUtil.isEmpty(dsId) ? DEFAULT_DATA_SOURCE : dsId;
		RdbDao dao = null;
		dao = daoMap.get(dsId);
		if (dao == null) {
			throw new ApplicationException("No found Dao instance built with " + dsId);
		}
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.connection.CommonDaoManager#beginTransaction(java.
	 * lang.String)
	 */
	@Override
	public void beginTransaction(String dsId) {
		dsId = StringUtil.isEmpty(dsId) ? DEFAULT_DATA_SOURCE : dsId;
		this.jdbcDataSourceManager.beginTransaction(dsId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.connection.CommonDaoManager#commitTransaction(java.
	 * lang.String)
	 */
	@Override
	public void commitTransaction(String dsId) {
		dsId = StringUtil.isEmpty(dsId) ? DEFAULT_DATA_SOURCE : dsId;
		this.jdbcDataSourceManager.commitTransaction(dsId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.connection.CommonDaoManager#rollbackTransaction(java.
	 * lang.String)
	 */
	@Override
	public void rollbackTransaction(String dsId) {
		dsId = StringUtil.isEmpty(dsId) ? DEFAULT_DATA_SOURCE : dsId;
		this.jdbcDataSourceManager.rollbackTransaction(dsId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.jdbc.DynaDaoManager#initDataSource(org.umeframework.dora.service.
	 * em.entity.EmDsCfgDTO)
	 */
	@Override
	public void initDataSource(EmDsCfgDto cfg) throws SystemException {
		String dsId = cfg.getId();
		Properties dsProp = new Properties();
		dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.url.toString(), cfg.getUrl());
		dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.driverClassName.toString(), cfg.getDriverClass());
		dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.username.toString(), cfg.getUsername());
		dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.password.toString(), cfg.getPassword());

		if (cfg.getInitialSize() != null) {
			dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.initialSize.toString(), cfg.getInitialSize());
		}
		if (cfg.getMaxActive() != null) {
			dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.maxActive.toString(), cfg.getMaxActive());
		}
		if (cfg.getMaxIdle() != null) {
			dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.maxIdle.toString(), cfg.getMaxIdle());
		}
		if (cfg.getMinIdle() != null) {
			dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.minIdle.toString(), cfg.getMinIdle());
		}
		if (cfg.getMaxWait() != null) {
			dsProp.setProperty(JdbcDataSourceManager.DataSourceProperty.maxWait.toString(), cfg.getMaxWait());
		}
		DataSource dataSource = null;
		RdbDao dao = null;
		try {
			dataSource = jdbcDataSourceManager.create(dsId, dsProp);
			dao = (RdbDao) beanFactory.autowireCapableCreateBean(JdbcDaoImpl.class, false);
			dao.setDataSource(dataSource);
			daoMap.put(dsId, dao);
		} catch (Exception e) {
			throw new SystemException(e, "Faild to get Dao instance with data source " + dsId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.umeframework.dora.service.em.jdbc.DynaDaoManager#addDataSource(org.umeframework.dora.service.em
	 * .entity.EmDsCfgDTO)
	 */
	@Override
	public void addDataSource(EmDsCfgDto cfg) {
		try {
			this.initDataSource(cfg);
			EmDsCfgDto exist = getDao().queryForObject(EmDsCfgDto.SQLID.FIND, cfg.getId(), EmDsCfgDto.class);
			if (exist == null) {
				cfg.setCreateAuthor(super.getUid());
				cfg.setUpdateAuthor(super.getUid());
				getDao().update(EmDsCfgDto.SQLID.INSERT, cfg);
				super.getLogger().info("Save data source configuration to database, id is " + cfg.getId());
			}
		} catch (Exception e) {
			super.getLogger().warn("Fail to add data source " + cfg.getId());
		}

	}

	/**
	 * Create Common data source pool
	 * 
	 * @throws SystemException
	 */
	@PostConstruct
	public synchronized void init() throws SystemException {
		try {
			List<EmDsCfgDto> dsCfgDTOs = getDao().queryForObjectList(EmDsCfgDto.SQLID.FIND_LIST, null, EmDsCfgDto.class);
			for (EmDsCfgDto cfg : dsCfgDTOs) {
				this.initDataSource(cfg);
			}
		} catch (Exception e) {
			super.getLogger().warn("Fail to init data source from EmDsCfg table!");
		}
		if (this.jdbcDataSourceManager.get(DEFAULT_DATA_SOURCE) == null) {
			DataSource dataSource = (DataSource) beanFactory.getBean("dataSource");
			RdbDao dao = null;
			try {
				dao = (RdbDao) beanFactory.autowireCapableCreateBean(JdbcDaoImpl.class, false);
				dao.setDataSource(dataSource);
				this.daoMap.put(DEFAULT_DATA_SOURCE, dao);
				this.jdbcDataSourceManager.put(DEFAULT_DATA_SOURCE, dataSource);
			} catch (Exception e) {
				throw new SystemException(e, "Faild to get Dao instance with data source 'DEFAULT'");
			}
			super.getLogger().info("Use 'dataSource' of spring context as 'DEFAULT'.");
		}
	}

	/**
	 * Release data source pool
	 */
	public synchronized void destroy() {
		this.daoMap.clear();
		this.jdbcDataSourceManager.clear();
	}

	/**
	 * @return the beanFactory
	 */
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	/**
	 * @param beanFactory
	 *            the beanFactory to set
	 */
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

}
