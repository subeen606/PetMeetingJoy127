package com.petmeeting.joy.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.admin.service.AdminService;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.funding.util.FUpUtil;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;

import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;

import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.service.PlayboardService;

@Controller
public class AdminCotroller {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PlayboardService playboardService;
	
	@RequestMapping(value = "adminMain.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String adminMain() {
		return "admin/adminMain";
	}
	
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
	
	@RequestMapping(value = "adminPlayboardDetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminPlayboardDetail(int seq, Model model) {
		PlayboardDto pDto = adminService.getPlayboardDetail(seq);
		
		List<PlayMemDto> partList = playboardService.getPlayMems(seq);
		
		MyProfileDto profile = playboardService.getMakerProfile(pDto.getEmail());
		
		PlayboardHashTagDto hashs = playboardService.getHashTags(seq);
		
		model.addAttribute("detail", pDto);
		model.addAttribute("hashs", hashs);
		model.addAttribute("profile", profile);
		model.addAttribute("partList", partList);
		return "admin/playboard/playboardDetail";

	}

	@RequestMapping(value = "adminBoardReportReason.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBoardReportReason(@RequestParam("seq") int seq, @RequestParam("board_code") String board_code, Model model) {
		
		System.out.println("seq : " + seq);
		System.out.println("board_code : " + board_code);
		
		List<BoardReportDto> reportList = adminService.getBoardReportReason(new BoardReportDto(board_code, seq));
		model.addAttribute("reasons", reportList);
		return "admin/reportReason";
	}
	
	@RequestMapping(value = "adminBoradReportDelete.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBoradReportDelete(BoardReportDto reportDto, RedirectAttributes redirectAttributes) {
		System.out.println(reportDto.toString());
		adminService.deleteBoardReport(reportDto);
		
		redirectAttributes.addAttribute("seq", reportDto.getBoard_seq());
		redirectAttributes.addAttribute("board_code", reportDto.getBoard_code());
		
		return "redirect:/adminBoardReportReason.do";
	}
	
	
	
	
	/*funding 관리자*/
	@RequestMapping(value = "adminFundingList.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String adminFundingList(Model model, fundingBean fbean) {
		System.out.println("펀딩 리스트에 들어온 admin: " + fbean.toString());
		
		int totalfundingCount = adminService.getFundingCount(fbean);
		int sn = fbean.getPageNumber();
		
		int start = sn * fbean.getRecordCountPerPage() + 1;
		fbean.setStart(start);
		
		int end = (sn + 1) * fbean.getRecordCountPerPage();
		if (end > totalfundingCount) {
			end = totalfundingCount;
		}
		fbean.setEnd(end);
		
		System.out.println("펀딩 리스트에 들어온 admin: " + fbean.toString());
		
		List<FundingDto> flist = adminService.getFundingList(fbean);
		
		
		System.out.println("펀딩 admin리스트 수: " + totalfundingCount);
		
		model.addAttribute("flist", flist);
		model.addAttribute("f_categorys", fbean.getF_categorys());
			
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", fbean.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalfundingCount);
		
		return "admin/fundingboard/fundingAdminList";
	}
	
	/*funding 삭제*/
	@RequestMapping(value = "adminFundDelete.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String fundDelete(HttpServletRequest req) {
		String [] Sseq = req.getParameterValues("seq");
		int[] seq = new int[Sseq.length];
		
		for(int i=0; i<Sseq.length; i++) {
			seq[i] = Integer.parseInt(Sseq[i]);
			System.out.println("들어온 seq : " + seq[i] );

			adminService.deletefunding(seq[i]);
			}
		return "redirect:/adminFundingList.do";
	}
	
	/*funding 글쓰기*/
	@RequestMapping(value = "fundingWrite.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String fundingWrite() {
		 
		return "admin/fundingboard/fundingWrite"; 
	
	}
	
	/*funding 글쓰기 Af*/
	@RequestMapping(value = "fundingWriteAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingWriteAf(FundingDto dto, DayBean bean,
			@RequestParam(value = "fileload",required = false)MultipartFile fileload, HttpServletRequest req) {
		
		String filename = fileload.getOriginalFilename();
		dto.setThumbnail(filename);// dto에 파일네임 넣기
		
		String fupload = req.getServletContext().getRealPath("/fundingFileupload");
		System.out.println("fupload 파일 업로드위치: " + fupload);
	
		String f = dto.getThumbnail();
		String newfilename = FUpUtil.getNewFileName(f);

		dto.setThumbnail(newfilename);
		File file = new File(fupload + "/" + newfilename);
		
		try {
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("들어온 정보: " + dto.toString() + " , " + bean.toString());
		
		adminService.addFunding(dto, bean);
		return "redirect:/adminFundingList.do";
	}
	
	
	/*수정*/
	@RequestMapping(value = "fundUpdate.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String fundUpdate(int seq, Model model) {
		System.out.println("들어옴: " + seq);
		FundingDto dto = adminService.fundingDetail(seq);
		model.addAttribute("dto", dto);
		return "admin/fundingboard/fundingUpdate";
	}
	
	
	/*funding 수정 Af*/
	@RequestMapping(value = "fundingUpateAf.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingUpateAf(FundingDto dto ,  DayBean bean, @RequestParam(value = "fileload",required = false)MultipartFile fileload, HttpServletRequest req) {

				String filename = fileload.getOriginalFilename();
				dto.setThumbnail(filename);// dto에 파일네임 넣기

				String fupload = req.getServletContext().getRealPath("/fundingFileupload");
				System.out.println("fupload 파일 업로드위치: " + fupload);

				String f = dto.getThumbnail();

				File file = new File(fupload + "/" + f);
				
				try {
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		System.out.println("수정Af에 들어온 dto: " + dto.toString());
		System.out.println("수정Af에 들어온 bean: " +bean.toString());
		
		boolean b = adminService.fundUpdate(dto,bean);
		if(b) {
			System.out.println("업데이트 성공");
			return "redirect:/adminFundingList.do";
		}
		else {
			System.out.println("업데이트 실패");
			return "redirect:/adminFundingList.do";
		}
	}
	
	/*funding 디테일*/
	@RequestMapping(value = "adminFundingDetail.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingDetail(Model model,int seq) {
		FundingDto dto = adminService.fundingDetail(seq);
		model.addAttribute("dto", dto);
		return "admin/fundingboard/fundingAdminDetail";
	}	
	
	/*fundingMem popup*/
	@RequestMapping(value = "fundingMempop.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingMempop(int seq, Model model){
		System.out.println("memlistPop:" + seq);
		List<FundMemberDto> mlist = adminService.whofundingMem(seq);
		FundingDto dto = adminService.fundingDetail(seq);
		model.addAttribute("mlist", mlist);
		model.addAttribute("dto", dto);
		return "admin/fundingboard/fundingMemPop";
	}
	
	
	/*funding 내역서 글쓰기 Af*/
	@RequestMapping(value = "statementAf.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String statementAf(FundingStaDto sta) {
		System.out.println("들어온 sta : " + sta.toString());
		
		adminService.addfundingSta(sta);
		
		return "redirect:/adminFundingList.do";
	}
}
