package org.sid.formation.entities;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class SousDirection {

	@Id @GeneratedValue
	private Long id;
	private String libelleAr;
	private String libelleFr;
	
	@OneToMany(mappedBy="direction",fetch=FetchType.LAZY)
	private Collection<Employe> employes;
	
	@ManyToOne
	@JoinColumn(name="CODE_DIRECTION")
	private Direction direction;

	public SousDirection(String libelleAr, String libelleFr, Direction direction) {
		super();
		this.libelleAr = libelleAr;
		this.libelleFr = libelleFr;
		this.direction =  direction;
	}

	public SousDirection() {
		super();
		// TODO Auto-generated constructor stub
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}
	
	
	
	
}
