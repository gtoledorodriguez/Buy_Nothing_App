package edu.iit.cs445.spring22;

public class DetailGive {
	private String total;
	private String active;
	private String inactive;
	
	public DetailGive(DetailGive dg) {
		this.total = dg.total;
		this.active = dg.active;
		this.inactive = dg.inactive;
	}

	public DetailGive() {
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
