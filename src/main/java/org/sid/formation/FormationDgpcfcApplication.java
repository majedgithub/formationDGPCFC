package org.sid.formation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.sid.formation.dao.*;
import org.sid.formation.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormationDgpcfcApplication implements CommandLineRunner{

	@Autowired
	DirectionRepository directionRepository;
	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	FonctionsRepository fonctionRepository;
	@Autowired
	SousDirectionRepository sousDirectionRepository;
	
	@Autowired
	FormateurRepository formateurRepository;
	
	@Autowired
	ActionRepository actionRepository;
	
	@Autowired
	EmployeRepository employeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FormationDgpcfcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Direction d = directionRepository.save(new Direction("الإدارة العامة للمصالح المشتركة", "Direction Générale des Services Communs"));
		Grade g = gradeRepository.save(new Grade("zzzz", "ingénieur principal"));
		Fonctions f = fonctionRepository.save(new Fonctions("a", "chef service de la recherche"));
		SousDirection sd= sousDirectionRepository.save(new SousDirection("دارة الشؤون اإلدارية", "la direction des affaires administratives", d));
	
		Formateur formmm = formateurRepository.save(new Formateur("09958877", "ben ali ", "58516162", "nabeul", "formationtech"));
		
		Employe emp = employeRepository.save(new Employe("5555", "55555", "mimi", "aze", "8625", g, f, d));
	    Set<Employe> employess = new HashSet<>();
	    Set<Action> actions = new HashSet<>();
	    employess.add(emp);
		Action c = actionRepository.save(new Action("55555", "app", "mehat", "b1", new Date(), 3.0, "ok", 45.0, 45.0, 90.0, "aaaaaaaa", 0, 14.0, 14.0, 28.0, formmm));
		actions.add(c);
		
		emp.setActions(actions);
		c.setEmployes(employess);
		
		employeRepository.save(emp);
		actionRepository.save(c);*/
		
	}
}
