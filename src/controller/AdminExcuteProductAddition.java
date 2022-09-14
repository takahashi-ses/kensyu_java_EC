package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

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
import model.TopItemsDto;
import model.TopPageBL;
import model.UserInfoDto;

/**
 * Servlet implementation class AdminExcuteProductAddition
 */
@WebServlet("/AdminExcuteProductAddition")
@MultipartConfig()
public class AdminExcuteProductAddition extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminExcuteProductAddition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

			boolean succesFlg = true;

			Library l = new Library();

			String name    = l.replaceEscapeChar(request.getParameter("name"));
			int price      = Integer.parseInt(request.getParameter("price"));
			int stock      = Integer.parseInt(request.getParameter("stock"));
			String setumei = l.replaceEscapeChar(request.getParameter("setumei"));
			String syousai = l.replaceEscapeChar(request.getParameter("syousai"));

			Part part = request.getPart("picture");
			//ファイル名を取得
			String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			//アップロードするフォルダ
			String path=getServletContext().getRealPath("/img");
			//実際にファイルが保存されるパス確認
			System.out.println(path);
			System.out.println(filename);

			//書き込み
			part.write(path+File.separator+filename);


			TopItemsDto dto = new TopItemsDto();
			dto.setName(name);
			dto.setPrice(price);
			dto.setStock(stock);
			dto.setRetention_stock(stock);
			dto.setSetumei(setumei);
			dto.setSyousai(syousai);
			dto.setPicture(filename);
			dto.setCreated( new Timestamp(System.currentTimeMillis()) );

			TopPageBL logic = new TopPageBL();
			succesFlg = logic.excuteInsertItem(dto);


			if (succesFlg) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/admin/admin_product_addition_fixed.jsp");
				dispatch.forward(request, response);

			} else {
				response.sendRedirect("htmls/error.html");
			}

		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}



	}

}
