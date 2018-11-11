package org.sid.formation.dao;

import org.sid.formation.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	
	@Query("select e from Employe e where e.cnrps= :cnrpss")
	public Employe findByCnrps(@Param("cnrpss") String cnrps);
	
	@Query("select s from Action s order by s.dateaction desc")
	public Page<Employe> listeEmployesPageable(Pageable pageeable);
}
