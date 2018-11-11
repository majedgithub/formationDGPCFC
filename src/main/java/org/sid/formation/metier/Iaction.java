package org.sid.formation.metier;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;

public interface Iaction {

	public Action ConsulterAction(Long id);
	public Action AjouterAction(Action c) throws Exception;
	public Action AffecterEmployeTAction(Action c, Employe e) throws Exception;
	public Page<Action> listeActions(int page, int size);
	public Page<Action> listeActionsIntitule(String intitule,int page, int size);
	public long CountActions ();
	
	public Set<Action> ListActionByIntitule(String intiti);
	public Set<Action> ListActionByTheme(String intiti);
	public Set<Action> ListActionByLieu(String intiti);
	public Set<Action> ListActionByActionDate(String intiti) throws ParseException;
	public Set<Action> ListActionByBureau(String intiti);
	
}
