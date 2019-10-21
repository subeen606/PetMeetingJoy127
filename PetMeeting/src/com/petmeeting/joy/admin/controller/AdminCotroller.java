package com.petmeeting.joy.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.AdminMemberDto;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.admin.model.MemberSearchBean;
import com.petmeeting.joy.admin.model.ReportDto;
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
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
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
				
	//	System.out.println("searchBean : " + search.toString());
		
		if(search.getPlayCategory() == null || search.getPlayCategory().equals("선택")) {
			search.setPlayCategory("");
		}	
		if(search.getSearchCategory() == null) {
			search.setSearchCategory("선택");
			search.setSearchText("");
		}
		if(search.getSortingType() == null) {
			search.setSortingType("선택");
		}
		
		int totalRowCount = adminService.getPlayboardTotalRowCount(search);
	//	System.out.println("소모임 총 글 수 : " + totalRowCount);
		
		search.setStartRow((search.getCurrPage() * 10) + 1);
		
		int end = (search.getCurrPage() + 1) * 10;
		if(end > totalRowCount) {
			end = totalRowCount;
		}
		search.setEndRow(end);
			
	//	System.out.println("searchBean : " + search.toString());
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
	public String adminPlayboardDetail(int seq, Model model, PlayboardQnADto qnaDto) {
		PlayboardDto pDto = adminService.getPlayboardDetail(seq);
		
		List<PlayMemDto> partList = playboardService.getPlayMems(seq);
		
		MyProfileDto profile = playboardService.getMyProfile(pDto.getEmail());
		
		PlayboardHashTagDto hashs = playboardService.getHashTags(seq);
		
		if(qnaDto.getCurrPage() == 0) {
			qnaDto = new PlayboardQnADto(seq, 1, 1, 10);
		}else {
			int totalQnA = playboardService.getTotalQnACount(seq);
			int end = qnaDto.getCurrPage()*10;
			if(end > totalQnA) {
				end = totalQnA;
			}
			qnaDto = new PlayboardQnADto(seq, qnaDto.getCurrPage(), ((qnaDto.getCurrPage()-1)*10)+1, end);
		}
		
		List<PlayboardQnADto> qnaList = playboardService.getPlayboardQnAList(qnaDto);
		
		model.addAttribute("detail", pDto);
		model.addAttribute("totalCount", playboardService.getTotalQnACount(seq));
		model.addAttribute("currPage", qnaDto.getCurrPage());
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("hashs", hashs);
		model.addAttribute("profile", profile);
		model.addAttribute("partList", partList);
		return "admin/playboard/playboardDetail";

	}

	@RequestMapping(value = "adminBoardReportReason.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBoardReportReason(@RequestParam("seq") int seq, @RequestParam("board_code") String board_code, Model model) {
		
		System.out.println("seq : " + seq);
		System.out.println("board_code : " + board_code);
		
		List<BoardReportDto> reportList = adminService.getBoardReportReason(new ReportDto(board_code, seq));
		model.addAttribute("board_seq", seq);
		model.addAttribute("board_code", board_code);
		model.addAttribute("board_reasons", reportList);
		return "admin/reportReason";
	}
	
	@RequestMapping(value = "adminBoardReportDelete.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBoradReportDelete(ReportDto reportDto, RedirectAttributes redirectAttributes) {
		System.out.println(reportDto.toString());
		adminService.deleteBoardReport(reportDto);
		
		redirectAttributes.addAttribute("seq", reportDto.getBoard_seq());
		redirectAttributes.addAttribute("board_code", reportDto.getBoard_code());
		
		return "redirect:/adminBoardReportReason.do";
	}
	
	
	@RequestMapping(value = "adminMemberList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminMemberList(MemberSearchBean memSearchBean, Model model) {
				
		if(memSearchBean.getSortingType() == null) {
			memSearchBean.setSortingType("전체");
		}
		
		if(memSearchBean.getGrade() == null || memSearchBean.getGrade().equals("전체")) {
			memSearchBean.setGrade("");
		}
		
		if(memSearchBean.getSearch_category() == null) {
			memSearchBean.setSearch_category("선택");
		}
		
		int totalRowCount = adminService.getMemberTotalCount(memSearchBean);
			System.out.println("총 회원 수 : " + totalRowCount);
			
			memSearchBean.setStart((memSearchBean.getCurrPage() * 10) + 1);
			
			int end = (memSearchBean.getCurrPage() + 1) * 10;
			if(end > totalRowCount) {
				end = totalRowCount;
			}
			memSearchBean.setEnd(end);
		
		System.out.println("memSearchBean : " + memSearchBean);
		List<AdminMemberDto> memberList = adminService.getAllMemberList(memSearchBean);
		//System.out.println(memberList.toString());
		model.addAttribute("totalRowCount", totalRowCount);
		model.addAttribute("searchBean", memSearchBean);
		model.addAttribute("memberList", memberList);
		return "admin/member/memberList";
	}
	
	@RequestMapping(value = "adminLeaveMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminLeaveMember(HttpServletRequest req) {
		String[] mems = req.getParameterValues("memcheck");
		System.out.println(Arrays.toString(mems));
		
		List<ReportDto> leaveMemberList = new ArrayList<ReportDto>();
		String reason = "관리자 권한";
		for (int i = 0; i < mems.length; i++) {
			leaveMemberList.add(new ReportDto(mems[i], reason));
		}
		
		System.out.println(leaveMemberList.toString());
		adminService.insertLeaveMember(leaveMemberList);
		
		return "redirect:/adminMemberList.do";
	}
	
	@RequestMapping(value = "adminMemberDetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminMemberDetail(String email, Model model) {
		System.out.println("정보 볼 이메일 : " + email);
		AdminMemberDto memberDetail = adminService.getMemberDetail(email);
		System.out.println("멤버 정보 : " + memberDetail.toString());
		model.addAttribute("detail", memberDetail);
		return "admin/member/memberDetail";
	}
	
	@RequestMapping(value = "adminMemberReportReason.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminMemberReportReason(@RequestParam("email") String email, Model model) {
		System.out.println("신고내역 확인 할 email : " + email);
		
		List<ReportDto> reportList = adminService.getMemberReportReason(email);
		model.addAttribute("bad_email", email);
		model.addAttribute("member_reasons", reportList);
		return "admin/reportReason";
	}
	
	@RequestMapping(value = "adminMemberReportDelete.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminMemberReportDelete(ReportDto reportDto, RedirectAttributes redirectAttributes) {
		System.out.println("삭제할 reportDto : " + reportDto.toString());
		
		adminService.deleteMemberReport(reportDto);
		
		redirectAttributes.addAttribute("email", reportDto.getBad_email());
		return "redirect:/adminMemberReportReason.do";
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
			
			
			List<FundMemberDto> memList = adminService.whofundingMem(dto.getSeq());
			for(int i=0; i<memList.size(); i++ ) {
			System.out.println("글수정 Af 에들어온 List : " + memList.get(i).toString() );
			}
			
			if(memList.size() > 0 ) {
				adminService.sendMsgUpfund(memList, dto.getTitle());
			}
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
		
		FundingDto dto = adminService.fundingDetail(sta.getSeq());
		
		List<FundMemberDto> memList = adminService.whofundingMem(sta.getSeq());
		for(int i=0; i<memList.size(); i++ ) {
		System.out.println("글쓰기 Af 에들어온 List : " + memList.get(i).toString() );
		}
		
		if(memList.size() > 0 ) {
			adminService.sendMsgfund(memList, dto.getTitle());
		}
		
		adminService.addfundingSta(sta);
		
		return "redirect:/adminFundingList.do";
	}
	
	/*funding 내역서만 삭제*/
	@ResponseBody
	@RequestMapping(value = "fundingStaDel.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public void fundingStaDel(int seq) {
		adminService.fundingStaDel(seq);
	}
	
	/*funding 내역서 수정*/
	@ResponseBody
	@RequestMapping(value = "fundingStaUp.do",method = {RequestMethod.GET,RequestMethod.POST})
	public void fundingStaUp() {
		
	}
}
