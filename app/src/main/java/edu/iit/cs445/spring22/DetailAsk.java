package edu.iit.cs445.spring22;

public class DetailAsk {
	private String total;
	private String active;
	private String inactive;
	
	public DetailAsk(DetailAsk da) {
		this.total = da.total;
		this.active = da.active;
		this.inactive = da.inactive;
	}

	public DetailAsk() {
		// TODO Auto-generated constructor stub
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}
}
