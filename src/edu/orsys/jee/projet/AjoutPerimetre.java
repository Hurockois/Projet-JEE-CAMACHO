package edu.orsys.jee.projet;

import java.io.IOException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import edu.orsys.jee.Connecteur;
import edu.orsys.jee.Model;
import edu.orsys.jee.projet.*;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/AjoutPerim")
public class AjoutPerimetre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int nb = 0;
	Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutPerimetre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		conn = Connecteur.getConnection();
		getServletContext().setAttribute("connection", conn);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		

		
		int id_user = Model.chercherUser(request.getParameter("login_user")).getId();
		String nom_perimetre = request.getParameter("nom_perim");
		ArrayList<String> liste = new ArrayList<String>();

		for (Logiciel l : Model.getListeL()) {
			if (request.getParameter(l.getName()) != null) {
				liste.add(l.getName());
			}
		}

		String liste_de_logiciel = String.join(",", liste);
		System.out.println(liste_de_logiciel);
		System.out.println(id_user);
		
		
//		User u = Model.chercherUser(login, pwd);
//		HttpSession session = request.getSession(true);

		
		
		if (nom_perimetre != null && liste_de_logiciel != null) {
			Perimetre p = new Perimetre(nom_perimetre, id_user, liste_de_logiciel);		
			
			Model.ajouterPerimetre(p);
			request.getRequestDispatcher("/Perimetres.jsp").forward(request, response);

		} else {
			request.setAttribute("message", "Champ nom ou logociel vide, veuillez remplir ces champs !!!");
			getServletContext().getRequestDispatcher("/Perimetres.jsp").forward(request, response);

		}	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("Servlet détruite...");
	}

}
