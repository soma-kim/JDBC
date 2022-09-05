package com.kh.model.dao;

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
	 * 
	 * 
	 * 
	 * 
	 */

}
