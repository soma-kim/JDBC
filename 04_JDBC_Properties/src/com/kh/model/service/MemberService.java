package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

/*
 * *Service
 * 기존의 Dao의 역할 중 DB에 접속 관련한 코드들만 빼 두는 파트
 * JDBC 흐름 중 접속과 관련된 Connection 객체 관련 코드들만 작업할 예정
 * 
 * Connection 객체 생성 -> Dao로 전달 -> Connection을 이용한 트랜잭션 처리 -> Connection 객체 반납
 */

public class MemberService {
	
	/**
	 * 회원 추가 기능을 위한 INSERT문을 실행하기 전 필요한 Connection 객체를 만들어 주는 메소드
	 * @param m: 추가할 회원의 정보
	 * @return: INSERT된 행의 개수
	 */
	public int insertMember(Member m) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao의 메소드를 호출 (conn, 전달값)
		int result = new MemberDao().insertMember(conn, m);
		
		// 3. 트랜잭션 처리
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 자원 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 반환
		return result;
	}
	
	/**
	 * 회원 전체 조회를 위한 SELECT문을 실행할 때 필요한 Connection 객체를 만들어 주는 메소드
	 * @return: 전체 회원들의 정보들
	 */
	public ArrayList<Member> selectAll(){
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2. Dao의 메소드 호출 (conn) => 전달값이 없기 때문에
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		// 3. 트랜잭션 처리 => SELECT문을 실행해서 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 반환(Controller 한테)
		return list;

	}
	
	public Member selectByUserId(String userId) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao의 메소드 호출 (conn, userId)
		Member m = new MemberDao().selectByUserId(conn, userId);
		// 생성자 = 클래스명과 이름 똑같고 반환형 없는 일종의 메소드
		
		// 3. 트랜잭션 처리 => SELECT문을 실행했기 때문에 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 반환 (Controller 한테)
		return m;
	}
	
	/**
	 * 회원 이름 키워드 검색을 위한 SELECT문을 실행할 때 필요한 Connection 객체를 생성해 주는 메소드
	 * @param keyword: 회원 이름 키워드 검색어
	 * @return: 검색된 회원의 목록
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 메소드 호출 (conn, keyword)
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		// 3. 트랜잭션 처리 => SELECT문을 실행했기 때문에 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 반환 (Controller 한테)
		return list;
	
	}
	
	/**
	 * 회원 정보 수정을 위한 UPDATE 구문을 실행할 수 있도록 CONNECTION 객체를 생성해 주는 메소드 
	 * @param m: 수정할 회원의 정보
	 * @return: 수정된 행의 갯수
	 */
	public int updateMember(Member m){
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 메소드 호출(conn, keyword)
		int result = new MemberDao().updateMember(conn, m);

		// 3. 트랜잭션 처리
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 반환 (Controller한테)
		return result;
	
	}
	
	/**
	 * 회원 탈퇴 요청을 위한 DELETE문을 실행할 수 있도록 Connection 객체를 생성해 주는 메소드
	 * @param userId: 탈퇴하고자 하는 아이디
	 * @return: 삭제된 행의 개수
	 */
	public int deleteMember(String userId) {
		
		// 1. Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 메소드 호출(conn, keyword)
		int result = new MemberDao().deleteMember(conn, userId);
		
		// 3. 트랜잭션 처리
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과반환(Controller 한테)
		return result;
	}

}