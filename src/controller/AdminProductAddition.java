package controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import confg.Library;
import confg.Validator;
import model.TopItemsDto;
import model.UserInfoDto;

/**
 * Servlet implementation class AdminProductAddition
 */
@WebServlet("/AdminProductAddition")
@MultipartConfig()
public class AdminProductAddition extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductAddition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session           = request.getSession();
		UserInfoDto dto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (dto != null) {

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_product_addition.jsp");
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
		UserInfoDto loginDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (loginDto != null) {

			if ( !( Validator.validateNotNull(request.getParameter("name"))   &&
					Validator.validateNotNull(request.getParameter("price"))   &&
					Validator.validateNotNull(request.getParameter("stock"))   &&
					Validator.validateNotNull(request.getParameter("setumei"))   &&
					Validator.validateNotNull(request.getParameter("syousai"))   &&
					Validator.validateNotNull(request.getParameter("add_check"))   ))  {

				RequestDispatcher dispatch = request.getRequestDispatcher("AdminTop");
				dispatch.forward(request, response);

			} else {

				Library l = new Library();

				String name    = l.replaceEscapeChar(request.getParameter("name"));
				int price      = Integer.parseInt(request.getParameter("price"));
				int stock      = Integer.parseInt(request.getParameter("stock"));
				String setumei = l.replaceEscapeChar(request.getParameter("setumei"));
				String syousai = l.replaceEscapeChar(request.getParameter("syousai"));

				Part part = request.getPart("picture");
				//ファイル名を取得
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();


				TopItemsDto dto = new TopItemsDto();
				dto.setName(name);
				dto.setPrice(price);
				dto.setStock(stock);
				dto.setSetumei(setumei);
				dto.setSyousai(syousai);
				dto.setPicture(filename);

				request.setAttribute("ADD_CHECK", dto);

				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_product_addition_check.jsp");
				dispatch.forward(request, response);



			}

		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}



	}

}
