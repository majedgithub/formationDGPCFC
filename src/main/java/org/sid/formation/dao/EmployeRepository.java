package org.sid.formation.dao;

import org.sid.formation.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long>{

}
