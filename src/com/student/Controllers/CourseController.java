package com.student.Controllers;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.student.DAOs.DAO;
import com.student.Models.Course;

@ManagedBean
@SessionScoped
public class CourseController {

	private DAO dao;
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public CourseController() throws Exception {
		super();
		dao = new DAO();
	}

	
	public void loadCourses() throws SQLException{
		System.out.println("load courses");
		
		this.courses = dao.loadCourses();
		System.out.println("in controller courses is " + courses.get(0).getcName());
}
	
	public String addCourse(Course courses) throws SQLException{
		System.out.println("add courses Course_Controller");
		if(dao != null)
		{
		try {
				dao.addCourse(courses);
				return "list_courses";
		} catch (MySQLIntegrityConstraintViolationException e) {
				// Throwing error of Course ID already exists
				FacesMessage message = new FacesMessage("Error: Course ID " + courses.getcID() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
		}
		}
		return null;
	}
}

		/*
		dao.addCourse(courses);
		System.out.println("Course Controller Add End");
		return "list_courses.xhtml";*/

	

