package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
		
		/*
		 * *기존의 방식: jdbc.driver 구문, 접속할 DB의 url, 접속할 계정명/비밀번호 들을
		 * 			    자바 소스코드 내에 직접 명시적으로 작성 => 정적 코딩 방식(하드코딩)
		 * - 문제점: DBMS가 변경되었을 경우, 접속할 DB의 url 또는 계정명 / 비밀번호가 변경될 경우
		 * 			=> 자바 코드를 수정해야 함
		 * 			수정된 내용을 적용시키고자 한다면 (반영시키고자 한다면)
		 * 			=> 프로그램을 재구동 해야 함
		 * 			(사용자 입장에서 프로그램 사용 중 비정상적으로 종료되었다가 다시 구동해서 사용해야 함, 운영이나 유지보수에도 불편하다)
		 * - 해결방식: DB와 관련된 정보들을 변도로 관리하는 외부 파일 (.properties)로 만들어서 접속 정보를 관리
		 * 			  외부파일로 읽어들여서 내용물을 반영시킬 것 
		 */
		
		Connection conn = null;
		
		Properties prop = new Properties();

			
		
		try {
			
			prop.load(new FileInputStream("resources/driver.properties"));
			
			// 현재 prop에는 driver.properties 파일로부터 읽어들인 key + value 세트들이 담겨 있을 것
			
			// 동적 코딩 방식으로 처리해 보자!
			// String driver = prop.getProperty("driver");
			// Class.forName(driver);
			Class.forName(prop.getProperty("driver")); // 위처럼 2줄로 쓰든 이렇게 1줄로 쓰든 선택해서!
		
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));		
			
			// 기존 정적 방식 코드 2줄은 주석 처리
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // 얘는 IOException의 자식이기 때문에 사실상 없어도 무방하지만 자바에서 자동으로 같이 만들어 줘서 그냥 두는 것!
		} catch (IOException e) {
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
