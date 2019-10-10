package com.petmeeting.joy.playboard.service;

import java.util.List;

import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.playboard.model.PlayboardCheckBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.model.ReportDto;

public interface PlayboardService {
	
	public void insertPlayboard(PlayboardDto pdto, PlayboardHashTagDto hash);
	public void insertPoint(PlayboardDto pdto);
	
	public List<PlayboardDto> getPlayboardList(PlayboardSearchBean search);
	public int getTotalRowCount(PlayboardSearchBean search);
	
	public PlayboardDto getPlayboardDetail(int seq, PlayboardDto pdto);
	public MyProfileDto getMakerProfile(String email);
	public PlayboardCheckBean getPlayboardChecks(PlayboardDto pdto);
	public void plusReadCount(int seq);	
	
	public void participate(int seq, PlayboardDto pdto);
	public void unParticipate(int seq, PlayboardDto pdto);
	
	public void like(int seq, PlayboardDto pdto);
	
	public void unlike(int seq, PlayboardDto pdto);
	
	public void reportPlay(ReportDto rdto);
	
	public PlayboardHashTagDto getHashTags(int seq);
	
	public List<PlayboardDto> getHashTagList(PlayboardSearchBean search);
	public int getHashTagRowCount(PlayboardSearchBean search);
	
	public void updatePlayboard(PlayboardDto pdto, PlayboardHashTagDto hash);

	public void deletePlayboard(int seq);
	
	public List<PlayMemDto> getPlayMems(int seq);
	
	public void sendMsgPlayMem(List<PlayMemDto> memList, String content);
	
	public void sendMsgPlayMaker(int seq, String content);
	
	public void insertPlayboardQnA(PlayboardQnADto qna);
	
	public List<PlayboardQnADto> getPlayboardQnAList(PlayboardQnADto qna);

	public int getTotalQnACount(int seq);
	
	public void insertPlayboardQnAReply(PlayboardQnADto qna);
}
