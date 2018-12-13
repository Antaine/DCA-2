package com.student.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Course {

		private String cID;//Course ID
		private String cName;//Course Name
		private int duration;//Duration
		
		public Course(String cID, String cName,int duration) {
			super();
			this.cID = cID;
			this.cName = cName;
			this.duration = duration;
		}
		
		public Course() {}

		public String getcID() {
			return cID;
		}

		public void setcID(String cID) {
			this.cID = cID;
		}

		public String getcName() {
			return cName;
		}

		public void setcName(String cName) {
			this.cName = cName;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}
		
}
