package edu.orsys.jee.projet;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String login;
	private String password;
	private String role;
	private String nom;
	private String prenom;
	private String email;


	public User() {
		super();
	}
	

	public User(String login, String password, String role, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


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


	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", pwd=" + password
				+ ", role=" + role +", email=" + email +"]";
	}
	
	
}
