package edu.orsys.jee.projet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import edu.orsys.jee.Model;

/**
 * Servlet implementation class SupprimerParticipant
 */
@WebServlet("/ModifierParametre")
public class ModifierParametre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierParametre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id_user = Model.chercherUser(request.getParameter("login_user")).getId();
		HttpSession session = request.getSession(false);
		String frequence = request.getParameter("frequence");
		String format = request.getParameter("format");
		int gravite = Integer.parseInt(request.getParameter("gravite"));
				

		if(session != null) {
			try {
				Setting s =new Setting(id_user, frequence, format, gravite);
				if (Model.chercherSetting(id_user)==null) {
					Model.ajouterSetting(s);
				}else {
					Model.modifierSetting(id_user, s);
				}
				
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
