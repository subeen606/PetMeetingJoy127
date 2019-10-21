package com.petmeeting.joy.admin.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.AdminMemberDto;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;

import com.petmeeting.joy.funding.model.FMsgDto;

import com.petmeeting.joy.admin.model.MemberSearchBean;
import com.petmeeting.joy.admin.model.ReportDto;

import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.MsgDto;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String namespace = "Admin.";
	
	/*playboard*/
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
	

	@Override
	public PlayboardDto getPlayboardDetail(int seq) {
		return sqlSession.selectOne(namespace+"getPlayboardDetail", seq);
	}

	@Override
	public List<BoardReportDto> getBoardReportReason(ReportDto reportDto) {
		return sqlSession.selectList(namespace+"getBoardReportReason", reportDto);
	}
		
	@Override
	public void deleteBoardReport(ReportDto reportDto) {
		sqlSession.delete(namespace+"deleteBoardReport", reportDto);		
	}

	@Override
	public void minusReportCount(ReportDto reportDto) {
		sqlSession.update(namespace+"minusReportCount", reportDto);
	}


	/* member */
	@Override
	public List<AdminMemberDto> getAllMemberList(MemberSearchBean memSearch) {
		return sqlSession.selectList(namespace + "getAllMemberList", memSearch);
	}
		
	@Override
	public int getMemberTotalCount(MemberSearchBean memSearch) {
		return sqlSession.selectOne(namespace + "getMemberTotalCount", memSearch);
	}
	
	@Override
	public AdminMemberDto getMemberDetail(String email) {
		return sqlSession.selectOne(namespace+"getMemberDetail", email);
	}
	
	@Override
	public void insertLeaveMember(List<ReportDto> leaveMemberList) {
		sqlSession.insert(namespace + "insertLeaveMember", leaveMemberList);
	}

	@Override
	public int leaveMemberCheck(String email) {
		return sqlSession.selectOne(namespace+"leaveMemberCheck", email);
	}

	@Override
	public int memberProfileCheck(String email) {
		return sqlSession.selectOne(namespace+"memberProfileCheck", email);
	}

	@Override
	public int petProfileCheck(String email) {
		return sqlSession.selectOne(namespace+"petProfileCheck", email);
	}
	
	@Override
	public List<ReportDto> getMemberReportReason(String email) {
		return sqlSession.selectList(namespace+"getMemberReportReason", email);
	}
	
	@Override
	public void deleteMemberReport(ReportDto reportDto) {
		sqlSession.delete(namespace+"deleteMemberReport", reportDto);
	}

	@Override
	public void minusMemberReportCount(ReportDto reportDto) {
		sqlSession.update(namespace+"minusMemberReportCount", reportDto);
	}

	/* funding */
	@Override
	public boolean addFunding(FundingDto dto) {
		int n = sqlSession.insert(namespace + "addFunding", dto);
		
		return n>0? true:false;
	}
	
	@Override
	public int getFundingCount(fundingBean fbean) {
		return sqlSession.selectOne(namespace + "getFundingCount", fbean);
	}

	
	@Override
	public List<FundingDto> getFundingList(fundingBean fbean) {	
		return sqlSession.selectList(namespace + "getFundingList", fbean);
	}

	
	@Override
	public boolean fundingUpdate(FundingDto dto) {
		int n = sqlSession.update(namespace + "fundingUpdate", dto);
		return n>0?true:false;
	}

	@Override
	public boolean addfundingSta(FundingStaDto sta) {
		int n = sqlSession.insert(namespace + "addfundingSta", sta);
		return n>0?true:false;
	}
	
	@Override
	public int fundingStacheck(int seq) {
		return sqlSession.selectOne(namespace + "fundingStacheck", seq);
	}

	@Override
	public void fundingDelete(int seq) {		
		sqlSession.delete(namespace + "fundingDelete" , seq);
	}

	@Override
	public void fundingStaDel(int seq) {
		sqlSession.delete(namespace + "fundingStaDel", seq);
	}

	@Override
	public FundingDto fundingDetail(int seq) {
		return sqlSession.selectOne(namespace + "adminfundingDetail", seq);
	}

	@Override
	public List<FundMemberDto> whofundingMem(int seq) {
		return sqlSession.selectList(namespace + "whofundingMem", seq);
	}

	@Override
	public void fundingMemDel(int seq) {
		sqlSession.delete(namespace + "fundingMemDel", seq);
	}

	@Override
	public void sendMsgFund(List<FMsgDto> msgList) {
		sqlSession.insert(namespace + "sendMsgFundMem", msgList);
	}

	@Override
	public void revMsgFund(List<FMsgDto> msgList) {
		sqlSession.insert(namespace + "revMsgFundMem" , msgList);
	}

	@Override
	public void deleteBoardReport(BoardReportDto reportDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void minusReportCount(BoardReportDto reportDto) {
		// TODO Auto-generated method stub
		
	}

}
