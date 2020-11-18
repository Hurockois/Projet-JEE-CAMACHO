package edu.orsys.jee.projet;

public class Version {
	private int id;
	private String logiciel_name;
	private String numero_version;
	public Version(String logiciel_name, String numero_version) {
		super();
		this.logiciel_name = logiciel_name;
		this.numero_version = numero_version;
	}
	public Version() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLogiciel_name() {
		return logiciel_name;
	}
	public void setLogiciel_name(String logiciel_name) {
		this.logiciel_name = logiciel_name;
	}
	public String getNumero_version() {
		return numero_version;
	}
	public void setNumero_version(String numero_version) {
		this.numero_version = numero_version;
	}
	
	
}
