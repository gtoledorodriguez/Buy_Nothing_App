package edu.iit.cs445.spring22;

public class Address {

	private String street;
	private String zip;
	
	public Address(String street, String zip) {
		this.street = street;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setzip(String zip) {
		this.zip = zip;
	}

}
