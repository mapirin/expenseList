package com.example.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.service.ExpenseManagementService;
import com.example.web.list_operate.DepositExpenseListInfo;
import com.example.web.list_operate.ExpenseListBean;
import com.example.web.list_operate.SpendingExpenseListInfo;

/**
 * メニュー画面の描画や遷移処理を行うコントローラ
 * 
 * @author neo-matrix506
 *
 */
@Controller
@SessionAttributes({ "loginForm", "expenseBean", "scopedTarget.depositInfo", "scopedTarget.spendingInfo" })
public class MenuManagementController {

	@Autowired
	public ExpenseManagementService expenseManagementService;

	@ModelAttribute("loginForm")
	public LoginManagementForm setLoginForm() {
		return new LoginManagementForm();
	}

	@ModelAttribute("expenseBean")
	public ExpenseListBean setExpenseBean() {
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

	// ログイン画面→メニュー
	@RequestMapping(value = "/menu", params = "login_btn")
	public String loginToMenu(@Validated @ModelAttribute("loginForm") LoginManagementForm loginForm,
			@RequestParam("userName") String userName, BindingResult result, Model model) {

		if (!(loginForm.getUserName().equals("") && loginForm.getPassword().equals(""))) {
			if (expenseManagementService.getUserInfoTableSelectUserData(loginForm.getUserName(),
					loginForm.getPassword())) {
				model.addAttribute("userName", userName);
				return "web/menu";
			} else {
				result.reject("error-mismatch-data");
			}
		} else {
			result.reject("error-empty-data");
		}
		return "web/login";
	}

	// 入金登録画面→メニュー
	@RequestMapping(value = "/deposit-data-end", params = "menu_btn")
	public String depositRegistToMenu(@ModelAttribute("loginForm") LoginManagementForm loginForm, Model model) {
		return "web/menu";
	}

	// 支出が麺登録→メニュー
	@RequestMapping(value = "/spending-data-end", params = "menu_btn")
	public String spendingRegistToMenu(@ModelAttribute("loginForm") LoginManagementForm loginForm) {
		return "web/menu";
	}

	// メニュー→家計簿を管理
	@RequestMapping(value = "/management-data", params = "managementData_btn")
	public String menuToExpenseList(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("expenseListBean") ExpenseListBean expenseListBean,
			@ModelAttribute("depositInfo") DepositExpenseListInfo depositInfo,
			@ModelAttribute("spengingInfo") SpendingExpenseListInfo spendingInfo, Model model) {

		expenseListBean.setExpenseYear(String.valueOf(LocalDate.now().getYear()));
		expenseListBean.setExpenseMonth(String.format("%02d", LocalDate.now().getMonthValue()));

		String year = expenseListBean.getExpenseYear();
		String month = expenseListBean.getExpenseMonth();
		String userName = loginForm.getUserName();
		Integer userId=expenseManagementService.getUserInfoTableSelectId(userName);

		Integer depositSumAmount = expenseManagementService.getDepositInfoTableSumAmount(year, month,userId);
		Integer spendingSumAmount = expenseManagementService.getSpendingInfoTableSumAmount(year, month,userId);
		Integer sumAmount = depositSumAmount - spendingSumAmount;

		List<DepositExpenseListInfo> depositDataList = expenseManagementService.getDepositInfoTableAllData(year, month,
				userName);
		List<SpendingExpenseListInfo> spendingDataList = expenseManagementService.getSpendingInfoTableAllData(year,
				month, userName);

		model.addAttribute("loginForm", loginForm);
		model.addAttribute("depositSumAmount", depositSumAmount);
		model.addAttribute("spendingSumAmount", spendingSumAmount);
		model.addAttribute("sumAmount", sumAmount);
		model.addAttribute("depositDataList", depositDataList);
		model.addAttribute("spendingDataList", spendingDataList);
		return "web/list_operate/expenseListOperate";
	}

}
