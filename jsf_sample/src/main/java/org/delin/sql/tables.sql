create table user_basic(
	name varchar(10) primary key not null,
	password varchar(65)
)engine='innodb' default charset='utf8'

create table book(
isbn varchar (15) primary key not null,
name varchar (30),
author varchar (50),
publishTime DATE,
price decimal (10,2),
firstType varchar (20),
secondType varchar (20)
)engine='innodb' default charset='utf8'
