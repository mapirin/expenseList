package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("scopedTarget.loginForm")
public class LoginController {

	@ModelAttribute("loginForm")
	public LoginManagementForm setLoginForm() {
		return new LoginManagementForm();
	}

	//
	// ログイン画面
	@RequestMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginManagementForm loginForm,SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// ユーザ情報入力→ログイン画面
	@RequestMapping(value = "/user-regist-conf", params = "back_btn")
	public String registInputToLogin(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// ユーザ登録完了→ログアウト
	@RequestMapping(value = "/login", params = "backLogin_btn")
	public String registEnd(SessionStatus sessionStatus) {
		return "web/login";
	}

	// メニューと入出金
	// メニュー→ログアウト
	@RequestMapping(value = "/management-data", params = "logout_btn")
	public String menuToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 入金登録画面→ログアウト
	@RequestMapping(value = "/deposit-data-end", params = "logout_btn")
	public String depositRegistToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 入金登録完了画面→ログアウト
	@RequestMapping(value = "/deposit-menu", params = "logout_btn")
	public String depositEndToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 支出登録画面→ログアウト
	@RequestMapping(value = "/spending-data-end", params = "logout_btn")
	public String spendingRegistToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 支出登録完了画面→ログアウト
	@RequestMapping(value = "/spending-menu", params = "logout_btn")
	public String spendingEndToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 家計簿管理
	// 入出金データリスト→ログアウト
	@RequestMapping(value = "/expenseList-operate-data", params = "logout_btn")
	public String expenseListToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 削除確認画面→ログアウト
	@RequestMapping(value = "/delete-end", params = "logout_btn")
	public String deleteConfToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	// 削除完了画面→ログアウト
	@RequestMapping(value = "/expenseList-delete-end", params = "logout_btn")
	public String deleteEndToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

	//
	// 詳細画面→ログアウト
	@RequestMapping(value = "/expenseList-deposit-detail-data", params = "logout_btn")
	public String expenseListDepositDetailToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}
	//
	@RequestMapping(value = "/expenseList-spending-detail-data", params = "logout_btn")
	public String expenseListSpendingDetailToLogout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/login";
	}

}
