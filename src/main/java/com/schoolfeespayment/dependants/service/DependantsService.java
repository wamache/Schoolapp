package com.schoolfeespayment.dependants.service;

import com.schoolfeespayment.dependants.entity.Dependants;

import java.util.List;

public interface DependantsService {
	List<Dependants> getAllDependants();

	Dependants saveDependants(Dependants dependant);

	Dependants getDependantsById(Long id);

//
//	Student getStudentByRegNumber(String regNumber);

	Dependants updateDependants(Dependants dependant);
	
	void deleteDependantsById(Long id);

	void deleteDependantsByRegNumber(String regNumber);
}
