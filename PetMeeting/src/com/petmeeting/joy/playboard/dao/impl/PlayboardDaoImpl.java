package com.petmeeting.joy.playboard.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.playboard.dao.playboardDao;
import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.MyProfileDto;
import com.petmeeting.joy.playboard.model.PlayMemDto;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardHashTagDto;
import com.petmeeting.joy.playboard.model.PlayboardQnADto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;
import com.petmeeting.joy.playboard.model.ReportDto;

@Repository
public class PlayboardDaoImpl implements playboardDao{
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String namespace = "Playboard.";

	@Override
	public void insertPlayboard(PlayboardDto pdto) {		
		sqlSession.insert(namespace+"insertPlayboard", pdto);		
	}

	@Override
	public void insertHashTag(PlayboardHashTagDto hash) {
		sqlSession.insert(namespace+"insertHashTag", hash);
	}

	@Override
	public int checkPoint() {
		return sqlSession.selectOne(namespace+"checkPoint");
	}

	@Override
	public void insertPoint(PlayboardDto pdto) {
		sqlSession.insert(namespace+"insertPoint", pdto);
	}

	@Override
	public void plusPoint(PlayboardDto pdto) {
		sqlSession.update(namespace+"plusPoint", pdto);
	}

	@Override
	public List<PlayboardDto> getPlayboardList(PlayboardSearchBean search) {
		List<PlayboardDto> list = sqlSession.selectList(namespace + "getPlayboardList", search);
		return list;
	}

	@Override
	public int getTotalRowCount(PlayboardSearchBean search) {
		return sqlSession.selectOne(namespace + "getTotalRowCount", search);
	}

	@Override
	public PlayboardDto getPlayboardDetail(int seq) {
		return sqlSession.selectOne(namespace+"getPlayboardDetail", seq);
	}

	@Override
	public MyProfileDto getMakerProfile(String email) {
		return sqlSession.selectOne(namespace+"getMakerProfile", email);
	}

	@Override
	public void plusReadCount(int seq) {
		sqlSession.update(namespace+"plusReadCount", seq);
	}

	@Override
	public int likeCheck(PlayboardDto pdto) {
		int count = sqlSession.selectOne(namespace+"likeCheck", pdto);
	//	System.out.println("likeCheck count = " + count);
		return count;
	}

	@Override
	public int partCheck(PlayboardDto pdto) {
		int count = sqlSession.selectOne(namespace+"partCheck", pdto);
	//	System.out.println("partCheck count = " + count);
		return count;
	}

	@Override
	public int reportCheck(PlayboardDto pdto) {
		int count = sqlSession.selectOne(namespace+"reportCheck", pdto);
		return count;
	}

	@Override
	public void insertPlayMem(PlayboardDto pdto) {
		sqlSession.insert(namespace + "insertPlayMem", pdto);
	}

	@Override
	public void plusPersonCount(int seq) {
		sqlSession.update(namespace+"plusPersonCount", seq);
	}
	

	@Override
	public void deletePlayMem(PlayboardDto pdto) {
		sqlSession.delete(namespace+"deletePlayMem", pdto);
	}

	@Override
	public void minusPersonCount(int seq) {
		sqlSession.update(namespace+"minusPersonCount", seq);
	}

	@Override
	public void insertPlayLike(PlayboardDto pdto) {
		sqlSession.insert(namespace+"insertPlayLike", pdto);
	}

	@Override
	public void plusLikeCount(int seq) {
		sqlSession.update(namespace+"plusLikeCount", seq);
	}

	@Override
	public void deletePlayLike(PlayboardDto pdto) {
		sqlSession.delete(namespace+"deletePlayLike", pdto);
	}

	@Override
	public void minusLikeCount(int seq) {
		sqlSession.update(namespace+"minusLikeCount", seq);
	}

	@Override
	public void insertPlayReport(ReportDto rdto) {
		sqlSession.insert(namespace+"insertPlayReport", rdto);
	}

	@Override
	public void plusReportCount(ReportDto rdto) {
		sqlSession.update(namespace+"plusReportCount", rdto);
	}

	@Override
	public PlayboardHashTagDto getHashTags(int seq) {
		PlayboardHashTagDto hashs = sqlSession.selectOne(namespace+"getHashTags", seq);
		return hashs;
	}

	@Override
	public List<PlayboardDto> getHashTagList(PlayboardSearchBean search) {
		List<PlayboardDto> list = sqlSession.selectList(namespace + "getHashTagList", search);
		return list;
	}

	@Override
	public int getHashTagRowCount(PlayboardSearchBean search) {
		return sqlSession.selectOne(namespace+"getHashTagRowCount", search);
	}

	@Override
	public void updatePlayboard(PlayboardDto pdto) {
		sqlSession.update(namespace+"updatePlayboard", pdto);
	}

	@Override
	public void updateHashTag(PlayboardHashTagDto hash) {
		sqlSession.update(namespace+"updateHashTag", hash);
	}

	@Override
	public void deletePlayboard(int seq) {
		sqlSession.update(namespace+"deletePlayboard", seq);
	}

	@Override
	public List<PlayMemDto> getPlayMems(int seq) {
		return sqlSession.selectList(namespace+"getPlayMems", seq);
	}

	@Override
	public void sendMsgPlayMem(List<MsgDto> msgList) {
		sqlSession.insert(namespace+"sendMsgPlayMem", msgList);
	}

	@Override
	public void revMsgPlayMem(List<MsgDto> msgList) {
		sqlSession.insert(namespace+"revMsgPlayMem", msgList);
	}

	@Override
	public void sendMsgPlayMaker(MsgDto msgDto) {
		sqlSession.insert(namespace+"sendMsgPlayMaker", msgDto);
	}

	@Override
	public void revMsgPlayMaker(MsgDto msgDto) {
		sqlSession.insert(namespace+"revMsgPlayMaker", msgDto);
	}

	@Override
	public void insertPlayboardQnA(PlayboardQnADto qna) {
		sqlSession.insert(namespace+"insertPlayboardQnA", qna);
	}

	@Override
	public List<PlayboardQnADto> getPlayboardQnAList(PlayboardQnADto qna) {
		return sqlSession.selectList(namespace+"getPlayboardQnAList", qna);
	}

	@Override
	public int getTotalQnACount(int seq) {
		return sqlSession.selectOne(namespace+"getTotalQnACount", seq);
	}

	@Override
	public void insertPlayboardQnAReply(PlayboardQnADto qna) {
		sqlSession.insert(namespace+"insertPlayboardQnAReply", qna);
	}

	@Override
	public int QnAReplyCheck(int seq) {
		return sqlSession.selectOne(namespace+"QnAreplyCheck", seq);
	}


	
}
