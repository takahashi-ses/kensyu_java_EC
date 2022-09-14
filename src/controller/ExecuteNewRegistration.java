package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ExcuteNewRegistrationBL;
import model.UserInfoDto;


@WebServlet("/ExecuteNewRegistration")
public class ExecuteNewRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteNewRegistration() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		boolean succesFlg = true;


		String checkRegist = request.getParameter("CHECK_REGIST");

		//System.out.println(checkRegist);

		if (checkRegist.equals("modify")) {

			boolean modify = true;
			request.setAttribute("MODIFY", modify);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/new_registration.jsp");
			dispatch.forward(request, response);

		} else if (checkRegist.equals("permit") ) {

			HttpSession session = request.getSession();
			UserInfoDto dto = (UserInfoDto)session.getAttribute("USER_DATA");

			ExcuteNewRegistrationBL logic = new ExcuteNewRegistrationBL();
			succesFlg = logic.excuteInsertRegistration(dto);

			session.invalidate();

			if (succesFlg) {
				response.sendRedirect("htmls/finish.html");
			} else {
				response.sendRedirect("htmls/error.html");
			}

		} else {

			response.sendRedirect("htmls/error.html");

		}

	}

}
