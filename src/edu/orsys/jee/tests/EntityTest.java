package edu.orsys.jee.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.orsys.jee.projet.Correspondre;
import edu.orsys.jee.projet.Logiciel;
import edu.orsys.jee.projet.Perimetre;
import edu.orsys.jee.projet.User;
import edu.orsys.jee.projet.Version;
import edu.orsys.jee.projet.Vulnerabilite;

public class EntityTest {

	@Test
	public void toStringMethods() {
		User u = new User();
		u.toString();
		Vulnerabilite v = new Vulnerabilite();
		v.toString();
		Version ve = new Version();
		ve.toString();
		Logiciel l = new Logiciel();
		l.toString();
		Correspondre c = new Correspondre();
		c.toString();
		Perimetre p = new Perimetre();
		p.toString();
	}

}
