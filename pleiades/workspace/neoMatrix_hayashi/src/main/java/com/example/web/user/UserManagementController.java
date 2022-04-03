package com.example.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.service.ExpenseManagementService;

@Controller
@SessionAttributes("userForm")
public class UserManagementController {

	@Autowired
	private ExpenseManagementService expenseManagementService;

	@ModelAttribute("userForm")
	public UserManagementForm setUserForm() {
		return new UserManagementForm();
	}

	// ログイン画面→ユーザ登録
	@RequestMapping(value = "/menu", params = "userRegist_btn")
	public String loginToUserRegistInput(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/user/userRegistInput";
	}

	//
	// ユーザ登録
	@RequestMapping(value = "/user-regist-conf", params = "registConf_btn")
	public String registInputToRegistConf(@Validated @ModelAttribute("userForm") UserManagementForm userForm,
			BindingResult result) {
		if (userForm.getUserName().equals("") || userForm.getPassword().equals("")) {
			result.reject("error-empty-data");
		} else if (!(expenseManagementService.getUserInfoTableUserExistCheck(userForm.getUserName()))) {
			result.reject("error-duplicate-data");
		}

		if (result.hasErrors()) {
			return "web/user/userRegistInput";
		}

		return "web/user/userRegistConf";
	}

	// ユーザ登録確認→ユーザ登録入力
	@RequestMapping(value = "/user-regist-end", params = "back_btn")
	public String registConfToRegistInput(@ModelAttribute("userForm") UserManagementForm userForm) {
		return "web/user/userRegistInput";
	}

	// ユーザ登録確認→ユーザ登録完了
	@RequestMapping(value = "/user-regist-end", params = "regist_btn")
	public String registConfToRegistEnd(@ModelAttribute("userForm") UserManagementForm userForm) {

		expenseManagementService.insertUserInfoTableData(userForm.getUserName(), userForm.getPassword());
		return "redirect:/user-regist-end?finish";
	}

	// リダイレクト
	@RequestMapping(value = "/user-regist-end", params = "finish")
	public String registEnd(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "web/user/userRegistEnd";
	}

}
