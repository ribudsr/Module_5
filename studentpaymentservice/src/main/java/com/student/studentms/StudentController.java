package com.student.studentms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentms.data.Student;
import com.student.studentms.service.StudentService;

@RestController
@FeignClient(name = "PAYMENT_SERVICE")
@LoadBalancerClient(name = "PAYMENT_SERVICE",
configuration=LoadBalancerConfiguration.class)
public class StudentController {

	@Autowired
	StudentService studentService;
		
	@GetMapping("/getFees/{studentid}")  
	private Long getStudentFees(@PathVariable("studentid") Long studentid)   
	{  
	return studentService.getStudentFeesByID(studentid);
	} 
	
	@PutMapping("/payfees")  
	private Student payfees(@RequestBody Student student)   
	{  
		studentService.saveOrUpdate(student);  
	    return student;  
	}  
}

