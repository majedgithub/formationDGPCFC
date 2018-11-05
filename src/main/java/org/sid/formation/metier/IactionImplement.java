package org.sid.formation.metier;

import java.util.Set;

import org.sid.formation.dao.ActionRepository;
import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IactionImplement implements Iaction{

	@Autowired
	ActionRepository actionR;
	@Override
	public Action ConsulterAction(Long id) throws Exception {
		Action a = actionR.getOne(id);
		
		if(a == null) throw new Exception("action vide");
		return a;
	}

	@Override
	public Action AjouterAction(Action c, Set<Employe> emps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Action> listeActions(int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActions(new PageRequest(page, size));
	}

}
