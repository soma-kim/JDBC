package com.kh.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.kh.view.MemberView;

public class Run {
	
	/*
	 * * MVC 패턴
	 * M: Model, 데이터 처리 담당(데이터들을 담기 위한 VO, 데이터들이 보관된 공간과 직접 접근해 주는 DAO)
	 * V: View, 화면을 담당(사용자가 보는 시각적인 요소, 출력 및 입력)
	 * C: Controller, 사용자의 요청을 담당(사용자의 요청을 처리 후 그에 해당되는 응답화면을 지정)
	 */

	public static void main(String[] args) {
		
		// 프로그램 실행만을 담당
		// 사용자가 보게 될 첫 화면을 띄워 주고 끝
		
		// 방법 1. 생성 후 호출
		 MemberView mv = new MemberView();
		 mv.mainMenu();
		
		// 방법 2. 객체 생성과 동시에 호출
		// new MemberView().mainMenu();
		
		// Properties 복습
		// Properties: Map 계열의 컬렉션 (key + value 세트로 담는 게 특징)
		//			      주로 외부 설정 파일 읽어오기 또는 파일 형태로 출력하고자 할 때 사용
		//			   (파일 입출력에 특화된 컬렉션: key, value 모두 String 타입으로 적어 주는 것을 권장)
		
		// .properties, .xml 파일로 내보내기
//		Properties prop = new Properties();
//		
//		// setProperty("key", "value"): 문자열 형태로 Properties에 키 + 밸류 세트로 담을 수 있는 메소드
//		prop.setProperty("List", "ArrayList");
//		prop.setProperty("Set", "HashSet");
//		prop.setProperty("Map", "HashMap"); // key 값이 중복되면 덮어씌워지기 때문에 얘는 없어짐
//		prop.setProperty("Map", "Proerties"); // 총 3개의 키 + 밸류 세트가 들어 있음
//		
//		System.out.println(prop); // 키값 중복 X, 넣은 순서 유지 X
//		
//		// prop에 담긴 내용물들을 파일로 내보내기
//		// store / storeToXML (출력스트림객체, 파일에대한설명문구)
//		try {
//			prop.store(new FileOutputStream("resources/test.properties"), "test.properties");
//			prop.storeToXML(new FileOutputStream("resources/test.xml"), "test.xml");
//			// java.io.FileNotFoundException: resources\test.properties (지정된 경로를 찾을 수 없습니다)
//			// 파일 이름만 쓰면 없는 파일 이름이라도 잘 만들어지는데
//			// 지금은 resources라는 폴더 안에 파일을 만들겠다라고 했기 때문에 resources 폴더가 없어서 오류 남!!
//			
//			// resources 폴더: 주로 프로젝트 내의 외부 파일들을 저장하는 역할의 폴더
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// .propertise 파일 읽어들이기
		// load(입력스트림객체)
//		Properties prop = new Properties();
//		
//		try {
//			prop.load(new FileInputStream("resources/driver.properties"));
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// 출력
//		// getProperty("키"): "밸류"
//		System.out.println(prop.getProperty("driver")); // oracle.jdbc.driver.OracleDriver
//		System.out.println(prop.getProperty("url")); // jdbc:oracle:thin:@localhost:1521:xe
//		System.out.println(prop.getProperty("username")); // JDBC
//		System.out.println(prop.getProperty("password")); // JDBC
//		System.out.println(prop.getProperty("pass")); // null, 존재하지 않는 key값 제시 시 null 반환함!
		
		// .xml 파일 읽어들이기
		// loadFromXML(입력스트림객체)
//		Properties prop = new Properties();
//		
//		try {
//			prop.loadFromXML(new FileInputStream("resources/query.xml"));
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// 출력
//		System.out.println(prop.getProperty("select")); // SELECT * FROM
//		System.out.println(prop.getProperty("insert")); // INSERT INTO MEMBER VALUES (1)
//		System.out.println(prop.getProperty("update")); // UPDATE MEMBER SET USERID = 1
		
	}

}
