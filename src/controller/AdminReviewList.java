package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import confg.Library;
import confg.Validator;
import model.AdminBL;
import model.AdminDto;
import model.UserInfoDto;

/**
 * Servlet implementation class AdminReviewList
 */
@WebServlet("/AdminReviewList")
public class AdminReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReviewList() {
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

			String message = "";

			if (request.getAttribute("MESSAGE") != null && session.getAttribute("USER_MAP") != null) {
				message = (String)request.getAttribute("MESSAGE");


				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>)session.getAttribute("USER_MAP");

				int id = Integer.parseInt(map.get("ID"));
				String name = map.get("NAME");

				List<AdminDto> list = new ArrayList<AdminDto>();
				AdminBL logic = new AdminBL();
				list = logic.excuteSelectReviewDetail(id);


				request.setAttribute("MESSAGE", message);
				request.setAttribute("USER_NAME", name);
				request.setAttribute("REVIEW_DETAIL", list);

				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_review_list.jsp");
				dispatch.forward(request, response);



			} else {

				RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
				dispatch.forward(request, response);

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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		if (!( Validator.validateNotNull(request.getParameter("id"))     &&
				Validator.validateNotNull(request.getParameter("name"))     &&
				Validator.validateNotNumber(request.getParameter("id")) )) {

			RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
			dispatch.forward(request, response);

		} else {

			Library l = new Library();

			String id = l.replaceEscapeChar(request.getParameter("id"));
			String name =  l.replaceEscapeChar(request.getParameter("name"));

			Map<String, String> map = new HashMap<>();

			map.put("ID", id);
			map.put("NAME", name);

			HttpSession session = request.getSession();
			session.setAttribute("USER_MAP", map);


			int userId = Integer.parseInt(id);
			List<AdminDto> list = new ArrayList<AdminDto>();
			AdminBL logic = new AdminBL();
			list = logic.excuteSelectReviewDetail(userId);

			request.setAttribute("USER_NAME", name);
			request.setAttribute("REVIEW_DETAIL", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_review_list.jsp");
			dispatch.forward(request, response);

		}
	}

}
