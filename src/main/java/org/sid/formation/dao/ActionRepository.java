package org.sid.formation.dao;

import org.sid.formation.entities.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionRepository extends JpaRepository<Action, Long>{
	@Query("select o from Action o order by o.dateaction desc")
	public Page<Action> listeActions(Pageable pageeable);
}
