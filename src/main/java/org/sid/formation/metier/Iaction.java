package org.sid.formation.metier;

import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;

public interface Iaction {

	public Action ConsulterAction(Long id) throws Exception;
	public Action AjouterAction(Action c, Set<Employe> emps);
	public Page<Action> listeActions(int page, int size);
}
