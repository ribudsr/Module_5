package com.student.studentms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.student.studentms.data.Student;
import com.student.studentms.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	EurekaClient eurekaClient;
	
	@PostMapping("/create-student")
	public void createStudent(@RequestBody Student student) {
		studentService.saveOrUpdate(student);
	}
	
	@GetMapping("/student/{studentid}")
	public Student getStudent(@PathVariable("studentid") Long studentid) {
		return studentService.getStudentByID(studentid);
	}
	
	@GetMapping("/allstudents")  
	private List<Student> getAllStudent()   
	{  
	return studentService.getAllStudents();
	}  
	
	@DeleteMapping("/student/{studentid}")  
	private void deleteStudent(@PathVariable("studentid") Long studentid)   
	{  
		studentService.deleteStudent(studentid);
	}
	
	@PutMapping("/student")  
	private Student update(@RequestBody Student student)   
	{  
		studentService.saveOrUpdate(student);  
	return student;  
	}  
	
	@GetMapping("/getFees/{studentid}")  
	private Long getStudentFees(@PathVariable("studentid") Long studentid)   throws Exception 
	{ 
		String url =serviceUrl()+"getFees/{studentid}";
		Map<String,Long> params = new HashMap<String,Long>();
		params.put("studentid", studentid);
		RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(url, Long.class,params);  
	    
	} 
	
	@PutMapping("/payfees")  
	private Student payfees(@RequestBody Student student)   
	{  
		String url =serviceUrl()+"payfees";
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(500);
		requestFactory.setReadTimeout(500);
		restTemplate.setRequestFactory(requestFactory);
	    return restTemplate.patchForObject(url,student, Student.class); 
	}
	
	public String serviceUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("PAYMENT_SERVICE", false);
	    return instance.getHomePageUrl();
	}
}

