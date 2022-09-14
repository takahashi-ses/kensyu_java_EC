package model;

public class ExcuteDeleteReviewBL {

	public boolean excuteDeleteReview(int id) {

		boolean succesInsert = false ;

		ReviewsDao dao = new ReviewsDao();
		succesInsert = dao.doDelete(id);

		return succesInsert;
	}
}
