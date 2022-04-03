package com.example.web.list_operate;

import java.io.Serializable;

/**
 * deposit_infoテーブルのBean
 * 
 * @author neo-matrix506
 *
 */
public class DepositExpenseListDetailInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer categoryName;
	private Integer depositAmount;

	public Integer getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(Integer categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

}
