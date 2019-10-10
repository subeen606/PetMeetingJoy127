package com.petmeeting.joy.playboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class playboardController {
	
	
	@RequestMapping(value = "playboard.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String goPlayboard() {
		//System.out.println("소모임 게시판으로 가자");
		return "playboard/playboardList";
	}
}
