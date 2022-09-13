package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public ArrayList<Board> selectAll(Connection conn) {
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
				
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Board b = new Board();
				
				// BNO, TITLE, CONTENT, CREATE_DATE, WRITER,DELETE_YN
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(b);
				
			}
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return list;

	}
	
	// 작성자 아이디로 검색 
	// SELECT * FROM BOARD WHERE USERID = ?
	public ArrayList<Board> selectByUserId(Connection conn, String userId) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  userId);
			
			rset = pstmt.executeQuery();
			 
			while (rset.next()) {
				Board b = new Board();
				// while문 안에 객체 생성 하지 않고 바깥으로 빼 버리면 생성된 객체가 초기화되지 않아 똑같은 객체 내용만 반복 출력됨
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(b);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}
	// 게시글 제목으로 검색 
	public ArrayList<Board> selectByBoardTitle(Connection conn, String title) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByBoardTitle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Board b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(b);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}
	
	// 게시글 상세 보기
	public ArrayList<Board> selectByBoardNo(Connection conn, int bNo) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Board b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(b);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}
	
	// 게시글 수정 (Board 버전) 
	public int updateBoard(Connection conn, Board b) {
		
		int result = 0;
		
		String sql = prop.getProperty("updateBoard");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getBno());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	// 게시글 삭제
	public int deleteBoard(Connection conn, Board b) {
		
	int result = 0;
		
		String sql = prop.getProperty("deleteBoard");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b.getBno());
			
			// y를입력받았다면 자료를 삭제하지말고 !! deleteyn만 y로 바꿔서 조회 시에
			// 노출되지 않게 해 => update
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

}
