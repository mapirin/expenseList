package com.example.web.expense_regist;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepositManagementForm implements Serializable{

	private final static long serialVersionUID=1L;
	
	@NotEmpty
	private String depositYear;
	@NotEmpty
	private String depositMonth;
	@NotEmpty
	private String depositDay;
	@NotNull
	private Integer depositAmount;
	@NotEmpty
	private String depositMethod;
	@NotEmpty
	private String depositCategory;
	private String depositDetail;

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

	public String getDepositMethod() {
		return depositMethod;
	}

	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
	}

	public String getDepositCategory() {
		return depositCategory;
	}

	public void setDepositCategory(String depositCategory) {
		this.depositCategory = depositCategory;
	}

	public String getDepositDetail() {
		return depositDetail;
	}

	public void setDepositDetail(String depositDetail) {
		this.depositDetail = depositDetail;
	}

	public List<Integer> getDepositYearList() {
		List<Integer> depositYearList = new ArrayList<>();
		int year = LocalDate.now().getYear();

		for (int i = year - 3; i <= year; i++) {
			depositYearList.add(i);
		}

		return depositYearList;
	}

	public List<String> getDepositMonthList() {
		List<String> depositMonthList = new ArrayList<>();
		String month="";

		for (int i = 1; i <= 12; i++) {
			month = String.format("%02d", i);
			depositMonthList.add(month);
		}

		return depositMonthList;
	}

	public List<String> getDepositDayList() {
		List<String> depositDayList = new ArrayList<>();
		String day = "";

		for (int i = 1; i <= 31; i++) {
			day = String.format("%02d", i);
			depositDayList.add(day);
		}

		return depositDayList;
	}
}
