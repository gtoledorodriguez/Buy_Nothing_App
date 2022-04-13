package edu.iit.cs445.spring22;

import java.util.UUID;

public class Accounts {
	private String uid;
	private String[] address = new String[2];
	private String street;
	private String zip;
	private String phone;
	private String picture;
	private boolean active;
	private String date_created;
	
	public Accounts(String name, String street, String zip, String phone, String picture, boolean active) {
		this.uid = UUID.randomUUID().toString();
		this.street = street;
		this.zip = zip;
		this.address[0] = this.street;
		this.address[1] = this.zip;
		this.phone = phone;
		this.picture = picture;
		this.active = active;
		
	}

}
