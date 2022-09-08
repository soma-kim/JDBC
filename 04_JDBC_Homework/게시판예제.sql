-- BOARD 테이블 생성
DROP TABLE BOARD;

CREATE TABLE BOARD(
    BNO NUMBER PRIMARY KEY, -- 게시글 넘버(시퀀스 이용하여 채번), private int bno;
    TITLE VARCHAR2(50) NOT NULL, -- 게시글 제목, private String title;
    CONTENT VARCHAR2(500) NOT NULL, -- 게시글 내용, private String content;
    CREATE_DATE DATE DEFAULT SYSDATE, -- 게시글 작성 시간, private Date createDate;
    WRITER NUMBER, -- 작성자(MEMBER테이블의 USERNO로 구분), private String writer; -- 번호일 경우에도 "1"로 가지고 올 수 있음! int로 하면 문자를 아예 못 받아 옴
    DELETE_YN CHAR(2) DEFAULT 'N', -- 게시글 삭제 여부(DELETE 말고 UPDATE를 써 보세요, 복구의 여지를 남김), private String deleteYN;
    FOREIGN KEY (WRITER) REFERENCES MEMBER(USERNO),
    CHECK(DELETE_YN IN('Y','N'))
);

-- BOARD 테이블의 PK로 사용될 시퀀스
DROP SEQUENCE SEQ_BOARD;

CREATE SEQUENCE SEQ_BOARD;

-- 테스트 데이터 삽입
INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '게시판 서비스를 시작합니다^^', '많은 이용 바랍니다~!', '21/01/27', 1, DEFAULT);
-- 1은 회원번호를 뜻함! 적어도 멤버 테이블에 없는 회원 번호를 입력할 경우 바로 오류 남!
-- MEMBER 테이블에 있는 회원번호로 넣어야 함

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, 'JDBC 어려워요', '살려주세요', '21/09/05', 2, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '게시글2', '게시글2 내용은 도대체 뭘까요', '21/09/05', 2, DEFAULT);

COMMIT;


SELECT * FROM BOARD;

-- 게시글 쓰기 / 글 전체 조회 / 작성자아이디로 검색 / 게시글 제목으로 검색 / 게시글 상세 조회 / 게시글 수정 / 게시글 삭제

SELECT BNO "게시글번호", TITLE "제목", CONTENT "내용", CREATE_DATE "작성일", USERID "작성자 아이디"
FROM BOARD, MEMBER
WHERE WRITER = USERNO;

