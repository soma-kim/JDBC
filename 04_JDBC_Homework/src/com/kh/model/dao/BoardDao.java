package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Board;

// Service에서 Dao 오는 순간 각 메소드에 필요한 쿼리문을 작성해 보세요
public class BoardDao {
	
	private Properties prop = new Properties();
	private PreparedStatement pstmt = null;
	
	public BoardDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 게시글 쓰기
	public int writeBoard(Connection conn, Board b) {
		
		int result = 0;

		String sql = prop.getProperty("writeBoard");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// BNO, TITLE, CONTENT, CREATE_DATE, WRITER,DELETE_YN
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	 // 글 전체 조회
	 // SELECT * FROM BOARD;
	public void selectAll(Connection conn) {
		ArrayList<Board> list = new ArrayList<>();
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
