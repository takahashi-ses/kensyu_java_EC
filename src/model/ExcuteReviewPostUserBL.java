package model;

import java.util.List;

public class ExcuteReviewPostUserBL {

	public List<ReviewsDto> excuteSelectReviewUser(int userId) {

		ReviewsDao dao = new ReviewsDao();
		List<ReviewsDto> dtoList = dao. doSelectReviewUser(userId);

		return dtoList;
	}

}
