package com.schoolfeespayment.student.controler;

import com.schoolfeespayment.student.entity.Student;
import com.schoolfeespayment.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping(path="/stud")
public class StudentController {
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	//handler method to handle list students and return mode view
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
		
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		Student student=new Student();
		model.addAttribute("student", student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		
		studentService.saveStudent(student);
		return "redirect:/students";
		
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
		
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent (@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setStudentName(student.getStudentName ());
		existingStudent.setRegNumber(student.getRegNumber ());
		existingStudent.setParent(student.getParent());
		
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
		
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

}
