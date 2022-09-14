package model;

import java.sql.Timestamp;

public class TopItemsDto {

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
