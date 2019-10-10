package com.petmeeting.joy.login.service;

import com.petmeeting.joy.login.model.MemberDto;

public interface MemberService {

	public void addMember(MemberDto mem);
	
	public MemberDto loginCheck(MemberDto mem);
	
}
