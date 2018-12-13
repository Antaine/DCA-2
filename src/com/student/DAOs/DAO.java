package com.student.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.student.Models.Course;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.PreparedStatementWrapper;

public class DAO {
	private static final Connection myStmt = null;
	private DataSource mysqlDS;

	 public DAO() throws Exception {
	     Context context = new InitialContext();
	     String jndiName = "java:comp/env/studentdb"; // name in web.xml & context.xml
	     mysqlDS = (DataSource) context.lookup(jndiName);
	  }
	 
	 public List<Course> loadCourses() throws SQLException{
		 System.out.println("DAO works");
		 Connection conn = mysqlDS.getConnection();
		 Statement myStmt = conn.createStatement();
		 String query = "select * from course";
		 ResultSet rs = myStmt.executeQuery(query);
		 
		 List<Course> courses = new ArrayList<>();
		
		 //Changw PRODID
		 while( rs.next() ) {
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			int duration = rs.getInt("duration");
			//System.out.println("Test");
			
			courses.add(new Course(cID, cName, duration));
			//System.out.println(cID + cName+ duration);
		}
		 System.out.println("courses is " + courses.get(0).getcName());
		 return courses;
	 }
	 
	 public void addCourse(Course courses) throws SQLException
	 {
		// 	Connection conn = null;
			PreparedStatement myStmt = null;
			ResultSet rs = null;
			
			 Connection conn = mysqlDS.getConnection();
			 String sql = "insert into course values (?, ?, ?)";
			 myStmt = conn.prepareStatement(sql);
				myStmt.setString(1, courses.getcID());
				myStmt.setString(2, courses.getcName());
				myStmt.setLong(3, courses.getDuration());
				myStmt.execute();
			//private String cName;
			//private long duration;

	/*	// System.out.println("DAO Add works");
		 Connection conn = mysqlDS.getConnection();
		 Statement myStmt = conn.createStatement();
		// String sql = "INSERT INTO course (cID, cName, duration) VALUES (?,?,?)";
		// myStmt.executeQuery(query);
		 System.out.println("Add Test " + cID);
	//	 myStmt.executeUpdate("INSERT INTO course (cID, cName, duration) VALUES ('ZACC', 'Test' ,5)");
	//	 PreparedStatement preparedStatement = conn.prepareStatement(sql);
		 preparedStatement.setString(1, "cID");
		 preparedStatement.setString(2, "cName");
		 preparedStatement.setLong(3,"duration");
		 preparedStatement.executeUpdate(sql);
		 return "add Course finished";*/
	 }
	 
/*	 public String add()
	 {
		 int i = 0;
		 if(cID != null)
		 {
			 PreparedStatement myStmt = null;
			 Connection conn = null;
		 }
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","","root");
			 String addSql = "INSERT INTO course(cID,cName,duration) VALUES(?,?,?)";
			 PreparedStatement myStmt = conn.prepareStatement(addSql);
			 myStmt.setString(1, cID);
			 myStmt.setString(2, cName);
			 myStmt.setLong(3, duration);
			 
			 i = myStmt.executeUpdate();
			 System.out.println("Post Added");
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 finally {
			 try
			 {
				// Connection conn = null;
				//conn.close();
				// myStmt.close();
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
		 }
		 if(i<0)
		 {
			 return "output";
		 }
		 else {
			 return "Invalid";
		 }
	 }*/
}
