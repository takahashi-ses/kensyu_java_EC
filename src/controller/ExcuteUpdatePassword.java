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
import model.PasswordChangeBL;
import model.UserInfoDto;

/**
 * Servlet implementation class ExcuteUpdatePassword
 */
@WebServlet("/ExcuteUpdatePassword")
public class ExcuteUpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteUpdatePassword() {
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

		boolean succesFlg = true;

		Library l = new Library();



		if (!( Validator.validateNotNull(request.getParameter("ID"))                    &&
				Validator.validateNotNull(request.getParameter("INPUT_OLD_PASS"))       &&
				Validator.validateNotNull(request.getParameter("OLD_PASS"))             &&
				Validator.checkPassword(request.getParameter("PASS"), request.getParameter("PASS_CHECK")) )) {

			System.out.println(request.getParameter("ID"));
			System.out.println(request.getParameter("INPUT_OLD_PASS"));
			System.out.println(request.getParameter("OLD_PASS"));
			System.out.println(request.getParameter("PASS"));
			System.out.println(request.getParameter("PASS_CHECK"));

			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);


		} else if (!(Validator.checkPassword(request.getParameter("INPUT_OLD_PASS"), request.getParameter("OLD_PASS")) )) {

			int    id         = Integer.parseInt( request.getParameter("ID"));
			String password   = l.replaceEscapeChar(request.getParameter("OLD_PASS"));

			System.out.println(request.getParameter("INPUT_OLD_PASS"));
			System.out.println(request.getParameter("OLD_PASS"));

			UserInfoDto dto = new UserInfoDto();
			dto.setId(id);
			dto.setPass(password);

			request.setAttribute("PASSWORD", dto);

			String message = "登録されているパスワードではありません";
			request.setAttribute("MESSAGE", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/password_input.jsp");
			dispatch.forward(request, response);

		} else {

			int    id         = Integer.parseInt( request.getParameter("ID"));
			String password   = l.replaceEscapeChar(request.getParameter("PASS"));

			UserInfoDto dto = new UserInfoDto();
			dto.setId(id);
			dto.setPass(password);

			PasswordChangeBL logic = new PasswordChangeBL();
			succesFlg = logic.excuteUpdatePassword(dto);


			if (succesFlg) {

				String updateMessage = "パスワードを変更しました";
				request.setAttribute("UPDATE_MESSAGE", updateMessage);
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/member_info.jsp");
				dispatch.forward(request, response);

			} else {
				response.sendRedirect("htmls/error.html");
			}
		}
	}

}
