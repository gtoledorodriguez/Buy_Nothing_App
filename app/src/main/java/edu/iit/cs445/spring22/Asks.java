package edu.iit.cs445.spring22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Asks {
	private String uid;
	private String aid;
	private String type;
	private String description;
	private String start_date;
	private String end_date;
	private String[] extra_zip;
	private boolean is_active;
	private String date_created;

	public Asks(Asks ask) {
		this.uid = ask.uid;
		this.aid = UUID.randomUUID().toString();
		this.type = ask.type;
		this.description = ask.description;
		this.start_date = ask.start_date;
		this.end_date = ask.end_date;
		this.extra_zip = ask.extra_zip;
		this.is_active = true;
		this.date_created = createDate();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String[] getExtra_zip() {
		return extra_zip;
	}

	public void setExtra_zip(String[] extra_zip) {
		this.extra_zip = extra_zip;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	public String createDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}

}
