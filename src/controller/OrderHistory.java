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

import model.OrderHistoryBL;
import model.OrderHistoryDto;
import model.UserInfoDto;

/**
 * Servlet implementation class OrderHistory
 */
@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistory() {
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

			List<OrderHistoryDto> list = new ArrayList<OrderHistoryDto>();
			OrderHistoryBL logic = new OrderHistoryBL();

			String message = "注文履歴";

			list = logic.excuteSelectOrderHistory(dto.getId());
			if (list == null) {
				message = "注文履歴はありません";
			}

			request.setAttribute("HISTORY", list);
			request.setAttribute("MESSAGE", message);

			String updateMessage = "";
			if (request.getAttribute("UPDATE_MESSAGE") != null) {
				updateMessage = (String)request.getAttribute("UPDATE_MESSAGE");
			}

			request.setAttribute("UPDATE_MESSAGE", updateMessage);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/order_history.jsp");
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
