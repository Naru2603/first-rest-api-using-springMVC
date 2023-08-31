package com.infosys.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.entity.Student;
import com.infosys.service.StudentService;
import com.infosys.util.StudentNameComparator;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/students")
	public Student createStudent(@RequestBody Student std) {

		System.out.println("Request received to create student : " + std);

		if (studentService.saveStudent(std))
			return std;
		else
			return null;
	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable("id") int id) {

		System.out.println("Request received to get student id : " + id);

		Student retrieveStudent = studentService.getStudent(id);

		return retrieveStudent;
	}

	@GetMapping("/students")
	public List<Student> getAllStudents(@RequestParam(required = false) Integer firstResult,
			@RequestParam(required = false) Integer maxResult) {

		if ((firstResult != null)&&(maxResult != null))
			return studentService.getStudents(firstResult, maxResult);
		else
			return studentService.getStudents();

	}

	@GetMapping("/students/sortByName")
	public List<Student> getAllStudentsByName() {

		System.out.println("Request received to get all sorted student by Name");

		List<Student> stdList = studentService.getStudents();

		Collections.sort(stdList, new StudentNameComparator());

		return stdList;

	}

	@DeleteMapping("/students/{id}")
	public String deleteStudentById(@PathVariable("id") int id) {

		System.out.println("Request received to Delete student with Id : " + id);

		if (studentService.deleteStudent(id))
			return "Student deleted Successfully..!!";
		else
			return "Error while deleting student...!!";
	}

	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student std) {

		System.out.println("Request received to update student : " + std);

		return studentService.updateStudent(std);
	}
}
