-- 회원 테이블 생성
DROP TABLE IF EXISTS MEMBER CASCADE;

CREATE TABLE MEMBER (
   MEMBER_IDX BIGINT GENERATED BY DEFAULT AS IDENTITY,
   EMAIL VARCHAR(255) NOT NULL UNIQUE,
   NAME VARCHAR(255) NOT NULL,
   PASSWORD VARCHAR(255) NOT NULL,
   MEMBER_TRY INTEGER NOT NULL,
   ADMIN_TRY INTEGER NOT NULL,
   AUTH VARCHAR(255) NOT NULL,
   MEMBER_STATE VARCHAR(15) NOT NULL,
   ADMIN_STATE VARCHAR(15) NOT NULL,
   RANKING INTEGER NOT NULL,
   REG_DATE TIMESTAMP NULL,
   REG_ADMIN VARCHAR(255) NULL,
   MOD_DATE TIMESTAMP NULL,
   MOD_ADMIN VARCHAR(255) NULL,
   POST_CNT INTEGER NOT NULL,
   PRIMARY KEY (MEMBER_IDX)
);

-- 게시판 테이블 생성
DROP TABLE IF EXISTS BOARD CASCADE;

CREATE TABLE BOARD (
   BOARD_IDX BIGINT GENERATED BY DEFAULT AS IDENTITY,
   BOARD_NAME VARCHAR(1000) NOT NULL UNIQUE,
   BOARD_DESC VARCHAR(5000) NOT NULL,
   REG_DATE TIMESTAMP NULL,
   REG_ADMIN VARCHAR(255) NOT NULL,
   MOD_DATE TIMESTAMP NULL,
   MOD_ADMIN VARCHAR(255) NULL,
   BOARD_STATE VARCHAR(15) NOT NULL,
   PRIMARY KEY (BOARD_IDX)
);

-- 게시물 테이블 생성
DROP TABLE IF EXISTS POST CASCADE;

CREATE TABLE POST (
   POST_IDX BIGINT GENERATED BY DEFAULT AS IDENTITY,
   POST_TITLE VARCHAR(1000) NOT NULL,
   BOARD_IDX BIGINT NOT NULL,
   MEMBER_IDX BIGINT NOT NULL,
   POST_CONTENTS VARCHAR(5000) NOT NULL,
   POST_CNT INTEGER NOT NULL,
   REG_DATE TIMESTAMP NULL,
   MOD_DATE TIMESTAMP NULL,
   MOD_ADMIN VARCHAR(255) NULL,
   POST_STATE VARCHAR(15) NOT NULL,
   PRIMARY KEY (POST_IDX)
);

-- 댓글 테이블 생성
DROP TABLE IF EXISTS COMMENT CASCADE;

CREATE TABLE COMMENT (
   COMMENT_IDX BIGINT GENERATED BY DEFAULT AS IDENTITY,
   COMMENT_CONTENTS VARCHAR(5000) NOT NULL,
   COMMENT_ORDER INTEGER NOT NULL,
   MEMBER_IDX BIGINT NOT NULL,
   POST_IDX BIGINT NOT NULL,
   REG_DATE TIMESTAMP NULL,
   MOD_DATE TIMESTAMP NULL,
   MOD_ADMIN VARCHAR(255) NULL,
   COMMENT_STATE VARCHAR(15) NOT NULL,
   PRIMARY KEY (COMMENT_IDX)
);