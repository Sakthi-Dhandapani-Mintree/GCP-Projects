package com.howtodoinjava.demo.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	private final Logger logger = Logger.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService service;
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
    	logger.info("getAllEmployees  ->from EmployeeController class");
        List<EmployeeEntity> list = service.getAllEmployees();
        logger.info("getAllEmployees  ->"+list.toString());
        System.out.println("getAllEmployees---->"+list.toString());
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	logger.info("getEmployeeById  ->from EmployeeController class");
        EmployeeEntity entity = service.getEmployeeById(id);
        logger.info("getAllEmployees  ->"+entity.toString());
        System.out.println("getAllEmployees  ->"+entity.toString());
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(EmployeeEntity employee)
                                                    throws RecordNotFoundException {
    	logger.info("createOrUpdateEmployee  ->from EmployeeController class");
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        logger.info("Values are added successfully in Db"+updated);
        System.out.println("Values are added successfully in Db"+updated);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	logger.info("deleteEmployeeById  ->from EmployeeController class");
        service.deleteEmployeeById(id);
        logger.info("deleteEmployeeById "+id);
        System.out.println("deleteEmployeeById "+id);
        return HttpStatus.FORBIDDEN;
    }
 
}