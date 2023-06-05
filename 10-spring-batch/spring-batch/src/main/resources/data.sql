CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_JOB_SEQ;

------
CREATE TABLE BATCH_STEP_EXECUTION_SEQ (ID BIGINT NOT NULL) ;
INSERT INTO BATCH_STEP_EXECUTION_SEQ values(0);
CREATE TABLE BATCH_JOB_EXECUTION_SEQ (ID BIGINT NOT NULL) ;
INSERT INTO BATCH_JOB_EXECUTION_SEQ values(0);
CREATE TABLE BATCH_JOB_SEQ (ID BIGINT NOT NULL) ;
INSERT INTO BATCH_JOB_SEQ values(0);
-------

CREATE TABLE BATCH_JOB_INSTANCE  (
  JOB_INSTANCE_ID BIGINT  PRIMARY KEY ,
  VERSION BIGINT,
  JOB_NAME VARCHAR(100) NOT NULL ,
  JOB_KEY VARCHAR(32) NOT NULL
);


CREATE TABLE BATCH_JOB_EXECUTION  (
  JOB_EXECUTION_ID BIGINT  PRIMARY KEY ,
  VERSION BIGINT,
  JOB_INSTANCE_ID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  START_TIME TIMESTAMP DEFAULT NULL,
  END_TIME TIMESTAMP DEFAULT NULL,
  STATUS VARCHAR(10),
  EXIT_CODE VARCHAR(20),
  EXIT_MESSAGE VARCHAR(2500),
  LAST_UPDATED TIMESTAMP,
  constraint JOB_INSTANCE_EXECUTION_FK foreign key (JOB_INSTANCE_ID)
  references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	PARAMETER_NAME VARCHAR(100) NOT NULL ,
	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
	PARAMETER_VALUE VARCHAR(2500) ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);


CREATE TABLE BATCH_STEP_EXECUTION  (
  STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY ,
  VERSION BIGINT NOT NULL,
  STEP_NAME VARCHAR(100) NOT NULL,
  JOB_EXECUTION_ID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  START_TIME TIMESTAMP DEFAULT NULL ,
  END_TIME TIMESTAMP DEFAULT NULL,
  STATUS VARCHAR(10),
  COMMIT_COUNT BIGINT ,
  READ_COUNT BIGINT ,
  FILTER_COUNT BIGINT ,
  WRITE_COUNT BIGINT ,
  READ_SKIP_COUNT BIGINT ,
  WRITE_SKIP_COUNT BIGINT ,
  PROCESS_SKIP_COUNT BIGINT ,
  ROLLBACK_COUNT BIGINT ,
  EXIT_CODE VARCHAR(20) ,
  EXIT_MESSAGE VARCHAR(2500) ,
  LAST_UPDATED TIMESTAMP,
  constraint JOB_EXECUTION_STEP_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
  JOB_EXECUTION_ID BIGINT PRIMARY KEY,
  SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT CLOB,
  constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
  STEP_EXECUTION_ID BIGINT PRIMARY KEY,
  SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT CLOB,
  constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
  references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;


insert into orders (id_order, date_create, date_update, status)
values(1001, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1002, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1003, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1004, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1005, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1006, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1007, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1008, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1009, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1010, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1011, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1012, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1013, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1014, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1015, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1016, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1017, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1018, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1019, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1020, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1021, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1022, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1023, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1024, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1025, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1026, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1027, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1028, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1029, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1030, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1031, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1032, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1033, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1034, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1035, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1036, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1037, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1038, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1039, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1040, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1041, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1042, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1043, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1044, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1045, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1046, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1047, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1048, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1049, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1050, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1051, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1052, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1053, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1054, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1055, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1056, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1057, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1058, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1059, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1060, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1061, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1062, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1063, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1064, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1065, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1066, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1067, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1068, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1069, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1070, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1071, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1072, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1073, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1074, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1075, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1076, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1077, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1078, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1079, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1080, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1081, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1082, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1083, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1084, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1085, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1086, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1087, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1088, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1089, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1090, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1091, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1092, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1093, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1094, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1095, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1096, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1097, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1098, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1099, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER'),
(1100, '2022-01-01', '2022-01-01', 'WE_RECEIVED_ORDER');

