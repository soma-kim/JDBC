package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.BoardService;
import com.kh.model.vo.Board;
import com.kh.view.BoardView;

public class BoardController {
	
	private BoardService service = new BoardService();
	
	// 게시글 쓰기
	public void writeBoard(String title, String content, String writer) {
		
		Board b = new Board();
		b.setTitle(title);
		b.setContent(content);
		b.setWriter(writer);
		
		int result = service.writeBoard(b);
		
		if(result > 0) { // 성공
			new BoardView().displaySuccess("게시글이 성공적으로 업로드되었습니다.");
			
			//--- MemberView mv = new MemberView();
			//--- mv.displaySuccess("회원가입에 성공했습니다.");
			
		} else { // 실패했을 경우
			new BoardView().displayFail("게시글 업로드에 실패하셨습니다.");
		}
	}
	
	 // 글 전체 조회
	public void selectAll() {
		ArrayList<Board> list = service.selectAll();
		
		// ArrayList<Member>list = new MemberService().selectAll();
		// ArrayList<Member> list = new ArrayList<>();
		// MemberService service = new MemberService();
		// service.selectAll();
				
		// 결과에 따른 응답 화면 지정
		if(list.size() == 0) { // 조회할 게시글 없음
			new BoardView().displayNoData("조회된 게시글이 없습니다.");
		} else { // 조회할 게시글 있음
			new BoardView().displayList(list);
		}
		
		
	}
	
	// 작성자 아이디로 검색 
	public void selectByUserId(String userId) {
		
		ArrayList<Board> list = service.selectByUserId(userId);
		
		if(list.isEmpty()) {
			new BoardView().displayNoData("조회된 결과가 없습니다.");
			
		} else {
			new BoardView().displayList(list);
			
		}
	}
	
	// 게시글 제목으로 검색 
	public void selectByBoardTitle(String title) {
		
		ArrayList<Board> list = service.selectByBoardTitle(title);
		
		if(list.isEmpty()) {
			new BoardView().displayNoData("조회된 결과가 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
		
	}
	
	// 게시글 상세 보기
	public void selectByBoardNo(int bNo) {
		ArrayList<Board> list = service.selectByBoardNo(bNo);
		
		if(list.isEmpty()) {
			new BoardView().displayNoData("조회된 결과가 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
		
	}
	
	// 게시글 수정 
	public void updateBoard(int bNo, String newTitle, String newContent) {
		
		Board b = new Board();
		b.setBno(bNo);
		b.setTitle(newTitle);
		b.setContent(newContent);
		
		int result = service.updateBoard(b);
		
		if(result > 0) {
			new BoardView().displaySuccess("게시글이 성공적으로 수정되었습니다.");
		} else {
			new BoardView().displayFail("일치하는 게시글 번호가 없습니다.");
		}
		
	}
	
	// 게시글 삭제
	public void deleteBoard(int bNo, String deleteYN) {
		Board b = new Board();
		b.setBno(bNo);
		b.setDeleteYN(deleteYN);
		
		int result = service.deleteBoard(b);
		
		if(result > 0) {
			new BoardView().displaySuccess("게시글이 성공적으로 삭제되었습니다.");
		} else {
			new BoardView().displayFail("일치하는 게시글 번호가 없습니다.");
		}
		
		
		
	}

}
