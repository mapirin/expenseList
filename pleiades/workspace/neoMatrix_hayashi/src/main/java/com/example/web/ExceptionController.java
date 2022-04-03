package com.example.web;

import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	//
	//エラー処理
	 @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	public String emptyError() {
		return "web/errorWindow";
	}
}
