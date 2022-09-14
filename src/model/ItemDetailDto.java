package model;

import java.sql.Timestamp;

public class ItemDetailDto {

	private int itemsId;
	private String itemsName;
	private int itemsPrice;
	private int itemsRetention_stock;
	private int itemsStock;
	private String itemsSetumei;
	private String itemsSyousai;
	private String itemsPicture;
	private Timestamp itemsCreated;



	public int getItemsId() {
		return itemsId;
	}
	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public int getItemsPrice() {
		return itemsPrice;
	}
	public void setItemsPrice(int itemsPrice) {
		this.itemsPrice = itemsPrice;
	}
	public int getItemsRetention_stock() {
		return itemsRetention_stock;
	}
	public void setItemsRetention_stock(int itemsRetention_stock) {
		this.itemsRetention_stock = itemsRetention_stock;
	}
	public int getItemsStock() {
		return itemsStock;
	}
	public void setItemsStock(int itemsStock) {
		this.itemsStock = itemsStock;
	}
	public String getItemsSetumei() {
		return itemsSetumei;
	}
	public void setItemsSetumei(String itemsSetumei) {
		this.itemsSetumei = itemsSetumei;
	}
	public String getItemsSyousai() {
		return itemsSyousai;
	}
	public void setItemsSyousai(String itemsSyousai) {
		this.itemsSyousai = itemsSyousai;
	}
	public String getItemsPicture() {
		return itemsPicture;
	}
	public void setItemsPicture(String itemsPicture) {
		this.itemsPicture = itemsPicture;
	}
	public Timestamp getItemsCreated() {
		return itemsCreated;
	}
	public void setItemsCreated(Timestamp itemsCreated) {
		this.itemsCreated = itemsCreated;
	}



}
