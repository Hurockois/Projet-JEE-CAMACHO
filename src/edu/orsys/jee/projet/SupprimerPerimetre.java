package edu.orsys.jee.projet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.orsys.jee.Model;

/**
 * Servlet implementation class SupprimerParticipant
 */
@WebServlet("/SupprimerPerimetre")
public class SupprimerPerimetre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPerimetre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String nom = request.getParameter("nom");
		int id_user = Model.chercherUser(request.getParameter("login_user")).getId();

		if(session != null) {
			try {
				
				Model.supprimerPerimetre(nom, id_user);
				getServletContext().getRequestDispatcher("/Perimetres.jsp").forward(request, response);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
