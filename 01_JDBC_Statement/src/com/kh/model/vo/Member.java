package com.kh.model.vo;

import java.sql.Date;

/*
 * *VO (Value Object)
 * 값을 담는 용도의 클래스
 * DB 테이블의 한 행에 대한 데이터를 기록할 수 있는 저장용 객체
 * 
 * 유사 용어: DTO(Data Transfer Object)
 * 
 * VO 조건
 * 1) 반드시 캡슐화 적용(필드는 private, getter / setter 메소드)
 * 2) 기본 생성자 및 매개변수 생성자를 작성할 것
 * + toString 메소드 오버라이딩 => Object 클래스(모든 클래스의 최상위 부모) 에서 제공하는 메소드
 *						   => 원래 역할: 주소값 return / 재정의할 역할: 모든 필드의 값을 하나의 문자열로 재정의
 */
 

public class Member {
	
	// 필드부
	// 필드는 DB 컬럼 정보와 유사하게 작업
	// 테이블에서 COLUMN_NAME, DATA_TYPE 복사
	private int userNo;			// USERNO	NUMBER
	private String userId;		// USERID	VARCHAR2(15 BYTE)
	private String userPwd;		// USERPWD	VARCHAR2(20 BYTE)
	private String userName;	// USERNAME	VARCHAR2(20 BYTE)
	private String gender;		// GENDER	CHAR(1 BYTE) => 오라클에서는 문자/문자열 구분이 없기 때문에 자바에서도 String으로 정의
	private int age;			// AGE		NUMBER
	private String email; 		// EMAIL	VARCHAR2(30 BYTE)
	private String phone;		// PHONE	CHAR(11 BYTE)
	private String address;		// ADDRESS	VARCHAR2(100 BYTE)
	private String hobby;		// HOBBY	VARCHAR2(50 BYTE)
	private Date enrollDate;	// ENROLLDATE	DATE
	
	// 생성자부
	public Member() { }

	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	
	// 회원가입용 생성자
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}
	
	// 메소드부

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
	
	

	

}
