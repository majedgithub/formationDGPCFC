package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.dao.SousDirectionRepository;
import org.sid.formation.entities.SousDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISousDirectionImplementation implements ISousDirection{

	@Autowired
	SousDirectionRepository sousDirectionRepo;
	
	@Override
	public List<SousDirection> listSousDirection() {
		// TODO Auto-generated method stub
		return sousDirectionRepo.findAll();
	}

	@Override
	public SousDirection GetSousDirectionById(long id) {
		// TODO Auto-generated method stub
		return sousDirectionRepo.getOne(id);
	}

}
