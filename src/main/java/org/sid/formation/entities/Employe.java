package org.sid.formation.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Employe {
	
	@Id @GeneratedValue
	private Long id;
	private String cnrps;
	private String cin;
	private String nom;
	private String addresse;
	private String tel;
	
	@ManyToOne
	@JoinColumn(name="CODE_GRADE")
	private Grade grade;

	@ManyToOne
	@JoinColumn(name="CODE_FONCTION")
	private Fonctions fonctions;
	
	@ManyToOne
	@JoinColumn(name="CODE_DIRECTION")
	private SousDirection direction;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "EMPLOYE_ACTION",
            joinColumns = { @JoinColumn(name = "employe_id") },
            inverseJoinColumns = { @JoinColumn(name = "action_id") })
    private Set<Action> actions = new HashSet<>();

	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Employe(String cnrps, String cin, String nom, String addresse, String tel, Grade grade, Fonctions fonctions,
			SousDirection direction) {
		super();
		this.cnrps = cnrps;
		this.cin = cin;
		this.nom = nom;
		this.addresse = addresse;
		this.tel = tel;
		this.grade = grade;
		this.fonctions = fonctions;
		this.direction = direction;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnrps() {
		return cnrps;
	}

	public void setCnrps(String cnrps) {
		this.cnrps = cnrps;
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

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Fonctions getFonctions() {
		return fonctions;
	}

	public void setFonctions(Fonctions fonctions) {
		this.fonctions = fonctions;
	}

	public SousDirection getDirection() {
		return direction;
	}

	public void setDirection(SousDirection direction) {
		this.direction = direction;
	}

	public Set<Action> getActions() {
		return actions;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}
	
	
}
