package edu.orsys.jee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.orsys.jee.projet.Correspondre;
import edu.orsys.jee.projet.Logiciel;
import edu.orsys.jee.projet.Perimetre;
import edu.orsys.jee.projet.Setting;
import edu.orsys.jee.projet.User;
import edu.orsys.jee.projet.Version;
import edu.orsys.jee.projet.Vulnerabilite;


public class Model {
	
	
	public static ArrayList<User> listeU = versCollectionU();
	public static ArrayList<Vulnerabilite> listeV = versCollectionV();
	public static ArrayList<Logiciel> listeL = versCollectionL();
	public static ArrayList<Correspondre> listeC = versCollectionC();
	public static ArrayList<Version> listeVe = versCollectionVe();
	public static ArrayList<Perimetre> listeP = versCollectionP();
	public static ArrayList<Setting> listeS = versCollectionS();
	
	public static ArrayList<Correspondre> getListeC() {
		return listeC;
	}
	
	public static ArrayList<Setting> getListeS() {
		return listeS;
	}

	public static void setListeS(ArrayList<Setting> listeS) {
		Model.listeS = listeS;
	}

	public static ArrayList<Version> getListeVe() {
		return listeVe;
	}

	public static void setListeVe(ArrayList<Version> listeVe) {
		Model.listeVe = listeVe;
	}

	public static void setListeC(ArrayList<Correspondre> listeC) {
		Model.listeC = listeC;
	}

	public static Connection conn = Connecteur.getConnection();
	
	public static ArrayList<User> getListeU() {
		return listeU;
	}

	public static ArrayList<Logiciel> getListeL() {
		return listeL;
	}

	public static void setListeL(ArrayList<Logiciel> listeL) {
		Model.listeL = listeL;
	}

	public static void setListeU(ArrayList<User> listeU) {
		Model.listeU = listeU;
	}

	
	public static ArrayList<Vulnerabilite> getListeV() {
		return listeV;
	}

	public static void setListeV(ArrayList<Vulnerabilite> listeV) {
		Model.listeV = listeV;
	}
	
	public static ArrayList<Perimetre> getListeP() {
		return listeP;
	}
	
	public static void setListeP(ArrayList<Perimetre> listeP) {
		Model.listeP = listeP;
	}
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		Model.conn = conn;
	}

	public static void ajouterUser(User u) throws SQLException {
		try {
			//Statement st = conn.createStatement();
			
			PreparedStatement prst = conn.prepareStatement(
					"insert into users(login, password , role, nom, prenom, email) value(?,?,?,?,?,?)");
			
			prst.setString(1, u.getLogin());
			prst.setString(2, u.getPassword());
			prst.setString(3, u.getRole());
			prst.setString(4, u.getNom());			
			prst.setString(5, u.getPrenom());
			prst.setString(6, u.getEmail());
			prst.executeUpdate();
			
			listeU = versCollectionU();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	
	}

	public static User chercherUser(String login, String pwd) {
		for (User u : listeU) {
			if (u.getLogin().equals(login) && u.getPassword().equals(pwd))
				return u;
		}
		return null;
	}
	
	public static User chercherUser(String login) {
		for (User u : listeU) {
			if (u.getLogin().equals(login))
				return u;
		}
		return null;
	}
	
	public static User chercherUser(int id) {
		for (User u : listeU) {
			if (u.getId()==id)
				return u;
		}
		return null;
	}

	public static void modifierUser(int id, User nvu) throws SQLException {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update users set login = ?, password = ?, nom=? , prenom=?, email=? where id= ?");
			prst.setString(1, nvu.getLogin());
			prst.setString(2, nvu.getPassword());
			prst.setString(3, nvu.getNom());
			prst.setString(4, nvu.getPrenom());
			prst.setString(5, nvu.getEmail());
			prst.setInt(6, id);

			prst.executeUpdate();
			listeU = versCollectionU();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void supprimerUser(int id) throws SQLException {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from users where id = ?");
			prst.setInt(1, id);
			prst.executeUpdate();
			listeU = versCollectionU();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void supprimerUser(String login) throws SQLException {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from users where login = ?");
			prst.setString(1, login);
			prst.executeUpdate();
			listeU = versCollectionU();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> versCollectionU() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<User> listeu = new ArrayList<User>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from users");
			if (rs != null) {
				while (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setLogin(rs.getString("login"));
					u.setPassword(rs.getString("password"));
					u.setRole(rs.getString("role"));
					u.setNom(rs.getString("nom"));
					u.setPrenom(rs.getString("prenom"));
					u.setEmail(rs.getString("email"));
					
					listeu.add(u);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeu;
	}

	public static void ajouterVulnerabilite(Vulnerabilite v) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into vulnerabilites(reference, description , niveau_gravite, url_source, created_at, titre) value(?,?,?,?,?,?)");
			//prst.setLong(1, p.getCode());
			prst.setString(1, v.getReference());
			prst.setString(2, v.getDescription());
			prst.setInt(3, v.getNiveau_gravite());
			prst.setString(4, v.getUrl_source());			
			prst.setDate(5, v.getCreated_at());		
			prst.setString(6, v.getTitre());	
			prst.execute();
			
			listeV = versCollectionV();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Vulnerabilite chercherVulnerabilite(int id) {
		for (Vulnerabilite v : listeV) {
			if (v.getId()==id)
				return v;
		}
		return null;
	}

	public static void modifierVulnerabilite(int id, Vulnerabilite nvv) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update vulnerabilites set reference = ?, description = ?, niveau_gravite=? , url_source=?, created_at=?,titre=? where id= ?");
			prst.setString(1, nvv.getReference());
			prst.setString(2, nvv.getDescription());
			prst.setInt(3, nvv.getNiveau_gravite());
			prst.setString(4, nvv.getUrl_source());
			prst.setDate(5, nvv.getCreated_at());
			prst.setString(6, nvv.getTitre());
			prst.setInt(7, id);

			prst.executeUpdate();
			listeV = versCollectionV();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void supprimerVulnerabilite(int id) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from Vulnerabilites where id = ?");
			prst.setInt(1, id);
			prst.executeUpdate();
			listeV = versCollectionV();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Vulnerabilite> versCollectionV() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Vulnerabilite> listev = new ArrayList<Vulnerabilite>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from Vulnerabilites");
			if (rs != null) {
				while (rs.next()) {
					Vulnerabilite v = new Vulnerabilite();
					v.setId(rs.getInt("id"));
					v.setReference(rs.getString("reference"));
					v.setDescription(rs.getString("description"));
					v.setNiveau_gravite(rs.getInt("niveau_gravite"));
					v.setUrl_source(rs.getString("url_source"));
					v.setCreated_at(rs.getDate("created_at"));
					v.setTitre(rs.getString("titre"));
					
					listev.add(v);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listev;
	}

	public static void ajouterLogiciel(Logiciel l) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into Logiciels(name) value(?)");
			//prst.setLong(1, p.getCode());
			prst.setString(1, l.getName());
		
			prst.execute();
			
			listeL = versCollectionL();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Logiciel chercherLogiciel(String name) {
		for (Logiciel l : listeL) {
			if (l.getName().equals(name))
				return l;
		}
		return null;
	}

	
	public static void supprimerLogiciel(String name) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from Logiciels where name = ?");
			prst.setString(1, name);
			prst.executeUpdate();
			listeL = versCollectionL();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Logiciel> versCollectionL() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Logiciel> listel = new ArrayList<Logiciel>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from logiciels");
			if (rs != null) {
				while (rs.next()) {					
					Logiciel l = new Logiciel();					
					l.setName(rs.getString("name"));

					
					listel.add(l);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listel;
	}
	
	public static void ajouterCorrespondre(Correspondre c) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into correspondre(id_vulnerabilite, id_version , id_solution) value(?,?,?)");
			//prst.setLong(1, p.getCode());
			prst.setInt(1, c.getId_vulnerabilite());
			prst.setInt(2, c.getId_version());
			prst.setInt(3, c.getId_solution());
			prst.execute();
			
			listeC = versCollectionC();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Correspondre chercherCorrespondre(int id) {
		for (Correspondre c : listeC) {
			if (c.getId()==id)
				return c;
		}
		return null;
	}

	public static void modifierCorrespondre(int id, Correspondre nvc) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update correspondre set id_vulnerabilite = ?,id_version = ?,id_solution = ?  where id= ?");
			prst.setInt(1, nvc.getId_vulnerabilite());
			prst.setInt(2, nvc.getId_version());
			prst.setInt(3, nvc.getId_solution());
			prst.setInt(4, id);

			prst.executeUpdate();
			listeC = versCollectionC();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void supprimerCorrespondre(int id) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from Correspondre where id = ?");
			prst.setInt(1, id);
			prst.executeUpdate();
			listeC = versCollectionC();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Correspondre> versCollectionC() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Correspondre> listec = new ArrayList<Correspondre>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from Correspondre");
			if (rs != null) {
				while (rs.next()) {
					Correspondre c = new Correspondre();
					c.setId(rs.getInt("id"));
					c.setId_vulnerabilite(rs.getInt("id_vulnerabilite"));
					c.setId_version(rs.getInt("id_version"));
					c.setId_solution(rs.getInt("id_solution"));

					
					listec.add(c);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listec;
	}
	
	public static void ajouterVersion(Version ve) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into versions(logiciel_name, numero_version) value(?,?)");
			//prst.setLong(1, p.getCode());
			prst.setString(1, ve.getLogiciel_name());
			prst.setString(2, ve.getNumero_version());
			prst.execute();
			
			listeVe = versCollectionVe();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Version chercherVersion(int id) {
		for (Version ve : listeVe) {
			if (ve.getId()==id)
				return ve;
		}
		return null;
	}

	public static void modifierVersion(int id, Version nvve) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update versions set logiciel_name = ?,numero_version = ?  where id= ?");
			prst.setString(1, nvve.getLogiciel_name());
			prst.setString(2, nvve.getNumero_version());			
			prst.setInt(3, id);

			prst.executeUpdate();
			listeVe = versCollectionVe();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void supprimerVersion(int id) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from versions where id = ?");
			prst.setInt(1, id);
			prst.executeUpdate();
			listeVe = versCollectionVe();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Version> versCollectionVe() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Version> listeve = new ArrayList<Version>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from versions");
			if (rs != null) {
				while (rs.next()) {
					Version v = new Version();
					v.setId(rs.getInt("id"));
					v.setLogiciel_name(rs.getString("logiciel_name"));
					v.setNumero_version(rs.getString("numero_version"));				

					
					listeve.add(v);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeve;
	}
	
	public static void ajouterPerimetre(Perimetre p) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into perimetres(nom, id_user,logiciel_list) value(?,?,?)");
			//prst.setLong(1, p.getCode());
			prst.setString(1, p.getNom());
			prst.setInt(2, p.getId_user());
			prst.setString(3, p.getLogiciel_list());
			prst.execute();
			
			listeP = versCollectionP();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Perimetre chercherPerimetre(String nom, Integer id_user) {
		for (Perimetre p : listeP) {
			if (p.getNom().equals(nom) && p.getId_user()==id_user)
				return p;
		}
		return null;
	}

	public static void modifierPerimetre(String nom,int id_user, Perimetre nvp) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update perimetres set nom = ?,id_user = ?,logiciel_list=?  where nom= ? AND id_user=?");
			prst.setString(1, nvp.getNom());
			prst.setInt(2, nvp.getId_user());
			prst.setString(3, nvp.getLogiciel_list());
			prst.setString(4, nom);
			prst.setInt(5, id_user);
			

			prst.executeUpdate();
			listeP = versCollectionP();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void supprimerPerimetre(String nom,int id_user) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from perimetres where nom = ? AND id_user=?");
			prst.setString(1, nom);
			prst.setInt(2, id_user);
			prst.executeUpdate();
			listeP = versCollectionP();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Perimetre> versCollectionP() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Perimetre> listep = new ArrayList<Perimetre>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from perimetres");
			if (rs != null) {
				while (rs.next()) {
					Perimetre p = new Perimetre();
					p.setNom(rs.getString("nom"));
					p.setId_user(rs.getInt("id_user"));
					p.setLogiciel_list(rs.getString("logiciel_list"));

					
					listep.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listep;
	}




	public static void ajouterSetting(Setting s) {
		try {
			//Statement st = conn.createStatement();
			PreparedStatement prst = conn.prepareStatement(
					"insert into settings(user_id, frequence, format, gravite) value(?,?,?,?)");
			//prst.setLong(1, p.getCode());
			prst.setInt(1, s.getUser_id());
			prst.setString(2, s.getFrequence());
			prst.setString(3, s.getFormat());
			prst.setInt(4, s.getGravite());
			prst.execute();
			
			listeS = versCollectionS();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
	}

	
	public static Setting chercherSetting(int user_id) {
		for (Setting s : listeS) {
			if (s.getUser_id()==user_id)
				return s;
		}
		return null;
	}

	public static void modifierSetting(int user_id, Setting nvs) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"update settings set user_id = ?,frequence = ?, format=?, gravite=?  where user_id= ?");
			prst.setInt(1, nvs.getUser_id());
			prst.setString(2, nvs.getFrequence());		
			prst.setString(3, nvs.getFormat());		
			prst.setInt(4, nvs.getGravite());		
			prst.setInt(5, user_id);

			prst.executeUpdate();
			listeS = versCollectionS();

		} catch (NumberFormatException | SQLException e) {
			e.getStackTrace();
		}
	}
	
	
	public static void supprimerSetting(int user_id) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement("delete from settings where user_id = ?");
			prst.setInt(1, user_id);
			prst.executeUpdate();
			listeS = versCollectionS();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Setting> versCollectionS() {
		Connection conn = Connecteur.getConnection();
		Statement st;
		ResultSet rs;
		ArrayList<Setting> listes = new ArrayList<Setting>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from settings");
			if (rs != null) {
				while (rs.next()) {
					Setting s = new Setting();					
					s.setUser_id(rs.getInt("user_id"));
					s.setFrequence(rs.getString("frequence"));
					s.setFormat(rs.getString("format"));	
					s.setGravite(rs.getInt("gravite"));
					

					
					listes.add(s);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listes;
	}
	
	
	
	
	
}
