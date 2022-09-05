package com.kh.run;

import com.kh.view.MemberView;

public class Run {
	
	/*
	 * * MVC 패턴
	 * M: Model, 데이터 처리 담당(데이터들을 담기 위한 VO, 데이터들이 보관된 공간과 직접 접근해 주는 DAO)
	 * V: View, 화면을 담당(사용자가 보는 시각적인 요소, 출력 및 입력)
	 * C: Controller, 사용자의 요청을 담당(사용자의 요청을 처리 후 그에 해당되는 응답화면을 지정)
	 */

	public static void main(String[] args) {
		
		// 프로그램 실행만을 담당
		// 사용자가 보게 될 첫 화면을 띄워 주고 끝
		
		// 방법 1. 생성 후 호출
		// MemberView mv = new MemberView();
		// mv.mainMenu();
		
		// 방법 2. 객체 생성과 동시에 호출
		new MemberView().mainMenu();
		
		
	}

}
