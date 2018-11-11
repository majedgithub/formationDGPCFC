package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.dao.FonctionsRepository;
import org.sid.formation.entities.Fonctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IFonctionsImplementation implements IFonctions{

	@Autowired
	FonctionsRepository fonctionRepo;
	
	@Override
	public List<Fonctions> listFonctions() {
		// TODO Auto-generated method stub
		return fonctionRepo.findAll();
	}

	@Override
	public Fonctions GetFonctionById(long id) {
		// TODO Auto-generated method stub
		return fonctionRepo.getOne(id);
	}

}
