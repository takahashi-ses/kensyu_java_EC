package model;

public class ExcuteReviewPostsBL {

	public boolean excuteInsertReview(ReviewsDto dto) {

		boolean succesInsert = false ;

		ReviewsDao dao = new ReviewsDao();
		succesInsert = dao.doInsertReview(dto);

		return succesInsert;
	}

}
