import java.io.*;

import java.sql.*;
import java.util.Scanner;


class Student

{
	// the host name of the server and the server instance name/id
	public static final String oracleServer = "dbs3.cs.umb.edu";
	public static final String oracleServerSid = "dbs3";
	
	
	public static void main(String args[]) throws SQLException,IOException
	{
		Connection conn = null;
		conn = getConnection();
		if (conn==null)
			System.exit(1);

		//now execute query
		Scanner input = new Scanner(System.in);
		  // Create statement object
	
	        while(true)
		{
	 	    
		    System.out.println("\nStudent ID = ");
		    int student_id =input.nextInt();
		    
			if (student_id==-1)
			{
			try
			{
			
			System.out.println("New Student id:");
			int newStud_id = input.nextInt();
			//System.out.print("New Student ID = ");
			//int newStud_id =input.nextInt();
			input.nextLine();
			System.out.print("New Student Name = ");
			String newStud_name =input.nextLine();
			
	
			String query = "insert into Students " + " (sid,sname)" + " values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, newStud_id);
			stmt.setString(2, newStud_name);
			int count= stmt.executeUpdate();
			System.out.println("You have successfully registered");
			System.out.println(count + " rows affected");
			}
			catch(Exception e)
			{
			System.out.println("Student id already registered by another student");
			}
			}

	else
        {
	//String q1=("select sid from Students where sid =" +student_id);
			Statement st_mt = conn.createStatement();
			ResultSet rs3 = st_mt.executeQuery("select sid from Students where sid =" +student_id); 
			while(rs3.next())
        		{
			int stu_id= rs3.getInt(1);


			if(student_id==stu_id)						
			{
			System.out.println("");
			System.out.println("Type any one f rom the following options:");
			System.out.println("L – List: lists all records in the course table");
			System.out.println("E – Enroll: enrolls the active student in a course where user is prompted for course ID and");
                        System.out.println("check for conflicts, student cannot enroll twice in same course");
			System.out.println("W – Withdraw:  deletes  an  entry  in  the  Enrolled  table  corresponding  to  active  student");
			System.out.println("student  is  prompted for course ID to be withdrawn from");
			System.out.println("S - Search:  search  course  based  on  substring  of  course  name  which  is  given  by  user");
  			System.out.println("list  all  matching  courses");
			System.out.println("M – My Classes: lists all classes enrolled in by the active student");
			System.out.println("X – Exit: exit application");
			System.out.println("");
			
			//rs3.close();
			System.out.println("enter option: ");
                        char input_Alphabet = input.next().charAt(0);
			

				
				if(input_Alphabet=='L')
				{
				Statement stmt1 = conn.createStatement();
				ResultSet rs = stmt1.executeQuery("select * from Courses");
				while(rs.next())
				{
				int CourseId = rs.getInt(1);
				String CourseName = rs.getString(2);
				int Credits = rs.getInt(3);  
			
				//Display values
         			System.out.println("CourseId: " + CourseId );
         			System.out.println(", Course Name: " + CourseName );
         			System.out.println(", Credits: " + Credits );
				System.out.println();

	         		}
				rs.close();
				}
				
	
				
				else if(input_Alphabet=='E')
				{

				System.out.println("Select any one cid from the following Courses:");
				int c_id = input.nextInt();
				try
				{
					
				String query1 = "insert into Enrolled values(?,?)";
				PreparedStatement stmt2 = conn.prepareStatement(query1);
				
				stmt2.setInt(1, student_id);
				stmt2.setInt(2, c_id);
 				stmt2.executeUpdate();
				System.out.println("subject enrolled and row affected");
				}
				catch(SQLException e)
				{
				System.out.println("cannot enroll in the same subject twice");
				System.out.println("0 rows affected");
				}
				}
				


				
				else if(input_Alphabet=='W')
				{
				System.out.println("Enter the Course Id to be withdrawn");
				int c_id1 = input.nextInt();
				try
				{
				PreparedStatement stmt3 = conn.prepareStatement("delete from Enrolled" + " where cid =" +c_id1);
        	                int count2 = stmt3.executeUpdate();
				System.out.println(count2 + "subject withdrawn and row affected");
				}
				catch(Exception e)
				{
				System.out.println(e);
				}
				}
				
	




				else if(input_Alphabet=='S')
				{
				
				System.out.println("Enter substring to search: ");
				input.nextLine();
				String sub_string = input.nextLine();
				
				PreparedStatement stmt4 = conn.prepareStatement(" select cid,cname,credits from Courses where cname LIKE ?");
				stmt4.setString(1, "%" + sub_string + "%");
				ResultSet rs1 = stmt4.executeQuery();
				while(rs1.next())
				{
				int sub_id = rs1.getInt(1);
				String Course_name = rs1.getString(2);
				int cre_dits = rs1.getInt(3);

				
				System.out.println("Matching Course name is: " + Course_name);
				System.out.println("Course id is: " + sub_id);
				System.out.println("Credits are: " + cre_dits);
				System.out.println("");
				}
				rs1.close();
				}



				else if(input_Alphabet=='M')
				{
				System.out.println("You are enrolled to the following classes: ");
			
				Statement stmt5 = conn.createStatement();
				ResultSet rs2 = stmt5.executeQuery(" Select e.cid,c.cname from Courses c,Enrolled e where c.cid = e.cid AND e.sid = " +student_id);
				if(rs2.next())
				{
				do{
				int cid2 = rs2.getInt("cid");
				String course_name1 = rs2.getString("cname");

				System.out.println("Course id: " +cid2);  
				System.out.print("Course name: " +course_name1);
				}while(rs2.next());
				}
				else
				{
					System.out.println("You are not enrolled to any classes");
				}
				rs2.close();
				}
			
				 




				else if(input_Alphabet=='X')
				break;
			
				
   				else
				System.out.println("No Records Retrieved");
				}
				}
				
		//else
		//{
		//System.out.println("no such student id. If not created one, go back to the Student id menu and type -1");
		//}		    
}
		  	
	}
	

	}

		public static Connection getConnection()
		{

		// first we need to load the driver
		String jdbcDriver = "oracle.jdbc.OracleDriver";
		try 
		{
			Class.forName(jdbcDriver); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
 		}

		// Get username and password
		Scanner input = new Scanner(System.in);
		System.out.print("Username:");
		String username = input.nextLine();
		System.out.print("Password:");
		//the following is used to mask the password
		Console console = System.console();
		String password = new String(console.readPassword()); 
		String connString = "jdbc:oracle:thin:@" + oracleServer + ":1521:"
				+ oracleServerSid;

		System.out.println("Connecting to the database...");
	
		Connection conn;
		// Connect to the database
		try
		{
			conn = DriverManager.getConnection(connString, username, password);
			System.out.println("Connection Successful");
		}
		catch(SQLException e)
		{
			System.out.println("Connection ERROR");
			e.printStackTrace();	
			return null;
		}

		return conn;
		}
}

