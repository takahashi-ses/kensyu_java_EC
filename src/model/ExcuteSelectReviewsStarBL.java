package model;

public class ExcuteSelectReviewsStarBL {

	public ReviewsDto executeSelectReviewsStar(int itemsId) {

		ReviewsDao dao = new ReviewsDao();
		ReviewsDto dto = dao.doSelectReviewsStar(itemsId);

		return dto;
	}
}
