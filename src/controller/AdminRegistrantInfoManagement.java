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
import javax.servlet.http.HttpSession;

import model.AdminBL;
import model.AdminDto;
import model.UserInfoDto;

/**
 * Servlet implementation class AdminRegistrantInfoManagement
 */
@WebServlet("/AdminRegistrantInfoManagement")
public class AdminRegistrantInfoManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegistrantInfoManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session           = request.getSession();
		UserInfoDto dto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (dto != null) {

			List<AdminDto> userList = new ArrayList<AdminDto>();
			AdminBL logic = new AdminBL();

			userList = logic.excuteSelectUser();

			request.setAttribute("USER_LIST", userList);


			List<AdminDto> userReviewList = new ArrayList<AdminDto>();
			logic = new AdminBL();

			userReviewList = logic.excuteSelectUserReview();

			request.setAttribute("USER_REVIEW_LIST", userReviewList);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_registrant_info_management.jsp");
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
