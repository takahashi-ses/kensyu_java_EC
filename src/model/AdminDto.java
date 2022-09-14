package model;

import java.sql.Timestamp;

public class AdminDto {

	private int id;
	private String name;
	private int price;
	private int jenre_id;
	private int retention_stock;
	private int stock;
	private String setumei;
	private String syousai;
	private String picture;
	private Timestamp created;

	private int purchase_price;
	private int purchase_number;
    private int reviewNumber;
    private int userReviewId;

    private String usersName;
    private int reviewId;
	private String comment;
	private int star;
	private String itemsName;
	private Timestamp reviewsCreated;




	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public Timestamp getReviewsCreated() {
		return reviewsCreated;
	}
	public void setReviewsCreated(Timestamp reviewsCreated) {
		this.reviewsCreated = reviewsCreated;
	}
	public int getUserReviewId() {
		return userReviewId;
	}
	public void setUserReviewId(int userReviewId) {
		this.userReviewId = userReviewId;
	}
	public int getReviewNumber() {
		return reviewNumber;
	}
	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}
	public int getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
	}
	public int getPurchase_number() {
		return purchase_number;
	}
	public void setPurchase_number(int purchase_number) {
		this.purchase_number = purchase_number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getJenre_id() {
		return jenre_id;
	}
	public void setJenre_id(int jenre_id) {
		this.jenre_id = jenre_id;
	}
	public int getRetention_stock() {
		return retention_stock;
	}
	public void setRetention_stock(int retention_stock) {
		this.retention_stock = retention_stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSetumei() {
		return setumei;
	}
	public void setSetumei(String setumei) {
		this.setumei = setumei;
	}
	public String getSyousai() {
		return syousai;
	}
	public void setSyousai(String syousai) {
		this.syousai = syousai;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}



}

