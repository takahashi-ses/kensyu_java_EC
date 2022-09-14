package model;

import java.sql.Timestamp;

public class ReviewsDto {

	private int reviewsId;
	private String reviewsComment;
	private int reviewsStar;
	private int reviewsUser_id;
	private int reviewsItem_id;
	private Timestamp reviewsCreated;
	private String reviewsNickname;
	private String reviewsItem_name;

	private double reviewsStarAvg;
	private int reviewsStarCount;


	public String getReviewsItem_name() {
		return reviewsItem_name;
	}
	public void setReviewsItem_name(String reviewsItem_name) {
		this.reviewsItem_name = reviewsItem_name;
	}
	public int getReviewsItem_id() {
		return reviewsItem_id;
	}
	public void setReviewsItem_id(int reviewsItem_id) {
		this.reviewsItem_id = reviewsItem_id;
	}
	public String getReviewsNickname() {
		return reviewsNickname;
	}
	public void setReviewsNickname(String reviewsNickname) {
		this.reviewsNickname = reviewsNickname;
	}
	public String getReviewsStarAvg() {
		return String.format("%.1f", reviewsStarAvg);
	}
	public void setReviewsStarAvg(double reviewsStarAvg) {
		this.reviewsStarAvg = reviewsStarAvg;
	}
	public int getReviewsStarCount() {
		return reviewsStarCount;
	}
	public void setReviewsStarCount(int reviewsStarCount) {
		this.reviewsStarCount = reviewsStarCount;
	}
	public int getReviewsId() {
		return reviewsId;
	}
	public void setReviewsId(int reviewsId) {
		this.reviewsId = reviewsId;
	}
	public String getReviewsComment() {
		return reviewsComment;
	}
	public void setReviewsComment(String reviewsComment) {
		this.reviewsComment = reviewsComment;
	}
	public int getReviewsStar() {
		return reviewsStar;
	}
	public void setReviewsStar(int reviewsStar) {
		this.reviewsStar = reviewsStar;
	}
	public int getReviewsUser_id() {
		return reviewsUser_id;
	}
	public void setReviewsUser_id(int reviewsUser_id) {
		this.reviewsUser_id = reviewsUser_id;
	}
	public Timestamp getReviewsCreated() {
		return reviewsCreated;
	}
	public void setReviewsCreated(Timestamp reviewsCreated) {
		this.reviewsCreated = reviewsCreated;
	}


}
