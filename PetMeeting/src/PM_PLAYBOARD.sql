/* MEMBER */
DROP TABLE PM_MEMBER
CASCADE CONSTRAINTS;

CREATE TABLE PM_MEMBER(
	EMAIL VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	NICKNAME VARCHAR2(50) UNIQUE,
	POSTCODE VARCHAR2(10),
	ADDRESS VARCHAR2(200),
	ADDRESS_DETAIL VARCHAR2(200),
	ADDRESS_REFER VARCHAR2(200),
	PHONE VARCHAR2(50) NOT NULL,
	TOTALPOINT NUMBER NOT NULL,
	POINT NUMBER NOT NULL,
	S_POINT NUMBER NOT NULL,
	REPORTCOUNT NUMBER NOT NULL,
	AUTH NUMBER NOT NULL
);

CREATE TABLE PM_GRADE(
	GRADE_NAME VARCHAR2(50),
	MIN_POINT NUMBER,
	MAX_POINT NUMBER,
	FILENAME VARCHAR2(50)
);


SELECT * FROM PM_MEMBER;

/* PLAYBOARD */
DROP TABLE PM_PLAYBOARD
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_PLAYBOARD_SEQ;

CREATE TABLE PM_PLAYBOARD(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	EMAIL VARCHAR2(50),
	TITLE VARCHAR2(200),
	CONTENT CLOB,
	FILENAME VARCHAR2(300),
	LOCATION VARCHAR2(200),
	LOCATION_DETAIL VARCHAR2(1000),
	CATEGORY VARCHAR2(50),
	PRICE NUMBER,
	PEOPLE NUMBER,
	PERSONCOUNT NUMBER,
	READCOUNT NUMBER,
	LIKECOUNT NUMBER,
	REPORTCOUNT NUMBER,
	PDATE DATE,
	EDATE DATE,
	REGDATE DATE,
	DEL NUMBER
);

CREATE SEQUENCE PM_PLAYBOARD_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_PLAYBOARD
ADD CONSTRAINT PM_PLAYBOARD_FK FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_PLAYBOARD;


/* PLAYMEM */
DROP TABLE PM_PLAYMEM
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_PLAYMEM_SEQ;

CREATE TABLE PM_PLAYMEM(
	SEQ NUMBER PRIMARY KEY,
	PLAY_SEQ NUMBER,
	EMAIL VARCHAR2(50)
);

CREATE SEQUENCE PM_PLAYMEM_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_PLAYMEM
ADD CONSTRAINT PM_PLAYMEM_FK FOREIGN KEY(PLAY_SEQ)
REFERENCES PM_PLAYBOARD(SEQ);

SELECT * FROM PM_PLAYMEM;


/* TAG */
DROP TABLE PM_TAG
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_TAG_SEQ;

CREATE TABLE PM_TAG(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	BOARD_SEQ NUMBER,
	HASH1 VARCHAR2(100),
	HASH2 VARCHAR2(100),
	HASH3 VARCHAR2(100),
	HASH4 VARCHAR2(100),
	HASH5 VARCHAR2(100)
);

CREATE SEQUENCE PM_TAG_SEQ
START WITH 1
INCREMENT BY 1;

SELECT * FROM PM_TAG;

SELECT NVL(MAX(SEQ), 0) FROM PM_PLAYBOARD


/* BOARD_LIKE */
DROP TABLE PM_BOARD_LIKE
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_BOARD_LIKE_SEQ;

CREATE TABLE PM_BOARD_LIKE(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	BOARD_SEQ NUMBER,
	EMAIL VARCHAR2(50)
);

CREATE SEQUENCE PM_BOARD_LIKE_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_BOARD_LIKE
ADD CONSTRAINT PM_BOARD_LIKE_FK FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_BOARD_LIKE;

/* MEMBER_REPORT */
DROP TABLE PM_MEMBER_REPORT
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_MEMBER_REPORT_SEQ;

CREATE TABLE PM_MEMBER_REPORT(
	SEQ NUMBER PRIMARY KEY,
	BAD_EMAIL VARCHAR2(50),
	EMAIL VARCHAR2(50),
	REASON VARCHAR2(300)
);

CREATE SEQUENCE PM_MEMBER_REPORT_SEQ
START WITH 1
INCREMENT BY 1;

SELECT * FROM PM_MEMBER_REPORT;

/* BOARD_REPORT */
DROP TABLE PM_BOARD_REPORT
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_BOARD_REPORT_SEQ;

CREATE TABLE PM_BOARD_REPORT(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	BOARD_SEQ NUMBER,
	EMAIL VARCHAR2(50),
	REASON VARCHAR2(300)
);

CREATE SEQUENCE PM_BOARD_REPORT_SEQ
START WITH 1
INCREMENT BY 1;

SELECT * FROM PM_BOARD_REPORT;



/* MYPROFILE */
DROP TABLE PM_MYPROFILE
CASCADE CONSTRAINTS;

CREATE TABLE PM_MYPROFILE(
	EMAIL VARCHAR2(50),
	MYPROFILE_IMG VARCHAR2(50),
	MYAGE NUMBER,
	MYSEX NUMBER,
	MYINTRO VARCHAR2(1000)
);

ALTER TABLE PM_MYPROFILE
ADD CONSTRAINT PM_MYPROFILE_FK FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

INSERT INTO PM_MYPROFILE
VALUES('abc@abc.com', '', 25, 0, '하이하이' )

SELECT * FROM PM_MYPROFILE;

/* PETPROFILE */
DROP TABLE PM_PETPROFILE
CASCADE CONSTRAINTS;

CREATE TABLE PM_PETPROFILE(
	EMAIL VARCHAR2(50),
	PETPROFILE_IMG VARCHAR2(50),
	PETNAME VARCHAR2(100),
	PETAGE NUMBER,
	PETTYPE VARCHAR2(50),
	PETDETAIL VARCHAR2(100),
	PETWEIGHT VARCHAR2(50),
	PETSEX NUMBER,
	PETTNR VARCHAR2(50),
	PETINTRO VARCHAR2(1000)
);

ALTER TABLE PM_PETPROFILE
ADD CONSTRAINT PM_PETPROFILE_FK FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_PETPROFILE

/* REVMSG */
DROP TABLE PM_REVMSG
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_REVMSG_SEQ;

CREATE TABLE PM_REVMSG(
   SEQ NUMBER PRIMARY KEY,
   TO_EMAIL VARCHAR2(50),
   FROM_EMAIL VARCHAR2(50),
   CONTENT VARCHAR2(500),
   SENDDATE DATE,
   READDATE DATE,
   IMPORTANT NUMBER,
   READCHECK NUMBER
);

CREATE SEQUENCE PM_REVMSG_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_REVMSG
ADD CONSTRAINT PM_REVMSG_FK1 FOREIGN KEY(TO_EMAIL)
REFERENCES PM_MEMBER(EMAIL);

ALTER TABLE PM_REVMSG
ADD CONSTRAINT PM_REVMSG_FK2 FOREIGN KEY(FROM_EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_REVMSG;


/* SENDMSG */
DROP TABLE PM_SENDMSG
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_SENDMSG_SEQ;

CREATE TABLE PM_SENDMSG(
   SEQ NUMBER PRIMARY KEY,
   TO_EMAIL VARCHAR2(50),
   FROM_EMAIL VARCHAR2(50),
   CONTENT VARCHAR2(500),
   SENDDATE DATE,
   READDATE DATE,
   IMPORTANT NUMBER,
   READCHECK NUMBER
);

CREATE SEQUENCE PM_SENDMSG_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_SENDMSG
ADD CONSTRAINT PM_SENDMSG_FK1 FOREIGN KEY(TO_EMAIL)
REFERENCES PM_MEMBER(EMAIL);

ALTER TABLE PM_SENDMSG
ADD CONSTRAINT PM_SENDMSG_FK2 FOREIGN KEY(FROM_EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_SENDMSG;


/* CHECK_BOARD */
DROP TABLE PM_CHECK_BOARD
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_CHECK_BOARD_SEQ;

CREATE TABLE PM_CHECK_BOARD(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	EMAIL VARCHAR2(50),
	WDATE DATE
);

CREATE SEQUENCE PM_CHECK_BOARD_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_CHECK_BOARD
ADD CONSTRAINT PM_CHECK_BOARD_FK1 FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

SELECT * FROM PM_CHECK_BOARD;


/* PLAYBOARDQNA */
DROP TABLE PM_PLAYBOARDQNA
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_PLAYBOARDQNA_SEQ;

CREATE TABLE PM_PLAYBOARDQNA(
	SEQ NUMBER PRIMARY KEY,
	BOARD_SEQ NUMBER,
	EMAIL VARCHAR2(50),
	REF NUMBER,
	PARENT NUMBER,
	CONTENT VARCHAR2(4000),
	REGDATE DATE,
	SECRET NUMBER,
	DEL NUMBER
);

CREATE SEQUENCE PM_PLAYBOARDQNA_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_PLAYBOARDQNA
ADD CONSTRAINT PM_PLAYBOARDQNA_FK1 FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

ALTER TABLE PM_PLAYBOARDQNA
ADD CONSTRAINT PM_PLAYBOARDQNA_FK2 FOREIGN KEY(BOARD_SEQ)
REFERENCES PM_PLAYBOARD(SEQ);

SELECT * FROM PM_PLAYBOARDQNA;







UPDATE PM_PLAYBOARD
SET LIKECOUNT=0
WHERE SEQ=2

DELETE FROM PM_BOARD_LIKE WHERE EMAIL='butter@ring.com'



UPDATE PM_PLAYBOARD
SET REPORTCOUNT = 0
WHERE SEQ=



WITH HASHLIST AS (
SELECT ROW_NUMBER()OVER(ORDER BY REGDATE DESC) AS RNUM, P.SEQ, P.BOARD_CODE, P.EMAIL, P.TITLE, P.CONTENT, P.FILENAME, P.LOCATION, P.LOCATION_DETAIL, P.CATEGORY, 
	   P.PRICE, P.PEOPLE, P.PERSONCOUNT, P.READCOUNT, P.LIKECOUNT, P.REPORTCOUNT, P.PDATE, P.EDATE, P.REGDATE, P.DEL,
	   T.HASH1, T.HASH2, T.HASH3, T.HASH4, T.HASH5
FROM (SELECT SEQ, BOARD_CODE, EMAIL, TITLE, CONTENT, FILENAME, LOCATION, LOCATION_DETAIL, CATEGORY, 
			PRICE, PEOPLE, PERSONCOUNT, READCOUNT, LIKECOUNT, REPORTCOUNT, PDATE, EDATE, REGDATE, DEL
	  FROM PM_PLAYBOARD WHERE DEL=0) P JOIN 
	  (SELECT BOARD_SEQ, HASH1, HASH2, HASH3, HASH4, HASH5 
	  FROM PM_TAG WHERE BOARD_CODE='PLAY' AND HASH1 LIKE '강아지' OR HASH2 LIKE '강아지' OR HASH3 LIKE '강아지' OR HASH4 LIKE '강아지' OR HASH5 LIKE '강아지') T 
	  ON P.SEQ=T.BOARD_SEQ
)
SELECT * FROM HASHLIST WHERE RNUM >= 1 AND RNUM <= 6
	  
WHERE RNUM <![CDATA[>=]]> #{startRow} AND RNUM <![CDATA[<=]]> #{endRow} AND T.BOARD_CODE='PLAY' AND T.HASH1 LIKE '%'||#{searchText}||'%' OR T.HASH2 LIKE '%'||#{searchText}||'%' OR T.HASH3 LIKE '%'||#{searchText}||'%' OR T.HASH4 LIKE '%'||#{searchText}||'%' OR T.HASH5 LIKE '%'||#{searchText}||'%'			

SELECT*FROM PM_MEMBER

SELECT SEQ, BOARD_SEQ, Q.EMAIL AS EMAIL, M.NICKNAME AS NICKNAME, PARENT, TITLE, CONTENT, REGDATE, SECRET, DEL
FROM PM_PLAYBOARDQNA Q JOIN PM_MEMBER M ON Q.EMAIL=M.EMAIL
WHERE BOARD_SEQ=18



SELECT Q.SEQ, Q.BOARD_SEQ, Q.EMAIL, M.NICKNAME, Q.PARENT, Q.CONTENT, Q.REGDATE, Q.SECRET, Q.DEL,
	  F.MYPROFILE_IMG, F.MYAGE, F.MYSEX, F.MYINTRO
FROM PM_PLAYBOARDQNA Q JOIN PM_MEMBER M ON Q.EMAIL=M.EMAIL FULL OUTER JOIN PM_MYPROFILE F ON Q.EMAIL=F.EMAIL
WHERE Q.BOARD_SEQ=18
ORDER BY Q.PARENT ASC, Q.SEQ DESC

UPDATE PM_PLAYBOARDQNA
SET SECRET=1
WHERE SEQ=2

SELECT * FROM PM_FREEBOARD


SELECT * FROM PM_GRADE

UPDATE PM_GRADE
SET MIN_POINT = 0
WHERE GRADE_NAME='new'

		WITH MEMBERLIST AS (
			SELECT M.EMAIL, M.PWD, M.NAME, M.NICKNAME, M.POSTCODE, M.ADDRESS, M.ADDRESS_DETAIL, M.ADDRESS_REFER, M.PHONE, M.TOTALPOINT, M.POINT, M.S_POINT, M.REPORTCOUNT, M.AUTH,
					(SELECT GRADE_NAME FROM PM_GRADE WHERE M.TOTALPOINT >= MIN_POINT AND TOTALPOINT <= MAX_POINT) AS GRADE_NAME,
					P.MYPROFILE_IMG, P.MYAGE, P.MYSEX, P.MYINTRO,
					F.PETPROFILE_IMG, F.PETNAME, F.PETAGE, F.PETTYPE, F.PETDETAIL, F.PETWEIGHT, F.PETSEX, F.PETTNR, F.PETINTRO,
					ROW_NUMBER()OVER(ORDER BY M.EMAIL ASC) AS RNUM
			FROM PM_MEMBER M FULL OUTER JOIN PM_MYPROFILE P ON M.EMAIL=P.EMAIL FULL OUTER JOIN PM_PETPROFILE F ON M.EMAIL=F.EMAIL 
			WHERE 1=1 AND GRADE_NAME LIKE '%new%'
		)
		SELECT *
		FROM MEMBERLIST
		WHERE  RNUM >= 1 AND RNUM <= 10 
		
		SELECT *
		FROM (
			SELECT EMAIL, PWD, NAME, NICKNAME, POSTCODE, ADDRESS, ADDRESS_DETAIL, ADDRESS_REFER, PHONE, TOTALPOINT, POINT, S_POINT, REPORTCOUNT, AUTH, GRADE_NAME,
					MYPROFILE_IMG, MYAGE, MYSEX, MYINTRO,
					PETPROFILE_IMG, PETNAME, PETAGE, PETTYPE, PETDETAIL, PETWEIGHT, PETSEX, PETTNR, PETINTRO,					
					ROW_NUMBER()OVER(ORDER BY EMAIL ASC) AS RNUM
			FROM (
				SELECT M.EMAIL, M.PWD, M.NAME, M.NICKNAME, M.POSTCODE, M.ADDRESS, M.ADDRESS_DETAIL, M.ADDRESS_REFER, M.PHONE, M.TOTALPOINT, M.POINT, M.S_POINT, M.REPORTCOUNT, M.AUTH,
						(SELECT GRADE_NAME FROM PM_GRADE WHERE M.TOTALPOINT >= MIN_POINT AND TOTALPOINT <= MAX_POINT) AS GRADE_NAME,
						P.MYPROFILE_IMG, P.MYAGE, P.MYSEX, P.MYINTRO,
						F.PETPROFILE_IMG, F.PETNAME, F.PETAGE, F.PETTYPE, F.PETDETAIL, F.PETWEIGHT, F.PETSEX, F.PETTNR, F.PETINTRO					
				FROM PM_MEMBER M FULL OUTER JOIN PM_MYPROFILE P ON M.EMAIL=P.EMAIL FULL OUTER JOIN PM_PETPROFILE F ON M.EMAIL=F.EMAIL 				
			) A 
			WHERE GRADE_NAME LIKE '%new%'
		)
		WHERE RNUM >= 1 AND RNUM <= 10 
		
		
		
		

SELECT * FROM PM_LEAVEMEMBER
		
		