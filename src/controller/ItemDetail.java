package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ExcuteSelectReviewsBL;
import model.ExcuteSelectReviewsStarBL;
import model.ItemDetailBL;
import model.ItemDetailDto;
import model.ReviewsDto;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF8");


		int itemId;
		String addMessage = "";
		String favoriteMessage = "";

		if (request.getParameter("id") != null) {
			itemId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("ITEM_ID", itemId);
		} else {
			itemId = (Integer)request.getAttribute("ITEM_ID");


		}

		if (request.getAttribute("ADD_MESSAGE") != null) {
			addMessage = (String)request.getAttribute("ADD_MESSAGE");
		}
		if (request.getAttribute("FAVORITE_MESSAGE") != null) {
			favoriteMessage = (String)request.getAttribute("FAVORITE_MESSAGE");
		}

		ItemDetailBL itemLogic = new ItemDetailBL();
		ItemDetailDto itemDetailDto = new ItemDetailDto();
		itemDetailDto = itemLogic.excuteSelectItemDetail(itemId);

		ExcuteSelectReviewsStarBL reviewsStarLogic = new ExcuteSelectReviewsStarBL();
		ReviewsDto reviewsStarDto = new ReviewsDto();
		reviewsStarDto = reviewsStarLogic.executeSelectReviewsStar(itemId);

		ExcuteSelectReviewsBL reviewsCommentLogic = new ExcuteSelectReviewsBL();
		List<ReviewsDto> reviewsCommentDtoList = new ArrayList<ReviewsDto>();
		reviewsCommentDtoList = reviewsCommentLogic.excuteSelectReviews(itemId);

		request.setAttribute("DETAIL", itemDetailDto);

		request.setAttribute("REVIEWS", reviewsStarDto);

		request.setAttribute("COMMENT", reviewsCommentDtoList);

		request.setAttribute("ADD_MESSAGE", addMessage);

		request.setAttribute("FAVORITE_MESSAGE", favoriteMessage);

		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/item_detail.jsp");
		dispatch.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
