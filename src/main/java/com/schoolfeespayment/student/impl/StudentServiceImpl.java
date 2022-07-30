package com.schoolfeespayment.student.impl;

import java.util.List;

import com.schoolfeespayment.student.entity.Student;
import com.schoolfeespayment.student.repository.StudentRepository;
import com.schoolfeespayment.student.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;


	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}



	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}



	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}



	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}
//
//	@Override
//	public Student getStudentByRegNumber(String regNumber) {
//		return (Student) studentRepository.findByRegNumber(regNumber);
//	}


	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}



	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);

	}

	@Override
	public void deleteStudentByRegNumber(String regNumber) {
		studentRepository.deleteStudentByRegNumber(regNumber);

	}


}
