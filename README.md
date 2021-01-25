# -Course-Enroller

Application starts by requesting student’s  ID;
no authentication is necessary, and the remaining session assumes  that  student  ID  is  active.  If  (-1)  is  introduced,  a  new  student  is  created,  and  the  user  is  prompted for all necessary information. 

A database called schema.sql is maintained throughout!

The main menu is the following:
L – List: lists all records in the course table
E – Enroll: enrolls the active student in a course; user is prompted for course ID; check for conflicts, i.e., student cannot enroll twice in same course
W –  Withdraw:  deletes  an  entry  in  the  Enrolled  table  corresponding  to  active  student;  student  is  prompted for course ID to be withdrawn from
S  –  Search:  search  course  based  on  substring  of  course  name  which  is  given  by  user;  list  all  matching  courses
M – My Classes: lists all classes enrolled in by the active student.
X – Exit: exit application
