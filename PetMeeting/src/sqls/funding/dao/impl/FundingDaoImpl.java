package com.petmeeting.joy.funding.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.funding.dao.FundingDao;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingParam;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.FundingmemDto;
import com.petmeeting.joy.login.model.MemberDto;

@Repository
public class FundingDaoImpl implements FundingDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns = "funding.";
	
	//펀딩등록
	@Override
	public boolean addFunding(FundingDto dto) {
		int n = sqlSession.insert(ns + "addFunding", dto);
		
		return n>0? true:false;
	}

	@Override
	public List<FundingDto> fundingList(FundingParam param) {
		return sqlSession.selectList(ns+"fundingList" , param);
	}

	
	 @Override 
	 public FundingDto fundingDetail(int seq){ 
		 return sqlSession.selectOne(ns + "fundingDetail", seq); 
	  }
	 
	
	@Override
	public MemberDto fundingMem(String email) {
		return sqlSession.selectOne(ns + "fundingmem", email);
	}

	

	@Override
	public int isFundlike(FundinglikeBean bean) {
		return sqlSession.selectOne(ns + "isFundlike", bean);
	}
	
	
	
	@Override
	public boolean Fundlike(FundingDto dto) {
		int n = sqlSession.insert(ns+ "Fundlike", dto);
		return n>0?true:false;
	}

	@Override
	public boolean FundNO(FundingDto dto) {
		int n = sqlSession.delete(ns + "FundNO", dto);
		return n>0?true:false;
	}


	@Override
	public boolean addFundMem(FundingmemDto mem) {
		int n = sqlSession.insert(ns +"addFundMem", mem);
		return n>0?true:false;
	}

	@Override
	public void personCount(int board_seq) {
		sqlSession.insert(ns + "personCount", board_seq);
	}

	@Override
	public void likeCountUp(int seq) {
		sqlSession.update(ns + "likeCountUp", seq);
		
	}

	@Override
	public void likeCounDown(int seq) {
		sqlSession.update(ns + "likeCountDown", seq);
		
	}

	@Override
	public void readcount(int seq) {
		sqlSession.update(ns + "readcount", seq);
	}

	@Override
	public int isfunding(FundingmemDto mem) {
		return sqlSession.selectOne(ns + "isfunding", mem);
	}

	@Override
	public int sumfund(int board_seq) {
		return sqlSession.selectOne(ns + "sumfund", board_seq);
	}

	@Override
	public void cPrice(FundingmemDto mem) {
		sqlSession.update(ns+ "cPrice", mem);
		
	}

	@Override
	public boolean fundingUpdate(FundingDto dto) {
		int n = sqlSession.update(ns + "fundingUpdate", dto);
		return n>0?true:false;
	}

	@Override
	public boolean fundingDelete(FundingDto dto) {
		int n = sqlSession.delete(ns + "fundingDelete", dto);
		return n>0?true:false;
	}

	@Override
	public boolean addfundingSta(FundingStaDto sta) {
		int n = sqlSession.insert(ns + "addfundingSta", sta);
		return n>0?true:false;
	}

	@Override
	public FundingStaDto fundingStaDetail(int seq) {
		return sqlSession.selectOne(ns +"fundingStaDetail", seq);
	}

	@Override
	public int fundingStacheck(int seq) {
		return sqlSession.selectOne(ns + "fundingStacheck", seq);
	}

}
