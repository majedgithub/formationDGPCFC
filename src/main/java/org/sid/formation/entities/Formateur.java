package org.sid.formation.entities;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Formateur {
	@Id @GeneratedValue
	private Long id;
	private String cin;
	private String nom;
	private String tel;
	private String addresse;
	private String bureau;
	
	@OneToMany(mappedBy="formateur",fetch=FetchType.LAZY)
	private Collection<Action> Actions;

	public Formateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Formateur(String cin, String nom, String tel, String addresse, String bureau) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.tel = tel;
		this.addresse = addresse;
		this.bureau = bureau;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public Collection<Action> getActions() {
		return Actions;
	}

	public void setActions(Collection<Action> actions) {
		Actions = actions;
	}
	
	
}
