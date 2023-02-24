package com.lti.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.demo.exception.DepartmentNotFoundException;
import com.lti.demo.model.Department;
import com.lti.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DepartmentRepository deptRepository;

	public List<Department> getAllDepartments() {
		return deptRepository.findAll();
	}

	public Department getDepartmentById(int departmentId) {
		Optional<Department> deptOptional = deptRepository.findById(departmentId);
		if (deptOptional.isPresent()) {
			LOG.info(deptOptional.get().toString());
			return deptOptional.get();
		} else {
			String errorMessage = "Department with did " + departmentId + " was not found.";
			LOG.warn(errorMessage);
			throw new DepartmentNotFoundException(errorMessage);
		}
	}

}
