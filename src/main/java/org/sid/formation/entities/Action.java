package org.sid.formation.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Action {

	@Id @GeneratedValue
	private Long id;
	private String intitule;
	private String theme;
	private String lieu;
	private String bureau;
	private Date dateaction;
	private Double duree;
	private String avis;
	
	private Double animationc;
	private Double organisationc;
	private Double totalec;
	private String sousparag;
	private int etat;
	private Double animationcop;
	private Double organisationcop;
	private Double totalecop;
	
	@ManyToOne
	@JoinColumn(name="CODE_FORMATEUR")
	private Formateur formateur;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "actions")
    private Set<Employe> employes = new HashSet<>();

	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Action(String intitule, String theme, String lieu, String bureau, Date dateaction, Double duree, String avis,
			Double animationc, Double organisationc, Double totalec, String sousparag, int etat, Double animationcop,
			Double organisationcop, Double totalecop, Formateur formateur) {
		super();
		this.intitule = intitule;
		this.theme = theme;
		this.lieu = lieu;
		this.bureau = bureau;
		this.dateaction = dateaction;
		this.duree = duree;
		this.avis = avis;
		this.animationc = animationc;
		this.organisationc = organisationc;
		this.totalec = totalec;
		this.sousparag = sousparag;
		this.etat = etat;
		this.animationcop = animationcop;
		this.organisationcop = organisationcop;
		this.totalecop = totalecop;
		this.formateur = formateur;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public Date getDateaction() {
		return dateaction;
	}

	public void setDateaction(Date dateaction) {
		this.dateaction = dateaction;
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public Double getAnimationc() {
		return animationc;
	}

	public void setAnimationc(Double animationc) {
		this.animationc = animationc;
	}

	public Double getOrganisationc() {
		return organisationc;
	}

	public void setOrganisationc(Double organisationc) {
		this.organisationc = organisationc;
	}

	public Double getTotalec() {
		return totalec;
	}

	public void setTotalec(Double totalec) {
		this.totalec = totalec;
	}

	public String getSousparag() {
		return sousparag;
	}

	public void setSousparag(String sousparag) {
		this.sousparag = sousparag;
	}

	
	public int getEtat() {
		return etat;
	}



	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Double getAnimationcop() {
		return animationcop;
	}

	public void setAnimationcop(Double animationcop) {
		this.animationcop = animationcop;
	}

	public Double getOrganisationcop() {
		return organisationcop;
	}

	public void setOrganisationcop(Double organisationcop) {
		this.organisationcop = organisationcop;
	}

	public Double getTotalecop() {
		return totalecop;
	}

	public void setTotalecop(Double totalecop) {
		this.totalecop = totalecop;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Set<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	
	
}
