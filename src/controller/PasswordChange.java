package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Library;
import confg.Validator;
import model.UserInfoDto;

/**
 * Servlet implementation class PasswordChange
 */
@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		Library l = new Library();


		if (!( Validator.validateNotNull(request.getParameter("ID"))         &&
				Validator.validateNotNull(request.getParameter("PASS"))     ))  {

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/update_registration.jsp");
			dispatch.forward(request, response);

		} else {

			int    id         = Integer.parseInt( request.getParameter("ID"));
			String password   = l.replaceEscapeChar(request.getParameter("PASS"));

			UserInfoDto dto = new UserInfoDto();
			dto.setId(id);
			dto.setPass(password);

			request.setAttribute("PASSWORD", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/password_input.jsp");
			dispatch.forward(request, response);

		}
	}
}
