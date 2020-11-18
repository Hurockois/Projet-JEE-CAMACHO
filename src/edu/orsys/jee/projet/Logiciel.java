package edu.orsys.jee.projet;

public class Logiciel {
	
	private String name;
	public Logiciel(String name) {	
		this.name = name;
	}
	public Logiciel() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	@Override
	public String toString() {
		return "Logiciel [nom=" + name +"]";
	}
 
 
}
