package com.example.domain;

/**
 * deposit_infoテーブルのBean
 * @author neo-matrix506
 *
 */
public class DepositInfo {

	private Integer depositId;
	private Integer userId;
	private String depositYear;
	private String depositMonth;
	private String depositDay;
	private Integer depositAmount;
	private Integer depositMethodId;
	private Integer depositCategoryId;
	private String depositDetail;

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDepositYear() {
		return depositYear;
	}

	public void setDepositYear(String depositYear) {
		this.depositYear = depositYear;
	}

	public String getDepositMonth() {
		return depositMonth;
	}

	public void setDepositMonth(String depositMonth) {
		this.depositMonth = depositMonth;
	}

	public String getDepositDay() {
		return depositDay;
	}

	public void setDepositDay(String depositDay) {
		this.depositDay = depositDay;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Integer getDepositMethodId() {
		return depositMethodId;
	}

	public void setDepositMethodId(Integer depositMethodId) {
		this.depositMethodId = depositMethodId;
	}

	public Integer getDepositCategoryId() {
		return depositCategoryId;
	}

	public void setDepositCategoryId(Integer depositCategoryId) {
		this.depositCategoryId = depositCategoryId;
	}

	public String getDepositDetail() {
		return depositDetail;
	}

	public void setDepositDetail(String depositDetail) {
		this.depositDetail = depositDetail;
	}
}
