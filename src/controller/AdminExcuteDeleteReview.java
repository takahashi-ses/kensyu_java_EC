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
import model.AdminBL;
import model.UserInfoDto;

/**
 * Servlet implementation class AdminExcuteDeleteReview
 */
@WebServlet("/AdminExcuteDeleteReview")
public class AdminExcuteDeleteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminExcuteDeleteReview() {
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

			boolean succesFlg = true;

			if (!( Validator.validateNotNull(request.getParameter("deleteId"))     &&
					Validator.validateNotNumber(request.getParameter("deleteId")) )) {

				RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
				dispatch.forward(request, response);

			} else {

				int id = Integer.parseInt( request.getParameter("deleteId"));

				AdminBL logic = new AdminBL();
				succesFlg = logic.excuteDeleteReview(id);


				if (succesFlg) {

					String message = "レビューを削除しました";
					request.setAttribute("MESSAGE", message);

					RequestDispatcher dispatch = request.getRequestDispatcher("AdminReviewList");
					dispatch.forward(request, response);

				} else {
					response.sendRedirect("htmls/error.html");
				}
			}

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
