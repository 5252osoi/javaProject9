show tables;

create table curriculum(
	idx int not null auto_increment primary key,
	name varchar(50) not null,
	curriculumStart datetime default now(),
	curriculumEnd datetime default now(),
	fee int default 0
);	
desc curriculum;
drop table curriculum;
select * from curriculum;

insert into curriculum values (default,'중등부 일반', default, '2023-12-30',default);
update curriculum set fee=300000 where name='고등부 입시 일반';

create table teacher(
	idx int not null auto_increment primary key,
 	name varchar(20) not null,
	age int default 20,
	phone varchar(11),
	address varchar(50),
	curri1 int,
	curri2 int,
	pay int default 0
);
desc teacher;
insert into teacher values (default,'홍길동',24,'01012345678','사직동',1,null,default);
insert into teacher values (default,'김복남',27,'01098765432','율량동',4,null,default);
select * from teacher;




create table student(
	idx int not null auto_increment primary key,
 	name varchar(20) not null,
	age int default 13,
	phone varchar(11),
	famPhone varchar(11),
	address varchar(50),
	school varchar(50),
	curri1 int,
	curri2 int,
	curri3 int,
	fee int		
);
desc student;
insert into student values (default,'홍길자',17,'01033553355','01012345678','사직동','청주고','1',null,null,default);
select * from student;

create table identify(
	id varchar(12) not null primary key,
	password varchar(20) not null
);
insert into identify values('admin','1234');

select * from identify
