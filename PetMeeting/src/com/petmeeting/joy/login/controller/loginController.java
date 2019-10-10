package com.petmeeting.joy.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.petmeeting.joy.login.model.MemberDto;
import com.petmeeting.joy.login.service.MemberService;

@Controller
public class loginController {

	@Autowired
	MemberService memService;
	
	@RequestMapping (value="login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login() {
		return "login/login";
	}
	
	@RequestMapping (value="account.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String account() {
		return "login/account";
	}
	
	@RequestMapping (value="accountAf.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String accountAf(MemberDto mem) {
		memService.addMember(mem);
		
		return "login/login";
	}
	
	@RequestMapping (value="loginAf.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginAf(MemberDto mem, HttpServletRequest req) {
		MemberDto user = memService.loginCheck(mem);
		
		if(user == null) {
			System.out.println("로그인 실패");
			req.getSession().removeAttribute("login");
			return "login/login";
		}
		
		System.out.println("로그인 성공");
		req.getSession().setAttribute("login", user);
		
		if(user.getEmail().equals("admin")) {
			return "admin/adminMain";
		}
		System.out.println("세션생성");
		return "main";
	}
	
	@RequestMapping (value="logout.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("login");
		System.out.println("세션 제거");
		
		return "login/login";
	}
	
	@RequestMapping (value="main.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String goMoin() {	
		return "main";
	}
}
		