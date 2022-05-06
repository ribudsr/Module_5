package com.example.apigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apigateway.data.StudentDO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ApiController {

	
	@Autowired
	EurekaClient eurekaClient;
	
	
	@PostMapping("apigateway/create-student")
	public void createStudent(@RequestBody StudentDO student) {
		String url =serviceUrl()+"create-student";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, student, StudentDO.class);
	}
	
	@GetMapping("apigateway/student/{studentid}")
	public Long getStudent(@PathVariable("studentid") Long studentid) {	
		String url =serviceUrl()+"student/{studentid}";
		Map<String,Long> params = new HashMap<String,Long>();
		params.put("studentid", studentid);
		RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(url, Long.class,params);  
	}
	
	@GetMapping("apigateway/allstudents")  
	private List<StudentDO> getAllStudent()   
	{
		String url =serviceUrl()+"allstudents";
	    return (List<StudentDO>)this.restTemplate().getForObject(url,Object.class);  
	}  
	
	@DeleteMapping("apigateway/student/{studentid}")  
	private void deleteStudent(@PathVariable("studentid") Long studentid)   
	{  	
		String url =serviceUrl()+"allstudents";
		Map<String,Long> params = new HashMap<String,Long>();
		params.put("studentid", studentid);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, params);
	}    
	
	
	@GetMapping("apigateway/getFees/{studentid}")  
	private Long getStudentFees(@PathVariable("studentid") Long studentid)   throws Exception 
	{ 
		String url =serviceUrl()+"getFees/{studentid}";
		Map<String,Long> params = new HashMap<String,Long>();
		params.put("studentid", studentid);
		RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(url, Long.class,params);  
	    
	} 
	
	@PutMapping("/payfees")  
	private StudentDO payfees(@RequestBody StudentDO student)   
	{  
		String url =serviceUrl()+"payfees";
		RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.patchForObject(url,student, StudentDO.class); 
	}
	
	public String serviceUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("STUDENT_SERVICE", false);
	    return instance.getHomePageUrl();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
}
