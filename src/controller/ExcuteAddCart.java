package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import confg.Validator;
import model.CartBL;
import model.CartDto;
import model.UserInfoDto;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/ExcuteAddCart")
public class ExcuteAddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteAddCart() {
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


		HttpSession session           = request.getSession();
		UserInfoDto dto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (dto != null) {

			if ( !( Validator.validateNotNull(request.getParameter("number"))   &&
					Validator.validateNotNull(request.getParameter("item_id"))   ))  {

				RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
				dispatch.forward(request, response);

			} else {

			    int  number    = Integer.parseInt(request.getParameter("number"));
				int  user_id   = dto.getId();
				int  item_id   = Integer.parseInt(request.getParameter("item_id"));

				CartDto cartDto = new CartDto();
				cartDto.setNumber(number);
				cartDto.setItem_id(item_id);
				cartDto.setUser_id(user_id);
				cartDto.setCreated( new Timestamp(System.currentTimeMillis()) );


				CartBL logic = new CartBL();

				int checkCount = logic.excuteSelectCartCheck(cartDto);

				System.out.println(checkCount);


				if (checkCount != 0 ) {
					succesFlg = logic.excuteUpdateCart(cartDto);
					System.out.println("update");
				} else {
					succesFlg = logic.excuteInsertCart(cartDto);
					System.out.println("insert");
				}


				if (succesFlg) {

					String addMessage = "カートに追加しました";
					request.setAttribute("ADD_MESSAGE", addMessage);
					request.setAttribute("ITEM_ID", item_id);
					RequestDispatcher dispatch = request.getRequestDispatcher("ItemDetail");
					dispatch.forward(request, response);

				} else {
					response.sendRedirect("htmls/error.html");
				}


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
