package edu.orsys.jee.projet;

import java.sql.Date;

public class Vulnerabilite {
	private int id;
	private String reference;
	private String description;
	private int niveau_gravite;
	private String url_source;
	private Date created_at;	
	private String titre;
	
	
	public Vulnerabilite(String reference, String description, int niveau_gravite, String url_source, Date created_at, String titre) {
		super();
		this.reference = reference;
		this.description = description;
		this.niveau_gravite = niveau_gravite;
		this.url_source = url_source;
		this.created_at = created_at;
		this.titre = titre;
	}
	
	
	public Vulnerabilite() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNiveau_gravite() {
		return niveau_gravite;
	}
	public void setNiveau_gravite(int niveau_gravite) {
		this.niveau_gravite = niveau_gravite;
	}
	public String getUrl_source() {
		return url_source;
	}
	public void setUrl_source(String url_source) {
		this.url_source = url_source;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	@Override
	public String toString() {
		return "Vulnerabilite [id= " + id + "titre= " + titre +", reference= " + reference + ", description= " + description + ", niveau de gravite= " + niveau_gravite + ", url de la source= " + url_source
				+ ", cree le =" + created_at +"]";
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}
	
}
