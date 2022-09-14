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

import confg.Library;
import confg.Validator;
import model.UserInfoDto;


@WebServlet("/NewRegistrationCheck")
public class NewRegistrationCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public NewRegistrationCheck() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Library l = new Library();



		if (!( Validator.validateName(request.getParameter("NAME"))             &&
				Validator.validateName_kana(request.getParameter("KANA"))      &&
				Validator.validateNickname(request.getParameter("NICK_NAME"))  &&
				Validator.validateNotNull(request.getParameter("SEX"))         &&
				Validator.validateNotNull(request.getParameter("BIRTHDAY"))    &&
				Validator.validateZipCode(request.getParameter("ZIPCODE"))     &&
				Validator.validateAddress(request.getParameter("ADDRESS"))     &&
				Validator.validateTell(request.getParameter("TELL"))           &&
				Validator.validateNotNull(request.getParameter("MAIL"))        &&
				Validator.validateNotNull(request.getParameter("PASS"))        &&
				Validator.checkPassword(request.getParameter("PASS"), request.getParameter("PASS_CHECK")) ))  {



			boolean validation = true;
			request.setAttribute("VALIDATE", validation);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/new_registration.jsp");
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
			String pass       = l.replaceEscapeChar(request.getParameter("PASS"));


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
			dto.setPass(pass);
			dto.setCreated( new Timestamp(System.currentTimeMillis()) );

			HttpSession session = request.getSession();

			session.setAttribute("USER_DATA", dto);

			String errorMessage = "";
			if (!Validator.checkEmail(email)) {
				errorMessage = "そのアドレスはすでに使われています";
				request.setAttribute("ERROR_MESSAGE", errorMessage);

				boolean modify = true;
				request.setAttribute("MODIFY", modify);

				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/new_registration.jsp");
				dispatch.forward(request, response);
			}

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/new_registration_check.jsp");
			dispatch.forward(request, response);


		}
	}



}
