package com.petmeeting.joy.admin.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.petmeeting.joy.admin.service.AdminService;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.service.PlayboardService;

@Controller
public class AdminCotroller {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PlayboardService playboardService;

	@RequestMapping(value = "adminPlayboardList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminPlayboardList(PlayboardSearchBean search, Model model) {
				
		System.out.println("searchBean : " + search.toString());
		
		if(search.getPlayCategory() == null || search.getPlayCategory().equals("선택")) {
			search.setPlayCategory("");
		}	
		if(search.getSearchCategory() == null) {
			search.setSearchCategory("선택");
		}
		
		int totalRowCount = adminService.getPlayboardTotalRowCount(search);
		System.out.println("소모임 총 글 수 : " + totalRowCount);
		
		search.setStartRow((search.getCurrPage() * 10) + 1);
		
		int end = (search.getCurrPage() + 1) * 10;
		if(end > totalRowCount) {
			end = totalRowCount;
		}
		search.setEndRow(end);
			
		System.out.println("searchBean : " + search.toString());
		List<PlayboardDto> playboardList = adminService.getAllPlayboardList(search);

		model.addAttribute("searchBean", search);
		model.addAttribute("playboardList", playboardList);
		model.addAttribute("totalRowCount", totalRowCount);
		
		return "admin/playboard/playboardList";
	}
	
	@RequestMapping(value = "adminPlayboardDelete.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminPlayboardDelete(HttpServletRequest req) {
		String[] dels = req.getParameterValues("delcheck");
		System.out.println(Arrays.toString(dels));
		for (int i = 0; i < dels.length; i++) {
			adminService.deletePlayboard(Integer.parseInt(dels[i]));
		}
		return "redirect:adminPlayboardList.do";
	}
	

	
	
	@RequestMapping(value = "adminFundingList.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String adminFundingList(Model model, fundingBean fbean) {
		
		int sn = fbean.getPageNumber();
		int start = sn * fbean.getRecordCountPerPage() + 1;
		int end = (sn + 1) * fbean.getRecordCountPerPage();
		
		System.out.println("펀딩 리스트에 들어온 admin: " + fbean.toString());
		
		List<FundingDto> flist = adminService.getFundingList(fbean);
		int totalfundingCount = adminService.getFundingCount(fbean);
		
		fbean.setStart(start);
		fbean.setEnd(end);
		
		model.addAttribute("flist", flist);
		model.addAttribute("f_categorys", fbean.getF_categorys());
		
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", fbean.getRecordCountPerPage());//그냥 10이라고 써도되지만 이렇게도 한번 써보자
		model.addAttribute("totalRecordCount", totalfundingCount);
		
		return "admin/fundingboard/fundingboardAdmin";
<<<<<<< HEAD
=======

>>>>>>> 41cb3d594b64e43dae7e8d23ff2bdab5fbfa8773
	}
	
	@RequestMapping(value = "adminPlayboardDetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminPlayboardDetail(int seq, Model model) {
		PlayboardDto pDto = adminService.getPlayboardDetail(seq);
		model.addAttribute("detail", pDto);
		return "admin/playboard/playboardDetail";

	}
}
