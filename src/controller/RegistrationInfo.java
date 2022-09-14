package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegistrationInfoBL;
import model.UserInfoDto;

/**
 * Servlet implementation class RegistrationInfo
 */
@WebServlet("/RegistrationInfo")
public class RegistrationInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session           = request.getSession();
		UserInfoDto dto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (dto != null) {

			RegistrationInfoBL logic = new RegistrationInfoBL();

			dto = logic.excuteSelectRegistration(dto.getId());

			request.setAttribute("REGIST", dto);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/registration_info.jsp");
			dispatch.forward(request, response);

		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
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
