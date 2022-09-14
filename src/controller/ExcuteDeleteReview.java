package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Validator;
import model.ExcuteDeleteReviewBL;

/**
 * Servlet implementation class ExcuteDeleteReview
 */
@WebServlet("/ExcuteDeleteReview")
public class ExcuteDeleteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteDeleteReview() {
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

		boolean succesFlg = true;


		if (!( Validator.validateNotNull(request.getParameter("deleteId"))     &&
				Validator.validateNotNumber(request.getParameter("deleteId")) )) {

			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);

		} else {

			int id = Integer.parseInt( request.getParameter("deleteId"));

			ExcuteDeleteReviewBL logic = new ExcuteDeleteReviewBL();
			succesFlg = logic.excuteDeleteReview(id);


			if (succesFlg) {

				String message = "レビューを削除しました";
				request.setAttribute("MESSAGE", message);

				RequestDispatcher dispatch = request.getRequestDispatcher("ReviewPosts");
				dispatch.forward(request, response);

			} else {
				response.sendRedirect("htmls/error.html");
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
