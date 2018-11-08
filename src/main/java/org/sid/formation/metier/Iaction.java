package org.sid.formation.metier;

import java.util.Date;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;

public interface Iaction {

	public Action ConsulterAction(Long id) throws Exception;
	public Action AjouterAction(Action c, Set<Employe> emps);
	public Page<Action> listeActions(int page, int size);
	public Page<Action> listeActionsIntitule(String intitule,int page, int size);
	public Page<Action> listeActionsDate(Date dateaction, int page, int size);
	public Page<Action> listeActionsIntituleDate(String intitule, Date dateaction, int page, int size);
	public long CountActions ();
	
}
