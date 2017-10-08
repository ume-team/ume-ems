/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.umeframework.dora.util.ReflectUtil;

/**
 * Util method factory implementation class.
 * 
 * @author mayue
 *
 */
public class UtilFactory {

	/**
	 * split char of service class name and method name
	 */
	private static final String WEB_SERVICE_CLASS_METHOD_SPLIT_CHAR = "#";
	/**
	 * UtilClass instance container
	 */
	private Map<String, Object> instanceMap = new LinkedHashMap<String, Object>();

	/**
	 * UtilClass method container
	 */
	private Map<String, Method> methodMap = new LinkedHashMap<String, Method>();

	/**
	 * configuration properties instance
	 */
	private Properties config;
	
	/**
	 * UtilFactory
	 */
	public UtilFactory() {}
	
	/**
	 * UtilFactory
	 * 
	 * @param config
	 */
	public UtilFactory(Properties config) {
		this.config = config;
	}

	/**
	 * contains
	 * 
	 * @param utilName
	 * @return
	 */
	public boolean has(String utilName) {
		return methodMap.containsKey(utilName);
	}

	/**
	 * Invoke util method
	 * 
	 * @param utilName
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public <R> R execute(String utilName, Object... params) throws Throwable {
		R result = null;

		Method method = methodMap.get(utilName);
		Object instance = instanceMap.get(utilName);
		try {
			result = (R) method.invoke(instance, params);
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			throw targetException;
		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	/**
	 * Init configuration properties
	 */
	public synchronized void init() throws Throwable {
		if (config != null) {
			for (Object key : config.keySet()) {
				String utilName = key.toString();
				String value = config.getProperty(utilName).trim();
				put(utilName, value);
			}
		}
	}

	/**
	 * put
	 * 
	 * @param utilName
	 * @param value
	 */
	public void put(String utilName, String value) throws Throwable {
		if (!value.contains(WEB_SERVICE_CLASS_METHOD_SPLIT_CHAR)) {
			throw new RuntimeException("Util define in UtilFactory define must split by '#' between class name and method name:" + value);
		}
		
		String[] elements = value.split(WEB_SERVICE_CLASS_METHOD_SPLIT_CHAR);
		String className = elements[0].trim();
		String methodName = elements[1].trim();
		Class<?> clazz = null;
		Method method = null;
		Object instance = null;
		try {
			clazz = Class.forName(className);
		} catch (Exception ex) {
			throw ex;
		}
		try {
			method = ReflectUtil.getNonBridgeMethod(clazz, methodName);
		} catch (Exception ex) {
			throw ex;
		}
		int modifiers = method.getModifiers();
		if (!Modifier.isStatic(modifiers)) {
			try {
				instance = clazz.newInstance();
			} catch (Exception ex) {
				throw ex;
			}
		}

		instanceMap.put(utilName, instance);
		methodMap.put(utilName, method);
	}

	/**
	 * getTargetInstance
	 * 
	 * @param utilName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getTargetInstance(String utilName) {
		return (T) instanceMap.get(utilName);
	}

	/**
	 * getNameList
	 * 
	 * @return
	 */
	public Set<String> getNameList() {
		return methodMap.keySet();
	}

	/**
	 * destroy
	 */
	public synchronized void destroy() {
		instanceMap.clear();
		methodMap.clear();
	}

	/**
	 * @return the config
	 */
	public Properties getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(Properties config) {
		this.config = config;
	}

}
