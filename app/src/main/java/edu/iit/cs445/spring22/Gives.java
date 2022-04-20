package edu.iit.cs445.spring22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Gives {
	private String uid;
	private String gid;
	private String type;
	private String description;
	private String start_date;
	private String end_date;
	private String[] extra_zip;
	private boolean is_active;
	private String date_created;
	private boolean is_Nil = false;
	
	public Gives() {
		this.uid = "";
		this.gid = UUID.randomUUID().toString();
		this.extra_zip = new String[0];
		this.is_active = true;
		this.date_created = createDate();
		this.type = "";
		this.description = "";
		this.start_date = "";
		this.end_date = "";
		
	}
	public Gives(Gives gives) {
		this.uid = gives.getUid();
		this.gid = UUID.randomUUID().toString();
		this.type = gives.type;
		this.description = gives.description;
		this.start_date = gives.start_date;
		this.end_date = gives.end_date;
		this.extra_zip = gives.extra_zip;
		this.is_active = true;
		this.date_created = createDate();
	}


	public String getUid() {
		return uid;
	}


	public String getGid() {
		return gid;
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
	
	public String createDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}
	
	public boolean isIs_Nil() {
		return is_Nil;
	}
	public void setIs_Nil(boolean is_Nil) {
		this.is_Nil = is_Nil;
	}
	public boolean matchesGid(String gid) {
        return(gid.equals(this.gid));
    }

}
