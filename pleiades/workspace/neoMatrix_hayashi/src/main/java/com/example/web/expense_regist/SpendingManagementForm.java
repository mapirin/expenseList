package com.example.web.expense_regist;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SpendingManagementForm implements Serializable{
	
	private final static long serialVersionUID=1L;

	@NotEmpty
	private String spendingYear;
	@NotEmpty
	private String spendingMonth;
	@NotEmpty
	private String spendingDay;
	@NotNull
	private Integer spendingAmount;
	@NotEmpty
	private String spendingMethod;
	@NotEmpty
	private String spendingCategory;
	private String spendingDetail;

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

	public List<Integer> getSpendingYearList() {
		List<Integer> spendingYearList = new ArrayList<>();
		int year = LocalDate.now().getYear();

		for (int i = year - 3; i <= year; i++) {
			spendingYearList.add(i);
		}

		return spendingYearList;
	}

	public List<String> getSpendingMonthList() {
		List<String> spendingMonthList = new ArrayList<>();
		String month = "";

		for (int i = 1; i <= 12; i++) {
			month = String.format("%02d", i);
			spendingMonthList.add(month);
		}

		return spendingMonthList;
	}

	public List<String> getSpendingDayList() {
		List<String> spendingDayList = new ArrayList<>();
		String day = "";

		for (int i = 1; i <= 31; i++) {
			day = String.format("%02d", i);
			spendingDayList.add(day);
		}

		return spendingDayList;
	}
}
