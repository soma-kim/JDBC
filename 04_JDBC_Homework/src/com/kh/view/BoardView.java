package com.kh.view;

import java.util.Scanner;

import com.kh.controller.BoardController;

public class BoardView {
	
	// 전역 변수 설정
	private Scanner sc = new Scanner(System.in);
	private BoardController controller = new BoardController();
	
	// 기본 생성자에 게시글 띄워 놓으면 기본으로 글 목록 뜨지 않을까,,?
	public BoardView() {
		
		System.out.println("***** 아무거나 써도 되는 게시판 *****");
		
		// 여기 전체 조회를 해야 됨! 게시판은 원래 들어가자마자 다 보이잖음
		selectAll();
		
	}
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("***** 메뉴 화면 *****");
			System.out.println("1. 게시글 쓰기");
			System.out.println("2. 글 전체 조회");
			System.out.println("3. 작성자 아이디로 게시글 검색");
			System.out.println("4. 게시글 제목으로 검색");
			System.out.println("5. 게시글 상세 조회");
			System.out.println("6. 게시글 수정");
			System.out.println("7. 게시글 삭제");
			System.out.println("0. 프로그램 종료");
			//-- 게시글 쓰기 / 글 전체 조회 / 작성자 아이디로 검색 / 게시글 제목으로 검색 / 게시글 상세 조회(글번호로 검색) / 게시글 수정 / 게시글 삭제
			//-- 글 전체 조회 때 WHERE DELETE_YN = 'N'인 것만 조회!
			//-- 게시글 삭제 시 UPDATE문으로 DELTE_YN을 'Y'로 바꿀 것!
			System.out.print(">> 이용할 메뉴: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
				case 1 :
					writeBoard();
					break;
				case 2 :
					selectAll();
					break;
				case 3 :
					selectByUserId();
					break;
				case 4 :
					selectByBoardTitle();
					break;
				case 5 :
					selectByBoardNo();
					break;
				case 6 :
					updateBoard();
					break;
				case 7 :
					deleteBoard();
					break;
				case 0 :
					System.out.println("게시판 프로그램을 종료합니다.");
					return;
				default :
					System.out.println("잘못 입력하셨습니다. 메뉴 번호 확인 후 재입력 해 주세요.");
			}
			
		}
		
	}
	// 게시글 쓰기
	public void writeBoard() {
	// 제목, 내용, 작성자번호(String) 입력받기
		
		System.out.println("--- 게시글 쓰기 ---");
		System.out.print("게시글 제목: ");
		String title = sc.nextLine();
		
		System.out.print("게시글 내용: ");
		String content = sc.nextLine();
		
		System.out.print("작성자 번호: "); // 1, 2, 3, 7 존재함
		String writer = sc.nextLine();
		
		controller.writeBoard(title, content, writer);
	}
	
	 // 글 전체 조회
	public void selectAll() {
		
		System.out.println("--- 전체 게시글 조회 ---");
		controller.selectAll();
		
	}
	
	// 작성자 아이디로 검색 
	public void selectByUserId() {
		
	}
	// 게시글 제목으로 검색 
	public void selectByBoardTitle() {
		
	}
	
	// 게시글 상세 보기
	public void selectByBoardNo() {
		
	}
	
	// 게시글 수정 
	public void updateBoard() {
		
	}
	
	// 게시글 삭제
	public void deleteBoard() {
		
	}
	
	// 서비스 요청 성공
	public void displaySuccess(String message) {
		System.out.println("[Service Success] " + message);
	}
	
	// 서비스 요청 성공
	public void displayFail(String message) {
		System.out.println("[Service Fail] " + message);
	}

}
