/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.validator;

import java.util.Map;

import org.umeframework.dora.exception.ValidationException;
import org.umeframework.ems.desc.dto.EmDescDto;

/**
 * EntityValidator
 * 
 * @author mayue
 *
 */
public interface Validator {
	/**
	 * 检查数据异常
	 * 
	 * @param desc
	 * @param plantTextParam
	 * @return
	 */
	void doValidate(
			EmDescDto desc,
			Map<String, String> plantTextParam) throws ValidationException;

}
