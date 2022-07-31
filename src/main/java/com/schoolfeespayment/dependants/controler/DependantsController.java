package com.schoolfeespayment.dependants.controler;

import com.schoolfeespayment.dependants.entity.Dependants;
import com.schoolfeespayment.dependants.service.DependantsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping(path="/api/dependants")
public class DependantsController {
	private DependantsService dependantsService;

	public DependantsController(DependantsService dependantsService) {
		super();
		this.dependantsService = dependantsService;
	}
	
	//handler method to handle list students and return mode view
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", dependantsService.getAllDependants());
		return "students";
		
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		Dependants dependants=new Dependants ();
		model.addAttribute("depentant", dependants);
		return "create_student";
	}
	
	@PostMapping("/dependants")
	public String saveStudent(@ModelAttribute("depentant") Dependants dependants) {

		dependantsService.saveDependants(dependants);
		return "redirect:/students";
		
	}
	
	@GetMapping("/dependants/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		
		model.addAttribute("dependant", dependantsService.getDependantsById(id));
		return "edit_dependant";
		
	}
	
	@PostMapping("/dependants/{id}")
	public String updateStudent (@PathVariable Long id,
			@ModelAttribute("dependant") Dependants dependant,
			Model model) {
		Dependants existingDependants = dependantsService.getDependantsById(id);
		existingDependants.setId(id);
		existingDependants.setSchoolName(dependant.getSchoolName ());
		existingDependants.setRegNumber(dependant.getRegNumber ());
		existingDependants.setStudent (dependant.getStudent ());

		dependantsService.updateDependants(existingDependants);
		return "redirect:/dependants";
		
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		dependantsService.deleteDependantsById(id);
		return "redirect:/Dependants";
	}

}
