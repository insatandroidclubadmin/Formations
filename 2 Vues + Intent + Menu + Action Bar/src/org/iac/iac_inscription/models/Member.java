package org.iac.iac_inscription.models;

import java.io.Serializable;

public class Member  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	String nom;
	String prenom;
	String atelier;
	
	
	public Member(String nom, String prenom, String atelier) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.atelier = atelier;
	}
	
	public Member(){
	}

	//getters and setters
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAtelier() {
		return atelier;
	}

	public void setAtelier(String atelier) {
		this.atelier = atelier;
	}

	
}
