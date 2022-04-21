package edu.iit.cs445.spring22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.google.gson.Gson;


public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	private static List<Asks> asks = new ArrayList<Asks>();
	private static List<Gives> gives = new ArrayList<Gives>();
	private static List<Thanks> thanks = new ArrayList<Thanks>();
	private static List<Notes> notes = new ArrayList<Notes>();
	
	private boolean inAccountsList = false;
	private boolean inAsksList = false;
	private boolean inGivesList = false;
	private boolean inThanksList = false;
	
	private boolean changingActiveStatus = false;
	private boolean changingAskActiveStatus = false;
	private boolean changingGiveActiveStatus = false;
	
	//TODO: ACCOUNTS
	public void preLoadAccountsList() {

		String json = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"Virgil Bistriceanu\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"10 West 31st ST\",\n"
				+ "    \"zip\": \"60616\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/test-virgil.jpeg\",\n"
				+ "  \"is_active\": true,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		String json2 = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"Jane Smith\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"123 2nd ST\",\n"
				+ "    \"zip\": \"60607\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/jane-smith.jpeg\",\n"
				+ "  \"is_active\": false,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		String json3 = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"CSR #1\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"101 W Main St.\",\n"
				+ "    \"zip\": \"60010\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/test-virgil.jpeg\",\n"
				+ "  \"is_active\": true,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		
		Gson gs = new Gson();
        Accounts il = gs.fromJson(json, Accounts.class);
        this.createAccounts(il);
        
        Accounts il2 = gs.fromJson(json2, Accounts.class);
        this.createAccounts(il2);
        
        Accounts il3 = gs.fromJson(json3, Accounts.class);
        this.createAccounts(il3);
        
        System.out.println(this.getAllAccounts().size());
	}
	public List<Accounts> getAllAccounts() {
		if(accounts.size() == 0) {
			this.preLoadAccountsList();
    	}
		
		return accounts;
	}
	
	public Accounts createAccounts(Accounts il) {
        Accounts l= new Accounts(il);
        accounts.add(l);
        return(l);
    }
	
	public void replaceAccount(String lid, Accounts il) {
		//Will not active account
    	Accounts l = findByUid(lid);
    	
    	l.setName(il.getName());
    	l.setAddress(il.getAddress());
    	l.setPhone(il.getPhone());
    	l.setPicture(il.getPicture());
    	
    	if(il.getIsActive()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	if(!isInAccountsList()) {
    		accounts.add(l);
    	}
    }
	
	private Accounts findByUid(String lid) {
    	//System.out.println(lid);
    	Iterator<Accounts> li = accounts.listIterator();
        while(li.hasNext()) {
            Accounts l = li.next();
            if(l.matchesUid(lid)) {
            	l.setIsNil(false);
            	this.setInAccountsList(true);
            	return(l);
            }
        }
        this.setInAccountsList(false);
        return (new NullAccount());
    }
	
	public Accounts getAccountDetail(String lid) {
        return(findByUid(lid));
    }
	public Accounts activateAccountDetail(String lid) {
		Accounts l = findByUid(lid);
		l.activate();
        return l;
    }

	public boolean isInAccountsList() {
		return inAccountsList;
	}

	public void setInAccountsList(boolean inAccountsList) {
		this.inAccountsList = inAccountsList;
	}

	public boolean isChangingActiveStatus() {
		return changingActiveStatus;
	}

	public void setChangingActiveStatus(boolean changingActiveStatus) {
		this.changingActiveStatus = changingActiveStatus;
	}
	
	public List<Accounts> searchAccounts(String key, String start_date, String end_date) {
		List<Accounts> searchAccs = new ArrayList<Accounts>();
		List<Accounts> allAccs = this.getAllAccounts();
		
		for(int i = 0; i<allAccs.size();i++) {
			
			Accounts l = allAccs.get(i);
			String name = l.getName().toLowerCase();
			Address address = l.getAddress();
			String phone = l.getPhone().toLowerCase();
			String picture = l.getPicture().toLowerCase();
			String street = address.getStreet().toLowerCase();
			String zip = address.getZip().toLowerCase();
			String date = l.getDate_created();
			if(key!=null) {
				key = key.toLowerCase();
			}
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if((!(date1.before(startDate) || date1.after(endDate))) && key!=null) {
						if(name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key)) {
	        				searchAccs.add(l);
	        			}
					}else if(!(date1.before(startDate) || date1.after(endDate))) {
						searchAccs.add(l);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(key!=null && (name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key))) {
    				searchAccs.add(l);
    			}
			}
			
		}
		return searchAccs;
	}
	

	@Override
	public void deleteAccount(String uid) {

    	Accounts l = findByUid(uid);
    	if (l.isNil()) {
    		throw new NoSuchElementException();
    	} else {
    		accounts.remove(l);
    	}
		
	}

	//TODO: ASKS
	public Asks createAsks(Asks il) {
		Asks l= new Asks(il);
        asks.add(l);
        return(l);
	}
	
	public Asks getAskDetail(String lid) {
		return (findByAid(lid));
	}
	private Asks findByAid(String lid) {
    	//System.out.println(lid);
    	Iterator<Asks> li = asks.listIterator();
        while(li.hasNext()) {
            Asks l = li.next();
            if(l.matchesAid(lid)) {
            	l.setIsNil(false);
            	this.setInAsksList(true);
            	return(l);
            }
        }
        this.setInAsksList(false);
        return (new NullAsk());
	}
	public boolean isChangingAskActiveStatus() {
		return changingAskActiveStatus;
	}
	public void setChangingAskActiveStatus(boolean changingAskActiveStatus) {
		this.changingAskActiveStatus = changingAskActiveStatus;
	}
	
	public void replaceAsk(String aid, Asks il) {
    	Asks l = findByAid(aid);
		l.setType(il.getType()); 
		l.setDescription(il.getDescription());
		l.setStart_date(il.getStart_date());
		l.setEnd_date(il.getEnd_date());
		l.setExtra_zip(il.getExtra_zip());
    	
    	if(il.isIs_active()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	if(!isInAsksList()) {
    		asks.add(l);
    	}
		
	}
	public boolean isInAsksList() {
		return inAsksList;
	}
	public void setInAsksList(boolean inAsksList) {
		this.inAsksList = inAsksList;
	}

	public Asks deactivateAskDetail(String aid) {
		Asks l = findByAid(aid);
		l.setIs_active(false);
        return l;
	}
	
	public List<Asks> getAllAsks() {
		return asks;
	}

	@Override
	public List<Asks> searchAsksByUidAndActiveStatus(String lid, String b) {
		List<Asks> searchAsks = new ArrayList<Asks>();
		List<Asks> allAsks = this.getAllAsks();
		
		for(int i = 0; i<allAsks.size();i++) {
			Asks l = allAsks.get(i);
			if(b!=null) {
				if(l.getUid().equals(lid) && (l.isIs_active()== Boolean.parseBoolean(b))) {
					searchAsks.add(l);
				}
			}else {
				if(l.getUid().equals(lid)) {
					searchAsks.add(l);
				}
			}
			
		}
		return searchAsks;
	}
	
	public List<Asks> searchAsksByUidAndActiveStatusAndZipCodes(String lid, String is_active) {

		//view by user lid
		List<Asks> searchAsks = new ArrayList<Asks>();
		List<Asks> allAsks = this.getAllAsks();
		Accounts user = this.findByUid(lid);
		String userZip = user.getAddress().getZip();

		for(int i = 0; i<allAsks.size();i++) {
			Asks l = allAsks.get(i);

			List<String> zip = new ArrayList<String>();
			zip.addAll(Arrays.asList(l.getExtra_zip()));
			
			Accounts giveUser = this.findByUid(l.getUid());
			String giveZip = giveUser.getAddress().getZip();
			
			if(!is_active.isEmpty()) {

				if((l.isIs_active() == Boolean.parseBoolean(is_active))) {
					
					if(user.getUid().equals(this.getAllAccounts().get(2).getUid())) {
						searchAsks.add(l);
					}else {
						if(userZip.equals(giveZip) || zip.contains(userZip)) {//userZip.equals(giveZip) l.getExtra_zip().length > 0
							searchAsks.add(l);
						}
					}

					

				}
			}else {
				
				if(user.getUid().equals(this.getAllAccounts().get(2).getUid())) {
					searchAsks.add(l);
				}else {
					if(userZip.equals(giveZip) || zip.contains(userZip)) {//userZip.equals(giveZip) l.getExtra_zip().length > 0
						searchAsks.add(l);
					}
				}
			}
			
		}
		return searchAsks;
	}
	@Override
	public void deleteAsk(String lid) {

    	Asks l = findByAid(lid);
    	if (l.isNil()) {
    		throw new NoSuchElementException();
    	} else {
    		asks.remove(l);
    	}
		
	}

	@Override
	public List<Asks> searchAsks(String key, String start_date, String end_date) {
		List<Asks> searchAsks = new ArrayList<Asks>();
		List<Asks> allAsks = this.getAllAsks();
		
		for(int i = 0; i<allAsks.size();i++) {
			
			Asks l = allAsks.get(i);
			
			String type = l.getType().toLowerCase();
			String description = l.getDescription().toLowerCase();
			String start_dateGive = l.getStart_date().toLowerCase();
			String end_dateGive = l.getEnd_date().toLowerCase();
			String[] extra_zip = l.getExtra_zip();
			String date = l.getDate_created();
			List<String> zip = new ArrayList<String>();
			zip.addAll(Arrays.asList(extra_zip ));
			if(key!=null) {
				key = key.toLowerCase();
			}
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if((!(date1.before(startDate) || date1.after(endDate))) && key!=null) {
						if(type.contains(key) || description.contains(key)|| start_dateGive.contains(key) || end_dateGive.contains(key) || zip.contains(key)) {
	        				searchAsks.add(l);
	        			}
					}else if(!(date1.before(startDate) || date1.after(endDate))) {
						searchAsks.add(l);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(type.contains(key) || description.contains(key)|| start_dateGive.contains(key) || end_dateGive.contains(key) || zip.contains(key)) {
    				searchAsks.add(l);
    			}
			}
			
		}
		return searchAsks;
	}
	
	//TODO: GIVES
	
	@Override
	public Gives createGives(Gives il) {
		Gives l= new Gives(il);
		gives.add(l);
        return(l);
	}
	public boolean isChangingGiveActiveStatus() {
		return changingGiveActiveStatus;
	}
	public void setChangingGiveActiveStatus(boolean changingGiveActiveStatus) {
		this.changingGiveActiveStatus = changingGiveActiveStatus;
	}
	@Override
	public void replaceGive(String gid, Gives il) {
    	Gives l = findByGid(gid);
		l.setType(il.getType()); 
		l.setDescription(il.getDescription());
		l.setStart_date(il.getStart_date());
		l.setEnd_date(il.getEnd_date());
		l.setExtra_zip(il.getExtra_zip());
    	
    	if(il.isIs_active()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	if(!isInGivesList()) {
    		gives.add(l);
    	}
		
	}
	private Gives findByGid(String gid) {
    	//System.out.println(lid);
    	Iterator<Gives> li = gives.listIterator();
        while(li.hasNext()) {
        	Gives l = li.next();
            if(l.matchesGid(gid)) {
            	l.setIs_Nil(false);
            	this.setInGivesList(true);
            	return(l);
            }
        }
        this.setInGivesList(false);
        return (new NullGive());
	}
	public boolean isInGivesList() {
		return inGivesList;
	}
	public void setInGivesList(boolean inGivesList) {
		this.inGivesList = inGivesList;
	}
	@Override
	public Gives getGivesDetail(String lid) {
		return (findByGid(lid));
	}
	@Override
	public Gives deactivateGivesDetail(String gid) {
		Gives l = findByGid(gid);
		l.setIs_active(false);
        return l;
	}
	@Override
	public List<Gives> searchGivesByUidAndActiveStatus(String lid, String is_active) {
		List<Gives> searchGives = new ArrayList<Gives>();
		List<Gives> allGives = this.getAllGives();
		
		for(int i = 0; i<allGives.size();i++) {
			Gives l = allGives.get(i);
			if(is_active!=null) {
				if(l.getUid().equals(lid) && (l.isIs_active()== Boolean.parseBoolean(is_active))) {
					searchGives.add(l);
				}
			}else {
				if(l.getUid().equals(lid)) {
					searchGives.add(l);
				}
			}
			
		}
		return searchGives;
	}
	
	public List<Gives> getAllGives() {
		return gives;
	}
	
	@Override
	public List<Gives> searchGivesByUidAndActiveStatusAndZipCodes(String lid, String is_active) {
		//view by user lid
		List<Gives> searchGives = new ArrayList<Gives>();
		List<Gives> allGives = this.getAllGives();
		Accounts user = this.findByUid(lid);
		String userZip = user.getAddress().getZip();

		for(int i = 0; i<allGives.size();i++) {
			Gives l = allGives.get(i);

			List<String> zip = new ArrayList<String>();
			zip.addAll(Arrays.asList(l.getExtra_zip()));
			
			Accounts giveUser = this.findByUid(l.getUid());
			String giveZip = giveUser.getAddress().getZip();
			
			if(!is_active.isEmpty()) {

				if((l.isIs_active() == Boolean.parseBoolean(is_active))) {
					//l.setDescription(l.getDescription() + ", IF = "+ (l.isIs_active() == Boolean.parseBoolean(is_active)));// == Boolean.parseBoolean(is_active)
					
					if(user.getUid().equals(this.getAllAccounts().get(2).getUid())) {
						searchGives.add(l);
					}else {
						if(userZip.equals(giveZip) || zip.contains(userZip)) {//userZip.equals(giveZip) l.getExtra_zip().length > 0
							searchGives.add(l);
						}
					}

				}
			}else {
				//l.setDescription(l.getDescription() + ", Else = "+ (l.isIs_active() == Boolean.parseBoolean(is_active)));
				
				if(user.getUid().equals(this.getAllAccounts().get(2).getUid())) {
					searchGives.add(l);
				}else {
					if(userZip.equals(giveZip) || zip.contains(userZip)) { //userZip.equals(giveZip) l.getExtra_zip().length > 0
						searchGives.add(l);
					}
				}
				
			}
			
		}
		return searchGives;
	}
	@Override
	public void deleteGive(String gid) {

    	Gives l = findByGid(gid);
    	if (l.isIs_Nil()) {
    		throw new NoSuchElementException();
    	} else {
    		gives.remove(l);
    	}
		
	}
	@Override
	public List<Gives> searchGives(String key, String start_date, String end_date) {
		List<Gives> searchGives = new ArrayList<Gives>();
		List<Gives> allGives = this.getAllGives();
		
		for(int i = 0; i<allGives.size();i++) {
			
			Gives l = allGives.get(i);
			
			String type = l.getType().toLowerCase();
			String description = l.getDescription().toLowerCase();
			String start_dateGive = l.getStart_date().toLowerCase();
			String end_dateGive = l.getEnd_date().toLowerCase();
			String[] extra_zip = l.getExtra_zip();
			String date = l.getDate_created();
			List<String> zip = new ArrayList<String>();
			zip.addAll(Arrays.asList(extra_zip ));
			if(key!=null) {
				key = key.toLowerCase();
			}
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if((!(date1.before(startDate) || date1.after(endDate))) && key!=null) {
						if(type.contains(key) || description.contains(key)|| start_dateGive.contains(key) || end_dateGive.contains(key) || zip.contains(key)) {
	        				searchGives.add(l);
	        			}
					}else if(!(date1.before(startDate) || date1.after(endDate))) {
						searchGives.add(l);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(type.contains(key) || description.contains(key)|| start_dateGive.contains(key) || end_dateGive.contains(key) || zip.contains(key)) {
    				searchGives.add(l);
    			}
			}
			
		}
		return searchGives;
	}
	
	//TODO: THANKS
	@Override
	public Thanks createThanks(Thanks il) {
		Thanks l= new Thanks(il);
		thanks.add(l);
        return(l);
	}
	@Override
	public boolean isInThanksList() {
		return inThanksList;
	}
	@Override
	public void setInThanksList(boolean inThanksList) {
		this.inThanksList = inThanksList;
	}

	@Override
	public void replaceThanks(String tid, Thanks il) {
		Thanks l = findByTid(tid);
		l.setDescription(il.getDescription());
		l.setThank_to(il.getThank_to());

		if(!isInThanksList()) {
		  thanks.add(l);
		}
		
	}
	private Thanks findByTid(String tid) {
    	//System.out.println(lid);
    	Iterator<Thanks> li = thanks.listIterator();
        while(li.hasNext()) {
        	Thanks l = li.next();
            if(l.matchesTid(tid)) {
            	l.setIs_Nil(false);
            	this.setInGivesList(true);
            	return(l);
            }
        }
        this.setInGivesList(false);
        return (new NullThanks());
	}
	@Override
	public Thanks getThanksDetail(String lid) {
		return (findByTid(lid));
	}
	@Override
	public List<Thanks> getAllThanks() {
		return thanks;
	}
	@Override
	public List<Thanks> searchThanks(String key, String start_date, String end_date) {
		List<Thanks> searchAccs = new ArrayList<Thanks>();
		List<Thanks> allAccs = this.getAllThanks();
		
		for(int i = 0; i<allAccs.size();i++) {
			
			Thanks l = allAccs.get(i);

			String description = l.getDescription();
			String date = l.getDate_created();
			
			if(key!=null) {
				key = key.toLowerCase();
			}
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if((!(date1.before(startDate) || date1.after(endDate))) && key!=null) {
						if(description.contains(key)) {
	        				searchAccs.add(l);
	        			}
					}else if(!(date1.before(startDate) || date1.after(endDate))) {
						searchAccs.add(l);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(key!=null && description.contains(key)) {
    				searchAccs.add(l);
    			}
			}
			
		}
		return searchAccs;
	}
	@Override
	public List<Thanks> searchThanksByUid(String lid) {
		List<Thanks> searchThanks = new ArrayList<Thanks>();
		List<Thanks> allThanks = this.getAllThanks();
		
		for(int i = 0; i<allThanks.size();i++) {
			Thanks l = allThanks.get(i);
			if(l.getUid().equals(lid)) {
				searchThanks.add(l);
			}
			
		}
		return searchThanks;
	}
	@Override
	public List<Thanks> searchThanksByThankTo(String lid) {
		List<Thanks> searchThanks = new ArrayList<Thanks>();
		List<Thanks> allThanks = this.getAllThanks();
		
		for(int i = 0; i<allThanks.size();i++) {
			Thanks l = allThanks.get(i);
			if(l.getThank_to().equals(lid)) {
				searchThanks.add(l);
			}
			
		}
		return searchThanks;
	}
	
	//TODO: NOTES
	@Override
	public Notes createNotes(Notes il) {
		Notes l= new Notes(il);
		notes.add(l);
        return(l);
	}
	@Override
	public void replaceNotes(String nid, Notes il) {
		Notes l = findByNid(nid);
//		this.uid = "";
//		this.to_type = "";
//		this.to_user_id = "";
//		this.to_id = "";
//		this.description = "";
		l.setTo_type(il.getTo_type());
		l.setTo_user_id(il.getTo_user_id());
		l.setTo_id(il.getTo_id());
		l.setDescription(il.getDescription());

		if(!isInThanksList()) {
		  notes.add(l);
		}
	}
	private Notes findByNid(String nid) {
    	//System.out.println(lid);
    	Iterator<Notes> li = notes.listIterator();
        while(li.hasNext()) {
        	Notes l = li.next();
            if(l.matchesNid(nid)) {
            	l.setIs_Nil(false);
            	this.setInGivesList(true);
            	return(l);
            }
        }
        this.setInGivesList(false);
        return (new NullNotes());
	}
	@Override
	public void deleteNote(String lid) {

		Notes l = findByNid(lid);
    	if (l.isIs_Nil()) {
    		throw new NoSuchElementException();
    	} else {
    		notes.remove(l);
    	}
	}
	@Override
	public List<Notes> getAllNotes() {
		return notes;
	}
	@Override
	public List<Notes> searchNotes(String uid, String to_user_id, String type, String to_id) {
		List<Notes> searchAccs = new ArrayList<Notes>();
		List<Notes> allAccs = this.getAllNotes();
//		List<String> types = new ArrayList<String>();
//		types.add("ask");
//		types.add("gives");
		boolean a = !uid.isEmpty();
		boolean b = !to_user_id.isEmpty();
		boolean c = !type.isEmpty();
		boolean d = !to_id.isEmpty();
		
		for(int i = 0; i<allAccs.size();i++) {
			
			Notes l = allAccs.get(i);
			String lUnid = l.getUid();
			String lToUserId = l.getTo_user_id();
			String lType = l.getTo_type();
			String lToId = l.getTo_id();
			
			if(a && b && c && d) {
				if(lUnid.equals(uid) && lToUserId.equals(to_user_id) && (!lType.equals("notes") && lType.equals(type)) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
			}else if(a && b && c) {
				if(lUnid.equals(uid) && lToUserId.equals(to_user_id) && (!lType.equals("notes") && lType.equals(type))) {
					searchAccs.add(l);
				}
				
			}else if(a && b && d) {
				if(lUnid.equals(uid) && lToUserId.equals(to_user_id) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
				
			}else if(a && c && d) {
				if(lUnid.equals(uid) && (!lType.equals("notes") && lType.equals(type)) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
				
			}else if(b && c && d) {
				if(lToUserId.equals(to_user_id) && (!lType.equals("notes") && lType.equals(type)) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
				
			}else if(a && b) {
				if(lUnid.equals(uid) && lToUserId.equals(to_user_id)) {
					searchAccs.add(l);
				}
			}else if(a && d) {
				if(lUnid.equals(uid) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
				
			}else if(c && d) {
				if((!lType.equals("notes") && lType.equals(type)) && lToId.equals(to_id)) {
					searchAccs.add(l);
				}
				
			}else if(b && c) {
				if(lToUserId.equals(to_user_id) && (!lType.equals("notes") && lType.equals(type))) {
					searchAccs.add(l);
				}
				
			}else if(a) {
				if(lUnid.equals(uid)) {
					searchAccs.add(l);
				}
				
			}else if(b) {
				if(lToUserId.equals(to_user_id)) {
					searchAccs.add(l);
				}
				
			}else if(c) {
				if((!lType.equals("notes") && lType.equals(type))) {
					searchAccs.add(l);
				}
				
			}else if(d) {
				if(lToId.equals(to_id)) {
					searchAccs.add(l);
				}
			}
			
		}
		return searchAccs;
	}
	@Override
	public Notes getNotesDetail(String lid) {
		return (findByNid(lid));
	}

	
	

}
