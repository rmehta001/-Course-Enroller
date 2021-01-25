# -Course-Enroller
I created a JDBC application for managing the course enrollment at a university. 
The schema is as follows:
Students(sid:integer, sname:string)
Courses(cid:integer, cname:string, credits:integer)
Enrolled(sid:integer, cid:integer)

The Students relation  stores  data  about  students: a  unique student  id  and  name.  
Each  course  has  a  course id, name and number of credits). 
The Enrolled relation stores what courses are taken by which students.
I create the above schema definition in a file called schema.sql. 

Also, I created a Java JDBC-based  application  run  by  students,  with  name  Student.java.  
The  application has a command-line  interface  menu  that  allows  the  user  to  select  one  option  as  below.  
Once  that  menu  function  is  completed,  the  program  must  return  to  the  main  menu.  
I used the DBS2 Oracle instance as DBMS.

The Application starts by requesting student’s  ID;
no authentication is necessary, and the remaining session assumes  that  student  ID  is  active.  If  (-1)  is  introduced,  a  new  student  is  created,  and  the  user  is  prompted for all necessary information. 

Student-Menu:

The main menu is the following:
L – List: lists all records in the course table
E – Enroll: enrolls the active student in a course; user is prompted for course ID; check for conflicts, i.e., student cannot enroll twice in same course
W –  Withdraw:  deletes  an  entry  in  the  Enrolled  table  corresponding  to  active  student;  student  is  prompted for course ID to be withdrawn from
S  –  Search:  search  course  based  on  substring  of  course  name  which  is  given  by  user;  list  all  matching  courses
M – My Classes: lists all classes enrolled in by the active student.
X – Exit: exit application
