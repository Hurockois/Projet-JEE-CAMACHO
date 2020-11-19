package edu.orsys.jee.projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.orsys.jee.Connecteur;
import edu.orsys.jee.Model;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Authentif")
public class Authentif extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int nb = 0;
	Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentif() {
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
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		User user = Model.chercherUser(login);
		HttpSession session = request.getSession(true);

		if (user != null) {
			User u = Model.chercherUser(login,pwd);
			if (u != null) {
				if (u.getRole().equals("admin")) {
					request.getRequestDispatcher("/accueil4admin.jsp").forward(request, response);
				} else if (u.getRole().equals("user")) {
					request.getRequestDispatcher("/accueil4users.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Une erreur s'est produite, veuillez recommencer");
					getServletContext().getRequestDispatcher("/authentif.jsp").forward(request, response);
				}
			} else {				
				request.setAttribute("message", "Desole, le mot de passe est erroné pour cet utilisateur ");
				getServletContext().getRequestDispatcher("/authentif.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "Desole, utilisateur non reconnu");
			getServletContext().getRequestDispatcher("/authentif.jsp").forward(request, response);
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
