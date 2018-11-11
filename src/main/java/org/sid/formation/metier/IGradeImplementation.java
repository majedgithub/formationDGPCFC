package org.sid.formation.metier;

import java.util.List;

import org.sid.formation.dao.GradeRepository;
import org.sid.formation.entities.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IGradeImplementation implements IGrade{

	@Autowired
	GradeRepository gradeRepo;
	
	@Override
	public List<Grade> listGrades() {
		// TODO Auto-generated method stub
		return gradeRepo.findAll();
	}

	@Override
	public Grade GetGradeById(long id) {
		// TODO Auto-generated method stub
		return gradeRepo.getOne(id);
	}

}
