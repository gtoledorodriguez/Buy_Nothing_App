package edu.iit.cs445.spring22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Notes {
	private String uid;
	private String nid;
	private String to_type;
	private String to_user_id;
	private String to_id;
	private String description;
	private String date_created;
	private boolean is_Nil = false;
	
	public Notes() {
		this.uid = "";
		this.nid = UUID.randomUUID().toString();
		this.to_type = "";
		this.to_user_id = "";
		this.to_id = "";
		this.description = "";
		this.date_created = createDate();
	}
	
	public Notes(Notes note) {
		this.uid = note.uid;
		this.nid = UUID.randomUUID().toString();
		this.to_type = note.to_type;
		this.to_user_id = note.to_user_id;
		this.to_id = note.to_id;
		this.description = note.description;
		this.date_created = createDate();
	}

	public String getUid() {
		return uid;
	}

	public String getNid() {
		return nid;
	}

	public String getTo_type() {
		return to_type;
	}

	public void setTo_type(String to_type) {
		this.to_type = to_type;
	}

	public String getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	public boolean matchesNid(String nid) {
        return(nid.equals(this.nid));
    }

}
