package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ExcuteReviewPostUserBL;
import model.ExcuteSelectReviewsBL;
import model.OrderHistoryBL;
import model.OrderHistoryDto;
import model.ReviewsDto;
import model.UserInfoDto;

/**
 * Servlet implementation class ReviewPosts
 */
@WebServlet("/ReviewPosts")
public class ReviewPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewPosts() {
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

			List<ReviewsDto> ReviewUserList = new ArrayList<ReviewsDto>();
			ExcuteReviewPostUserBL reviewPostUserLogic = new ExcuteReviewPostUserBL();
			ReviewUserList = reviewPostUserLogic.excuteSelectReviewUser(dto.getId());

			request.setAttribute("REVIEW_POST_USER", ReviewUserList);


			ReviewsDto reviewDto = new ReviewsDto();
			ExcuteSelectReviewsBL reviewsLogic = new ExcuteSelectReviewsBL();

			int itemId;

			if (request.getParameter("id") != null) {
				itemId = Integer.parseInt(request.getParameter("id"));
				System.out.println(request.getParameter("id"));
				session.setAttribute("ITEM_ID", itemId);
			} else {
				itemId = (Integer)session.getAttribute("ITEM_ID");
			}

			int userId = (Integer)dto.getId();

			HashMap<String,Integer> map = new HashMap<>();
			map.put("ITEM_ID", itemId);
			map.put("USER_ID", userId);

			request.setAttribute("REVIEW_POST_ID", map);

			OrderHistoryDto historyDto = new OrderHistoryDto();
			OrderHistoryBL logic = new OrderHistoryBL();

			historyDto = logic.doSelectOrderHistoryItemName(itemId);

			request.setAttribute("REVIEW_ITEM", historyDto);

			if (request.getAttribute("MESSAGE") != null) {
				request.setAttribute("MESSAGE", request.getAttribute("MESSAGE"));
			}

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/review_posts.jsp");
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
