/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.common.impl;

import org.springframework.stereotype.Service;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.transaction.TransactionManager;
import org.umeframework.ems.common.AutoSequence;
import org.umeframework.ems.entity.EmSeqDto;

import javax.annotation.Resource;

/**
 * AutoSeqServiceImpl
 *
 * @author Yue MA
 *
 */
@Service
public class AutoSequenceImpl extends BaseDBComponent implements AutoSequence {
    /**
     * TX PROPAGATION AS REQUIRES_NEW
     */
    private static final int TX_PROPAGATION_REQUIRES_NEW = 3;

	/**
	 * Repeatable flag
	 */
	private String repeatable = "Y";

	/**
	 * nestManualTransactionManager
	 */
	@Resource(name="doraTransactionManager")
	private TransactionManager transactionManager;
    
	/*
	 * (non-Javadoc)
	 *
	 * @see org.umeframework.dora.common.service.AutoSeqService#nextValue(java.lang.String)
	 */
	@Override
	public String nextValue(
			String itemName) {
		
	    int oriPropagation = transactionManager.getPropagation();
        transactionManager.setPropagation(TX_PROPAGATION_REQUIRES_NEW);
		try {
			transactionManager.begin();
			EmSeqDto seq = getDao().queryForObject(EmSeqDto.SQLID.FIND_FOR_UPDATE, itemName, EmSeqDto.class);
			if (seq == null) {
				throw new RuntimeException("No found valid sequence item:" + itemName + " current=" + itemName);
			}

			int current = seq.getCurrentIndex();
			int increment = seq.getIncrementValue();

			int nextValue = current + increment;
			if (nextValue > seq.getMaxValue()) {
				if (getRepeatable().equalsIgnoreCase(seq.getRepeatable())) {
					nextValue = seq.getMinValue();
				} else {
					throw new RuntimeException("Overflow error of sequence item:" + itemName + " current=" + current);
				}
			}

			seq.setCurrentIndex(nextValue);
			getDao().update(EmSeqDto.SQLID.UPDATE, seq);

			StringBuilder stb = new StringBuilder();
			stb.append(getPrefix(seq));
			stb.append(padding(seq, nextValue));
			stb.append(getPostfix(seq));

			transactionManager.commit();
			return stb.toString();
		} catch (Exception ex) {
		    transactionManager.rollback();
			throw new RuntimeException(ex.getMessage());
		} finally {
            transactionManager.setPropagation(oriPropagation);
		}
	}

	/**
	 * padding
	 *
	 * @param seq
	 * @param nextValue
	 * @return
	 */
	protected String padding(
			EmSeqDto seq,
			long nextValue) {
		int length = seq.getItemLength();
		int lengthGap = length - String.valueOf(nextValue).length();

		StringBuilder res = new StringBuilder(length);
		if (lengthGap > 0) {
			for (int i = 0; i < lengthGap; i++) {
				res.append('0');
			}
		}
		res.append(nextValue);
		return res.toString();
	}

	/**
	 * getPrefix
	 * 
	 * @param seq
	 * @return
	 */
	protected String getPrefix(
			EmSeqDto seq) {
		return seq.getPrefix() == null ? "" : seq.getPrefix();
	}

	/**
	 * getPostfix
	 * 
	 * @param seq
	 * @return
	 */
	protected String getPostfix(
			EmSeqDto seq) {
		return seq.getPostfix() == null ? "" : seq.getPostfix();
	}

    /**
     * @return the repeatable
     */
    public String getRepeatable() {
        return repeatable;
    }

    /**
     * @param repeatable the repeatable to set
     */
    public void setRepeatable(
            String repeatable) {
        this.repeatable = repeatable;
    }

    /**
     * @return the transactionManager
     */
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * @param transactionManager the transactionManager to set
     */
    public void setTransactionManager(
            TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
