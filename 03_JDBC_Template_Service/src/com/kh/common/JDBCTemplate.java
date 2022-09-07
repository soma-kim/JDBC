package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	/*
	 * *JDBC 과정 중 반복적으로 쓰이는 구문들을 각각의 메소드로 정의해 둘 것 
	 * 	"재사용할 목적"으로 공통 템플릿 작업 진행
	 * 
	 * * 이 클래스에서의 모든 메소드들은 모두 static 메소드로 만들 것임
	 * => 싱글톤 패턴: 메모리 영역에 단 한 번만 올라간 것을 두고두고 공유하며 재사용하는 개념
	 */
	
	// 1. DB와 접속된 Connection 객체를 생성해서 반환시켜 주는 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. 전달받은 JDBC용 객체를 반납시켜 주는 메소드
	// 2_1. Connection 객체를 전달받아서 반납시켜 주는 메소드
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) { // 얘를 try ~ catch로 묶으면 conn.close()까지 자동 처리됨

					conn.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2_2. (Prepared)Statement 객체를 전달받아서 반납시켜 주는 메소드
	// => 다형성으로 인해 PreparedStatement 객체 또한 매개변수로 전달 가능
	public static void close(Statement stmt) { // 오버로딩 적용
		
		try {
			if(stmt != null && !stmt.isClosed()) { // 얘를 try ~ catch로 묶으면 conn.close()까지 자동 처리됨
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2_3. ResultSet 객체를 전달받아서 반납시켜 주는 메소드
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) { // 얘를 try ~ catch로 묶으면 conn.close()까지 자동 처리됨
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection 객체를 가지고 트랜잭션 처리를 해 주는 메소드
	// 3_1. 전달받은 Connection 객체를 가지고 COMMIT 시켜 주는 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3_2. 전달받은 Connection 객체를 가지고 ROLLBACK 시켜 주는 메소드
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
