package org.sid.formation.metier;

import java.util.Collection;

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
}
