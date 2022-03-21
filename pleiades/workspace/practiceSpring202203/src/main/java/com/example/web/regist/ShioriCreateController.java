package com.example.web.regist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("shioriForm")
public class ShioriCreateController {

	@ModelAttribute("shioriForm")
	public ShioriForm setForm() {
		return new ShioriForm();
	}

	@RequestMapping("/shiori-tsukuuru")
	public String menuWindow() {
		return "shioriMakeMenu";
	}

	@RequestMapping(value = "/shiori-data-input", params = "make_btn")
	public String menuToInputWindow(@ModelAttribute("shioriForm") ShioriForm shioriForm) {
		return "shioriMakeInput";
	}

	@RequestMapping(value = "/shiori-data-conf", params = "back_btn")
	public String inputToMenuWindow(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		
		return "shioriMakeMenu";
	}

	@RequestMapping(value = "/shiori-data-conf", params = "conf_btn")
	public String inputToConfWindow(@ModelAttribute("shioriForm") ShioriForm shioriForm) {
		return "shioriMakeConf";
	}

	@RequestMapping(value = "/shiori-data-end", params = "back_btn")
	public String confToInputWindow(@ModelAttribute("shioriForm") ShioriForm shioriForm) {
		return "shioriMakeInput";
	}

	@RequestMapping(value = "/shiori-data-end", params = "regist_btn")
	public String ConfToEndWindow(@ModelAttribute("shioriForm") ShioriForm shioriForm) {
		
		return "redirect:/shiori-data-end?finish";
	}
	
	@RequestMapping(value = "/shiori-data-end", params = "finish")
	public String endWindow() {
		
		return "shioriMakeEnd";
	}

	@RequestMapping(value = "/shiori-tsukuuru", params = "menu_btn")
	public String endToMenuWindow() {
		return "shioriMakeMenu";
	}

}
