package com.petmeeting.joy.admin.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String namespace = "Admin.";
	
	@Override
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search) {
		return sqlSession.selectList(namespace+"getPlayboardList", search);
	}

	@Override
	public int getPlayboardTotalRowCount(PlayboardSearchBean search) {
		return sqlSession.selectOne(namespace+"getTotalRowCount", search);
	}

	@Override
	public void deletePlayboard(int seq) {
		sqlSession.delete(namespace+"deletePlayboard", seq);
	}

	@Override
	public void deletePlayMem(int seq) {
		sqlSession.delete(namespace+"deletePlayMem", seq);
	}

	@Override
	public void deletePlayboardQnA(int seq) {
		sqlSession.delete(namespace+"deletePlayboardQnA", seq);
	}
	
	/*funding*/
	
	@Override
	public List<FundingDto> getFundingList(fundingBean fbean) {	
		return sqlSession.selectList(namespace + "getFundingList", fbean);
	}

	@Override
	public int getFundingCount(fundingBean fbean) {
		return sqlSession.selectOne(namespace + "getFundingCount", fbean);
	}

	@Override
	public PlayboardDto getPlayboardDetail(int seq) {
		return sqlSession.selectOne(namespace+"getPlayboardDetail", seq);
	}

}
