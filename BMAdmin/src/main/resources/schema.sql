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
   MEMBER_STATE VARCHAR(255) NOT NULL,
   ADMIN_STATE VARCHAR(255) NOT NULL,
   RANKING INTEGER NOT NULL,
   REG_DATE TIMESTAMP NOT NULL,
   REG_ADMIN VARCHAR(255) NOT NULL,
   MOD_DATE TIMESTAMP NOT NULL,
   MOD_ADMIN VARCHAR(255) NOT NULL,
   PRIMARY KEY (MEMBER_IDX)
)