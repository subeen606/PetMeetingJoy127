package com.petmeeting.joy.playboard.dao;

import java.util.List;

import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.model.ReportDto;

public interface playboardDao {
	public void insertPlayboard(PlayboardDto pdto);
	public void insertHashTag(PlayboardHashTagDto hash);
	
	public int checkPoint();
	
	public void insertPoint(PlayboardDto pdto);
	public void plusPoint(PlayboardDto pdto);
	
	public List<PlayboardDto> getPlayboardList(PlayboardSearchBean search);
	
	public int getTotalRowCount(PlayboardSearchBean search);
	
	public PlayboardDto getPlayboardDetail(int seq);
	public MyProfileDto getMakerProfile(String email);
	
	public void plusReadCount(int seq);
	
	public int likeCheck(PlayboardDto pdto);
	public int partCheck(PlayboardDto pdto);
	public int reportCheck(PlayboardDto pdto);
	
	public void insertPlayMem(PlayboardDto pdto);
	public void plusPersonCount(int seq);
	
	public void deletePlayMem(PlayboardDto pdto);
	public void minusPersonCount(int seq);
	
	public void insertPlayLike(PlayboardDto pdto);
	public void plusLikeCount(int seq);
	
	public void deletePlayLike(PlayboardDto pdto);
	public void minusLikeCount(int seq);
	
	public void insertPlayReport(ReportDto rdto);
	public void plusReportCount(ReportDto rdto);
	
	public PlayboardHashTagDto getHashTags(int seq);
	
	public List<PlayboardDto> getHashTagList(PlayboardSearchBean search);
	public int getHashTagRowCount(PlayboardSearchBean search);
	
	public void updatePlayboard(PlayboardDto pdto);
	public void updateHashTag(PlayboardHashTagDto hash);
	
	public void deletePlayboard(int seq);
	
	public List<PlayMemDto> getPlayMems(int seq);
	
	public void sendMsgPlayMem(List<MsgDto> msgList);
	public void revMsgPlayMem(List<MsgDto> msgList);
	
	public void sendMsgPlayMaker(MsgDto msgDto);
	public void revMsgPlayMaker(MsgDto msgDto);
	
	public void insertPlayboardQnA(PlayboardQnADto qna);
	
	public List<PlayboardQnADto> getPlayboardQnAList(PlayboardQnADto qna);
	
	public int getTotalQnACount(int seq);
	
	public void insertPlayboardQnAReply(PlayboardQnADto qna);
	
	public int QnAReplyCheck(int seq);


}
