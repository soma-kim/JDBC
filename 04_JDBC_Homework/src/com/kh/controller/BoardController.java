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
		
		
		// ArrayList<Member> list = new MemberService().selectAll();
		// ArrayList<Member> list = new ArrayList<>();
		// MemberService service = new MemberService();
		// service.selectAll();
		
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

}
