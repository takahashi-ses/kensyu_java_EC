package model;

import java.util.List;

public class AdminBL {

	public List<AdminDto> excuteSelectItem() {

		AdminDao dao = new AdminDao();
		List<AdminDto> dtoList = dao.doSelectItem();

		return dtoList;
	}

	public boolean excuteDeleteItem(int id) {

		boolean succesInsert = false ;

		AdminDao dao = new AdminDao();
		succesInsert = dao.doDelete(id);

		return succesInsert;
	}

	public List<AdminDto> excuteSelectUser() {

		AdminDao dao = new AdminDao();
		List<AdminDto> dtoList = dao.doSelectUser();

		return dtoList;
	}

	public List<AdminDto> excuteSelectUserReview() {

		AdminDao dao = new AdminDao();
		List<AdminDto> dtoList = dao.doSelectUserReview();

		return dtoList;
	}

	public List<AdminDto> excuteSelectReviewDetail(int id) {

		AdminDao dao = new AdminDao();
		List<AdminDto> dtoList = dao.doSelectReviewDetail(id);

		return dtoList;
	}

	public boolean excuteDeleteReview(int id) {

		boolean succesInsert = false ;

		AdminDao dao = new AdminDao();
		succesInsert = dao.doDeleteReview(id);

		return succesInsert;
	}
}
