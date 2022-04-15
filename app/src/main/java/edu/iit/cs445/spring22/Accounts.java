package edu.iit.cs445.spring22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Accounts {
	private String uid;
	private String name;
	private Address address;
	private String phone;
	private String picture;
	private boolean is_active;
	private String date_created;
	private List<Asks> asksList= new ArrayList<Asks>();
	
	public Accounts() {
		this.uid = UUID.randomUUID().toString();
		this.name = "";
		this.address = new Address();
		this.phone = "";
		this.picture = "";
		isNotActive() ;
		this.date_created = createDate();
	}
	
	public Accounts(String name, Address address, String phone, String picture, String is_active) {
		this.uid = UUID.randomUUID().toString();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.picture = picture;
		isNotActive() ;
		if(is_active == "true") {
			activate();
		}
		this.date_created = createDate();
		
	}
	
	public Accounts(Accounts acc) {
		this.uid = UUID.randomUUID().toString();
		this.name = acc.name;
		this.address = acc.address;
		this.phone = acc.phone;
		this.picture = acc.picture;
		isNotActive() ;
		if(acc.is_active == true) {
			activate();
		}
		
		this.date_created = createDate();
		
	}
	
	public String createDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}
	
	public void activate() {
        this.is_active= true;
    }
	public void isNotActive() {
        this.is_active = false;
    }
	
	public void updatesAccount(String name, Address address, String phone, String picture, boolean active) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.picture = picture;
		this.is_active = active;
	}

	public void setUid() {
		this.uid = UUID.randomUUID().toString();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setisActive(boolean active) {
		this.is_active = active;
	}

	public void setDate_created() {
		this.date_created = createDate();
	}
	
	public String getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getPicture() {
		return picture;
	}

	public boolean getIsActive() {
		return is_active;
	}

	public String getDate_created() {
		return date_created;
	}
	
	
	public boolean matchesUid(String uid) {
        return(uid.equals(this.uid));
    }

	public List<Asks> getAsksList() {
		return asksList;
	}

	public void setAsksList(List<Asks> asksList) {
		this.asksList = asksList;
	}
}
