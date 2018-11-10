package org.sid.formation.metier;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.Date;
import java.util.HashSet;
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
	
	@Autowired
	IemployeCTRL iemploye;
	@Override
	public Action ConsulterAction(Long id) throws Exception {
		Action a = actionR.getOne(id);
		
		if(a == null) throw new Exception("action vide");
		return a;
	}

	@Override
	public Action AjouterAction(Action c)  {
			return actionR.save(c);
		
	}

	@Override
	public Page<Action> listeActions(int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActions(new PageRequest(page, size));
	}

	@Override
	public Page<Action> listeActionsIntitule(String intitule,int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActionsSearchIntitule(intitule,new PageRequest(page,size));
	}

	@Override
	public Page<Action> listeActionsDate(Date dateaction,int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActionsSearchDate(dateaction,new PageRequest(page,size));
	}

	@Override
	public Page<Action> listeActionsIntituleDate(String intitule,Date dateaction,int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActionsIntituleDate(intitule,dateaction,(new PageRequest(page,size)));
	}

	@Override
	public long CountActions() {
		return actionR.count();
	}

	@Override
	public Action AffecterEmployeTAction(Action c, Employe e) throws Exception {

			if(c.getEmployes() !=null) {
				c.getEmployes().add(e);
			}else {
			 Set<Employe> listemp = new HashSet<>();
			 listemp.add(e);
			}
			//iemploye.AjouterEmploye(e);
			//Action a =AjouterAction(c);
			
			return c;
	}

}
