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
import model.ExcuteDeleteCartItemBL;
import model.UserInfoDto;

/**
 * Servlet implementation class purchase
 */
@WebServlet("/Purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Purchase() {
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

		boolean succesFlg = true;

		if (dto != null && request.getParameter("purchase") != null) {

			List<CartDto> list = new ArrayList<CartDto>();
			CartBL logic = new CartBL();

			list = logic.excuteSelectCart(dto.getId());

			succesFlg = logic.excuteInsertHistry(list);

			if (succesFlg) {

				ExcuteDeleteCartItemBL deleteLogic = new ExcuteDeleteCartItemBL();

				succesFlg = deleteLogic.excuteDeleteAll(dto.getId());

				if (succesFlg) {

					System.out.println(request.getParameter("purchase_price"));
					request.setAttribute("PURCHASER_PRICE", request.getParameter("purchase_price"));


					request.setAttribute("PURCHASER_INFO", dto);

					RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/purchase_fixing.jsp");
					dispatch.forward(request, response);
				} else {
					response.sendRedirect("htmls/error.html");
				}

			} else {
				response.sendRedirect("htmls/error.html");
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
