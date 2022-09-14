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

import model.TopItemsDto;
import model.TopPageBL;


@WebServlet("/TopPage")
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TopPage() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		List<TopItemsDto> list = new ArrayList<TopItemsDto>();
		TopPageBL logic = new TopPageBL();


		String search = "";

		if (request.getParameter("search") != "") {
			search = request.getParameter("search");

		}

		if (search == null) {
			search = "";
		}



		list = logic.excuteSelectTopItem(search);

		request.setAttribute("TOPITEMS", list);


		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/top_page.jsp");
		dispatch.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
