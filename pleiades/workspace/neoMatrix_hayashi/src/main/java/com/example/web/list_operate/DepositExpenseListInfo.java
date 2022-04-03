package com.example.web.list_operate;

import java.io.Serializable;

import org.springframework.web.context.annotation.SessionScope;

/**
 * 入金データ一覧の画面表示に使用する値を格納するクラス
 * @author neo-matrix506
 *
 */
@SessionScope
public class DepositExpenseListInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer depositId;
	private String depositYear;
	private String depositMonth;
	private String depositDay;
	private Integer depositAmount;
	private String depositMethod;
	private String depositCategory;
	private String depositDetail;

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
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
}
