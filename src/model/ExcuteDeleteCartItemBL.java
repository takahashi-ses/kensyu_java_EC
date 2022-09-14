package model;

public class ExcuteDeleteCartItemBL {

	public boolean excuteDeleteCartItem(int id) {

		boolean succesInsert = false ;

		CartDao dao = new CartDao();
		succesInsert = dao.doDelete(id);

		return succesInsert;
	}


	public boolean excuteDeleteAll(int id) {

		boolean succesInsert = false ;

		CartDao dao = new CartDao();
		succesInsert = dao.doDeleteAll(id);

		return succesInsert;
	}

}
