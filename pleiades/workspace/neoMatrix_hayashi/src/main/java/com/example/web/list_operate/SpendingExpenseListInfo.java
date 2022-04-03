package com.example.web.list_operate;

import java.io.Serializable;

import org.springframework.web.context.annotation.SessionScope;

/**
 * 支出データ一覧の画面表示に使用する値を格納するクラス
 * @author neo-matrix506
 *
 */
@SessionScope
public class SpendingExpenseListInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer spendingId;
	private String spendingYear;
	private String spendingMonth;
	private String spendingDay;
	private Integer spendingAmount;
	private String spendingMethod;
	private String spendingCategory;
	private String spendingDetail;

	public Integer getSpendingId() {
		return spendingId;
	}

	public void setSpendingId(Integer spendingId) {
		this.spendingId = spendingId;
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

	public String getSpendingMethod() {
		return spendingMethod;
	}

	public void setSpendingMethod(String spendingMethod) {
		this.spendingMethod = spendingMethod;
	}

	public String getSpendingCategory() {
		return spendingCategory;
	}

	public void setSpendingCategory(String spendingCategory) {
		this.spendingCategory = spendingCategory;
	}

	public String getSpendingDetail() {
		return spendingDetail;
	}

	public void setSpendingDetail(String spendingDetail) {
		this.spendingDetail = spendingDetail;
	}

}
