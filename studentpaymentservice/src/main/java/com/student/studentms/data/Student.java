package com.student.studentms.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long studentId;
	
	@Column(name = "STUDENT_NAME", nullable = false, length = 50)
	private String studentName;
	 
	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;
	
	@Column(name = "FEES", nullable = true)
	private Long fees;
	
	
	 public Long getFees() {
		return fees;
	}

	public void setFees(Long fees) {
		this.fees = fees;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	
}
