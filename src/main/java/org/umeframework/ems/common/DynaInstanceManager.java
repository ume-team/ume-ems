package org.umeframework.ems.common;

import java.util.List;

/**
 * Dynamic instance manager
 * 
 * @author mayue
 *
 */
public interface DynaInstanceManager<T> {
    /**
     * getInstance
     * 
     * @param name
     * @return
     */
    T getInstance(
            String name);
    
    /**
     * getInstances
     * 
     * @param names
     * @return
     */
    List<T> getInstances(
            String[] names);
}
