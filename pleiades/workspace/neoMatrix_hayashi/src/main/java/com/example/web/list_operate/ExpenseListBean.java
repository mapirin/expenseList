package com.example.web.list_operate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.annotation.SessionScope;

/**
 * 入出金データ一覧表示の日付指定に使用するBean
 * 
 * @author neo-matrix506
 *
 */
@SessionScope
public class ExpenseListBean implements Serializable {

	private final static long serialVersionUID = 1L;

	private String expenseYear;
	private String expenseMonth;

	public String getExpenseYear() {
		return expenseYear;
	}

	public void setExpenseYear(String expenseYear) {
		this.expenseYear = expenseYear;
	}

	public String getExpenseMonth() {
		return expenseMonth;
	}

	public void setExpenseMonth(String expenseMonth) {
		this.expenseMonth = expenseMonth;
	}

	public List<Integer> getExpenseYearList() {
		List<Integer> expenseYearList = new ArrayList<>();
		int year = LocalDate.now().getYear();

		for (int i = year - 3; i <= year; i++) {
			expenseYearList.add(i);
		}

		return expenseYearList;
	}

	public List<String> getExpenseMonthList() {
		List<String> expenseMonthList = new ArrayList<>();
		String month = "";

		for (int i = 1; i <= 12; i++) {
			month = String.format("%02d", i);
			expenseMonthList.add(month);
		}

		return expenseMonthList;
	}
}
