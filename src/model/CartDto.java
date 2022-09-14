package model;

import java.sql.Timestamp;

public class CartDto {

	private int id;
	private int user_id;
	private int item_id;
	private int number;
	private Timestamp created;
	private String items_name;
	private int items_price;
	private int totalPrice;
	private String items_picture;


	public String getItems_picture() {
		return items_picture;
	}
	public void setItems_picture(String items_picture) {
		this.items_picture = items_picture;
	}
	public String getItems_name() {
		return items_name;
	}
	public void setItems_name(String items_name) {
		this.items_name = items_name;
	}
	public int getItems_price() {
		return items_price;
	}
	public void setItems_price(int items_price) {
		this.items_price = items_price;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPraice) {
		this.totalPrice = totalPraice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}


}
