package org.sid.formation.entities;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Grade {

	@Id @GeneratedValue
	private Long id;
	private String libelleAr;
	private String libelleFr;
	
	@OneToMany(mappedBy="grade",fetch=FetchType.LAZY)
	private Collection<Employe> employes;

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grade(String libelleAr, String libelleFr) {
		super();
		this.libelleAr = libelleAr;
		this.libelleFr = libelleFr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelleAr() {
		return libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public String getLibelleFr() {
		return libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}

	
}
