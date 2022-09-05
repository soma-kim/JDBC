package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;

/*
 * * View
 * 사용자가 보게 될 시각적인 요소, 화면
 * 
 * - CLI: Command Line Interface (단순히 키보드만으로 컴퓨터와 소통할 수 있는 환경)
 * 		  출력문 (println, print, ...), 입력문(Scanner)
 * 
 * - GUI: Graphic User Interface (마우스, 키보드를 모두 이용해서 컴퓨터와 소통할 수 있는 환경)
 * 		  html 태그들
 */

public class MemberView {
	
	// 전역으로 다 쓸 수 있게끔 Scanner 객체 생성
	private Scanner sc = new Scanner(System.in);
	
	// 전역으로 다 쓸 수 있게끔 MemberController 객체 생성
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면(메인화면)
	 * 주석을 이렇게 만들면 바로 뒤 메소드명에 마우스 올렸을 때 설명이 보임!
	 * 다른 클래스에서  호출했을 때도 보이니까 유용함! 메소드 설명으로 활용하세요~
	 */
	public void mainMenu() {
		
		while (true) { // for (;;)
		
			System.out.println("***** 회원 관리 프로그램 *****");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("------------------------");
			System.out.print(">> 이용할 메뉴 선택: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1 : 
				insertMember(); // 회원 추가용 화면을 담당하는 메소드
				break;
			case 2 : 
				
				break;
			case 3 : 
				
				break;
			case 4 : 
				
				break;
			case 5 : 
				
				break;
			case 6 : 
				
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("번호를 잘못 입력했습니다. 다시 입력해 주세요.");
			}
		
		}
		
	}
	
	// 파란색 주석(메소드 설명 주석) 단축키: Alt + Shift + j
	/**
	 * 회원 추가용 화면
	 * 추가하고자 하는 회원의 정보를 입력받아서 추가 요청을 할 수 있는 화면
	 */
	public void insertMember() {
		
		System.out.println("----- 회원 추가 -----");
		
		// 회원 추가 --> MEMBER 테이블에 INSERT 하겠다.
		// 회원 추가 시 필요한 데이터들
		// 아이디, 비밀번호, 이름, 성별, 나이, 이메일, 휴대폰, 주소, 취미 (총 9개)
		System.out.print("아이디: ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호: ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름: ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F): ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(숫자만): ");
		String phone = sc.nextLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine();
		
		System.out.print("취미(콤마(,) 사용하여 공백 없이 나열): ");
		String hobby = sc.nextLine();
		
		// 회원 추가 요청 => MemberController 클래스의 어떤 메소드를 호출
		
		mc.insertMember(userId, userPwd, userName, gender, age,
						email, phone, address, hobby);
				
		
		
	}

}
