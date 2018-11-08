package org.sid.formation.dao;

import org.sid.formation.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	
	@Query("select e from Employe e where e.cnrps= :cnrpss")
	public Employe findByCnrps(@RequestParam("cnrpss") String cnrps);
}
