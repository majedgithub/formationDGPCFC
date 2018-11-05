package org.sid.formation.metier;

import java.util.Collection;

import org.sid.formation.dao.EmployeRepository;
import org.sid.formation.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

@Controller
public class IemployeImplementation  implements IemployeCTRL{

	@Autowired
	EmployeRepository emp;
	
	@Override
	public Employe ConsulterEmploye(Long id) throws Exception {
		Employe e = emp.getOne(id);
		if(e == null) throw new Exception("Employe null");
		return e;
	}

	@Override
	public Employe AjouterEmploye(Employe c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employe EditEmploye(Employe e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employe> listeEmploye(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateListeEmployeExcel(Collection<Employe> newEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long NumbreEmploye() {
		return emp.count();
	}

}
