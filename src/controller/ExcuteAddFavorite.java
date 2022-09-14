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
import model.FavoriteBL;
import model.FavoriteDto;
import model.UserInfoDto;

/**
 * Servlet implementation class Favorite
 */
@WebServlet("/ExcuteAddFavorite")
public class ExcuteAddFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcuteAddFavorite() {
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

			if ( !( Validator.validateNotNull(request.getParameter("id"))   &&
					Validator.validateNotNull(request.getParameter("id"))   ))  {

				RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
				dispatch.forward(request, response);

			} else {

				int  user_id   = dto.getId();
				int  item_id    = Integer.parseInt(request.getParameter("id"));
				String favoriteMessage;

				FavoriteDto favoriteDto = new FavoriteDto();
				favoriteDto.setItem_id(item_id);
				favoriteDto.setUser_id(user_id);
				favoriteDto.setCreated( new Timestamp(System.currentTimeMillis()) );


				FavoriteBL logic = new FavoriteBL();

				int checkCount = logic.excuteSelectFavoriteCheck(favoriteDto);

				System.out.println(checkCount);


				if (checkCount != 0 ) {
					favoriteMessage = "お気に入りに追加済みです";
					request.setAttribute("FAVORITE_MESSAGE", favoriteMessage);
					request.setAttribute("ITEM_ID", item_id);
					RequestDispatcher dispatch = request.getRequestDispatcher("ItemDetail");
					dispatch.forward(request, response);
				} else {
					succesFlg = logic.excuteInsertFavorite(favoriteDto);
					System.out.println("insert");

					if (succesFlg) {

						favoriteMessage = "お気に入りに追加しました";
						request.setAttribute("FAVORITE_MESSAGE", favoriteMessage);
						request.setAttribute("ITEM_ID", item_id);
						RequestDispatcher dispatch = request.getRequestDispatcher("ItemDetail");
						dispatch.forward(request, response);

					} else {
						response.sendRedirect("htmls/error.html");
					}
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
