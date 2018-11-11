package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Formateur;
import org.springframework.data.domain.Page;

public interface IFormateur {

	public List<Formateur> ListFormateur();
	public Page<Formateur> ListFormateurPages(int page, int size);
	public Formateur AddFormateur(Formateur f);
	public Formateur getFormateur(long id);
	public void DeleteFormateur(long id) ;
	public Set<Action> ListActionByCinFormateur(String cin);
	public Set<Action> ListActionByNomFormateur(String nom);
	public Set<Action> ListActionByBureauFormateur(String bureau);
}
