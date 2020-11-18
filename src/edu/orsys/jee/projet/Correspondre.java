package edu.orsys.jee.projet;

public class Correspondre {
	private int id;
	private int id_vulnerabilite;
	private int id_version;
	private int id_solution;
	public Correspondre(int id_vulnerabilite, int id_version, int id_solution) {
		super();
		this.id_vulnerabilite = id_vulnerabilite;
		this.id_version = id_version;
		this.id_solution = id_solution;
	}
	public Correspondre() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_vulnerabilite() {
		return id_vulnerabilite;
	}
	public void setId_vulnerabilite(int id_vulnerabilite) {
		this.id_vulnerabilite = id_vulnerabilite;
	}
	public int getId_version() {
		return id_version;
	}
	public void setId_version(int id_version) {
		this.id_version = id_version;
	}
	public int getId_solution() {
		return id_solution;
	}
	public void setId_solution(int id_solution) {
		this.id_solution = id_solution;
	}
	
	@Override
	public String toString() {
		return "Correspondre [id= " + id + ", id vulnerablite= " + id_vulnerabilite + ", id version= " + id_version + ", id solution= " + id_solution 
				+"]";
	}
	
	
}
