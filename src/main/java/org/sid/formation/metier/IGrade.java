package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.entities.Grade;

public interface IGrade {

	public List<Grade> listGrades();
	public Grade GetGradeById(long id);
	
}
