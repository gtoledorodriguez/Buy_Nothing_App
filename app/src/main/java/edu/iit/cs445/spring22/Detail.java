package edu.iit.cs445.spring22;

public class Detail {
	private String zip;
	private DetailAsk asks;
	private DetailGive gives;
	
	public Detail() {
		this.zip = "";
		this.asks = new DetailAsk();
		this.gives = new DetailGive();
	}

	public Detail(Detail d) {
		this.zip = d.zip;
		this.asks = d.asks;
		this.gives = d.gives;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public DetailAsk getAsks() {
		return asks;
	}

	public void setAsks(DetailAsk asks) {
		this.asks = asks;
	}

	public DetailGive getGives() {
		return gives;
	}

	public void setGives(DetailGive gives) {
		this.gives = gives;
	}

}
