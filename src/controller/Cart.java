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

import model.CartBL;
import model.CartDto;
import model.UserInfoDto;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
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

			List<CartDto> list = new ArrayList<CartDto>();
			CartBL logic = new CartBL();

			list = logic.excuteSelectCart(dto.getId());
			String message = "カート";
			if (list == null) {
				message = "カートに商品はありません";
			} else {
				if (request.getAttribute("DELETE_MESSAGE") != null) {
					message = (String)request.getAttribute("DELETE_MESSAGE");
				}
			}

			request.setAttribute("CART", list);
			request.setAttribute("MESSAGE", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session           = request.getSession();
		UserInfoDto dto = (UserInfoDto)session.getAttribute("LOGIN_INFO");


		if (dto != null && request.getParameter("purchase-check") != null) {

			List<CartDto> list = new ArrayList<CartDto>();
			CartBL logic = new CartBL();

			list = logic.excuteSelectCart(dto.getId());


			request.setAttribute("CART", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/purchase.jsp");
			dispatch.forward(request, response);

		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}
	}

}
