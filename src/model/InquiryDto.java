package model;

import java.sql.Timestamp;

public class InquiryDto {

	private int id;
	private String email;
	private String inquiry_post;
	private String kenmei;
	private String name;
	private Timestamp created;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInquiry_post() {
		return inquiry_post;
	}
	public void setInquiry_post(String inquiry_post) {
		this.inquiry_post = inquiry_post;
	}
	public String getKenmei() {
		return kenmei;
	}
	public void setKenmei(String kenmei) {
		this.kenmei = kenmei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}



}
