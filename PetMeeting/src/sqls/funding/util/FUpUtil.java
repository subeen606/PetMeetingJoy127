package com.petmeeting.joy.funding.util;

import java.util.Date;

public class FUpUtil {
	
	// f = myfile.txt => f.indexOf('.') 을 쓰면 : 6 이 나옴
	// f.substring( 6 ) => .txt 가 나옴  (이건 시작위치가 6인데 마지막을 안정해줬기때문에 끝까지나옴)
	// f.substring(0,6) => myfile 이 나옴 (0부터 6전까지)
	
	//즉, 1)점이있는 위치를 찾는다 2)확장자명 3)파일명 얻어올 수 있다.
	
	
	//<파일명 얻어올수 있는 함수 제작>
	
	// myfile.txt 라는 것이 들어왔다 ---> 2131453.txt로 바꿔주기(타임으로)
	                          //원본 파일명
	public static String getNewFileName(String f) {
		String filename ="";
		String fpost="";
		
		if(f.indexOf('.') >= 0) {//.가 0부터 시작할수도있으니까 >= 로 써주기
			fpost = f.substring(f.indexOf('.')); //이렇게 하면 확장자명이 나옴(.txt)
			filename = new Date().getTime() + fpost; // 123123.txt(숫자로 변형후 확장자명을 붙임)
					
		}else {//확장자명이 없을 때	
			filename = new Date().getTime() +".back"; //확장자명없을때 마음대로 붙여주기
		}
			return filename;
	}
}
