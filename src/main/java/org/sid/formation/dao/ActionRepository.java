package org.sid.formation.dao;

import java.util.Date;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActionRepository extends JpaRepository<Action, Long>{
	@Query("select s from Action s order by s.dateaction desc")
	public Page<Action> listeActions(Pageable pageeable);
	
	@Query("select e from Action e where e.intitule= :x order by e.dateaction desc")
	public Page<Action> listeActionsSearchIntitule(@Param("x") String intitule, Pageable pageeable);

	@Query("select a from Action a where a.intitule =:x")
	public Set<Action> GetListActionByIntitule(@Param("x") String intitule);

	@Query("select a from Action a where a.theme =:x")
	public Set<Action> GetListActionByTheme(@Param("x") String theme);
	
	@Query("select a from Action a where a.dateaction =:x")
	public Set<Action> GetListActionByDate(@Param("x") Date dateaction);
	
	@Query("select a from Action a where a.lieu =:x")
	public Set<Action> GetListActionByLieu(@Param("x") String lieu);
	
	@Query("select a from Action a where a.bureau =:x")
	public Set<Action> GetListActionByBureau(@Param("x") String bureau);
	
}
