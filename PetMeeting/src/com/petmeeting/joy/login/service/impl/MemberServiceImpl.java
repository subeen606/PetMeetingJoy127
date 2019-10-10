package com.petmeeting.joy.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.login.dao.MemberDao;
import com.petmeeting.joy.login.model.MemberDto;
import com.petmeeting.joy.login.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memDao;

	@Override
	public void addMember(MemberDto mem) {
		memDao.addMember(mem);
	}

	@Override
	public MemberDto loginCheck(MemberDto mem) {
		return memDao.loginCheck(mem);
	}
	
}
