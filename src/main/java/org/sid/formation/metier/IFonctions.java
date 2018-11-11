package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.entities.Fonctions;

public interface IFonctions {

	public List<Fonctions> listFonctions();
	public Fonctions GetFonctionById(long id);
	
}
