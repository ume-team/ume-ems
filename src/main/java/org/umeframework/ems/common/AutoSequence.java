package org.umeframework.ems.common;

/**
 * Auto create sequence service interface
 *
 * @author Yue MA
 *
 */
public interface AutoSequence {

    /**
     * Get next sequence value
     *
     * @param itemName
     * @return
     */
    String nextValue(
            String itemName);
    
    /**
     * Set sequence repeatable use flag
     * 
     * @param repeatable
     */
    void setRepeatable(
            String repeatable);
}
