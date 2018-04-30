/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.common.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.umeframework.dora.bean.BeanFactory;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.service.BaseComponent;
import org.umeframework.ems.common.DynaInstanceManager;

/**
 * Dynamic instance manager
 * 
 * @author mayue
 *
 */
@Service
public class DynaInstanceManagerImpl<T> extends BaseComponent implements DynaInstanceManager<T> {

    /**
     * dataConvertorMap
     */
    private Map<String, T> container = new LinkedHashMap<String, T>();

    /**
     * BeanFactory instance
     */
    @Resource(name = "doraBeanFactory")
    private BeanFactory beanFactory;
    /**
     * config
     */
    private Properties config;

    /**
     * Init configuration properties
     */
    @SuppressWarnings("unchecked")
    public synchronized void init() {
        if (config != null) {

            for (Object key : config.keySet()) {
                String value = config.getProperty(key.toString()).trim();

                T instance = null;
                try {
                    Class<T> clazz = (Class<T>) Class.forName(value);
                    instance = (T) beanFactory.autowireCapableCreateBean(clazz, false);
                    if (instance != null) {
                        container.put(key.toString(), instance);
                    }
                } catch (Exception ex) {
                    getLogger().error("Failed in create instance. ID=" + key + " IMPL=" + value, ex);
                    throw new ApplicationException(ex, "Failed in create instance. ID=" + key + " IMPL=" + value);
                }
            }
            getLogger().info("Plugin:" + this.getClass().getSimpleName() + " initialized.");
        }
    }
    
    /**
     * destroy
     */
    public synchronized void destroy() {
    	config.clear();
    	container.clear();
        getLogger().info("Plugin:" + this.getClass().getSimpleName() + " destroy.");
    }

    /**
     * getInstances
     * 
     * @param convertor
     * @return
     */
    @Override
    public T getInstance(
            String name) {
        if (container == null) {
            init();
        }
        return container.get(name);
    }

    /**
     * getInstances
     * 
     * @param names
     * @return
     */
    @Override
    public List<T> getInstances(
            String[] names) {
        List<T> instances = new ArrayList<T>();
        for (String name : names) {
            T t = getInstance(name);
            if (t != null) {
                instances.add(t);
            }
        }
        return instances;
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
    public void setBeanFactory(
            BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

	/**
	 * @return the config
	 */
	public Properties getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(Properties config) {
		this.config = config;
	}

}
