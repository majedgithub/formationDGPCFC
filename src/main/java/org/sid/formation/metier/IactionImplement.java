package org.sid.formation.metier;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public Action ConsulterAction(Long id)  {
		Action a = actionR.getOne(id);
		if(a == null) {return null;}else {
		return a;
		}
	}

	@Override
	public Action AjouterAction(Action c)  {
			return actionR.save(c);
		
	}

	@Override
	public Page<Action> listeActions(int page, int size) {
		return actionR.listeActions(new PageRequest(page, size));
	}

	@Override
	public Page<Action> listeActionsIntitule(String intitule,int page, int size) {
		// TODO Auto-generated method stub
		return actionR.listeActionsSearchIntitule(intitule,new PageRequest(page,size));
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

	@Override
	public Set<Action> ListActionByIntitule(String intiti) {
		// TODO Auto-generated method stub
		return actionR.GetListActionByIntitule(intiti);
	}

	@Override
	public Set<Action> ListActionByTheme(String intiti) {
		// TODO Auto-generated method stub
		return actionR.GetListActionByTheme(intiti);
	}

	@Override
	public Set<Action> ListActionByLieu(String intiti) {
		// TODO Auto-generated method stub
		return actionR.GetListActionByLieu(intiti);
	}

	@Override
	public Set<Action> ListActionByActionDate(String intiti) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
	    Date convertedDate = dateFormat.parse(intiti); 
		return actionR.GetListActionByDate(convertedDate);
	}

	@Override
	public Set<Action> ListActionByBureau(String intiti) {
		// TODO Auto-generated method stub
		return actionR.GetListActionByBureau(intiti);
	}

}
