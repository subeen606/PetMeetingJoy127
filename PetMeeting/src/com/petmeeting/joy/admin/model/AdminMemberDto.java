package com.petmeeting.joy.admin.model;

import java.io.Serializable;

public class AdminMemberDto implements Serializable {
	
	private String email;	// 이메일
	private String pwd;	// 비밀번호
	private String nickname;	// 닉네임
	private String name;	// 이름
	private String postcode;	// 우편번호
	private String address;		// 주소
	private String address_detail;	// 상세주소
	private String address_refer;	// 상세주소 참고항목
	private String phone;	// 연락처
	private int totalpoint;	// 누적포인트
	private int point;	// 현재포인트
	private int s_point;	// 스토어적립금
	private int reportcount;	//신고수
	private int auth;	// 회원구분 : 1 일반회원 3 본인인증회원 5 블랙리스트회원 8 관리자
	
	private String grade_name;	// 등급명
	
	private MyProfileDto myProfile;
	private PetProfileDto petProfile;
	
	private boolean leavememberCheck;	// leavemember 테이블에 있으면 true 없으면 false
	private boolean myProfileCheck;
	private boolean petProfileCheck;
	
	public AdminMemberDto() {
	}

	public AdminMemberDto(String email, String pwd, String nickname, String name, String postcode, String address,
			String address_detail, String address_refer, String phone, int totalpoint, int point, int s_point,
			int reportcount, int auth, String grade_name, MyProfileDto myProfile, PetProfileDto petProfile) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.name = name;
		this.postcode = postcode;
		this.address = address;
		this.address_detail = address_detail;
		this.address_refer = address_refer;
		this.phone = phone;
		this.totalpoint = totalpoint;
		this.point = point;
		this.s_point = s_point;
		this.reportcount = reportcount;
		this.auth = auth;
		this.grade_name = grade_name;
		this.myProfile = myProfile;
		this.petProfile = petProfile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_refer() {
		return address_refer;
	}

	public void setAddress_refer(String address_refer) {
		this.address_refer = address_refer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotalpoint() {
		return totalpoint;
	}

	public void setTotalpoint(int totalpoint) {
		this.totalpoint = totalpoint;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getS_point() {
		return s_point;
	}

	public void setS_point(int s_point) {
		this.s_point = s_point;
	}

	public int getReportcount() {
		return reportcount;
	}

	public void setReportcount(int reportcount) {
		this.reportcount = reportcount;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public MyProfileDto getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MyProfileDto myProfile) {
		this.myProfile = myProfile;
	}

	public PetProfileDto getPetProfile() {
		return petProfile;
	}

	public void setPetProfile(PetProfileDto petProfile) {
		this.petProfile = petProfile;
	}

	public boolean isLeavememberCheck() {
		return leavememberCheck;
	}

	public void setLeavememberCheck(boolean leavememberCheck) {
		this.leavememberCheck = leavememberCheck;
	}

	public boolean isMyProfileCheck() {
		return myProfileCheck;
	}

	public void setMyProfileCheck(boolean myProfileCheck) {
		this.myProfileCheck = myProfileCheck;
	}

	public boolean isPetProfileCheck() {
		return petProfileCheck;
	}

	public void setPetProfileCheck(boolean petProfileCheck) {
		this.petProfileCheck = petProfileCheck;
	}

	@Override
	public String toString() {
		return "AdminMemberDto [email=" + email + ", pwd=" + pwd + ", nickname=" + nickname + ", name=" + name
				+ ", postcode=" + postcode + ", address=" + address + ", address_detail=" + address_detail
				+ ", address_refer=" + address_refer + ", phone=" + phone + ", totalpoint=" + totalpoint + ", point="
				+ point + ", s_point=" + s_point + ", reportcount=" + reportcount + ", auth=" + auth + ", grade_name="
				+ grade_name + ", myProfile=" + myProfile + ", petProfile=" + petProfile + ", leavememberCheck="
				+ leavememberCheck + ", myProfileCheck=" + myProfileCheck + ", petProfileCheck=" + petProfileCheck
				+ "]";
	}

	
}
