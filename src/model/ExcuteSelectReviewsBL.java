package model;

import java.util.List;

public class ExcuteSelectReviewsBL {

	public List<ReviewsDto> excuteSelectReviews(int itemId) {

		ReviewsDao dao = new ReviewsDao();
		List<ReviewsDto> dtoList = dao.doSelectReviews(itemId);

		return dtoList;
	}

	public ReviewsDto excuteSelectReviewItems(int itemId, int userId) {

		ReviewsDao dao = new ReviewsDao();
		ReviewsDto dto = dao.doSelectReviewItem(itemId, userId);

		return dto;
	}

}
