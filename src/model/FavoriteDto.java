package model;

import java.sql.Timestamp;

public class FavoriteDto {

	private int user_id;
	private int item_id;
	private Timestamp created;
	private String favoriteItemName;
	private int favoriteItemPrice;
	private String favoriteItemPicture;




	public String getFavoriteItemPicture() {
		return favoriteItemPicture;
	}
	public void setFavoriteItemPicture(String favoriteItemPicture) {
		this.favoriteItemPicture = favoriteItemPicture;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getFavoriteItemName() {
		return favoriteItemName;
	}
	public void setFavoriteItemName(String favoriteItemName) {
		this.favoriteItemName = favoriteItemName;
	}
	public int getFavoriteItemPrice() {
		return favoriteItemPrice;
	}
	public void setFavoriteItemPrice(int favoriteItemPrice) {
		this.favoriteItemPrice = favoriteItemPrice;
	}



}
