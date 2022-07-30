package com.schoolfeespayment.student.service;

import java.util.List;

import com.schoolfeespayment.student.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);

//
//	Student getStudentByRegNumber(String regNumber);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);

	void deleteStudentByRegNumber(String regNumber);
}
