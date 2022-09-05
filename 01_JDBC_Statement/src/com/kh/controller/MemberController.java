package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

/*
 * * Controller
 * View를 통해서 요청한 기능을 처리하는 담당
 * 해당 메소드로 전달된 데이터를 VO 객체로 가공처리 한 후(첫 번째 역할) Dao 메소드 호출 시 전달(두 번째 역할)
 * Dao로부터 반환받은 결과에 따라 사용자가 보게 될 View(응답화면)을 결정 (View의 메소드 호출)
 * 
 */ 


public class MemberController {
	
	/**
	 * 사용자의 호원 추가 요청을 처리해 주는 메소드
	 * @param userId ~ hobby: 회원가입  요청 시 사용자가 입력한 값들
	 *
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, int age,
							 String email, String phone, String address, String hobby) {
		
		// 1. 전달된 데이터들을 VO 객체로 주섬주섬 담기 (== 가공하겠다)
		Member m = new Member(userId, userPwd, userName, gender, age,
							  email, phone, address, hobby);
		
		// 2. VO 객체 Dao 메소드로 넘겨 주기 (== 메소드 호출)		
		// 3. 결과 받기
		int result = new MemberDao().insertMember(m);
		// => 성공이면 1, 실패면 0
		
		// 4. 결과에 따른 응답화면 지정하기(== View단의 메소드 호출)
		if(result > 0) { // 성공했을 경우
			new MemberView().displaySuccess("회원가입에 성공했습니다.");
			
		} else { // 실패했을 경우
			new MemberView().displayFail("회원가입에 실패했습니다.");
			
		}
	}

}