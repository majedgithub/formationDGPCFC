package org.sid.formation.dao;

import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Formateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormateurRepository extends JpaRepository<Formateur, Long>{
	@Query("select o from Formateur o")
	public Page<Formateur> GetListeFormateurs(Pageable pageable);
	
	@Query("select a from Action a, Formateur f where f.cin =:x and f.id = a.formateur.id")
	public Set<Action> GetListActionByFormateurCin(@Param("x") String cin);
	
	@Query("select a from Action a, Formateur f where f.nom =:x and f.id = a.formateur.id")
	public Set<Action> GetListActionByFormateurNom(@Param("x") String nom);
	
	@Query("select a from Action a, Formateur f where f.bureau =:x and f.id = a.formateur.id")
	public Set<Action> GetListActionByFormateurBureau(@Param("x") String bureau);
}
