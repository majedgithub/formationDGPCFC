package org.sid.formation.metier;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.sid.formation.dao.FormateurRepository;
import org.sid.formation.entities.Action;
import org.sid.formation.entities.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IFormateurImplementation  implements IFormateur{

	@Autowired
	FormateurRepository  formateurRepo;
	
	@Autowired
	Iaction iaction;
	
	@Override
	public List<Formateur> ListFormateur() {
		return formateurRepo.findAll();
	}

	@Override
	public Formateur getFormateur(long id) {
		return formateurRepo.getOne(id);
	}

	@Override
	public Page<Formateur> ListFormateurPages(int page, int size) {
		return formateurRepo.GetListeFormateurs(new PageRequest(page, size));
	}

	@Override
	public void DeleteFormateur(long id){
		
		try {
			Formateur f = getFormateur(id);
			Collection<Action> ac = f.getActions();
			Iterator i = ac.iterator();
			while(i.hasNext()){
			   Action tmp = (Action)i.next();
			   tmp.setFormateur(null);
			   iaction.AjouterAction(tmp);
			   formateurRepo.delete(f);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	@Override
	public Formateur AddFormateur(Formateur f) {
		
		return formateurRepo.save(f);
	}

	@Override
	public Set<Action> ListActionByCinFormateur(String cin) {
		return formateurRepo.GetListActionByFormateurCin(cin);
	}
	
	@Override
	public Set<Action> ListActionByBureauFormateur(String bureau) {
		return formateurRepo.GetListActionByFormateurBureau(bureau);
	}
	
	@Override
	public Set<Action> ListActionByNomFormateur(String nom) {
		return formateurRepo.GetListActionByFormateurNom(nom);
	}

}
