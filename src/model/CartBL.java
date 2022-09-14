package model;

import java.util.List;

public class CartBL {

	public boolean excuteInsertCart(CartDto dto) {

		boolean succesInsert = false ;

		CartDao dao = new CartDao();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}

	public boolean excuteUpdateCart(CartDto dto) {

		boolean succesInsert = false ;

		CartDao dao = new CartDao();
		succesInsert = dao.doUpdate(dto);

		return succesInsert;
	}

	public List<CartDto> excuteSelectCart(int id) {

		CartDao dao = new CartDao();
		List<CartDto> dtoList = dao.doSelectCart(id);

		return dtoList;
	}

	public int excuteSelectCartCheck(CartDto dto) {

		CartDao dao = new CartDao();
		int checkCount = dao.doSelectCartCheck(dto);


		return checkCount;
	}

	public boolean excuteInsertHistry(List<CartDto> dtoList) {

		boolean succesInsert = false ;

		CartDao dao = new CartDao();
		succesInsert = dao.doInsertHistory(dtoList);

		return succesInsert;
	}
}
