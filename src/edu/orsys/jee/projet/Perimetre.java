package edu.orsys.jee.projet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.orsys.jee.Connecteur;
import edu.orsys.jee.Model;

public class Perimetre {
//	private int id;
	private String nom;
	private int id_user;
	private String logiciel_list;

	
	
	public Perimetre() {
		super();
	}
	public Perimetre(String nom, int id_user, String logiciel_list) {
		super();
		this.nom = nom;
		this.id_user = id_user;
		this.logiciel_list = logiciel_list;
	}
	

	public String getLogiciel_list() {
		return logiciel_list;
	}
	
	public String[] getLogiciel_tab() {	
		
		String[] listelog = this.getLogiciel_list().split(",");
		
		
		return listelog;
	}
	
	public void setLogiciel_list(String logiciel_list) {
		this.logiciel_list = logiciel_list;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public ArrayList<Correspondre> chercherBulettin() {
		ArrayList<Correspondre> listec = new ArrayList<Correspondre>();
		
		for(String s:this.getLogiciel_tab()) {
			for(Correspondre c:Model.getListeC()) {
				if(Model.chercherVersion(c.getId_version()).getLogiciel_name().equals(s)) {
					listec.add(c);
				}			
			}
		}
		return listec;
	}
	
	@Override
	public String toString() {
		return "Perimetre [nom=" + nom + ", id_user=" + id_user 
				+", logiciels=" + logiciel_list +"]";
	}
}
