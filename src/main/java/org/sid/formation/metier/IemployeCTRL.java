package org.sid.formation.metier;

import java.util.Collection;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;

public interface IemployeCTRL {

	public Employe ConsulterEmploye(Long id) throws Exception;
	public Employe ConsulterEmployeByCnrps(String id) throws Exception;
	public Employe AjouterEmploye(Employe c);
	public Employe AffecterActionToEmploye(Action a,Employe c);
	public Employe EditEmploye(Employe e);
	public Page<Employe> listeEmploye(int page, int size);
	public void updateListeEmployeExcel(Collection<Employe> newEmp);
	public Long NumbreEmploye();
	
	public Set<Action> GetListActionByEmployeCnrps(String cnrps);
	public Set<Action> GetListActionByEmployeNom(String nom);
	public Set<Action> GetListActionByEmployeDirection(long id);
	public Set<Action> GetListActionByEmployeFonction(long id);
	public Set<Action> GetListActionByEmployeGrade(long id);
}
