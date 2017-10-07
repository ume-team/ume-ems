package org.umeframework.ems.desc.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Data structure to contain entity configuration items.
 * 
 * @author Yue MA
 *
 */
public class EmDescDto implements Serializable {
	/**
	* serial version UID
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * configuration items for table level
	 */
	private EmEntDescDto entCfg;
	/**
     * configuration items for each column level
	 */
	private Map<String, EmColDescDto> colCfgMap = new LinkedHashMap<String, EmColDescDto>();

	/**
	 * getHideColSet
	 * 
	 * @return
	 */
	public Set<String> getHideColSet() {
		return convertStringToSet(entCfg.getRefTblHideCols(), ",");
	}

	/**
	 * setHideColSet
	 * 
	 * @return
	 */
	public void setHideColSet(
			Set<String> set) {
		entCfg.setRefTblHideCols(convertSetToString(set, ","));
	}

	/**
	 * getDisableColSet
	 * 
	 * @return
	 */
	public Set<String> getDisableColSet() {
		return convertStringToSet(entCfg.getRefTblDisableCols(), ",");
	}

	/**
	 * setDisableColSet
	 * 
	 * @return
	 */
	public void setDisableColSet(
			Set<String> set) {
		entCfg.setRefTblDisableCols(convertSetToString(set, ","));
	}

	/**
	 * getReadonlyColSet
	 * 
	 * @return
	 */
	public Set<String> getReadonlyColSet() {
		return convertStringToSet(entCfg.getRefTblReadonlyCols(), ",");
	}

	/**
	 * setReadonlyColSet
	 * 
	 * @return
	 */
	public void setReadonlyColSet(
			Set<String> set) {
		entCfg.setRefTblReadonlyCols(convertSetToString(set, ","));
	}

	/**
	 * getPrimaryKeyColCfgs
	 * 
	 * @return
	 */
	public Set<String> getPrimaryKeyColSet() {
		Set<String> pks = new LinkedHashSet<String>();
		for (EmColDescDto col : colCfgMap.values()) {
			if (1 == col.getPkFlag()) {
				pks.add(col.getColId());
			}
		}
		return pks;
	}

	/**
	 * addColumnCfg
	 * 
	 * @param columnCfg
	 *            the columnCfg to add
	 */
	public void addColCfg(
			String column,
			EmColDescDto columnCfg) {
		this.colCfgMap.put(column, columnCfg);
	}

	/**
	 * getColumnCfg
	 * 
	 * @param column
	 * @return
	 */
	public EmColDescDto getColCfg(
			String column) {
		return this.colCfgMap.get(column);
	}

	/**
	 * @return the entCfg
	 */
	public EmEntDescDto getEntCfg() {
		return entCfg;
	}

	/**
	 * @param entCfg
	 *            the entCfg to set
	 */
	public void setEntCfg(
	        EmEntDescDto tableCfg) {
		this.entCfg = tableCfg;
	}

	/**
	 * @return the columnCfgMap
	 */
	public Map<String, EmColDescDto> getColCfgMap() {
		return colCfgMap;
	}

	/**
	 * @param columnCfgMap
	 *            the columnCfgMap to set
	 */
	public void setColCfgMap(
			Map<String, EmColDescDto> columnCfgMap) {
		this.colCfgMap = columnCfgMap;
	}

	/**
	 * stringToSet
	 * 
	 * @param str
	 * @return
	 */
	protected Set<String> convertStringToSet(
			String str,
			String splitChar) {
		Set<String> set = new LinkedHashSet<String>();
		if (str != null) {
			String[] array = str.split(splitChar);
			if (array != null) {
				for (String e : array) {
					set.add(e);
				}
			}
		}
		return set;
	}

	/**
	 * setToString
	 * 
	 * @param set
	 * @return
	 */
	protected String convertSetToString(
			Set<String> set,
			String splitChar) {
		StringBuilder str = new StringBuilder();
		if (set != null) {
			int i = 0;
			for (Object e : set) {
				if (i > 0) {
					str.append(splitChar);
				}
				str.append(String.valueOf(e));
				i++;
			}
		}
		return str.toString();
	}
}
