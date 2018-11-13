package org.sid.formation.dao;

import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	
	@Query("select e from Employe e where e.cnrps= :cnrpss")
	public Employe findByCnrps(@Param("cnrpss") String cnrps);
	
	@Query("select s from Employe s order by s.nom desc")
	public Page<Employe> listeEmployesPageable(Pageable pageeable);
	
	@Query("select e.actions from Employe e where e.cnrps =:x")
	public Set<Action> GetListActionByEmployeCnrps(@Param("x") String cnrps);

	@Query("select e.actions from Employe e where e.nom =:x")
	public Set<Action> GetListActionByEmployeNom(@Param("x") String nom);
	
	
	@Query("select e.actions from Employe e where e.direction.id =:x")
	public Set<Action> GetListActionByEmployeDirection(@Param("x") long id);
	
	@Query("select e.actions from Employe e where e.grade.id =:x ")
	public Set<Action> GetListActionByEmployeGrade(@Param("x") long id);
	
	@Query("select e.actions from Employe e where e.fonctions.id =:x ")
	public Set<Action> GetListActionByEmployeFonction(@Param("x") long id);
}
