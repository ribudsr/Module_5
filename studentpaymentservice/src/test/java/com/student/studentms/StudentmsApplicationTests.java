package com.student.studentms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.student.studentms.data.Student;
import com.student.studentms.data.StudentRepository;

@SpringBootTest
class StudentmsApplicationTests {

	
	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private StudentRepository repo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateStudent() {
	    Student student = new Student();
	    student.setEmail("abc@gmail.com");
	    student.setStudentName("Arun Kumar");
	    
	     
	    Student savedUser = repo.save(student);
	     
	    Student existUser = entityManager.find(Student.class,savedUser.getStudentId());
	     
	    assertThat(student.getEmail()).isEqualTo(existUser.getEmail());
	     
	}
}
