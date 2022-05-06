package com.student.studentms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.studentms.data.Student;
import com.student.studentms.data.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public void saveOrUpdate(Student student) {
		studentRepository.save(student);
	}
	
	public Student getStudentByID(Long ID) {
		return studentRepository.findById(ID).get();
	}
	
	public List<Student> getAllStudents()   
	{  
	List<Student> studentList = new ArrayList<Student>();  
	studentRepository.findAll().forEach(students -> studentList.add(students));  
	return studentList;  
	}  
	
	public void deleteStudent(Long studentid) {
		studentRepository.deleteById(studentid);
	}
	
	public Long getStudentFeesByID(Long ID) {
		return studentRepository.findById(ID).get().getFees();
	}
	
	
}
