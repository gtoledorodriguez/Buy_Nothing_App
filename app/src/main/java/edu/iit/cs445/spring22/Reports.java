package edu.iit.cs445.spring22;

import java.util.UUID;

public class Reports {
	
	private String rid;
	private String name;
	private String c_by;
	private String v_by;
	private String start_date;
	private String end_date;
	private String asks;
	private String gives;
	private Detail[] detail;
	
	public Reports() {
		this.rid = UUID.randomUUID().toString();
		this.name = "";
		this.c_by = "";
		this.v_by = "";
		this.start_date = "";
		this.end_date = "";
		this.asks = "";
		this.gives = "";
		this.detail = new Detail[0];
	}

	public Reports(Reports r) {
		this.rid = UUID.randomUUID().toString();
		this.name = r.name;
		this.c_by = r.c_by;
		this.v_by = r.v_by;
		this.start_date = r.start_date;
		this.end_date = r.end_date;
		this.asks = r.asks;
		this.gives = r.gives;
		this.detail = r.detail;
	}

	public String getRid() {
		return rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getC_by() {
		return c_by;
	}

	public void setC_by(String c_by) {
		this.c_by = c_by;
	}

	public String getV_by() {
		return v_by;
	}

	public void setV_by(String v_by) {
		this.v_by = v_by;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getAsks() {
		return asks;
	}

	public void setAsks(String asks) {
		this.asks = asks;
	}

	public String getGives() {
		return gives;
	}

	public void setGives(String gives) {
		this.gives = gives;
	}

	public Detail[] getDetail() {
		return detail;
	}

	public void setDetail(Detail[] detail) {
		this.detail = detail;
	}

}
