create database myboard;

use myboard;

create table freeboard(
	no int(10),
    writer varchar(20),
    subject varchar(50),
    password varchar(20),
    writeDate date,
    ref int(10),
    reStep int(10),
    reLevel int(10),
    readCount int(10),
    content varchar(20)
);
create table noticeboard(
	no int(10),
    kind int(10),
    subject varchar(50),
    writeDate date,
    ref int(10),
    readCount int(10),
    content varchar(20)
);
create table onetooneboard(
	no int(10),
    writer varchar(20),
    subject varchar(50),
    writeDate date,
    ref int(10),
    reStep int(10),
    content varchar(20),
    answer boolean
);
select * from onetooneboard;

create table faqboard(
	no int(10),
    writer varchar(20),
    subject varchar(50),
    writeDate date,
    ref int(10),
    readCount int(10),
    question varchar(20),
    answer varchar(20)
);
select * from faqboard;