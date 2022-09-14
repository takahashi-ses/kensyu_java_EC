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
 * Servlet implementation class AdminExcuteDeleteItem
 */
@WebServlet("/AdminExcuteDeleteItem")
public class AdminExcuteDeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminExcuteDeleteItem() {
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

			if (!( Validator.validateNotNull(request.getParameter("id"))     &&
					Validator.validateNotNumber(request.getParameter("id")) )) {

				RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
				dispatch.forward(request, response);

			} else {

				int id = Integer.parseInt( request.getParameter("id"));

				AdminBL logic = new AdminBL();
				succesFlg = logic.excuteDeleteItem(id);


				if (succesFlg) {

					String message = "商品を削除しました";
					request.setAttribute("MESSAGE", message);

					RequestDispatcher dispatch = request.getRequestDispatcher("AdminPastListing");
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
