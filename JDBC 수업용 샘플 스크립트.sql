-- 회원관리 프로그램 작성을 위한 회원 테이블 구성
DROP TABLE MEMBER;
 
CREATE TABLE MEMBER (
    USERNO NUMBER PRIMARY KEY, -- 회원번호
    USERID VARCHAR2(15) NOT NULL UNIQUE, -- 회원아이디
    USERPWD VARCHAR2(20) NOT NULL, -- 회원비밀번호
    USERNAME VARCHAR2(20) NOT NULL, -- 회원명
    GENDER CHAR(1) CHECK(GENDER IN ('M', 'F')), -- 성별
    AGE NUMBER, -- 나이
    EMAIL VARCHAR2(30), -- 이메일
    PHONE CHAR(11), -- 휴대폰
    ADDRESS VARCHAR(100), -- 집주소
    HOBBY VARCHAR(50), -- 회원의 취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL -- 회원가입일 
); 

-- 회원번호 채번을 위한 시퀀스 생성
DROP SEQUENCE SEQ_USERNO;
CREATE SEQUENCE SEQ_USERNO
NOCACHE;

-- 테스트용 데이터(더미데이터) 추가
-- 2명의 회원 정보 추가
INSERT INTO MEMBER
VALUES (SEQ_USERNO.NEXTVAL
            , 'admin'
            , '1234'
            , '관리자'
            , 'F'
            , 45
            , 'admin@naver.com'
            , '01012345678'
            , '서울시 마포구'
            , '낮잠자기'
            , TO_DATE('2021/01/25','YYYY-MM-DD'));
            
INSERT INTO MEMBER
VALUES (SEQ_USERNO.NEXTVAL
            , 'user01'
            , 'pass01'
            , '홍길녀'
            , 'M'
            , 23
            , NULL
            , '01067891234'
            , NULL
            , '영화보기'
            , TO_DATE('2021/07/13', 'YYYY-MM-DD'));
            
-- 커밋
COMMIT;

-- 조회
SELECT * FROM MEMBER;
