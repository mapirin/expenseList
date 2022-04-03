package com.example.web.expense_regist;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.ExpenseManagementService;
import com.example.web.LoginManagementForm;

@Controller
@SessionAttributes({ "loginForm", "depositForm", "spendingForm" })
public class ExpenseRegistController {

	@Autowired
	private ExpenseManagementService expenseService;

	@Autowired
	HttpSession session;

	@ModelAttribute("loginForm")
	public LoginManagementForm setLoginForm() {
		return new LoginManagementForm();
	}

	@ModelAttribute("depositForm")
	public DepositManagementForm setDepositForm() {
		return new DepositManagementForm();
	}

	@ModelAttribute("spendingForm")
	public SpendingManagementForm setSpendingForm() {
		return new SpendingManagementForm();
	}

	// 入金登録
	// メニュー→入金登録画面
	@RequestMapping(value = "/management-data", params = "depositRegist_btn")
	public String menuToDepositRegist(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("depositForm") DepositManagementForm depositForm, Model model) {
		List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
		List<String> depositCategoryList = expenseService.getCategoryInfoTableDepositData();

		if (loginForm.getUserName().equals("")) {
			return "web/errorWindow";
		}

		session.setAttribute("depositForm", depositForm);
		model.addAttribute("methodNameList", methodNameList);
		model.addAttribute("depositCategoryList", depositCategoryList);
		return "web/expense_regist/depositDataRegist";
	}

	// 入金登録画面→入金登録完了画面
	@RequestMapping(value = "/deposit-data-end", params = "registEnd_btn")
	public String depoistRegistToDepositEnd(@Validated @ModelAttribute("depositForm") DepositManagementForm depositForm,
			BindingResult result, @ModelAttribute("loginForm") LoginManagementForm loginForm,
			RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
			List<String> depositCategoryList = expenseService.getCategoryInfoTableDepositData();

			model.addAttribute("methodNameList", methodNameList);
			model.addAttribute("depositCategoryList", depositCategoryList);
			return "web/expense_regist/depositDataRegist";
		}
		expenseService.getDepositInfoTableInsertData(depositForm, loginForm.getUserName());

		String userName = loginForm.getUserName();
		redirectAttributes.addFlashAttribute("userName", userName);
		return "redirect:/deposit-data-end?finish";
	}

	// リダイレクト
	@RequestMapping(value = "/deposit-data-end", params = "finish")
	public String depoistEnd(@ModelAttribute("userName") String userName, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/expense_regist/depositDataEnd";
	}

	// 入金登録完了→メニュー
	@RequestMapping(value = "/deposit-menu", params = "menu_btn")
	public String depositEndToMenu(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("userName") String userName, Model model) {
		if (userName.equals("")) {
			return "web/errorWindow";
		}
		loginForm.setUserName(userName);
		model.addAttribute("loginForm", loginForm);
		return "web/Menu";
	}

	// 入金登録完了→追加登録
	@RequestMapping(value = "deposit-menu", params = "addDepositRegist_btn")
	public String endToDepositRegist(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("userName") String userName, Model model) {
		List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
		List<String> depositCategoryList = expenseService.getCategoryInfoTableDepositData();

		if (userName.equals("")) {
			return "web/errorWindow";
		}
		loginForm.setUserName(userName);
		model.addAttribute("loginForm", loginForm);
		model.addAttribute("methodNameList", methodNameList);
		model.addAttribute("depositCategoryList", depositCategoryList);
		return "web/expense_regist/depositDataRegist";
	}

	// 支出登録
	// メニュー→支出登録画面
	@RequestMapping(value = "/management-data", params = "spendingRegist_btn")
	public String menuToSpendingRegist(Model model) {
		List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
		List<String> spendingCategoryList = expenseService.getCategoryInfoTableSpendingData();

		model.addAttribute("methodNameList", methodNameList);
		model.addAttribute("spendingCategoryList", spendingCategoryList);
		return "web/expense_regist/spendingDataRegist";
	}

	// 支出登録画面→支出登録完了画面
	@RequestMapping(value = "/spending-data-end", params = "registEnd_btn")
	public String spendingRegistToSpendingEnd(
			@Validated @ModelAttribute("spendingForm") SpendingManagementForm spendingForm, BindingResult result,
			@ModelAttribute("loginForm") LoginManagementForm loginForm, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
			List<String> spendingCategoryList = expenseService.getCategoryInfoTableSpendingData();

			model.addAttribute("methodNameList", methodNameList);
			model.addAttribute("spendingCategoryList", spendingCategoryList);
			return "web/expense_regist/spendingDataRegist";
		}
		expenseService.getSpendingInfoTableInsertData(spendingForm, loginForm.getUserName());

		String userName = loginForm.getUserName();
		redirectAttributes.addFlashAttribute("userName", userName);
		return "redirect:/spending-data-end?finish";
	}

	// リダイレクト
	@RequestMapping(value = "/spending-data-end", params = "finish")
	public String spendingEnd(@ModelAttribute("userName") String userName, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/expense_regist/spendingDataEnd";
	}

	// 支出登録完了→メニュー
	@RequestMapping(value = "/spending-menu", params = "menu_btn")
	public String spendingEndToMenu(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("userName") String userName, Model model) {
		if (userName.equals("")) {
			return "web/errorWindow";
		}
		loginForm.setUserName(userName);
		model.addAttribute("loginForm", loginForm);
		return "web/Menu";
	}

	// 支出登録完了画面→追加登録
	@RequestMapping(value = "spending-menu", params = "addSpendingRegist_btn")
	public String endToSpendingRegist(@ModelAttribute("loginForm") LoginManagementForm loginForm,
			@ModelAttribute("userName") String userName, Model model) {

		List<String> methodNameList = expenseService.getMethodInfoTableSelectName();
		List<String> spendingCategoryList = expenseService.getCategoryInfoTableSpendingData();
		
		if (userName.equals("")) {
			return "web/errorWindow";
		}
		loginForm.setUserName(userName);
		model.addAttribute("loginForm", loginForm);
		model.addAttribute("methodNameList", methodNameList);
		model.addAttribute("spendingCategoryList", spendingCategoryList);
		return "web/expense_regist/spendingDataRegist";
	}

}
