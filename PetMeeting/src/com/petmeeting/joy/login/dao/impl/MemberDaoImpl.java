package com.petmeeting.joy.login.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.login.dao.MemberDao;
import com.petmeeting.joy.login.model.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession sqlSession;
	
	String ns = "Member.";

	@Override
	public void addMember(MemberDto mem) {
		sqlSession.insert(ns + "addMember", mem);
	}

	@Override
	public MemberDto loginCheck(MemberDto mem) {
		return sqlSession.selectOne(ns + "loginCheck", mem);
	}
	
	
	
}
