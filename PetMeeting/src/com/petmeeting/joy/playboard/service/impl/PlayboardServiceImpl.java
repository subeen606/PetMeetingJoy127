package com.petmeeting.joy.playboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.login.model.MemberDto;
import com.petmeeting.joy.playboard.Util.DateUtil;
import com.petmeeting.joy.playboard.dao.playboardDao;
import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.playboard.model.PlayboardCheckBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.model.ReportDto;
import com.petmeeting.joy.playboard.service.PlayboardService;

@Service
public class PlayboardServiceImpl implements PlayboardService{
	
	@Autowired
	playboardDao pdao;

	@Override
	public void insertPlayboard(PlayboardDto pdto, PlayboardHashTagDto hash) {
		pdao.insertPlayboard(pdto);
		
		String hash1 = hash.getHash1().replace("#", "");
		String hash2 = hash.getHash2().replace("#", "");
		String hash3 = hash.getHash3().replace("#", "");
		String hash4 = hash.getHash4().replace("#", "");
		String hash5 = hash.getHash5().replace("#", "");
		hash.setHash1(hash1);
		hash.setHash2(hash2);
		hash.setHash3(hash3);
		hash.setHash4(hash4);
		hash.setHash5(hash5);
		
		pdao.insertHashTag(hash);
	}

	@Override
	public void insertPoint(PlayboardDto pdto) {
		if(pdao.checkPoint() <= 3) {
			pdao.insertPoint(pdto);
			pdao.plusPoint(pdto);
		}		
	}

	@Override
	public List<PlayboardDto> getPlayboardList(PlayboardSearchBean search) {
		
		List<PlayboardDto> all = pdao.getPlayboardList(search);
		List<PlayboardDto> checkList = new ArrayList<PlayboardDto>();
		
		for (PlayboardDto dto : all) {
			if(DateUtil.isEnd(dto.getEdate()) == true) {	// 마감
				dto.setDeadlineCheck(true);
			}else if(DateUtil.isEnd(dto.getEdate()) == false) {
				dto.setDeadlineCheck(false);
			}
			
			if(dto.getPeople() == dto.getPersoncount()) {	// 모집인원이 다 찬경우
				dto.setFullCheck(true);
			}else {
				dto.setFullCheck(false);
			}
			
			checkList.add(dto);
		}
		return checkList;
	}

	@Override
	public int getTotalRowCount(PlayboardSearchBean search) {
		return pdao.getTotalRowCount(search);
	}

	@Override
	public PlayboardDto getPlayboardDetail(int seq, PlayboardDto pdto) {		
		PlayboardDto detail = pdao.getPlayboardDetail(seq);
		if(DateUtil.isEnd(detail.getEdate()) == true) {	// 마감
			detail.setDeadlineCheck(true);
		}else if(DateUtil.isEnd(detail.getEdate()) == false) {
			detail.setDeadlineCheck(false);
		}
		
		if(detail.getPeople() == detail.getPersoncount()) {	// 모집인원이 다 찬경우
			detail.setFullCheck(true);
		}else {
			detail.setFullCheck(false);
		}			
		return detail;
	}

	@Override
	public MyProfileDto getMakerProfile(String email) {
		return pdao.getMakerProfile(email);
	}

	@Override
	public PlayboardCheckBean getPlayboardChecks(PlayboardDto pdto) {
		PlayboardCheckBean checks = new PlayboardCheckBean();
		checks.setLikeCheck(pdao.likeCheck(pdto)>0?true:false);
		checks.setPartCheck(pdao.partCheck(pdto)>0?true:false);
		checks.setReportCheck(pdao.reportCheck(pdto)>0?true:false);
		return checks;
	}

	@Override
	public void plusReadCount(int seq) {
		pdao.plusReadCount(seq);
	}

	@Override
	public void participate(int seq, PlayboardDto pdto) {
		pdao.insertPlayMem(pdto);
		pdao.plusPersonCount(seq);
	}

	@Override
	public void unParticipate(int seq, PlayboardDto pdto) {
		pdao.deletePlayMem(pdto);
		pdao.minusPersonCount(seq);
	}

	@Override
	public void like(int seq, PlayboardDto pdto) {
		pdao.insertPlayLike(pdto);
		pdao.plusLikeCount(seq);
	}

	@Override
	public void unlike(int seq, PlayboardDto pdto) {
		pdao.deletePlayLike(pdto);
		pdao.minusLikeCount(seq);
	}

	@Override
	public void reportPlay(ReportDto rdto) {
		pdao.insertPlayReport(rdto);
		pdao.plusReportCount(rdto);
	}

	@Override
	public PlayboardHashTagDto getHashTags(int seq) {
		return pdao.getHashTags(seq);
	}

	@Override
	public List<PlayboardDto> getHashTagList(PlayboardSearchBean search) {
		return pdao.getHashTagList(search);				
	}

	@Override
	public int getHashTagRowCount(PlayboardSearchBean search) {
		return pdao.getHashTagRowCount(search);
	}

	@Override
	public void updatePlayboard(PlayboardDto pdto, PlayboardHashTagDto hash) {
		pdao.updatePlayboard(pdto);
		String hash1 = hash.getHash1().replace("#", "");
		String hash2 = hash.getHash2().replace("#", "");
		String hash3 = hash.getHash3().replace("#", "");
		String hash4 = hash.getHash4().replace("#", "");
		String hash5 = hash.getHash5().replace("#", "");
		hash.setHash1(hash1);
		hash.setHash2(hash2);
		hash.setHash3(hash3);
		hash.setHash4(hash4);
		hash.setHash5(hash5);
		pdao.updateHashTag(hash);
	}

	@Override
	public void deletePlayboard(int seq) {
		pdao.deletePlayboard(seq);
	}

	@Override
	public List<PlayMemDto> getPlayMems(int seq) {
		return pdao.getPlayMems(seq);
	}

	@Override
	public void sendMsgPlayMem(List<PlayMemDto> memList, String content) {
		List<MsgDto> msgList = new ArrayList<MsgDto>();
		
		for (PlayMemDto mem : memList) {
			MsgDto msg = new MsgDto();
			PlayboardDto pdto = pdao.getPlayboardDetail(mem.getPlay_seq());
			msg.setTo_email(mem.getEmail());
			msg.setFrom_email("admin");
			msg.setContent(mem.getNickname()+"님, 참여중인 모임 <"+ pdto.getTitle()+ "> 의 내용이 주최자에 의해 "+content+"되었습니다. 확인하세요");
			
			msgList.add(msg);
		}
		pdao.sendMsgPlayMem(msgList);
		pdao.revMsgPlayMem(msgList);
		System.out.println(msgList.toString());
	}

	@Override
	public void sendMsgPlayMaker(int seq, String content) {
		PlayboardDto pdto = pdao.getPlayboardDetail(seq);
		MsgDto msg = new MsgDto();
		msg.setTo_email(pdto.getEmail());
		msg.setContent(pdto.getNickname()+"님, 주최하신 모임   <"+ pdto.getTitle()+ "> 의 참여인원이 "+ content + "하였습니다. 확인하세요");
		pdao.sendMsgPlayMaker(msg);
		pdao.revMsgPlayMaker(msg);
	}

	@Override
	public void insertPlayboardQnA(PlayboardQnADto qna) {
		pdao.insertPlayboardQnA(qna);
	}

	@Override
	public List<PlayboardQnADto> getPlayboardQnAList(PlayboardQnADto qna) {
		List<PlayboardQnADto> list = pdao.getPlayboardQnAList(qna);
		
		for (PlayboardQnADto dto : list) {
			int count = pdao.QnAReplyCheck(qna.getSeq());
			if(count > 0) {
				dto.setReplyCheck(true);
			}
		}
		
		return list;
	}

	@Override
	public int getTotalQnACount(int seq) {
		return pdao.getTotalQnACount(seq);
	}

	@Override
	public void insertPlayboardQnAReply(PlayboardQnADto qna) {
		pdao.insertPlayboardQnAReply(qna);
	}

	
	
}
