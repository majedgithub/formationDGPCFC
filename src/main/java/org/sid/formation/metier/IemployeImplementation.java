package org.sid.formation.metier;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.sid.formation.dao.EmployeRepository;
import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.sid.formation.entities.SousDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

@Controller
public class IemployeImplementation  implements IemployeCTRL{

	@Autowired
	EmployeRepository emp;
	
	@Autowired
	ISousDirection isousdirection;
	
	@Override
	public Employe ConsulterEmploye(Long id) throws Exception {
		Employe e = emp.getOne(id);
		if(e == null) throw new Exception("Employe null");
		return e;
	}

	@Override
	public Employe AjouterEmploye(Employe c) {
		return emp.save(c);
	}

	@Override
	public Employe EditEmploye(Employe e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employe> listeEmploye(int page, int size) {
		
		return emp.listeEmployesPageable(new PageRequest(page, size));
	}

	@Override
	public void updateListeEmployeExcel(Collection<Employe> newEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long NumbreEmploye() {
		return emp.count();
	}

	@Override
	public Employe AffecterActionToEmploye(Action c, Employe e) {

		try {
			if(e.getActions() !=null) {
				e.getActions().add(c);
			}else {
				Set<Action> listaction = new HashSet<>();
				listaction.add(c);
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return e;
	}

	@Override
	public Employe ConsulterEmployeByCnrps(String id) throws Exception {
		// TODO Auto-generated method stub
		return emp.findByCnrps(id);
	}

	@Override
	public Set<Action> GetListActionByEmployeCnrps(String cnrps) {
		
		return emp.GetListActionByEmployeCnrps(cnrps);
	}

	@Override
	public Set<Action> GetListActionByEmployeNom(String nom) {
		// TODO Auto-generated method stub
		return emp.GetListActionByEmployeNom(nom);
	}

	@Override
	public Set<Action> GetListActionByEmployeDirection(long id) {
		return emp.GetListActionByEmployeDirection(id);
	}

	@Override
	public Set<Action> GetListActionByEmployeFonction(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Action> GetListActionByEmployeGrade(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
