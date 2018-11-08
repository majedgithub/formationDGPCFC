package org.sid.formation.metier;

import java.util.List;

import org.sid.formation.dao.FormateurRepository;
import org.sid.formation.entities.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IFormateurImplementation  implements IFormateur{

	@Autowired
	FormateurRepository formateurRepo;
	
	@Override
	public List<Formateur> ListFormateur() {
		return formateurRepo.findAll();
	}

	@Override
	public Formateur getFormateur(long id) {
		return formateurRepo.getOne(id);
	}

}
