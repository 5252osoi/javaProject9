show tables;

create table curriculum(
	idx int not null auto_increment primary key,
	name varchar(50) not null,
	curriculumStart datetime default now(),
	curriculumEnd datetime default now(),
	fee int default 0,
	x varchar(50) null
);	
desc curriculum;
select * from curriculum;
	
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


create table identify(
	id varchar(12) not null primary key,
	password varchar(20) not null
);
insert into identify values('admin','1234');

select * from identify
