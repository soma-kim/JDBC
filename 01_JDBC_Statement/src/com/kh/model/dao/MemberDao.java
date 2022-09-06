package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	 * - Connection: DB의 연결정보를 담고 있는 객체 (== 접속 연결용)
	 * - (Prepared)Statement: 해당 DB에 SQL문을 전달하고 실행한 결과를 받아내는 객체 (== 볼 일 볼 때 쓰는 애)
	 * - ResultSet: 만일 실행한 SQL문이 "SELECT문일 경우(에만 씀)" 조회된 결과들이 담겨 있는 객체
	 * 
	 * *JDBC 처리 순서
	 * 1) JDBC Driver 등록: 해당 DBMS가 제공하는 클래스 등록 (== 나 이제 DB랑 연결할 거야! 근데 DB 중에서도 오라클이랑 연결할 거임)
	 * 2) Connection 객체 생성: 접속하고자 하는 DB 정보를 기술해서 DB에 접속(== DB의 주소지, 계정, 비밀번호...)
	 * 3) Statement 객체 생성: Connection 객체를 이용해서 생성 (== 연결이 되었다! 본격적으로 SQL문 실행 가능한 상태)
	 * 4) SQL문 전달하면서 실행: Statement 객체에서 제공하는 메소드를 호출함으로써 SQL문 실행
	 * 		> SELECT문일 경우 - "executeQuery 메소드"를 이용하여 실행
	 * 		> 나머지 DML문일 경우 - "executeUpdate 메소드"를 이용하여 실행
	 * 		(INSERT, UPDATE, DELETE)
	 * 5) 결과 받기
	 * 		> SELECT문일 경우 - ResultSet 객체 (조회된 데이터들이 담겨 있음)로 받기  => 6_1)
	 * 		> 나머지 DML문일 경우 - int형(처리된 행의 개수)으로 받기				  => 6_2)
	 * 		(INSERT, UPDATE, DELETE) (== 오라클에서 '1개의 행이 삽입되었습니다'와 같이 나오던 게 알고 보면 return의 int 자료형이었던 것임)
	 * 6_1) ResultSet에 담겨 있는 데이터들을 하나씩 뽑아서 VO 객체에 담기
	 * 6_2) 트랜잭션 처리(성공이면 COMMIT, 실패면 ROLLBACK)
	 * 7) 다 쓴 JDBC용 객체들을 "반드시" 자원 반납(close) => 생성된 순서의 역순으로 반납 (== 반드시 해 줘야 하므로 finally로 반납)
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
		// 전역변수로 세팅하는 이유: try문에 넣어 버리면 try 닫히면 없어짐! finally에서 반납하고자 할 때 알아먹지 못하니까 
		
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
												// 구분자 잘 확인할 것!
			
			// 3) Statement 객체 생성(Connection 객체로부터)
			stmt = conn.createStatement();
			// => 적어도 이 단계까지 완료가 되었다면 본격적으로 SQL문을 실행시킬 수 있음
			
			// 4, 5) DB에 완성된 SQL문을 전달하면서 실행 후 결과 (처리된 행의 개수) 받기			
			result = stmt.executeUpdate(sql);
			// => insert이기 때문에 executeUpdate로 받고, result에는 처리된 행의 개수가 들어 있음
			
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
	
	/**
	 * 사용자가 회원 전체 조회 요청 시 SELECT문을 실행해 주는 메소드
	 * @return: 테이블로부터 조회된 전체 회원의 정보를 나타내는 ArrayList
	 */
	public ArrayList<Member> selectAll() {
		
		// SELECT문 => ResultSet 객체 => 여러 행 조회 (Member 1개당 회원 1명): ArrayList 타입으로 묶기
		
		// 0) 필요한 변수들 먼저 세팅 (선언 및 초기화)
		ArrayList<Member> list = new ArrayList<>(); // 조회된 결과를 뽑아서 담아 줄 ArrayList를 생성 (텅 빈 리스트)
		Connection conn = null; // 접속된 DB의 연결 정보를 담는 변수
		Statement stmt = null; // SQL문 실행 후 결과를 받기 위한 변수
		ResultSet rset = null; // SELECT문이 실행될 조회 결과값들이 처음에 실질적으로 담겨 올 객체 
		
		// 실행할 SQL문(완성된 형태로, 세미콜론은 빼고)
		// SELECT * FROM MEMBER
		String sql = "SELECT * FROM MEMBER";
		
		try {

			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 이미 try 블럭 안이므로 2번째 선택지인 "Add catch clause to surrounding try"를 선택해 주면 catch문만 생김 
			
			// 3) Statement 객체 생성 (Connection 객체로부터 얻어냄)
			stmt = conn.createStatement();
			
			// 4, 5) SQL문 (SELECT) 실행해서 결과값 받아내기
			rset = stmt.executeQuery(sql);
			// => rset에는 조회된 결과물들이 다 담겨 있을 것임
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet에서 한 행씩 뽑아서 VO 객체에 담기
			// => ResultSet 객체는 커서를 한 줄씩 아래로 옮겨서 현재 행의 위치를 나타내는 구조
			// => 이때, 커서를 rest.next() 메소드로 다음 줄로 옮겨 버리기
			while (rset.next()) { // 커서를 한 줄 아애로 움직여 주고
								  // 해당 행이 존재할 경우 true, 존재하지 않을 경우 false를 반환함
				// 적어도 이 중괄호 안에 들어와서 코드가 실행된다라는 것은
				// ResultSet 객체로부터 뽑아낼 데이터가 있다라는 뜻!
				
				// 현재 rset의 커서가 가리키고 있는 해당 행의 데이터를 하나씩 뽑아서 Member 객체에 담기
				// 한 행의 데이터 == 회원 한 명의 정보 == Member VO 객체 한 개
				Member m = new Member();
				
				// rset으로부터 어떤 컬럼에 해당하는 값을 뽑을 건지 제시해서
				// 해당 컬럼의 값들을 각 필드로 옮겨 주기
				// -> 해당 행의 해당 컬럼의 값을 자바의 지정된 자료형으로 가지고 옴
				// rset.getInt(컬럼명/컬럼순번): int형 값을 뽑아낼 때
				// rset.getString(컬럼명/컬럼순번): String 형 값을 뽑아낼 때
				// rset.getDate(컬럼명/컬럼순번): Date형 값을 뽑아낼 때
				// => 컬럼명은 대소문자를 가리지 않음
				// => 권장사항: 컬럼명으로 쓰고, 대문자로 쓰는 것을 권장! (가독성 높아져 협업 때 편함)
				
				// m.userNo = rset.getInt("USERNO"); --> private이라 직접 접근 불가
				m.setUserNo(rset.getInt("USERNO")); // 모든 컬럼에 대해 이 작업을 다 해 줘야 함 
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				// 컬럼명 오타 주의! get자료형 주의!
				// 한 행에 대한 모든 데이터 값들을 하나의 Member 객체로 옮겨 담기 끝!
				
				// 리스트에 해당 Member 객체를 담아 둘 것!
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
			// 7) 다 쓴 JDBC용 객체 반납 (생성된 역순)
			// 생성 순서: Connection -> Statement -> ResultSet
			// 닫는 순서: ResultSet -> Statement -> Connection
				rset.close();
				stmt.close();
				conn.close();
				// "Surround with try/catch" 클릭해서 3개 모두 try ~ catch문에 넣어 주기
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 8) 결과 반환
		return list; // 회원들의 정보가 담겨 있음!
					 // 만약 아무도 조회되지 않았다면 list.size() == 0 또는 list.isEmpty() == true 일 것
	}
	
	/**
	 * 사용자의 아이디 검색 조회를 위한 SELECT문을 실행할 메소드
	 * @param userId: 검색하고자 하는 조건에 해당되는 아이디 값
	 * @return: 검색된 회원 한 명의 정보
	 */
	public Member selectByUserId(String userId) {
		
		// SELECT문 => ResultSet 객체 => 한 행 조회 (Member 하나로 결과 받기)
		
		
		
		Member m = new Member();

		
		return m;
	}
	
}	