package com.kh.controller;

import java.util.ArrayList;

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
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해 주는 메소드
	 */
	public void selectAll() {
		
		// 1. 전달받은 값들을 VO 객체로 가공
		// --> 뷰단에서 사용자로부터 입력받은 값이 없는 상태이므로 이 단계는 패스!
		
		// 2. VO 객체를 Dao단의 메소드에 매개변수로 넘기면서 요청 (호출)
		// 3. 결과 받기
		// --> Dao 메소드 호출 시 생각해 볼 것 (매개변수 X인 상태이며, return받을 값의 타입 => 회원 전체 조회: 적어도 여러 명의 회원 정보가 조회됨)
		ArrayList<Member> list = new MemberDao().selectAll();
		// => list에 조회된 회원들의 정보들이 다 담겨 있을 것
		// => 만약 조회된 회원이 없다면 list.size() == 0 또는 list.isEmpty() == true
		
		// 4. 결과에 따른 응답화면 지정
		if(list.size() == 0) { // 조회된 결과가 없을 경우
			new MemberView().displayNodata("전체 조회된 결과가 없습니다.");
		} else { // 조회 결과가 있을 경우
			new MemberView().displayList(list);

		}

	}
	
	/**
	 * 사용자의 아이디로 검색 요청을 처리하는 메소드
	 * @param userId: 검색하고자 하는 아이디 값
	 */
	public void selectByUserId(String userId) {
		
		// 1. 전달받은 값들을 VO로 가공하기
		// => 어차피 전달받은 값이 단 한 개이기 때문에 이 경우에는 패스
		
		// 2. 전달값을 Dao로 넘기면서 메소드 호출
		// 3. 결과값 받기
		// => Dao 메소드를 호출할 때 고려해야 할 것 (매개변수: userId, 리턴값의 타입: Member)
		// => 아이디 컬럼은 UNIQUE 제약조건에 의해 조회 가능하다면 무조건 1개의 값만 나옴(Arraylist가 아닌 Member로 리턴하는 이유)
		Member m = new MemberDao().selectByUserId(userId);
		// 만약 조회된 회원이 없다면 null
		
		// 4. 결과에 따른 응답 처리
		if(m == null) { // 조회 결과가 없는 경우
			new MemberView().displayNodata(userId + " 에 해당하는 검색결과가 없습니다.");
		} else { // 조회 결과가 있는 경우
			new MemberView().displayOne(m);
		}
		
	}
	
	/**
	 * 사용자의 회원명(키워드)으로 검색 요청 시 처리해 주는 메소드
	 * @param keyword: 사용자가 입력했던 검색하고자 하는 회원명(키워드)
	 */
	public void selectByUserName(String keyword) {
		
		// 1. VO 객체로 전달값들을 가공하기
		// => 어차피 전달받은 값이 한 개이기 때문에 패스
		
		// 2. Dao에 요청 시 전달값을 넘기면서 메소드 호출
		// 3. 결과 받기
		// Dao 메소드 호출 시 생각해 볼 것 (매개변수: keyword, 리턴받을 타입: (Object/Array/ArrayList) 여러 행 조회될 가능성 있고(Array/ArrayList), 몇 행이 나올지 미리 알 수 없음(ArrayList))
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) { // 검색 결과가 없는 경우
			new MemberView().displayNodata(keyword + "에 해당하는 검색결과가 없습니다.");
		} else { // 검색 결과가 있는 경우
			new MemberView().displayList(list);
		}
		
	}
	
	/**
	 * 사용자의 회원 정보 변경 요청 시 처리해 주는 메소드
	 * @param userId: 변경하고자 하는 회원의 아이디 (구분용)
	 * @param newPwd ~ newAddress: 변경할 회원 정보들(비밀번호, 이메일, 전화번소, 주소)
	 * @return
	 */
	public void updateMember(String userId, String newPwd, String newMail,
										  String newPhone, String newAddress){
		
		// 1. 요청 시 전달값들을 VO 객체로 가공하기
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newMail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 2. 전달값을 Dao의 메소드로 넘기기
		// 3. 결과 받기
		int result = new MemberDao().updateMember(m);
		// sql문 update를 쓸 거니까?
		
		// 4. 결과에 따른 응답화면을 지정
		if(result > 0) { // 수정 성공
			new MemberView().displaySuccess("회원 정보 변경 성공");
		} else { // 수정 실패
			new MemberView().displayFail("회원 정보 변경 실패");
		} 

	}
	
	/**
	 * 사용자가 회원 탈퇴 요청 시 처리해 주는 메소드
	 * @param userId: 사용자가 입력한 삭제하고자 하는 회원의 아이디 값
	 */
	public void deleteMember(String userId) {
		
		// 1. 요청 시 전달값들을 VO 객체로 가공하기
		// --> 전달값이 1개이므로 패스
		
		// 2.전달값을 Dao단의 메소드를 호출할 때 넘겨 주기
		// 3. 결과값 받기
		// => Dao 메소드를 호출할 때 고려해야 할 것(매개변수: userId, 리턴받을 값: Member)
		
		int result = new MemberDao().deleteMember(userId);
		
		if(result == 0) { // 조회 결과가 없는 경우
			new MemberView().displayNodata(userId + " 에 해당하는 검색결과가 없습니다.");
			// new MemberView().displayFail("회원 탈퇴 실패");
		} else { // 조회 결과가 있는 경우
			new MemberView().displaySuccess(userId + "이/가 성공적으로 삭제되었습니다.");
		}
	}

}