package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Library;
import confg.Validator;
import model.ExcuteReviewPostsBL;
import model.ReviewsDto;

/**
 * Servlet implementation class ExcuteReviewPosts
 */
@WebServlet("/ExcuteReviewPosts")
public class ExcuteReviewPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteReviewPosts() {
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


		if (!( Validator.validateNotNull(request.getParameter("star"))     &&
				Validator.validateNotNull(request.getParameter("comment")) )) {

			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);

		} else {

			int     star      = Integer.parseInt( request.getParameter("star"));
			String  comment   = l.replaceEscapeChar(request.getParameter("comment"));
			int     user_id   = Integer.parseInt(request.getParameter("user_id"));
			int     item_id   = Integer.parseInt(request.getParameter("item_id"));


			ReviewsDto dto = new ReviewsDto();
			dto.setReviewsStar(star);
			dto.setReviewsComment(comment);
			dto.setReviewsUser_id(user_id);
			dto.setReviewsItem_id(item_id);
			dto.setReviewsCreated( new Timestamp(System.currentTimeMillis()) );


			ExcuteReviewPostsBL logic = new ExcuteReviewPostsBL();
			succesFlg = logic.excuteInsertReview(dto);


			if (succesFlg) {

				String updateMessage = "レビューを投稿しました";
				request.setAttribute("UPDATE_MESSAGE", updateMessage);
				RequestDispatcher dispatch = request.getRequestDispatcher("OrderHistory");
				dispatch.forward(request, response);

			} else {
				response.sendRedirect("htmls/error.html");
			}
		}
	}

}
