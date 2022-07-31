package com.schoolfeespayment.dependants.impl;

import com.schoolfeespayment.dependants.entity.Dependants;
import com.schoolfeespayment.dependants.repository.DependantsRepository;

import com.schoolfeespayment.dependants.service.DependantsService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependantsServiceImpl implements DependantsService {
	
	private DependantsRepository dependantsRepository;


	public DependantsServiceImpl(DependantsRepository dependantsRepository) {
		super();
		this.dependantsRepository = dependantsRepository;
	}



	@Override
	public List<Dependants> getAllDependants() {
		// TODO Auto-generated method stub
		return dependantsRepository.findAll();
	}



	@Override
	public Dependants saveDependants(Dependants dependant) {
		// TODO Auto-generated method stub
		return dependantsRepository.save(dependant);
	}



	@Override
	public Dependants getDependantsById(Long id) {
		// TODO Auto-generated method stub
		return dependantsRepository.findById(id).get();
	}
//
//	@Override
//	public Student getStudentByRegNumber(String regNumber) {
//		return (Student) studentRepository.findByRegNumber(regNumber);
//	}


	@Override
	public Dependants updateDependants(Dependants dependant) {
		// TODO Auto-generated method stub
		return dependantsRepository.save(dependant);
	}



	@Override
	public void deleteDependantsById(Long id) {
		// TODO Auto-generated method stub
		dependantsRepository.deleteById(id);

	}

	@Override
	public void deleteDependantsByRegNumber(String regNumber) {
		dependantsRepository.deleteStudentByRegNumber(regNumber);

	}


}
