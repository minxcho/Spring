
CREATE TABLE TBLBOARD (
	IDX INT NOT NULL,
	MEMID VARCHAR(20) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
	CONTENT VARCHAR(2000) NOT NULL,
	WRITER VARCHAR(30) NOT NULL,
	INDATE DATETIME DEFAULT NOW(),
	COUNT INT DEFAULT 0,
	-- 댓글 기능 추가 --
	BOARDGROUP INT, -- 부모 댓글 하나의 그룹 --
	BOARDSEQUENCE INT, -- 같은 그룹안에서 댓글의 순서 --
	BOARDLEVEL INT, -- 1단계 댓글인지 2단계 댓글인지에 대한 정보 --
	BOARDAVAILABLE INT, -- 삭제된 글인지 여부 --
	PRIMARY KEY(IDX)
);

SELECT * FROM TBLBOARD;

delete from tblboard where idx=5;

INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;

INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'pbk', 'Spring 친구들은 들으라', '맛있는 간식 또 사줄게요 ^^*', '병관쌤', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;

INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'jjy', '준용쌤입니다! 여러분 제발 늦지마세요', '아침마다 연락 자주 받는 친구들 알겠지?', '준용대디', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;

INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.1', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.2', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.3', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.4', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.5', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.6', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.7', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.8', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.9', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.10', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.11', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.12', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.13', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.14', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.15', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.16', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.17', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.18', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.19', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;
INSERT INTO TBLBOARD 
SELECT IFNULL(MAX(IDX)+1, 1), 'aischool', '공지사항입니다.20', '다음주 월요일 정상등원하겠습니다.', '교육운영부', NOW(), 0,
	 IFNULL(MAX(BOARDGROUP)+1, 1), 0, 0, 1
FROM TBLBOARD;


















CREATE TABLE TBLMEMBER(
   MEMID VARCHAR(50) NOT NULL,
   MEMPWD VARCHAR(50) NOT NULL,
   MEMNAME VARCHAR(50) NOT NULL,
   MEMPHONE VARCHAR(50) NOT NULL,
   MEMADDR VARCHAR(100),
   LATITUDE DECIMAL(13, 10), -- 현재위치위도 --
   LONGITUDE DECIMAL(13, 10), -- 현재위치경도 --
   PRIMARY KEY(MEMID)
);

SELECT * FROM TBLMEMBER;

INSERT INTO TBLMEMBER (MEMID, MEMPWD, MEMNAME, MEMPHONE)
VALUES ('aischool', '1234', '교육운영부', '010-1111-2222');


INSERT INTO TBLMEMBER (MEMID, MEMPWD, MEMNAME, MEMPHONE)
VALUES ('pbk', '1234', '병관쌤', '010-3333-4444');

INSERT INTO TBLMEMBER (MEMID, MEMPWD, MEMNAME, MEMPHONE)
VALUES ('jjy', '1234', '준용대디', '010-5555-6666');