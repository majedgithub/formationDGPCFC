package org.sid.formation.metier;

import java.util.List;

import org.sid.formation.entities.Formateur;

public interface IFormateur {

	public List<Formateur> ListFormateur();
	public Formateur getFormateur(long id);
}
