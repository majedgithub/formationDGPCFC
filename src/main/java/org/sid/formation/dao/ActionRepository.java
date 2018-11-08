package org.sid.formation.dao;

import java.util.Date;

import org.sid.formation.entities.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActionRepository extends JpaRepository<Action, Long>{
	@Query("select o from Action o order by o.dateaction desc")
	public Page<Action> listeActions(Pageable pageeable);
	
	@Query("select e from Action e where e.intitule= :x order by e.dateaction desc")
	public Page<Action> listeActionsSearchIntitule(@Param("x") String intitule, Pageable pageeable);
	
	@Query("select z from Action z where z.dateaction=:y order by z.dateaction desc")
	public Page<Action> listeActionsSearchDate(@Param("y") Date dateaction,Pageable pageeable);
	
	@Query("select o from Action o where o.intitule= :c and o.dateaction= :v order by o.dateaction desc")
	public Page<Action> listeActionsIntituleDate(@Param("c") String c, @Param("v") Date v, Pageable pageeable);
	
}
