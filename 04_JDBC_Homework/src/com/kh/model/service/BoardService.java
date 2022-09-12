package com.kh.model.service;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.BoardDao;
import com.kh.model.vo.Board;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	
	// 게시글 쓰기
	public int writeBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.writeBoard(conn, b);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	 // 글 전체 조회
	public ArrayList<Board> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
				
		ArrayList<Board> list = dao.selectAll(conn);
				
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	// 작성자 아이디로 검색 
	public ArrayList<Board> selectByUserId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = dao.selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}
	// 게시글 제목으로 검색 
	public void selectByBoardTitle(String title) {
		
		Connection conn = JDBCTemplate.getConnection();
		
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
