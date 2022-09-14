package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Library;
import confg.Validator;
import model.ExcuteUpdateRegistrationBL;
import model.UserInfoDto;

/**
 * Servlet implementation class ExecuteUpdateRegistration
 */
@WebServlet("/ExecuteUpdateRegistration")
public class ExecuteUpdateRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		boolean succesFlg = true;
		String errorMessage = "";

		Library l = new Library();


		if (!( Validator.validateName(request.getParameter("NAME"))             &&
				Validator.validateName_kana(request.getParameter("KANA"))      &&
				Validator.validateNickname(request.getParameter("NICK_NAME"))  &&
				Validator.validateNotNull(request.getParameter("SEX"))         &&
				Validator.validateNotNull(request.getParameter("BIRTHDAY"))    &&
				Validator.validateZipCode(request.getParameter("ZIPCODE"))     &&
				Validator.validateAddress(request.getParameter("ADDRESS"))     &&
				Validator.validateTell(request.getParameter("TELL"))           &&
				Validator.validateNotNull(request.getParameter("MAIL"))      ))  {


			RequestDispatcher dispatch = request.getRequestDispatcher("TopPage");
			dispatch.forward(request, response);


		} else {

			String name       = l.replaceEscapeChar(request.getParameter("NAME"));
			String name_kana  = l.replaceEscapeChar(request.getParameter("KANA"));
			String nickName   = l.replaceEscapeChar(request.getParameter("NICK_NAME"));
			int    sex        = Integer.parseInt( request.getParameter("SEX"));
			String birthday   = l.replaceEscapeChar(request.getParameter("BIRTHDAY"));
			String zipcode    = l.replaceEscapeChar(request.getParameter("ZIPCODE"));
			String address    = l.replaceEscapeChar(request.getParameter("ADDRESS"));
			String tell       = l.replaceEscapeChar(request.getParameter("TELL"));
			String email      = l.replaceEscapeChar(request.getParameter("MAIL"));
			int    id         = Integer.parseInt( request.getParameter("ID"));


			if (!Validator.checkEmailUpdate(email)){

				errorMessage = "そのアドレスはすでに使われています";
				request.setAttribute("ERROR_MESSAGE", errorMessage);

				RequestDispatcher dispatch = request.getRequestDispatcher("UpdateRegistration");
				dispatch.forward(request, response);
			}

			UserInfoDto dto = new UserInfoDto();
			dto.setName(name);
			dto.setName_kana(name_kana);
			dto.setNickname(nickName);
			dto.setSex(sex);
			dto.setBirthday(birthday);
			dto.setZipcode(zipcode);
			dto.setAddress(address);
			dto.setTell(tell);
			dto.setEmail(email);
			dto.setUpdatetime( new Timestamp(System.currentTimeMillis()) );
			dto.setId(id);

			ExcuteUpdateRegistrationBL logic = new ExcuteUpdateRegistrationBL();
			succesFlg = logic.excuteUpdateRegistration(dto);


			if (succesFlg) {

				String updateMessage = "登録情報を更新しました";
				request.setAttribute("UPDATE_MESSAGE", updateMessage);
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/member_info.jsp");
				dispatch.forward(request, response);

			} else {
				response.sendRedirect("htmls/error.html");
			}
		}
	}
}
