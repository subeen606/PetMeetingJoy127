package com.petmeeting.joy.funding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.funding.dao.FundingDao;
import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingParam;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.FundingmemDto;
import com.petmeeting.joy.funding.service.FundingService;
import com.petmeeting.joy.login.model.MemberDto;

@Service
public class FundingServiceImpl implements FundingService {

	@Autowired
	FundingDao funddao;
	
	@Override
	public boolean addFunding(FundingDto dto , DayBean bean) {
		
		FundingDto fundDto = new FundingDto(bean.getEmail(),
											dto.getTitle(), 
											dto.getIntro(), 
											dto.getContent(),
											dto.getThumbnail(),
											dto.getMax_price(), 
											bean.getSDate(), 
											bean.getEDate()
											);
			
		return funddao.addFunding(fundDto);
	}

	@Override
	public List<FundingDto> fundingList(String email,FundingParam param) {
		List<FundingDto> list = funddao.fundingList(param);
		List<FundingDto> flist = new ArrayList<FundingDto>();
		
		for(FundingDto fund : list) {
			
			int seq = fund.getSeq();
			
			int check = funddao.fundingStacheck(seq);
			int count = funddao.isfunding(new FundingmemDto(seq, email));
			
			if(count==1) {
				fund.setIsfunding(true);
			}else {
				fund.setIsfunding(false);
			}
			if(check==1) {
				fund.setIsfundingsta(true);
			}else {
				fund.setIsfundingsta(false);
			}
			flist.add(fund);
		}
		return flist;
		}
	
	 @Override 
	 public FundingDto fundingDetail(int seq,FundinglikeBean bean) 
	 { 
		funddao.readcount(seq);//조회수 + 1 
		FundingDto dto = funddao.fundingDetail(seq);
		FundingmemDto mem = new FundingmemDto(seq, bean.getEmail());

		System.out.println(" service로 들어온 dto : " +dto.toString());
		System.out.println(" service로 들어온 ilkebean: " + bean.toString());
		
		int n = funddao.isFundlike(bean);
		int f = funddao.isfunding(mem);
		int c = funddao.fundingStacheck(seq);
		
			System.out.println("좋아요 숫자: " + n);
			System.out.println("펀딩했는지 숫자: " + f);
			
			if(n>0) {	
				dto.setIslike(true);
			}
			if(n==0) {
			     dto.setIslike(false);
			}
			
			if(f>0) {
				dto.setIsfunding(true);
			}
			if(f==0) {
				dto.setIsfunding(false);
			}
			if(c>0) {
				dto.setIsfundingsta(true);
			}
			if(c==0) {
				dto.setIsfundingsta(false);
			}
			
			
			System.out.println("디테일가는 service dto : " +dto.toString());
			
			
		 return dto;
	 }

	 
	@Override
	public MemberDto fundingMem(String email) {
		return funddao.fundingMem(email);
	}

	@Override
	public boolean Fundlike(FundingDto dto) {
		dto = new FundingDto(
								dto.getSeq(),
								dto.getEmail()
								);
		
		return funddao.Fundlike(dto);
	}

	@Override
	public boolean FundNO(FundingDto dto) {
		dto = new FundingDto(
				dto.getSeq(),
				dto.getEmail()
				);
		
		return funddao.FundNO(dto);
	}
	

	@Override
	public boolean addFundMem(FundingmemDto mem) {
		mem = new FundingmemDto(mem.getFunding_seq(),mem.getEmail(), mem.getDonation());
		
		funddao.cPrice(mem);
		
		return funddao.addFundMem(mem);
	}

	@Override
	public void personCount(int board_seq) {
		funddao.personCount(board_seq);		
	}


	@Override
	public void likeCountUp(int seq) {
		funddao.likeCountUp(seq);
	}

	@Override
	public void likeCountDown(int seq) {
		funddao.likeCounDown(seq);
	}

	@Override
	public int isFundlike(FundinglikeBean bean) {
		return funddao.isFundlike(bean);
	}

	/*update하기위한 디테일*/
	@Override
	public FundingDto fundingUpdate(int seq) {
		return funddao.fundingDetail(seq);
	}

	@Override
	public boolean fundUpdate(FundingDto dto, DayBean bean) {
		System.out.println("서비스들어오는 dto 와 빈: " +dto.toString()+","+bean.toString());
		FundingDto fundDto = new FundingDto(
											dto.getSeq(),
											bean.getEmail(),
											dto.getTitle(), 
											dto.getIntro(), 
											dto.getContent(),
											dto.getThumbnail(),
											dto.getMax_price(), 
											bean.getSDate(), 
											bean.getEDate()
											);

		 return funddao.fundingUpdate(fundDto);
	}

	@Override
	public boolean fundingDelete(FundingDto dto) {
		return funddao.fundingDelete(dto);
	}

	@Override
	public boolean addfundingSta(FundingStaDto sta) {
		return funddao.addfundingSta(sta);
	}

	@Override
	public FundingStaDto fundingStaDetail(int seq) {
		return funddao.fundingStaDetail(seq);
	}


}
