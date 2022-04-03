package com.example.domain;

/**
 * spending_infoテーブルのBean
 * @author neo-matrix506
 *
 */

public class SpendingInfo {

	private Integer spendingId;
	private Integer userId;
	private String spendingYear;
	private String spendingMonth;
	private String spendingDay;
	private Integer spendingAmount;
	private Integer spendingMethodId;
	private Integer spendingCategoryId;
	private String spendingDetail;

	public Integer getSpendingId() {
		return spendingId;
	}

	public void setSpendingId(Integer spendingId) {
		this.spendingId = spendingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSpendingYear() {
		return spendingYear;
	}

	public void setSpendingYear(String spendingYear) {
		this.spendingYear = spendingYear;
	}

	public String getSpendingMonth() {
		return spendingMonth;
	}

	public void setSpendingMonth(String spendingMonth) {
		this.spendingMonth = spendingMonth;
	}

	public String getSpendingDay() {
		return spendingDay;
	}

	public void setSpendingDay(String spendingDay) {
		this.spendingDay = spendingDay;
	}

	public Integer getSpendingAmount() {
		return spendingAmount;
	}

	public void setSpendingAmount(Integer spendingAmount) {
		this.spendingAmount = spendingAmount;
	}

	public Integer getSpendingMethodId() {
		return spendingMethodId;
	}

	public void setSpendingMethodId(Integer spendingMethodId) {
		this.spendingMethodId = spendingMethodId;
	}

	public Integer getSpendingCategoryId() {
		return spendingCategoryId;
	}

	public void setSpendingCategoryId(Integer spendingCategoryId) {
		this.spendingCategoryId = spendingCategoryId;
	}

	public String getSpendingDetail() {
		return spendingDetail;
	}

	public void setSpendingDetail(String spendingDetail) {
		this.spendingDetail = spendingDetail;
	}

}