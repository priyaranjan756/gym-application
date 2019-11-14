create table Exercise
(
   id bigint(20) NOT NULL AUTO_INCREMENT,
   userId bigint(20) NOT NULL,
   description varchar(255) not null,
   type varchar(255) not null,
   startTime varchar(255) not null,
   duration bigint(20) NOT NULL ,
   calories bigint(20) NOT NULL,
   primary key(id)
);