package edu.orsys.jee.projet;

public class Setting {

	private int user_id;
	private String frequence;
	private String format;
	private int gravite;

	public Setting(int user_id, String frequence, String format, int gravite) {
		this.user_id = user_id;
		this.frequence = frequence;
		this.format = format;
		this.gravite = gravite;
	}

	public Setting() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getGravite() {
		return gravite;
	}

	public void setGravite(int gravite) {
		this.gravite = gravite;
	}

}
