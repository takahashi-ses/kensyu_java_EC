package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import confg.Validator;
import model.ExecuteLoginBL;
import model.UserInfoDto;


@WebServlet("/ExecuteLogin")
public class ExecuteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ExecuteLogin() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");


		boolean error_display = false;

		if (userInfoOnSession != null) {

			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);

		} else {

			boolean possible_to_login = true;
			boolean admin = false;

			if( !(Validator.validateNotNull(request.getParameter("MAIL")) &&
					Validator.validateNotNull(request.getParameter("PASSWORD"))  ) ) {

				possible_to_login = false ;

			}else {

				String mail   = request.getParameter("MAIL");
				String passWord = request.getParameter("PASSWORD");

				ExecuteLoginBL logic = new ExecuteLoginBL();
				UserInfoDto    dto   = logic.executeSelectUserInfo(mail, passWord);

				if (dto.getEmail() == null) {

					possible_to_login = false ;
					error_display = true;

				}else {

					if (dto.getId() == 1) {
						admin = true;
					}

					session.setAttribute("LOGIN_INFO", dto);


				}


			}

			if (possible_to_login) {

				if (admin) {
					RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
					dispatch.forward(request, response);
				}

				RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
				dispatch.forward(request, response);
			} else {
				request.setAttribute("DISPLAY", error_display);
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatch.forward(request, response);
			}
		}
	}
}
