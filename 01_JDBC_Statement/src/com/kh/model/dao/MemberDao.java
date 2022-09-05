package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.model.vo.Member;

/*
 * * DAO(Data Access Object)
 * Controller를 통해서 호출된 기능을 수행하기 위해
 * DB에 직접적으로 접근한 후 해당 SQL문 실행 및 결과를 리턴받는 부분
 * => JDBC 코드 작성
 */

public class MemberDao {
	
	/*
	 * *JDBC용 객체
	 * - Connection: DB의 연결정보를 담고 있는 객체
	 * - (Prepared)Statement: 해당 DB에 SQL문을 전달하고 실행한 결과를 받아내는 객체
	 * - ResultSet: 만일 실행한 SQL문이 SELECT문일 경우 조회된 결과들이 담겨 있는 객체
	 * 
	 * *JDBC 처리 순서
	 * 1) JDBC Driver 등록: 해당 DBMS가 제공하는 클래스 등록
	 * 2) Connection 객체 생성: 접속하고자 하는 DB 정보를 기술해서 DB에 접속
	 * 3) Statement 객체 생성: Connection 객체를 이용해서 생성
	 * 4) SQL문 전달하면서 실행: Statement 객체에서 제공하는 메소드를 호출함으로써 SQL문 실행
	 * 		> SELECT문일 경우 - executeQuery 메소드를 이용하여 실행
	 * 		> 나머지 DML문일 경우 - executeUpdate 메소드를 이용하여 실행
	 * 		(INSERT, UPDATE, DELETE)
	 * 5) 결과 받기
	 * 		> SELECT문일 경우 - ResultSet 객체 (조회된 데이터들이 담겨 있음)로 받기 => 6_1)
	 * 		> 나머지 DML문일 경우 - int형(처리된 행의 개수)으로 받기				  => 6_2)
	 * 		(INSERT, UPDATE, DELETE)
	 * 6_1) ResultSet에 담겨 있는 데이터들을 하나씩 뽑아서 VO 객체에 담기
	 * 6_2) 트랜잭션 처리(성공이면 COMMIT, 실패면 ROLLBACK)
	 * 7) 다 쓴 JDBC용 객체들을 반드시 자원 반납(close) => 생성된 순서의 역순으로 반납
	 * 8) 결과 반환 (Controller 한테)
	 * 		> SELECT문일 경우 - 6_1)에서 만들어진 결과 리턴
	 * 		> 나머지 DML문일 경우 - int (처리된 행의 개수) 값 리턴
	 * 		(INSERT, UPDATE, DELETE)
	 * 
	 * ** Statement 특징: 완성된 SQL문을 실행할 수 있는 객체
	 */
	
	/**
	 * 사용자가 회원 추가 요청 시 입력했던 값들을 가지고 INSERT문을 실행하는 메소드
	 * @param m: 사용자가 입력했던 아이디 ~ 취미까지의 값들이 모두 담겨 있는 Member 객체
	 * @return: insert문 실행 후 처리된 행의 개수 (몇 개의 행이 INSERT 되었는지)
	 */
	public int insertMember(Member m) {
		
		// INSERT문 => 처리된 행의 개수(int) => 트랜잭션 처리
		
		// 0) 필요한 변수들 먼저 세팅 및 초기화 (선언 및 초기화)
		int result = 0; // 처리된 결과를 받을 수 있는 변수
		Connection conn = null; // 접속된 DB의 연결 정보를 담을 수 있는 변수
		Statement stmt = null;
		
		// 실행할 SQL문 (완성된 형태로 만들어 둘 것)
		// => 끝에 세미콜론이 있으면 안 됨
		// INSERT INTO MEMBER
		// VALUES (SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X'
		//			XX, 'XXXX', 'XXXXX', 'XXXXXXXXXXX', 'XXXX', DEFAULT);
		
		String sql = "INSERT INTO MEMBER "
								+ "VALUES(SEQ_USERNO.NEXTVAL, "
										+ "'" + m.getUserId() + "'" + ", "
										+ "'" + m.getUserPwd() + "'" + ","
										+ "'" + m.getUserName() + "'" + ", "
										+ "'" + m.getGender() + "'" + ", "
										+ m.getAge() + ", "
										+ "'" + m.getEmail() + "'" + ", "
										+ "'" + m.getPhone() + "'" + ", "
										+ "'" + m.getAddress() + "'" + ", "
										+ "'" + m. getHobby() + "'" + ", DEFAULT)";
		System.out.println(sql);
		
		try {
			
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			// ojdbc6.jar 파일 내에 있는 oracle.ojdbc.driver.OracleDriver 클래스를 등록
			// => ojdbc6.jar 파일이 누락되었거나, 잘 추가되었지만 오타가 있는 경우
			//	  ClassNotFoundException 발생!
			// 	  1) .jar 파일 있는지 확인 2) 클래스명 확인
				
			// 2) Connection 객체 생성 (DB와 연결 --> url: DB의 주소지, 계정명, 비밀번호)

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성(Connection 객체로부터)
			stmt = conn.createStatement();
			// => 적어도 이 단계까지 완료가 되었다면 본격적으로 SQL문을 실행시킬 수 있음
			
			// 4, 5) DB에 완성된 SQL문을 전달하면서 실행 후 결과 (처리된 행의 개수) 받기
			
			result = stmt.executeUpdate(sql);
			// => result에는 처리된 행의 개수가 들어 있음
			
			// 6_2) 트랜잭션 처리
			// => result 변수에 담긴 값 기준으로 조건 초기
			if(result > 0) { // 1개의 행 insert => insert가 아주 잘되어 있음 (성공) => commit
				conn.commit();
				
			} else { // 0개의 행 insert => insert에 실패했음 (실패) => rollback
				conn.rollback();
			}			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			try {
			// 7) 다 쓴 JDBC용 객체 자원 반납
			// => 반드시 작성해야 하는 코드이기 때문에 finally 블록 안에서 작성
			// => 생성된 순서의 역순으로 close
			
			// 생성 순서: Connection -> Statement
			// 닫는 순서: Statement -> Connection
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) 결과 반환
		return result; // 처리된 행의 개수(성공했을 경우 1, 실패했을 경우 0)
	}
	
}
