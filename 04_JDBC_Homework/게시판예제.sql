-- BOARD ���̺� ����
DROP TABLE BOARD;

CREATE TABLE BOARD(
    BNO NUMBER PRIMARY KEY, -- �Խñ� �ѹ�(������ �̿��Ͽ� ä��), private int bno;
    TITLE VARCHAR2(50) NOT NULL, -- �Խñ� ����, private String title;
    CONTENT VARCHAR2(500) NOT NULL, -- �Խñ� ����, private String content;
    CREATE_DATE DATE DEFAULT SYSDATE, -- �Խñ� �ۼ� �ð�, private Date createDate;
    WRITER NUMBER, -- �ۼ���(MEMBER���̺��� USERNO�� ����), private String writer; -- ��ȣ�� ��쿡�� "1"�� ������ �� �� ����! int�� �ϸ� ���ڸ� �ƿ� �� �޾� ��
    DELETE_YN CHAR(2) DEFAULT 'N', -- �Խñ� ���� ����(DELETE ���� UPDATE�� �� ������, ������ ������ ����), private String deleteYN;
    FOREIGN KEY (WRITER) REFERENCES MEMBER(USERNO),
    CHECK(DELETE_YN IN('Y','N'))
);

-- BOARD ���̺��� PK�� ���� ������
DROP SEQUENCE SEQ_BOARD;

CREATE SEQUENCE SEQ_BOARD;

-- �׽�Ʈ ������ ����
INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '�Խ��� ���񽺸� �����մϴ�^^', '���� �̿� �ٶ��ϴ�~!', '21/01/27', 1, DEFAULT);
-- 1�� ȸ����ȣ�� ����! ��� ��� ���̺� ���� ȸ�� ��ȣ�� �Է��� ��� �ٷ� ���� ��!
-- MEMBER ���̺� �ִ� ȸ����ȣ�� �־�� ��

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, 'JDBC �������', '����ּ���', '21/09/05', 2, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '�Խñ�2', '�Խñ�2 ������ ����ü �����', '21/09/05', 2, DEFAULT);

COMMIT;


SELECT * FROM BOARD;

-- �Խñ� ���� / �� ��ü ��ȸ / �ۼ��ھ��̵�� �˻� / �Խñ� �������� �˻� / �Խñ� �� ��ȸ / �Խñ� ���� / �Խñ� ����

SELECT BNO "�Խñ۹�ȣ", TITLE "����", CONTENT "����", CREATE_DATE "�ۼ���", USERID "�ۼ��� ���̵�"
FROM BOARD, MEMBER
WHERE WRITER = USERNO;

