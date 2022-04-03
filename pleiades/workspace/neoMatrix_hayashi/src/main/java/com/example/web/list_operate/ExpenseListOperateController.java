package com.example.web.list_operate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.ExpenseManagementService;
import com.example.web.LoginManagementForm;

/**
 * 入出金データ一覧検索表示画面の描画や遷移処理を行うコントローラ
 * 
 * @author neo-matrix506
 *
 */
@Controller
@SessionAttributes({ "loginForm", "expenseBean", "depositInfo", "spendingInfo" })
public class ExpenseListOperateController {

	@Autowired
	public ExpenseManagementService expenseManagementService;

	@ModelAttribute("loginForm")
	public LoginManagementForm setLoginForm() {
		return new LoginManagementForm();
	}

	@ModelAttribute("expenseBean")
	public ExpenseListBean setExpenseInfo() {
		return new ExpenseListBean();
	}

	@ModelAttribute("depositInfo")
	public DepositExpenseListInfo setDepositInfo() {
		return new DepositExpenseListInfo();
	}

	@ModelAttribute("spendingInfo")
	public SpendingExpenseListInfo setSpendingInfo() {
		return new SpendingExpenseListInfo();
	}

	//
	// 一覧→メニュー
	@RequestMapping(value = "/expenseList-operate-data", params = "menu_btn")
	public String expenseListToMenu() {

		return "web/menu";
	}

	// 一覧→検索
	@RequestMapping(value = "/expenseList-operate-data", params = "search_btn")
	public String expenseListToexpenseList(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@ModelAttribute("depositInfo") DepositExpenseListInfo depositInfo,
			@ModelAttribute("spendingInfo") SpendingExpenseListInfo spendingInfo, Model model) {

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	//
	// 一覧→入金詳細画面
	@RequestMapping(value = "/expenseList-operate-data", params = "depositDetail_btn")
	public String expenseListToexpenseListDepositDetail(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@ModelAttribute("depositInfo") DepositExpenseListInfo depositInfo,
			@RequestParam("depositYear") String depositYear, @RequestParam("depositMonth") String depositMonth,
			Model model) {
		expenseListBean.setExpenseYear(depositYear);
		expenseListBean.setExpenseMonth(depositMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();

		Map<String, Integer> depositDataMAp = expenseManagementService.depositCategoryAndAmountSelectData(year, month,
				userName);

		model.addAttribute("depositYear", depositYear);
		model.addAttribute("depositMonth", depositMonth);
		model.addAttribute("depositDataMAp", depositDataMAp);
		return "web/list_operate/expenseListDepositDetail";
	}

	// 一覧→支出詳細画面
	@RequestMapping(value = "/expenseList-operate-data", params = "spendingDetail_btn")
	public String expenseListToexpenseListSpendingDetail(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@ModelAttribute("spendingInfo") SpendingExpenseListInfo spendingInfo,
			@RequestParam("spendingYear") String spendingYear, @RequestParam("spendingMonth") String spendingMonth,
			Model model) {
		expenseListBean.setExpenseYear(spendingYear);
		expenseListBean.setExpenseMonth(spendingMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();

		Map<String, Integer> spendingDataMAp = expenseManagementService.spendingCategoryAndAmountSelectData(year, month,
				userName);

		model.addAttribute("spendingYear", spendingYear);
		model.addAttribute("spendingMonth", spendingMonth);
		model.addAttribute("spendingDataMAp", spendingDataMAp);
		return "web/list_operate/expenseListSpendingDetail";
	}

	// 一覧→入金削除確認画面
	@RequestMapping(value = "/expenseList-operate-data", params = "depositDelete_btn")
	public String expenseListToexpenseListDepositDelete(@RequestParam("depositYear") String depositYear,
			@RequestParam("depositMonth") String depositMonth, @RequestParam("depositId") Integer depositId,
			Model model) {
		List<DepositExpenseListInfo> depositDataList = expenseManagementService
				.getDepositInfoTableDeleteDataConf(depositId);

		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("depositYear", depositYear);
		model.addAttribute("depositMonth", depositMonth);
		model.addAttribute("depositId", depositId);
		return "web/list_operate/expenseListDepositDeleteConf";
	}

	// 一覧→支出削除確認画面
	@RequestMapping(value = "/expenseList-operate-data", params = "spendingDelete_btn")
	public String expenseListToexpenseListSpendingDelete(@RequestParam("spendingYear") String spendingYear,
			@RequestParam("spendingMonth") String spendingMonth, @RequestParam("spendingId") Integer spendingId,
			Model model) {
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService
				.getSpendingInfoTableDeleteDataConf(spendingId);

		model.addAttribute("spendingDataList", spendingDataList);
		model.addAttribute("spendingYear", spendingYear);
		model.addAttribute("spendingMonth", spendingMonth);
		model.addAttribute("spendingId", spendingId);
		return "web/list_operate/expenseListSpendingDeleteConf";
	}

	// 入金削除確認画面→一覧
	@RequestMapping(value = "/delete-end", params = "depositToBack_btn")
	public String depositDeleteConfToExpenseListOperate(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("depositYear") String depositYear, @RequestParam("depositMonth") String depositMonth,
			Model model) {
		expenseListBean.setExpenseYear(depositYear);
		expenseListBean.setExpenseMonth(depositMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = expenseManagementService.getSumAmount(year, month, userId);

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	// 支出削除確認画面→一覧
	@RequestMapping(value = "/delete-end", params = "spendingToBack_btn")
	public String spendingDeleteConfToExpenseListOperate(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("spendingYear") String spendingYear, @RequestParam("spendingMonth") String spendingMonth,
			Model model) {
		expenseListBean.setExpenseYear(spendingYear);
		expenseListBean.setExpenseMonth(spendingMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	// 入金削除完了画面リダイレクト
	@RequestMapping(value = "/delete-end", params = "depositDeleteEnd_btn")
	public String dpositDeleteConfToDeleteEnd(RedirectAttributes redirectAttributes,
			@RequestParam("depositYear") String depositYear, @RequestParam("depositMonth") String depositMonth,
			@RequestParam("depositId") Integer depositId) {
		expenseManagementService.deleteDepositInfoTableData(depositId);

		redirectAttributes.addFlashAttribute("depositYear", depositYear);
		redirectAttributes.addFlashAttribute("depositMonth", depositMonth);
		return "redirect:/delete-end?depositDeleteEnd";
	}

	// 支出削除完了画面リダイレクト
	@RequestMapping(value = "/delete-end", params = "spendingDeleteEnd_btn")
	public String spendingDeleteConfToDeleteEnd(RedirectAttributes redirectAttributes,
			@RequestParam("spendingYear") String spendingYear, @RequestParam("spendingMonth") String spendingMonth,
			@RequestParam("spendingId") Integer spendingId) {
		expenseManagementService.deleteSpendingInfoTableData(spendingId);

		redirectAttributes.addFlashAttribute("spendingYear", spendingYear);
		redirectAttributes.addFlashAttribute("spendingMonth", spendingMonth);
		return "redirect:/delete-end?spendingDeleteEnd";
	}

	// 入金削除完了画面
	@RequestMapping(value = "/delete-end", params = "depositDeleteEnd")
	public String depositDeleteEnd(@ModelAttribute("depositYear") String depositYear,
			@ModelAttribute("depositMonth") String depositMonth, Model model) {

		model.addAttribute("depositYear", depositYear);
		model.addAttribute("depositMonth", depositMonth);
		return "web/list_operate/expenseListDepositDeleteEnd";
	}

	// 支出削除完了画面
	@RequestMapping(value = "/delete-end", params = "spendingDeleteEnd")
	public String spendingDeleteEnd(@ModelAttribute("spendingYear") String spendingYear,
			@ModelAttribute("spendingMonth") String spendingMonth, Model model) {

		model.addAttribute("spendingYear", spendingYear);
		model.addAttribute("spendingMonth", spendingMonth);
		return "web/list_operate/expenseListSpendingDeleteEnd";
	}

	// 入金削除完了画面→一覧
	@RequestMapping(value = "/expenseList-delete-end", params = "depositToExpenseList_btn")
	public String depositToExpenseListOperate(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("depositYear") String depositYear, @RequestParam("depositMonth") String depositMonth,
			Model model) {
		expenseListBean.setExpenseYear(depositYear);
		expenseListBean.setExpenseMonth(depositMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		if (year.equals("") && month.equals("")) {
			return "web/errorWindow";
		}

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	// 支出削除完了画面→一覧
	@RequestMapping(value = "/expenseList-delete-end", params = "spendingToExpenseList_btn")
	public String spendingToExpenseListOperate(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("spendingYear") String spendingYear, @RequestParam("spendingMonth") String spendingMonth,
			Model model) {
		expenseListBean.setExpenseYear(spendingYear);
		expenseListBean.setExpenseMonth(spendingMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		if (year.equals("") && month.equals("")) {
			return "web/errorWindow";
		}

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	//
	// 入金データ詳細表示→一覧
	@RequestMapping(value = "/expenseList-deposit-detail-data", params = "back_btn")
	public String expenseListDepositDetailToeExpenseListOperate(
			@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("depositYear") String depositYear, @RequestParam("depositMonth") String depositMonth,
			Model model) {
		expenseListBean.setExpenseYear(depositYear);
		expenseListBean.setExpenseMonth(depositMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("expenseYear", depositYear);
		model.addAttribute("expenseMonth", depositMonth);
		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

	// 支出データ詳細表示→一覧
	@RequestMapping(value = "/expenseList-spending-detail-data", params = "back_btn")
	public String expenseListSpendingDetailToeExpenseListOperate(
			@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@RequestParam("spendingYear") String spendingYear, @RequestParam("spendingMonth") String spendingMonth,
			Model model) {
		expenseListBean.setExpenseYear(spendingYear);
		expenseListBean.setExpenseMonth(spendingMonth);

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId = expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month, userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month, userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("expenseYear", spendingYear);
		model.addAttribute("expenseMonth", spendingMonth);
		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}
}
