package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import confg.Library;
import confg.Validator;
import model.InquiryDto;

/**
 * Servlet implementation class Inquiry
 */
@WebServlet("/Inquiry")
public class Inquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inquiry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/inquiry.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		boolean succesFlg = true;


		if (!( Validator.validateNotNull(request.getParameter("name"))         &&
				Validator.validateNotNull(request.getParameter("email"))       &&
				Validator.validateNotNull(request.getParameter("kenmei"))      &&
				Validator.validateNotNull(request.getParameter("inquiry_post")) )) {

			String message = "不正な値が入力されました";
			request.setAttribute("MESSAGE", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/inquiry.jsp");
			dispatch.forward(request, response);

		} else {

			Library l = new Library();

			String name         = l.replaceEscapeChar(request.getParameter("name"));
			String email        = l.replaceEscapeChar(request.getParameter("email"));
			String kenmei       = l.replaceEscapeChar(request.getParameter("kenmei"));
			String inquiry_post = l.replaceEscapeChar(request.getParameter("inquiry_post"));


			InquiryDto dto = new InquiryDto();
			dto.setName(name);
			dto.setEmail(email);
			dto.setKenmei(kenmei);
			dto.setInquiry_post(inquiry_post);

			request.setAttribute("INQUIRY", dto);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/inquiry_check.jsp");
			dispatch.forward(request, response);

		}

	}

}
