package edu.iit.cs445.spring22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Thanks {
	
	private String uid;
	private String tid;
	private String thank_to;
	private String description;
	private String date_created;
	private boolean is_Nil = false;
	
	public Thanks() {
		this.uid = "";
		this.tid = UUID.randomUUID().toString();
		this.thank_to = "";
		this.description = "";
		this.date_created = createDate();
	}
	
	public Thanks(Thanks thank) {
		this.uid = thank.uid;
		this.tid = UUID.randomUUID().toString();
		this.thank_to = thank.thank_to;
		this.description = thank.description;
		this.date_created = createDate();
	}

	public String getUid() {
		return uid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getThank_to() {
		return thank_to;
	}

	public void setThank_to(String thank_to) {
		this.thank_to = thank_to;
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
	public boolean matchesTid(String tid) {
        return(tid.equals(this.tid));
    }

}
