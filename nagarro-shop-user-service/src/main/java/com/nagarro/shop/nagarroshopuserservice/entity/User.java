package com.nagarro.shop.nagarroshopuserservice.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "google_id_token")
	private String googleIdToken;
	
	@Column(name = "date_updated")
	private ZonedDateTime dateUpdated;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getGoogleIdToken() {
		return googleIdToken;
	}
	public void setGoogleIdToken(String googleIdToken) {
		this.googleIdToken = googleIdToken;
	}
	public ZonedDateTime getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(ZonedDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
