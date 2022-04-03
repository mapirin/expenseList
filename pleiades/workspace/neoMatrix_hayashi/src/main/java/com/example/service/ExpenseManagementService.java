package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CategoryInfo;
import com.example.domain.DepositDetailInfo;
import com.example.domain.DepositInfo;
import com.example.domain.MethodInfo;
import com.example.domain.SpendingDetailInfo;
import com.example.domain.SpendingInfo;
import com.example.persistence.ExpenseListOperateMapper;
import com.example.persistence.ExpenseRegistMapper;
import com.example.persistence.ExpenseUserMapper;
import com.example.web.expense_regist.DepositManagementForm;
import com.example.web.expense_regist.SpendingManagementForm;
import com.example.web.list_operate.DepositExpenseListInfo;
import com.example.web.list_operate.SpendingExpenseListInfo;

/**
 * 値やDBから抽出したデータを加工しコントローラに渡すサービスクラス
 * 
 * @author neo-matrix506
 *
 */
@Service
public class ExpenseManagementService {

	@Autowired
	private ExpenseRegistMapper expenseRegistMapper;

	@Autowired
	private ExpenseListOperateMapper expenseListOperateMapper;

	@Autowired
	private ExpenseUserMapper expenseUserMapper;

	//
	//
	public boolean getUserInfoTableUserExistCheck(String userName) {
		Integer existCount = expenseUserMapper.userInfoTableUserExistCheck(userName);

		if (existCount > 0) {
			return false;
		}
		return true;
	}

	// ユーザ登録
	public void insertUserInfoTableData(String userName, String password) {
		expenseUserMapper.userInfoTableInsertData(userName, password);
	}

	//
	// ログイン
	public boolean getUserInfoTableSelectUserData(String userName, String password) {
		Integer userDataCount = expenseUserMapper.userInfoTableSelectUserData(userName, password);

		if (userDataCount == 0) {
			return false;
		} else {
			return true;
		}
	}

	//
	// ユーザId取得
	public Integer getUserInfoTableSelectId(String userName) {
		return expenseListOperateMapper.userInfoTableSelectId(userName);
	}

	//
	// セレクトボックスに追加する値取得
	public List<String> getMethodInfoTableSelectName() {
		return expenseRegistMapper.methodInfoTableSelectName();
	}

	public List<String> getCategoryInfoTableDepositData() {
		List<String> depositCategoryList = new ArrayList<>();

		for (CategoryInfo info : expenseRegistMapper.categoryInfoTableSelectData()) {
			if (info.getCategoryType() == 0) {
				depositCategoryList.add(info.getCategoryName());
			}
		}
		return depositCategoryList;
	}

	public List<String> getCategoryInfoTableSpendingData() {
		List<String> spendingCategoryList = new ArrayList<>();

		for (CategoryInfo info : expenseRegistMapper.categoryInfoTableSelectData()) {
			if (info.getCategoryType() == 1) {
				spendingCategoryList.add(info.getCategoryName());
			}
		}
		return spendingCategoryList;
	}

	//
	// 入金データ登録
	@Transactional
	public void getDepositInfoTableInsertData(DepositManagementForm depositForm, String userName) {
		DepositInfo depositInfo = new DepositInfo();

		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setMethodName(depositForm.getDepositMethod());
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCategoryType(0);
		categoryInfo.setCategoryName(depositForm.getDepositCategory());

		// ID取得
		Integer methodId = expenseRegistMapper.methodInfoTableSelectId(methodInfo.getMethodName());
		Integer categoryId = expenseRegistMapper.categoryInfoTableSelectId(categoryInfo.getCategoryType(),
				categoryInfo.getCategoryName());
		Integer userId = expenseRegistMapper.userInfoTableSelectId(userName);

		BeanUtils.copyProperties(depositForm, depositInfo);
		depositInfo.setDepositMethodId(methodId);
		depositInfo.setDepositCategoryId(categoryId);
		depositInfo.setUserId(userId);

		expenseRegistMapper.depositInfoTableInsertData(depositInfo);
	}

	// 支出データ登録
	@Transactional
	public void getSpendingInfoTableInsertData(SpendingManagementForm spendingForm, String userName) {
		SpendingInfo spendingInfo = new SpendingInfo();

		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setMethodName(spendingForm.getSpendingMethod());
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCategoryType(１);
		categoryInfo.setCategoryName(spendingForm.getSpendingCategory());
		// ID取得
		Integer methodId = expenseRegistMapper.methodInfoTableSelectId(methodInfo.getMethodName());
		Integer categoryId = expenseRegistMapper.categoryInfoTableSelectId(categoryInfo.getCategoryType(),
				categoryInfo.getCategoryName());
		Integer userId = expenseRegistMapper.userInfoTableSelectId(userName);

		BeanUtils.copyProperties(spendingForm, spendingInfo);
		spendingInfo.setSpendingMethodId(methodId);
		spendingInfo.setSpendingCategoryId(categoryId);
		spendingInfo.setUserId(userId);

		expenseRegistMapper.spendingInfoTableInsertData(spendingInfo);
	}

	//
	// 入金データ一覧検索表示
	public List<DepositExpenseListInfo> getDepositInfoTableAllData(String year, String month, String userName) {

		List<DepositExpenseListInfo> depositDataList = new ArrayList<>();
		Integer userId = expenseListOperateMapper.userInfoTableSelectId(userName);

		for (DepositInfo info : expenseListOperateMapper.depositInfoTableAllData(year, month, userId)) {
			DepositExpenseListInfo listInfo = new DepositExpenseListInfo();

			listInfo.setDepositId(info.getDepositId());
			listInfo.setDepositYear(info.getDepositYear());
			listInfo.setDepositMonth(info.getDepositMonth());
			listInfo.setDepositDay(info.getDepositDay());
			listInfo.setDepositAmount(info.getDepositAmount());
			// methodNameを取得
			listInfo.setDepositMethod(expenseListOperateMapper.methodInfoTableSelectName(info.getDepositMethodId()));
			// categoryNameを取得
			listInfo.setDepositCategory(
					expenseListOperateMapper.categoryInfoTableSelectName(info.getDepositCategoryId()));
			listInfo.setDepositDetail(info.getDepositDetail());

			depositDataList.add(listInfo);
		}

		return depositDataList;
	}

	// 支出データ一覧検索表示
	public List<SpendingExpenseListInfo> getSpendingInfoTableAllData(String year, String month, String userName) {

		List<SpendingExpenseListInfo> spendingDataList = new ArrayList<>();
		Integer userId = expenseListOperateMapper.userInfoTableSelectId(userName);

		for (SpendingInfo info : expenseListOperateMapper.spendingInfoTableAllData(year, month, userId)) {
			SpendingExpenseListInfo listInfo = new SpendingExpenseListInfo();

			listInfo.setSpendingId(info.getSpendingId());
			listInfo.setSpendingYear(info.getSpendingYear());
			listInfo.setSpendingMonth(info.getSpendingMonth());
			listInfo.setSpendingDay(info.getSpendingDay());
			listInfo.setSpendingAmount(info.getSpendingAmount());
			// methodNameを取得
			listInfo.setSpendingMethod(expenseListOperateMapper.methodInfoTableSelectName(info.getSpendingMethodId()));
			// categoryNameを取得
			listInfo.setSpendingCategory(
					expenseListOperateMapper.categoryInfoTableSelectName(info.getSpendingCategoryId()));
			listInfo.setSpendingDetail(info.getSpendingDetail());

			spendingDataList.add(listInfo);
		}

		return spendingDataList;
	}

	//
	// 合計額を取得
	public Integer getDepositInfoTableSumAmount(String year, String month, Integer userId) {
		Integer depositSumAmount = expenseListOperateMapper.depositInfoTableSumAmount(year, month, userId);
		if (depositSumAmount == null) {
			depositSumAmount = 0;
		}
		return depositSumAmount;
	}

	public Integer getSpendingInfoTableSumAmount(String year, String month, Integer userId) {
		Integer spendingSumAmount = expenseListOperateMapper.spendingInfoTableSumAmount(year, month, userId);
		if (spendingSumAmount == null) {
			spendingSumAmount = 0;
		}
		return spendingSumAmount;
	}

	public Integer getSumAmount(String year, String month, Integer userId) {
		Integer depositSumAmount = getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = getSpendingInfoTableSumAmount(year, month, userId);

		Integer sumAmount = 0;
		if (depositSumAmount != 0 && spendingSumAmount != 0) {
			sumAmount = depositSumAmount - spendingSumAmount;
		} else if (depositSumAmount != 0 && spendingSumAmount == 0) {
			sumAmount = depositSumAmount;
		} else if (depositSumAmount == 0 && spendingSumAmount != 0) {
			sumAmount = spendingSumAmount;
		}
		return sumAmount;
	}

	//
	// 削除確認画面
	public List<DepositExpenseListInfo> getDepositInfoTableDeleteDataConf(Integer depositId) {

		List<DepositExpenseListInfo> depositDeleteConfList = new ArrayList<>();

		for (DepositInfo info : expenseListOperateMapper.depositInfoTableDeleteDataConf(depositId)) {
			DepositExpenseListInfo listInfo = new DepositExpenseListInfo();
			listInfo.setDepositYear(info.getDepositYear());
			listInfo.setDepositMonth(info.getDepositMonth());
			listInfo.setDepositDay(info.getDepositDay());
			listInfo.setDepositAmount(info.getDepositAmount());
			listInfo.setDepositMethod(expenseListOperateMapper.methodInfoTableSelectName(info.getDepositMethodId()));
			listInfo.setDepositCategory(
					expenseListOperateMapper.categoryInfoTableSelectName(info.getDepositCategoryId()));
			listInfo.setDepositDetail(info.getDepositDetail());

			depositDeleteConfList.add(listInfo);
		}

		return depositDeleteConfList;
	}

	public List<SpendingExpenseListInfo> getSpendingInfoTableDeleteDataConf(Integer spendingId) {

		List<SpendingExpenseListInfo> spendingDeleteConfList = new ArrayList<>();

		for (SpendingInfo info : expenseListOperateMapper.spendingInfoTableDeleteDataConf(spendingId)) {
			SpendingExpenseListInfo listInfo = new SpendingExpenseListInfo();
			listInfo.setSpendingYear(info.getSpendingYear());
			listInfo.setSpendingMonth(info.getSpendingMonth());
			listInfo.setSpendingDay(info.getSpendingDay());
			listInfo.setSpendingAmount(info.getSpendingAmount());
			listInfo.setSpendingMethod(expenseListOperateMapper.methodInfoTableSelectName(info.getSpendingMethodId()));
			listInfo.setSpendingCategory(
					expenseListOperateMapper.categoryInfoTableSelectName(info.getSpendingCategoryId()));
			listInfo.setSpendingDetail(info.getSpendingDetail());

			spendingDeleteConfList.add(listInfo);
		}

		return spendingDeleteConfList;
	}

	//
	// 削除処理
	@Transactional
	public void deleteDepositInfoTableData(Integer depositId) {
		expenseListOperateMapper.depositInfoTableDataDelete(depositId);
	}

	@Transactional
	public void deleteSpendingInfoTableData(Integer spendingId) {
		expenseListOperateMapper.spendingInfoTableDataDelete(spendingId);
	}

	//
	// 入金詳細表示
	public Map<String, Integer> depositCategoryAndAmountSelectData(String year, String month, String userName) {

		Integer userId = expenseListOperateMapper.userInfoTableSelectId(userName);
		List<DepositDetailInfo> depositList = expenseListOperateMapper.depositPiechartSelectData(year, month, userId);

		Map<String, Integer> map = new HashMap<>();
		for (DepositDetailInfo info : depositList) {
			map.putIfAbsent(info.getCategoryName(), info.getDepositAmount());
		}
		return map;
	}

	// 支出詳細表示
	public Map<String, Integer> spendingCategoryAndAmountSelectData(String year, String month, String userName) {

		Integer userId = expenseListOperateMapper.userInfoTableSelectId(userName);
		List<SpendingDetailInfo> spendingList = expenseListOperateMapper.spendingPiechartSelectData(year, month,
				userId);

		Map<String, Integer> map = new HashMap<>();
		for (SpendingDetailInfo info : spendingList) {
			map.putIfAbsent(info.getCategoryName(), info.getSpendingAmount());
		}
		return map;
	}
}