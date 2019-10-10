package com.petmeeting.joy.funding.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingParam;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.FundingmemDto;
import com.petmeeting.joy.funding.service.FundingService;
import com.petmeeting.joy.funding.util.FUpUtil;
import com.petmeeting.joy.login.model.MemberDto;


@Controller
public class FundingController {
	
	@Autowired
	FundingService service;
	
	/*펀딩 리스트*/
	@RequestMapping(value = "funding.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingList(Model model,HttpServletRequest req, FundingParam param) {
		String email = ((MemberDto)req.getSession().getAttribute("login")).getEmail();
		
		System.out.println("컨트롤러로 들어오는 param: " +param.toString());
			
		List<FundingDto> list = service.fundingList(email,param);
		model.addAttribute("list", list);

		
		if(param.getKeyword() != null) {
		model.addAttribute("keyword", param.getKeyword());
		}
		model.addAttribute("categorys", param.getCategorys());
		
		model.addAttribute("param", param);
		return "funding/fundingList";
	}
		
	/*펀딩 글쓰기*/
	@RequestMapping(value = "fundingWrite.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String fundingWrite() {
		 
		return "funding/fundingWrite"; 
	
	}
	
	/*펀딩 글쓰기 Af*/
	@RequestMapping(value = "fundingWriteAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingWriteAf(FundingDto dto, DayBean bean,
			@RequestParam(value = "fileload",required = false)MultipartFile fileload, HttpServletRequest req) {
		
		//1.파일 이름 취득
		String filename = fileload.getOriginalFilename();
		dto.setThumbnail(filename);// dto에 파일네임 넣기
		
		//2.업로드 경로 설정
		String fupload = req.getServletContext().getRealPath("/fundingFileupload");
		System.out.println("fupload 파일 업로드위치: " + fupload);
		
		//3.file명 변경해주기
		String f = dto.getThumbnail();
		String newfilename = FUpUtil.getNewFileName(f);
		
		//4.업로드
		dto.setThumbnail(newfilename);
		File file = new File(fupload + "/" + newfilename);
		
		try {
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("들어온 정보: " + dto.toString() + " , " + bean.toString());
		
		service.addFunding(dto, bean);
		return "redirect:/funding.do";
	}
	
	/*펀딩 디테일*/
	@RequestMapping(value = "fundingDetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingDetail(FundinglikeBean bean, FundingDto dto ,int seq, Model model) {

		System.out.println("들어온 seq: " + seq);

		dto = service.fundingDetail(seq,bean);
		
		System.out.println("서비스에서 컨트롤러로 들어온 dto: " + dto.toString());

		model.addAttribute("dto", dto);
		
		return "funding/fundingDetail";
	}
	
	/*좋아요*/
	@ResponseBody
	@RequestMapping(value = "fundinglike.do" , 
					produces = "application/String; charset=utf-8",
					method = {RequestMethod.GET,RequestMethod.POST})
	public String fundinglike(FundinglikeBean bean, FundingDto dto) {
		System.out.println("에이좍스 들어온것 seq : " + bean.getBoard_seq() + "메일: " + bean.getEmail() );
		String msg="";
		
		int n = service.isFundlike(bean);
		System.out.println("컨트롤러에서 check :" + n);
		if(n>0) { //좋아요 한것이 있다.
			service.FundNO(dto);//좋아요 delete
			service.likeCountDown(dto.getSeq());
			//dto.setIsFunding(true);
			msg="NO";
			
		}else {
			service.Fundlike(dto);//좋아요 insert
			service.likeCountUp(dto.getSeq());
			//dto.setIsFunding(false);
			msg="YES";
		}
		//System.out.println("최종 Is 좋아요 : " + dto.getIsFunding());
		return msg;
		
	}
	
	
	/*펀딩 페이*/
	@RequestMapping(value = "fundingPay.do",method = {RequestMethod.GET, RequestMethod.POST})
	public String fundingPay(int seq, String email, Model model, FundinglikeBean bean, int funding_seq) {
		
		FundingDto dto = service.fundingDetail(seq,bean); //detail의 정보 받아오기
		MemberDto mem = service.fundingMem(email);
		
		model.addAttribute("dto", dto);
		model.addAttribute("mem", mem);
		
		return "funding/fundingPay";
	}
	
	/*펀딩페이 Af*/
	@ResponseBody
	@RequestMapping(value = "fundingpayAf.do",method = {RequestMethod.GET,RequestMethod.POST})
	public void fundingpayAf(String donation, FundingmemDto mem, int funding_seq) {
		
		System.out.println("후원금: " + donation);
		System.out.println("후원한 게시판의 seq: "+ funding_seq);
		System.out.println(mem.toString());
		service.personCount(funding_seq);
		service.addFundMem(mem);
	}
	
	/*수정*/
	@RequestMapping(value = "fundUpdate.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String fundUpdate(int seq, Model model) {
		System.out.println("들어옴: " + seq);
		FundingDto dto = service.fundingUpdate(seq);
		model.addAttribute("dto", dto);
		return "funding/fundingUpdate";
	}
	
	/*수정 Af*/
	@RequestMapping(value = "fundingUpateAf.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingUpateAf(FundingDto dto ,  DayBean bean, @RequestParam(value = "fileload",required = false)MultipartFile fileload, HttpServletRequest req) {
		
				//1.파일 이름 취득
				String filename = fileload.getOriginalFilename();
				dto.setThumbnail(filename);// dto에 파일네임 넣기
				
				//2.업로드 경로 설정
				String fupload = req.getServletContext().getRealPath("/fundingFileupload");
				System.out.println("fupload 파일 업로드위치: " + fupload);
				
				//3.file명 변경해주기
				String f = dto.getThumbnail();
				//String newfilename = FUpUtil.getNewFileName(f);
				
				//4.업로드
				//dto.setThumbnail(newfilename);
				File file = new File(fupload + "/" + f);
				
				try {
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		System.out.println("수정Af에 들어온 dto: " + dto.toString());
		System.out.println("수정Af에 들어온 bean: " +bean.toString());
		
		boolean b = service.fundUpdate(dto,bean);
		if(b) {
			System.out.println("업데이트 성공");
			return "redirect:/funding.do";
		}
		else {
			System.out.println("업데이트 실패");
			return "redirect:/funding.do";
		}
	}
	
	/*삭제*/
	@RequestMapping(value = "fundDelete.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String fundDelete(FundingDto dto) {
		boolean b = service.fundingDelete(dto);
		if(b) {
			  System.out.println("삭제성공");
			  return "redirect:/funding.do";
			  }
		else {
			System.out.println("삭제실패");
			return "redirect:/funding.do";
			}
		}
	
	/*관리자 내역서 글쓰기 창*/
	@RequestMapping(value = "statement.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String statement(FundinglikeBean bean, FundingDto dto ,int seq, Model model) {
		
		dto = service.fundingDetail(seq, bean);
		
		model.addAttribute("dto", dto);
		
		return "funding/fundingStatementWr";
	}
	
	/*내역서 글쓰기 Af*/
	@RequestMapping(value = "statementAf.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String statementAf(FundingStaDto sta) {
		System.out.println("들어온 sta : " + sta.toString());
		
		service.addfundingSta(sta);
		
		return "redirect:/funding.do";
	}
	
	/*내역서 디테일*/
	@RequestMapping(value = "fundingstaDetail.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String fundingstaDetail(int seq , Model model) {
		System.out.println("funding 내역서 seq = " +seq);
		FundingStaDto sta = service.fundingStaDetail(seq);
		model.addAttribute("sta", sta);
		
		return "funding/fundingstaDetail";
	}
	
	/*이미지 업로드*/
	@ResponseBody
	@RequestMapping(value = "img.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 업로드할 폴더 경로
		String realFolder = request.getSession().getServletContext().getRealPath("profileUpload");
		UUID uuid = UUID.randomUUID();

		// 업로드할 파일 이름
		String org_filename = file.getOriginalFilename();
		String str_filename = uuid.toString() + org_filename;

		System.out.println("원본 파일명 : " + org_filename);
		System.out.println("저장할 파일명 : " + str_filename);

		String filepath = realFolder + "\\" + str_filename;
		System.out.println("파일경로 : " + filepath);

		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		file.transferTo(f);
		out.println("profileUpload/"+str_filename);
		out.close();
	}
	
	
}
