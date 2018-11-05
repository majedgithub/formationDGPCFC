package org.sid.formation.entities;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Direction {

	@Id @GeneratedValue
	private Long id;
	private String libelleAr;
	private String libelleFr;
	
	@OneToMany(mappedBy="direction",fetch=FetchType.LAZY)
	private Collection<SousDirection> sousDirestions;

	
	
	public Direction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Direction(String libelleAr, String libelleFr) {
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



	public Collection<SousDirection> getSousDirestions() {
		return sousDirestions;
	}

	public void setSousDirestions(Collection<SousDirection> sousDirestions) {
		this.sousDirestions = sousDirestions;
	}
	
	
}
