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

	public Thanks(Thanks thank) {
		this.uid = UUID.randomUUID().toString();
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

}
