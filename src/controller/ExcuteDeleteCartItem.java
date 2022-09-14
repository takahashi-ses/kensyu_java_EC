package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Validator;
import model.ExcuteDeleteCartItemBL;

/**
 * Servlet implementation class ExcuteDeleteCartItem
 */
@WebServlet("/ExcuteDeleteCartItem")
public class ExcuteDeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteDeleteCartItem() {
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

		if (!( Validator.validateNotNull(request.getParameter("id"))     &&
				Validator.validateNotNumber(request.getParameter("id")) )) {

			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);

		} else {

			int id = Integer.parseInt( request.getParameter("id"));

			ExcuteDeleteCartItemBL logic = new ExcuteDeleteCartItemBL();
			succesFlg = logic.excuteDeleteCartItem(id);


			if (succesFlg) {

				String message = "カートの商品を削除しました";
				request.setAttribute("MESSAGE", message);

				RequestDispatcher dispatch = request.getRequestDispatcher("Cart");
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
