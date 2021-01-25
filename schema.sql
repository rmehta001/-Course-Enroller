create table Students
(
sid number PRIMARY KEY,
sname varchar(20)
);

create table Courses
(
cid number primary key, 
cname varchar(20),
credits number
);

create table Enrolled
(
sid number references Students(sid),
cid number references Courses(cid),
primary key(sid,cid)
);
