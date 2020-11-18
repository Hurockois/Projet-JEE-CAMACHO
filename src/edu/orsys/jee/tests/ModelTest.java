package edu.orsys.jee.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.orsys.jee.Connecteur;
import edu.orsys.jee.Model;
import edu.orsys.jee.projet.Correspondre;
import edu.orsys.jee.projet.Logiciel;
import edu.orsys.jee.projet.Perimetre;
import edu.orsys.jee.projet.Setting;
//
import edu.orsys.jee.projet.User;
import edu.orsys.jee.projet.Version;
import edu.orsys.jee.projet.Vulnerabilite;

public class ModelTest {
	User userDeTest;
	String login = "Mon login de test";
	String password = "pwddetest";
	String role = "Mon role de test";
	String nom = "Mon nom de test";
	String prenom = "Mon prenom de test";
	String email = "Mon email de test";

	Vulnerabilite vulnerabiliteDeTest;
	String reference = "Ma reference de test";
	String description = "Ma description de test";
	int niveau_gravite = 1;
	String url_source = "Mon url de test";
	Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	Date created_at = date;
	String titre = "Mon titre de test";

	Logiciel logicielDeTest;
	String name = "Ma name de test";

	Correspondre correspondreDeTest;
	int id_vulnerabilite = -200;
	int id_version = 1;
	int id_solution = 1;

	Version versionDeTest;
	String logiciel_name = "Ma logiciel_name de test";
	String numero_version = "Ma version de test";

	Perimetre perimetreDeTest;
	int id_user = 0;
	String logiciel_list = "Ma logiciel_list de test";

	Setting settingDeTest;
	int user_id = -200;
	String frequence = "Ma frequence de test";
	String format = "Mon format de test";
	int gravite = 1;

	@Before
	public void setup() {
		userDeTest = new User(login, password, role, nom, prenom, email);
		vulnerabiliteDeTest = new Vulnerabilite(reference, description, niveau_gravite, url_source, created_at, titre);
		logicielDeTest = new Logiciel(name);
		correspondreDeTest = new Correspondre(id_vulnerabilite, id_version, id_solution);
		versionDeTest = new Version(logiciel_name, numero_version);
		perimetreDeTest = new Perimetre(nom, id_user, logiciel_list);
		settingDeTest = new Setting(user_id, frequence, format, gravite);
	}

	@Test
	public void AllGettersAndSetterOnEntityLists() {
		ArrayList<User> listeUtest = Model.getListeU();
		ArrayList<Vulnerabilite> listeVtest = Model.getListeV();
		ArrayList<Logiciel> listeLtest = Model.getListeL();
		ArrayList<Correspondre> listeCtest = Model.getListeC();
		ArrayList<Version> listeVetest = Model.getListeVe();
		ArrayList<Perimetre> listePtest = Model.getListeP();
		ArrayList<Setting> listeStest = Model.getListeS();

		Model.setListeU(listeUtest);
		Model.setListeV(listeVtest);
		Model.setListeL(listeLtest);
		Model.setListeC(listeCtest);
		Model.setListeVe(listeVetest);
		Model.setListeP(listePtest);
		Model.setListeS(listeStest);

	}

	@Test
	public void ConnectionGetterAndSetter() {
		Connection connection = Model.getConn();
		Model.setConn(connection);
	}

	@Test
	public void CreerUneEntiteUser() {

		// Check entity setters:
		userDeTest.setLogin(login);
		userDeTest.setPassword(password);
		userDeTest.setRole(role);
		userDeTest.setNom(nom);
		userDeTest.setPrenom(prenom);
		userDeTest.setEmail(email);

		// Check entity getters:
		Assert.assertEquals("Mon nom de test", userDeTest.getNom());
		Assert.assertEquals("Mon login de test", userDeTest.getLogin());
		Assert.assertEquals("pwddetest", userDeTest.getPassword());
		Assert.assertEquals("Mon role de test", userDeTest.getRole());
		Assert.assertEquals("Mon prenom de test", userDeTest.getPrenom());
		Assert.assertEquals("Mon email de test", userDeTest.getEmail());
	}

	@Test
	public void AjouterEntiteUserDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();
			
//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterUser(userDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertTrue(rs2.next());
			assertEquals(login, rs2.getString(2));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerUser("Mon login de test");
			ResultSet rs3 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntiteUserDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		User userDeTest2 = new User(login + "2", password, role, nom, prenom, email);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st
					.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test' OR login='Mon login de test2'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterUser(userDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertTrue(rs2.next());
			assertEquals(login, rs2.getString(2));
			int id = rs2.getInt(1);
//			On modifie l'entite ajoutée
			Model.modifierUser(id, userDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test2'");
			assertTrue(rs4.next());
			assertEquals(userDeTest2.getLogin(), rs4.getString(2));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerUser(userDeTest2.getLogin());
			ResultSet rs5 = st
					.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'OR login='Mon login de test2'");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteUserDansDatabaseByLogin() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterUser(userDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertTrue(rs2.next());
			assertEquals(login, rs2.getString(2));
//			On supprime l'entite
			Model.supprimerUser("Mon login de test");
			ResultSet rs3 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteUserDansDatabaseById() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterUser(userDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
			assertTrue(rs2.next());
			assertEquals(login, rs2.getString(2));
			int id = rs2.getInt(1);
//			On supprime l'entite
			Model.supprimerUser(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM users WHERE login= 'Mon login de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherUserParLoginEtPassword() {
//		verification que la liste existe
		assertNotNull(Model.listeU);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		try {
			Model.ajouterUser(userDeTest);
		} catch (SQLException e) {
			fail(e.toString());
		}
//		assertion		
		assertEquals(userDeTest.getLogin(), Model.chercherUser(login, password).getLogin());
		assertEquals(userDeTest.getNom(), Model.chercherUser(login, password).getNom());
		assertEquals(userDeTest.getPrenom(), Model.chercherUser(login, password).getPrenom());
		assertEquals(userDeTest.getRole(), Model.chercherUser(login, password).getRole());
		assertEquals(userDeTest.getEmail(), Model.chercherUser(login, password).getEmail());
		assertEquals(userDeTest.getPassword(), Model.chercherUser(login, password).getPassword());
//		rollback (fonction supprimer testée ailleurs)
		try {
			Model.supprimerUser(userDeTest.getLogin());
		} catch (SQLException e) {
			fail(e.toString());
		}
//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherUser("testnull", "testnull"));

	}

	@Test
	public void ChercherUserParLogin() {
//		verification que la liste existe
		assertNotNull(Model.listeU);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		try {
			Model.ajouterUser(userDeTest);
		} catch (SQLException e) {
			fail(e.toString());
		}
//		assertion		
		assertEquals(userDeTest.getLogin(), Model.chercherUser(login).getLogin());
		assertEquals(userDeTest.getNom(), Model.chercherUser(login).getNom());
		assertEquals(userDeTest.getPrenom(), Model.chercherUser(login).getPrenom());
		assertEquals(userDeTest.getRole(), Model.chercherUser(login).getRole());
		assertEquals(userDeTest.getEmail(), Model.chercherUser(login).getEmail());
		assertEquals(userDeTest.getPassword(), Model.chercherUser(login).getPassword());
//		rollback (fonction supprimer testée ailleurs)
		try {
			Model.supprimerUser(userDeTest.getLogin());
		} catch (SQLException e) {
			fail(e.toString());
		}
//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherUser("testnull"));

	}

	@Test
	public void ChercherUserParId() {
//		verification que la liste existe
		assertNotNull(Model.listeU);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		try {
			Model.ajouterUser(userDeTest);
		} catch (SQLException e) {
			fail(e.toString());
		}
//		assertion
		User user = Model.chercherUser(login);
		assertEquals(userDeTest.getNom(), Model.chercherUser(user.getId()).getNom());

//		rollback (fonction supprimer testée ailleurs)
		try {
			Model.supprimerUser(userDeTest.getLogin());
		} catch (SQLException e) {
			fail(e.toString());
		}
//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherUser(1000000));

	}

	@Test
	public void CreerUneEntiteVulnerabilite() {

		// Check entity setters:
		vulnerabiliteDeTest.setCreated_at(created_at);
		vulnerabiliteDeTest.setDescription(description);
		vulnerabiliteDeTest.setTitre(titre);
		vulnerabiliteDeTest.setNiveau_gravite(niveau_gravite);
		vulnerabiliteDeTest.setReference(reference);
		vulnerabiliteDeTest.setUrl_source(url_source);

		// Check entity getters:
		Assert.assertEquals(date, vulnerabiliteDeTest.getCreated_at());
		Assert.assertEquals("Ma description de test", vulnerabiliteDeTest.getDescription());
		Assert.assertEquals("Mon titre de test", vulnerabiliteDeTest.getTitre());
		Assert.assertEquals(1, vulnerabiliteDeTest.getNiveau_gravite());
		Assert.assertEquals("Ma reference de test", vulnerabiliteDeTest.getReference());
		Assert.assertEquals("Mon url de test", vulnerabiliteDeTest.getUrl_source());
	}

	@Test
	public void AjouterEntiteVulnerabiliteDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterVulnerabilite(vulnerabiliteDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertTrue(rs2.next());
			assertEquals(reference, rs2.getString(2));
			int id = rs2.getInt(1);
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerVulnerabilite(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntiteVulnerabiliteDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		Vulnerabilite vulnerabiliteDeTest2 = new Vulnerabilite(reference + "2", description, niveau_gravite, url_source,
				created_at, titre);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery(
					"SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test' OR reference='Ma reference de test2'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterVulnerabilite(vulnerabiliteDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertTrue(rs2.next());
			assertEquals(reference, rs2.getString(2));
			int id = rs2.getInt(1);
//			On modifie l'entite ajoutée
			Model.modifierVulnerabilite(id, vulnerabiliteDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test2'");
			assertTrue(rs4.next());
			assertEquals(vulnerabiliteDeTest2.getReference(), rs4.getString(2));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerVulnerabilite(id);
			ResultSet rs5 = st.executeQuery(
					"SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'OR reference='Ma reference de test2'");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteVulnerabiliteDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterVulnerabilite(vulnerabiliteDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertTrue(rs2.next());
			assertEquals(reference, rs2.getString(2));
			int id = rs2.getInt(1);
//			On supprime l'entite
			Model.supprimerVulnerabilite(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherVulnerabilite() {
//		verification que la liste existe
		assertNotNull(Model.listeV);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		Integer id = null;
		Model.ajouterVulnerabilite(vulnerabiliteDeTest);
		try {
			// On recupere l'id de l'entite de test
			Connection connection = Connecteur.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs1 = st.executeQuery("SELECT * FROM vulnerabilites WHERE reference= 'Ma reference de test'");
			assertTrue(rs1.next());
			id = rs1.getInt(1);
		} catch (SQLException e) {
			fail(e.toString());
		}

//		assertion	
		assertEquals(vulnerabiliteDeTest.getDescription(), Model.chercherVulnerabilite(id).getDescription());
		assertEquals(vulnerabiliteDeTest.getNiveau_gravite(), Model.chercherVulnerabilite(id).getNiveau_gravite());
		assertEquals(vulnerabiliteDeTest.getReference(), Model.chercherVulnerabilite(id).getReference());
		assertEquals(vulnerabiliteDeTest.getTitre(), Model.chercherVulnerabilite(id).getTitre());
		assertEquals(vulnerabiliteDeTest.getUrl_source(), Model.chercherVulnerabilite(id).getUrl_source());
		Model.supprimerVulnerabilite(id);

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherVulnerabilite(1));
	}

	@Test

	public void CreerUneEntiteLogiciel() {

		// Check entity setters:
		logicielDeTest.setName(name);

		// Check entity getters:
		Assert.assertEquals(name, logicielDeTest.getName());

	}

	@Test
	public void AjouterEntiteLogicielDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterLogiciel(logicielDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
			assertTrue(rs2.next());
			assertEquals(name, rs2.getString(1));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerLogiciel(name);
			ResultSet rs3 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteLogicielDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterLogiciel(logicielDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
			assertTrue(rs2.next());
			assertEquals(name, rs2.getString(1));
//			On supprime l'entite
			Model.supprimerLogiciel(name);
			ResultSet rs3 = st.executeQuery("SELECT * FROM logiciels WHERE name= 'Ma name de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherLogiciel() {
//		verification que la liste existe
		assertNotNull(Model.listeL);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)		
		Model.ajouterLogiciel(logicielDeTest);

//		assertion	
		assertEquals(logicielDeTest.getName(), Model.chercherLogiciel(name).getName());

//		Rollback
		Model.supprimerLogiciel(name);

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherLogiciel("test null"));
	}

	@Test
	public void CreerUneEntiteCorrespondre() {

		// Check entity setters:
		correspondreDeTest.setId_solution(id_solution);
		correspondreDeTest.setId_version(id_version);
		;
		correspondreDeTest.setId_vulnerabilite(id_vulnerabilite);
		;

		// Check entity getters:
		Assert.assertEquals(1, correspondreDeTest.getId_solution());
		Assert.assertEquals(1, correspondreDeTest.getId_version());
		Assert.assertEquals(-200, correspondreDeTest.getId_vulnerabilite());

	}

	@Test
	public void AjouterEntiteCorrespondreDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterCorrespondre(correspondreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertTrue(rs2.next());
			assertEquals(id_vulnerabilite, rs2.getInt(1));
			int id = rs2.getInt(3);
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerCorrespondre(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntiteCorrespondreDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		Correspondre correspondreDeTest2 = new Correspondre(id_vulnerabilite + 1, id_version, id_solution);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st
					.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200 OR id_vulnerabilite=-199");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterCorrespondre(correspondreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertTrue(rs2.next());
			assertEquals(id_vulnerabilite, rs2.getInt(1));
			int id = rs2.getInt(3);
//			On modifie l'entite ajoutée
			Model.modifierCorrespondre(id, correspondreDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -199");
			assertTrue(rs4.next());
			assertEquals(correspondreDeTest2.getId_vulnerabilite(), rs4.getInt(1));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerCorrespondre(id);
			ResultSet rs5 = st
					.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200 OR id_vulnerabilite=-199");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteCorrespondreDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterCorrespondre(correspondreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertTrue(rs2.next());
			assertEquals(id_vulnerabilite, rs2.getInt(1));
			int id = rs2.getInt(3);
//			On supprime l'entite
			Model.supprimerCorrespondre(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherCorrespondre() {
//		verification que la liste existe
		assertNotNull(Model.listeV);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		Integer id = null;
		Model.ajouterCorrespondre(correspondreDeTest);
		try {
			// On recupere l'id de l'entite de test
			Connection connection = Connecteur.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs1 = st.executeQuery("SELECT * FROM correspondre WHERE id_vulnerabilite= -200");
			assertTrue(rs1.next());
			id = rs1.getInt(3);
		} catch (SQLException e) {
			fail(e.toString());
		}

//		assertion	
		assertEquals(correspondreDeTest.getId_solution(), Model.chercherCorrespondre(id).getId_solution());
		assertEquals(correspondreDeTest.getId_version(), Model.chercherCorrespondre(id).getId_version());
		assertEquals(correspondreDeTest.getId_vulnerabilite(), Model.chercherCorrespondre(id).getId_vulnerabilite());

		Model.supprimerCorrespondre(id);

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherCorrespondre(10000));
	}

	@Test
	public void CreerUneEntiteVersion() {

		// Check entity setters:

		versionDeTest.setLogiciel_name(logiciel_name);
		versionDeTest.setNumero_version(numero_version);

		// Check entity getters:
		Assert.assertEquals("Ma version de test", versionDeTest.getNumero_version());
		Assert.assertEquals("Ma logiciel_name de test", versionDeTest.getLogiciel_name());

	}

	@Test
	public void AjouterEntiteVersionDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterVersion(versionDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertTrue(rs2.next());
			assertEquals(logiciel_name, rs2.getString(1));
			int id = rs2.getInt(3);
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerVersion(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntiteVersionDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		Version versionDeTest2 = new Version(logiciel_name + "2", numero_version);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery(
					"SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test' OR logiciel_name='Ma logiciel_name de test2'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterVersion(versionDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertTrue(rs2.next());
			assertEquals(logiciel_name, rs2.getString(1));
			int id = rs2.getInt(3);
//			On modifie l'entite ajoutée
			Model.modifierVersion(id, versionDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test2'");
			assertTrue(rs4.next());
			assertEquals(versionDeTest2.getLogiciel_name(), rs4.getString(1));
			assertEquals(versionDeTest2.getNumero_version(), rs4.getString(2));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerVersion(id);
			ResultSet rs5 = st.executeQuery(
					"SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test' OR logiciel_name='Ma logiciel_name de test2'");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteVersionDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterVersion(versionDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertTrue(rs2.next());
			assertEquals(logiciel_name, rs2.getString(1));
			int id = rs2.getInt(3);
//			On supprime l'entite
			Model.supprimerVersion(id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherVersion() {
//		verification que la liste existe
		assertNotNull(Model.listeV);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)
		Integer id = null;
		Model.ajouterVersion(versionDeTest);
		try {
			// On recupere l'id de l'entite de test
			Connection connection = Connecteur.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs1 = st.executeQuery("SELECT * FROM versions WHERE logiciel_name= 'Ma logiciel_name de test'");
			assertTrue(rs1.next());
			id = rs1.getInt(3);
		} catch (SQLException e) {
			fail(e.toString());
		}

//		assertion	
		assertEquals(versionDeTest.getLogiciel_name(), Model.chercherVersion(id).getLogiciel_name());
		assertEquals(versionDeTest.getNumero_version(), Model.chercherVersion(id).getNumero_version());

		Model.supprimerVersion(id);

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherVersion(-10000));
	}

	@Test
	public void CreerUneEntitePerimetre() {

		// Check entity setters:
		perimetreDeTest.setId_user(id_user);
		perimetreDeTest.setLogiciel_list(logiciel_list);
		perimetreDeTest.setNom(nom);

		// Check entity getters:
		Assert.assertEquals(0, perimetreDeTest.getId_user());
		Assert.assertEquals("Ma logiciel_list de test", perimetreDeTest.getLogiciel_list());
		Assert.assertEquals("Mon nom de test", perimetreDeTest.getNom());

	}

	@Test
	public void AjouterEntitePerimetreDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test' AND id_user=0");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterPerimetre(perimetreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test' AND id_user=0");
			assertTrue(rs2.next());
			assertEquals(nom, rs2.getString(1));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerPerimetre(nom, id_user);
			ResultSet rs3 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test' AND id_user=0");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntitePerimetreDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		Perimetre perimetreDeTest2 = new Perimetre(nom + "2", id_user, logiciel_list);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st
					.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test' OR nom='Mon nom de test2'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterPerimetre(perimetreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test'");
			assertTrue(rs2.next());
			assertEquals(nom, rs2.getString(1));
			assertEquals(id_user, rs2.getInt(2));

//			On modifie l'entite ajoutée
			Model.modifierPerimetre(nom, id_user, perimetreDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test'");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test2'");
			assertTrue(rs4.next());
			assertEquals(perimetreDeTest2.getNom(), rs4.getString(1));
			assertEquals(perimetreDeTest2.getId_user(), rs4.getInt(2));

//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerPerimetre(perimetreDeTest2.getNom(), perimetreDeTest2.getId_user());
			ResultSet rs5 = st
					.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test' OR nom= 'Mon nom de test2'");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntitePerimetreDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test'");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterPerimetre(perimetreDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test'");
			assertTrue(rs2.next());
			assertEquals(nom, rs2.getString(1));
//			On supprime l'entite
			Model.supprimerPerimetre(nom, id_user);
			;
			ResultSet rs3 = st.executeQuery("SELECT * FROM perimetres WHERE nom= 'Mon nom de test'");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherPerimetre() {
//		verification que la liste existe
		assertNotNull(Model.listeV);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)		
		Model.ajouterPerimetre(perimetreDeTest);

//		assertion	
		assertEquals(perimetreDeTest.getId_user(), Model.chercherPerimetre(nom, id_user).getId_user());
		assertEquals(perimetreDeTest.getNom(), Model.chercherPerimetre(nom, id_user).getNom());
		assertEquals(perimetreDeTest.getLogiciel_list(), Model.chercherPerimetre(nom, id_user).getLogiciel_list());
		Model.supprimerPerimetre(nom, id_user);
		;

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherPerimetre("Null de test", -3000));
	}

	@Test
	public void CreerUneEntiteSetting() {

		// Check entity setters:
		settingDeTest.setUser_id(user_id);
		settingDeTest.setFrequence(frequence);
		settingDeTest.setFormat(format);
		settingDeTest.setGravite(gravite);

		// Check entity getters:
		Assert.assertEquals(-200, settingDeTest.getUser_id());
		Assert.assertEquals("Ma frequence de test", settingDeTest.getFrequence());
		Assert.assertEquals("Mon format de test", settingDeTest.getFormat());
		Assert.assertEquals(1, settingDeTest.getGravite());

	}

	@Test
	public void AjouterEntiteSettingDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();

		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterSetting(settingDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertTrue(rs2.next());
			assertEquals(user_id, rs2.getInt(1));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerSetting(user_id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ModifierEntiteSettingDansDatabase() throws Exception {

		Connection connection = Connecteur.getConnection();
		Setting settingDeTest2 = new Setting(user_id + 1, frequence, format, gravite);
		try {
			Statement st = connection.createStatement();

//			Verif que les entites de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200 OR user_id=-199");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (méthode testée ici)
			Model.ajouterSetting(settingDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertTrue(rs2.next());
			assertEquals(user_id, rs2.getInt(1));
//			On modifie l'entite ajoutée
			Model.modifierSetting(user_id, settingDeTest2);
//			On verifie que l'entree originale a bien été modifiée
			ResultSet rs3 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertFalse(rs3.next());
			ResultSet rs4 = st.executeQuery("SELECT * FROM settings WHERE user_id= -199");
			assertTrue(rs4.next());
			assertEquals(settingDeTest2.getUser_id(), rs4.getInt(1));
//			RollBack (methode supprimer testée dans autre test)
			Model.supprimerSetting(rs4.getInt(1));
			ResultSet rs5 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200 OR user_id=-199");
			assertFalse(rs5.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void SupprimerEntiteSettingDansDatabase() throws Exception {
		Connection connection = Connecteur.getConnection();
		try {
			Statement st = connection.createStatement();

//			Verif que l'entite de test n'existe pas dans la DB
			ResultSet rs1 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertFalse(rs1.next());
//			On ajoute l'entite de test dans la DB (methode ajouter testée dans autre test)
			Model.ajouterSetting(settingDeTest);
//			On verifie que l'entite a bien été ajoutée
			ResultSet rs2 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
			assertTrue(rs2.next());
			assertEquals(user_id, rs2.getInt(1));
//			On supprime l'entite
			Model.supprimerSetting(user_id);
			ResultSet rs3 = st.executeQuery("SELECT * FROM settings WHERE user_id= -200");
//			On verifie qu'elle a bien été supprimée (méthode testée ici)
			assertFalse(rs3.next());

		} catch (SQLException e) {
			fail(e.toString());
		} finally {
			connection.close();
		}
	}

	@Test
	public void ChercherSetting() {
//		verification que la liste existe
		assertNotNull(Model.listeS);
//		ajout de l'entite de test dans la DB (fonction ajouter testée ailleurs)

		Model.ajouterSetting(settingDeTest);

//		assertion	
		assertEquals(settingDeTest.getUser_id(), Model.chercherSetting(user_id).getUser_id());
		assertEquals(settingDeTest.getFrequence(), Model.chercherSetting(user_id).getFrequence());
		assertEquals(settingDeTest.getFormat(), Model.chercherSetting(user_id).getFormat());
		assertEquals(settingDeTest.getGravite(), Model.chercherSetting(user_id).getGravite());

		Model.supprimerSetting(user_id);

//		Verification de retour null si l'entite n'existe pas
		assertNull(Model.chercherSetting(-10000));
	}

}
