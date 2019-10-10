package com.petmeeting.joy.playboard.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import com.petmeeting.joy.login.model.MemberDto;
import com.petmeeting.joy.playboard.Util.DateUtil;
import com.petmeeting.joy.playboard.Util.PlayboardUtil;
import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.playboard.model.PlayboardCheckBean;
import com.petmeeting.joy.playboard.model.PlayboardDateBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.model.ReportDto;
import com.petmeeting.joy.playboard.service.PlayboardService;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

@Controller
public class PlayboardController {
	
	@Autowired
	PlayboardService pService;
	
	// 소모임 리스트 띄우기
	@RequestMapping(value = "playboard.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String goPlayboard(PlayboardSearchBean search, Model model) {
		//System.out.println("소모임 게시판으로 가자");
		
		System.out.println("맨 처음 들어오는 searchBean : " + search);
		if(search.getPlayCategory() == null || search.getPlayCategory().equals("선택")) {
			search.setPlayCategory("");
		}			
		if(search.getSearchCategory() == null) {
			search.setSearchCategory("선택");
		}
		if(search.getSearchText() == null) {
			search.setSearchText("");
		}				
		if(search.getSortingType() == null) {
			search.setSortingType("선택");
		}		
		if(search.getStartRow() == 0) {
			search.setStartRow(1);
		}
		if(search.getEndRow() == 0) {
			search.setEndRow(6);
		}
		
		List<PlayboardDto> playboardList = pService.getPlayboardList(search);		
		System.out.println("총 글 수: " + pService.getTotalRowCount(search));
		//System.out.println(playboardList.toString());
		model.addAttribute("totoalRowCount", pService.getTotalRowCount(search));
		model.addAttribute("searchBean", search);
		model.addAttribute("playboardList", playboardList);
		
		return "playboard/playboardList";
	}
	
	// 소모임 리스트 더보기
	@ResponseBody
	@RequestMapping(value="loadMore.do", method={RequestMethod.GET,RequestMethod.POST})
	public List<PlayboardDto> loadMore(PlayboardSearchBean search){
		System.out.println("ajax에서 넘어왔을까요?????? : "+search.toString());
		if(search.getPlayCategory() == null || search.getPlayCategory().equals("선택")) {
			search.setPlayCategory("");
		}	
		List<PlayboardDto> list = pService.getPlayboardList(search);
		for (PlayboardDto dto : list) {
			dto.setTitle(PlayboardUtil.ReduceTitle(dto.getCategory(), dto.getTitle()));
			dto.setLocation(PlayboardUtil.SimpleLocation(dto.getLocation()));
		}
		return list;
	}
	
	// 해쉬태그 눌렀을 때 해당 태그를 가진 글 리스트 띄우기
	@RequestMapping(value = "hashSearch.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String hashSearch(PlayboardSearchBean search, Model model) {
		System.out.println("해쉬태그 : " + search.getSearchText());
		if(search.getSortingType() == null) {
			search.setSortingType("선택");
		}	
		if(search.getStartRow() == 0) {
			search.setStartRow(1);
		}
		if(search.getEndRow() == 0) {
			search.setEndRow(6);
		}
		System.out.println("해쉬검색 : " + search.toString());
		List<PlayboardDto> list = pService.getHashTagList(search);
		
		model.addAttribute("searchBean", search);
		model.addAttribute("hashtag", search.getSearchText());
		model.addAttribute("hashList", list);
		model.addAttribute("hashCount", pService.getHashTagRowCount(search));
		System.out.println("해쉬태그 총 개수 : " + pService.getHashTagRowCount(search));
		return "playboard/playboardHashTagList";
	}
	
	// 해쉬태그 더보기
	@ResponseBody
	@RequestMapping(value="loadMoreHash.do", method={RequestMethod.GET,RequestMethod.POST})
	public List<PlayboardDto> loadMoreHash(PlayboardSearchBean search){
		System.out.println("ajax에서 넘어왔을까요?????? : "+search.toString());
		if(search.getPlayCategory() == null || search.getPlayCategory().equals("선택")) {
			search.setPlayCategory("");
		}	
		List<PlayboardDto> list = pService.getHashTagList(search);
		for (PlayboardDto dto : list) {
			dto.setTitle(PlayboardUtil.ReduceTitle(dto.getCategory(), dto.getTitle()));
			dto.setLocation(PlayboardUtil.SimpleLocation(dto.getLocation()));
		}

		return list;
	}
	
	
	// 소모임 만들기
	@RequestMapping(value = "makePlay.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String goPlayboardWrite() {
		return "playboard/playboardWrite";
	}
	
	// CK Editor 속 파일 업로드
	@RequestMapping(value="fileupload.do", method={RequestMethod.GET,RequestMethod.POST})
	public void fileUpload(@RequestParam MultipartFile upload, 
							HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8"); 
		
		String filename = upload.getOriginalFilename();
		System.out.println("원본 filename : " + filename);
		
		int index = filename.lastIndexOf(".");
		String filetype = filename.substring(index);
		System.out.println("파일 유형 : "+filetype);
		
		filename = UUID.randomUUID().toString() + filetype;
		System.out.println("UUID filename : " + filename);
		
		String fupload = req.getServletContext().getRealPath("/playboardUpload");
		System.out.println("fupload : " + fupload);
		
		File file = new File(fupload + "/" + filename);
		
		try {
			// 실제 파일 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, upload.getBytes());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = resp.getWriter();
		
		String callback = req.getParameter("CKEditorFuncNum");
		System.out.println("callback : " + callback);
		
		String fileUrl = req.getContextPath()+"/playboardUpload/"+filename;
		
		String script="<script>window.parent.CKEDITOR.tools.callFunction(";
	    script +=callback;
	    script +=",'";
	    script +=fileUrl;
	    script +="','이미지를 업로드 했습니다.') ";
	    script +="</script>";
	    
	    pw.println(script);
	    pw.flush();

	}
	
	// 글쓰기 완료 후
	@RequestMapping(value="makePlayAf.do", method={RequestMethod.GET,RequestMethod.POST})
	public String playboardWriteAf(PlayboardDto pdto, PlayboardDateBean datebean, PlayboardHashTagDto hash,
								   @RequestParam(value = "fileload", required = false)MultipartFile fileload, HttpServletRequest req) {
		
		System.out.println(pdto.toString());
		System.out.println(datebean.toString());
		System.out.println(hash.toString());		
		
		// Date형식으로 변환
		Date pdate = DateUtil.toDate(datebean.getPyear(), datebean.getPmonth(), datebean.getPday());
		Date edate = DateUtil.toDate(datebean.getEyear(), datebean.getEmonth(), datebean.getEday());
		pdto.setPdate(pdate);
		pdto.setEdate(edate);
		
		// 썸네일 첨부파일의 원본 파일명
		String filename = fileload.getOriginalFilename();
		System.out.println("썸네일 원본 filename : " + filename);
		
		// 첨부파일의 파일 유형 얻어내기
		int index = filename.lastIndexOf(".");
		String filetype = filename.substring(index);
		System.out.println("썸네일 파일 유형 : "+filetype);
		
		// 파일명 중복방지를 위한 UUID 사용
		filename = UUID.randomUUID().toString() + filetype;
		System.out.println("썸네일 UUID filename : " + filename);
		pdto.setFilename(filename);
		
		// 업로드 경로
		String fupload = req.getServletContext().getRealPath("/playboardUpload");
		System.out.println("썸네일 fupload : " + fupload);
		
		File file = new File(fupload + "/" + filename);
		
		try {
			// 실제 파일 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("insert할 pdto : " + pdto.toString());
		pService.insertPlayboard(pdto, hash);
		pService.insertPoint(pdto);
		
		return "redirect:/playboard.do";
	}
	
	// 업데이트 뷰로 이동
	@RequestMapping(value="updatePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String playboardUpdate(int seq, Model model, HttpServletRequest req) {
		PlayboardDto checkDto = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
	
		pService.plusReadCount(seq);
		PlayboardDto dto = pService.getPlayboardDetail(seq, checkDto);
		
		PlayboardHashTagDto hashs = pService.getHashTags(seq);
		
		PlayboardDateBean date = new PlayboardDateBean();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dto.getPdate());
		date.setPyear(cal.get(Calendar.YEAR));
		date.setPmonth(cal.get(Calendar.MONTH) + 1);
		date.setPday(cal.get(Calendar.DATE));
		
		cal.setTime(dto.getEdate());
		date.setEyear(cal.get(Calendar.YEAR));
		date.setEmonth(cal.get(Calendar.MONTH) + 1);
		date.setEday(cal.get(Calendar.DATE));
		
		model.addAttribute("dateBean", date);
		model.addAttribute("hashs", hashs);
		model.addAttribute("detail", dto);
		return "playboard/playboardUpdate";
	}
	
	// 업데이트 
	@RequestMapping(value="updatePlayAf.do", method={RequestMethod.GET,RequestMethod.POST})
	public String playboardUpdateAf(PlayboardDto pdto, PlayboardDateBean datebean, PlayboardHashTagDto hash,
				@RequestParam(value = "fileload", required = false)MultipartFile fileload, HttpServletRequest req) {
		
		System.out.println(pdto.toString());
		System.out.println(datebean.toString());
		System.out.println(hash.toString());		
		hash.setBoard_seq(pdto.getSeq());
		// Date형식으로 변환
		Date pdate = DateUtil.toDate(datebean.getPyear(), datebean.getPmonth(), datebean.getPday());
		Date edate = DateUtil.toDate(datebean.getEyear(), datebean.getEmonth(), datebean.getEday());
		pdto.setPdate(pdate);
		pdto.setEdate(edate);
		
		// 썸네일 첨부파일의 원본 파일명
		String filename = ""; 

		if(fileload.isEmpty()) {
			filename = pdto.getFilename();
			System.out.println("수정파일 없음");
		}else {
			filename = fileload.getOriginalFilename();
			System.out.println("파일명: " + filename);
			// 첨부파일의 파일 유형 얻어내기
			int index = filename.lastIndexOf(".");
			String filetype = filename.substring(index);
			System.out.println("썸네일 파일 유형 : "+filetype);
			
			// 파일명 중복방지를 위한 UUID 사용
			filename = UUID.randomUUID().toString() + filetype;
			System.out.println("썸네일 UUID filename : " + filename);
			pdto.setFilename(filename);
			
			// 업로드 경로
			String fupload = req.getServletContext().getRealPath("/playboardUpload");
			System.out.println("썸네일 fupload : " + fupload);
			
			File file = new File(fupload + "/" + filename);
			
			try {
				// 실제 파일 업로드 되는 부분
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("filename : " + filename);
			
		System.out.println("update할 pdto : " + pdto.toString());
		List<PlayMemDto> memList = pService.getPlayMems(pdto.getSeq());
		if(memList.size() > 0) {
			pService.sendMsgPlayMem(memList, "수정");
		}		
		pService.updatePlayboard(pdto, hash);		
		return "redirect:/playboard.do";
	}
	
	// 삭제
	@RequestMapping(value="deletePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String playboardDelete(int seq) {
		pService.deletePlayboard(seq);
		List<PlayMemDto> memList = pService.getPlayMems(seq);
		if(memList.size() > 0) {
			pService.sendMsgPlayMem(memList, "삭제");
		}	
		return "redirect:/playboard.do";
	}
	
	// 디테일
	@RequestMapping(value="detailPlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String playboardDetail(@RequestParam("seq") int seq, Model model, HttpServletRequest req, PlayboardQnADto qnaDto) {
		
		System.out.println("디테일 볼 글의 seq : " + seq);
		
		PlayboardDto checkDto = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		//System.out.println(checkDto.toString());
	
		pService.plusReadCount(seq);
		PlayboardDto dto = pService.getPlayboardDetail(seq, checkDto);
		PlayboardCheckBean checks = pService.getPlayboardChecks(checkDto);
	//	System.out.println("디테일 : "+dto.toString());
	//	System.out.println("체크!! : " + checks.toString());
		
		PlayboardHashTagDto hashs = pService.getHashTags(seq);
		MyProfileDto profile = pService.getMakerProfile(dto.getEmail());
		
		List<PlayMemDto> partList = pService.getPlayMems(seq);
	//	System.out.println("참여자들 목록 : " + partList.toString());
		
		
		if(qnaDto.getCurrPage() == 0) {
			qnaDto = new PlayboardQnADto(seq, 1, 1, 6);
		}else {
			int totalQnA = pService.getTotalQnACount(seq);
			int end = qnaDto.getCurrPage()*6;
			if(end > totalQnA) {
				end = totalQnA;
			}
			qnaDto = new PlayboardQnADto(seq, qnaDto.getCurrPage(), ((qnaDto.getCurrPage()-1)*6)+1, end);
		}
		
		
		System.out.println("Q&A 총 개수 : " + pService.getTotalQnACount(seq));
		System.out.println("Q&A 페이징!!!!!!!!!!!!!!!! : " + qnaDto.toString());
		List<PlayboardQnADto> qnaList = pService.getPlayboardQnAList(qnaDto);
		System.out.println("QnA 리스트 : " + qnaList.toString());
	
		model.addAttribute("totalCount", pService.getTotalQnACount(seq));
		model.addAttribute("currPage", qnaDto.getCurrPage());
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("partList", partList);
		model.addAttribute("hashs", hashs);
		model.addAttribute("checks", checks);
		model.addAttribute("profile", profile);
		model.addAttribute("detail", dto);
		return "playboard/playboardDetail";
	}
	
	// 참여하기, 참여하기 취소 후 reload(새로고침)시켜주는 컨트롤러
	@RequestMapping(value="reloadDetailPlay.do",method={RequestMethod.GET,RequestMethod.POST})
	public String reloadDetail(Model model, HttpServletRequest req, @RequestParam("afterSeq") int afterSeq) {
		System.out.println("디테일 볼 글의 seq : " + afterSeq);
		
		PlayboardDto checkDto = new PlayboardDto(afterSeq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		//System.out.println(checkDto.toString());
		
		PlayboardDto dto = pService.getPlayboardDetail(afterSeq, checkDto);
		PlayboardCheckBean checks = pService.getPlayboardChecks(checkDto);
		System.out.println("디테일 : "+dto.toString());
		
		PlayboardHashTagDto hashs = pService.getHashTags(afterSeq);
		MyProfileDto profile = pService.getMakerProfile(dto.getEmail());
		
		model.addAttribute("hashs", hashs);
		model.addAttribute("checks", checks);
		model.addAttribute("profile", profile);
		model.addAttribute("detail", dto);
		return "playboard/playboardDetail";
	}
	
	// 모임 참여
	@RequestMapping(value="participatePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String participatePlay(int seq, HttpServletRequest req, RedirectAttributes redirectAttr) {
		
		PlayboardDto partMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("모임 참가자 : " + partMem.toString());
		
		pService.participate(seq, partMem);
		redirectAttr.addAttribute("seq", seq);
		String content = "증가";
		pService.sendMsgPlayMaker(seq, content);
		return "redirect:/detailPlay.do";
	}
	
	// 참여 취소
	@RequestMapping(value="cancelPartPlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String unParticipatePlay(int seq, HttpServletRequest req, RedirectAttributes redirectAttr) {
		
		PlayboardDto calceltMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("모임 취소자 : " +  calceltMem.toString());
		
		pService.unParticipate(seq, calceltMem);
		redirectAttr.addAttribute("seq", seq);
		String content = "감소";
		pService.sendMsgPlayMaker(seq, content);
		return "redirect:/detailPlay.do";
	}
	
	// 모임 좋아요
	@ResponseBody
	@RequestMapping(value="likePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String likePlay(int seq, HttpServletRequest req) {
		PlayboardDto likeMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("좋아요 누른 사람 정보 : " + likeMem.toString());
		pService.like(seq, likeMem);
		
		PlayboardDto dto = pService.getPlayboardDetail(seq, likeMem);
		String likeCount = dto.getLikecount()+"";
		System.out.println("likcount = "+likeCount);
		return likeCount;			
		
	}
	
	// 모임 좋아요 취소
	@ResponseBody
	@RequestMapping(value="unLikePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String unLikePlay(int seq, HttpServletRequest req) {		
		PlayboardDto unLikeMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("좋아요 취소하는 사람 정보 : " + unLikeMem.toString());		
		pService.unlike(seq, unLikeMem);
		
		PlayboardDto dto = pService.getPlayboardDetail(seq, unLikeMem);
		String likeCount = dto.getLikecount()+"";
		System.out.println("likcount = "+likeCount);
		return likeCount;
	}
	/*
	@RequestMapping(value="likePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String likePlay(int seq, HttpServletRequest req, RedirectAttributes redirectAttr) {
		PlayboardDto likeMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("좋아요 누른 사람 정보 : " + likeMem.toString());
		
		pService.like(seq, likeMem);
		redirectAttr.addAttribute("afterSeq", seq);
		return "redirect:/reloadDetailPlay.do";
	}
	
	@RequestMapping(value="unLikePlay.do", method={RequestMethod.GET,RequestMethod.POST})
	public String unLikePlay(int seq, HttpServletRequest req, RedirectAttributes redirectAttr) {		
		PlayboardDto unLikeMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		System.out.println("좋아요 취소하는 사람 정보 : " + unLikeMem.toString());
		
		pService.unlike(seq, unLikeMem);
		redirectAttr.addAttribute("afterSeq", seq);
		return "redirect:/reloadDetailPlay.do";
	}
	*/
	
	// 모임 신고
	@RequestMapping(value="report.do", method={RequestMethod.GET,RequestMethod.POST})
	public String reportPlay(int seq, HttpServletRequest req, Model model) {
		PlayboardDto reportMem = new PlayboardDto(seq, ((MemberDto)req.getSession().getAttribute("login")).getEmail());
		//System.out.println(checkDto.toString());
		
		PlayboardDto dto = pService.getPlayboardDetail(seq, reportMem);
		
		model.addAttribute("detail", dto);
		return "playboard/report";
	}
	/*
	@ResponseBody
	@RequestMapping(value="reportAf.do", method={RequestMethod.GET,RequestMethod.POST})
	public void reportPlayAf(ReportDto reportDto) {
		System.out.println(reportDto.toString());
		
		if(reportDto.getReason().equals("기타")) {
			reportDto.setReason("기타 사유: "+reportDto.getReasonTxt());
		}
		
		pService.reportPlay(reportDto);
	}
	*/

	@RequestMapping(value="reportAf.do", method={RequestMethod.GET,RequestMethod.POST})
	public void reportPlayAf(ReportDto reportDto) {
		System.out.println(reportDto.toString());
		
		if(reportDto.getReason().equals("기타")) {
			reportDto.setReason("기타 사유: "+reportDto.getReasonTxt());
		}
		
		pService.reportPlay(reportDto);
		
	}
	
	
	// Q&A 작성
	@RequestMapping(value="insertQnA.do", method={RequestMethod.GET,RequestMethod.POST})
	public String insertQnA(PlayboardQnADto qna, RedirectAttributes redirectAttr) {
		System.out.println(qna.toString());
		redirectAttr.addAttribute("seq", qna.getBoard_seq());
		pService.insertPlayboardQnA(qna);
		return "redirect:/detailPlay.do";
	}
	
	// Q&A 답변 작성
	
	@RequestMapping(value="insertQnAReply.do", method={RequestMethod.GET,RequestMethod.POST})
	public String insertQnAReply(PlayboardQnADto qna, RedirectAttributes redirectAttr) {
		System.out.println(qna.toString());
		redirectAttr.addAttribute("seq", qna.getBoard_seq());
		pService.insertPlayboardQnAReply(qna);
		return "redirect:/detailPlay.do";
	}

	
}
