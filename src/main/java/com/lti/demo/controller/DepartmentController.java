package com.lti.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.demo.model.Department;
import com.lti.demo.service.DepartmentService;

@RestController
@RequestMapping(value = "/dept")
public class DepartmentController {

	@Autowired
	DepartmentService deptService;

//	localhost:8000/dept/get-all-depts

	@RequestMapping(value = "/get-all-depts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Department>> getAllDepts() {
		return new ResponseEntity<List<Department>>(deptService.getAllDepartments(), HttpStatus.FOUND);
	}

}
