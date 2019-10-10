package com.petmeeting.joy.login.dao;

import com.petmeeting.joy.login.model.MemberDto;

public interface MemberDao {
	
	public void addMember(MemberDto mem);
	
	public MemberDto loginCheck(MemberDto mem);
	
}
