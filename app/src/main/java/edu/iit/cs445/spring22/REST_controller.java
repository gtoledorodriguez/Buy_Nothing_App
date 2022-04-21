package edu.iit.cs445.spring22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

// Pingable at http://localhost:8080/bn/api/accounts
//   bn:		the basename of the WAR file, see the gradle.build file
//   api:			see the @ApplicationPath annotation in LampDemo.java
//   demo:			see the @Path annotation *above* the REST_controller declaration in this file
//   lamps:			see the @Path declaration above the first @GET in this file

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class REST_controller {
    private BoundaryInterface bi = new BnManager();
    
/**
 * TODO: ACCOUNTS
 */
    
    @Path("/accounts")
    @GET
    public Response getAllAccounts(@QueryParam("key") String key,@QueryParam("start_date") String start_date,@QueryParam("end_date") String end_date) {
    	
    	if(key!=null || (start_date!=null && end_date !=null)) {
    		
    		List<Accounts> searchAccs = bi.searchAccounts(key, start_date, end_date);
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(searchAccs);
    		return Response.status(Response.Status.OK).entity(s).build();
    	}else {
	        // calls the "Get All Lamps" use case
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String s = gson.toJson(bi.getAllAccounts());
	        return Response.status(Response.Status.OK).entity(s).build();
    	}
    }
    
    
    @Path("/accounts")
    @POST
    public Response makeAccounts(@Context UriInfo uriInfo, String json) {
    	System.out.println("makeAccounts");
        String id;
        // calls the "Create Lamp" use case
        Gson gs = new Gson();
        Accounts il = gs.fromJson(json, Accounts.class);
        Accounts l = bi.createAccounts(il);
        
        id = l.getUid();
        Gson gson = new Gson();
        String s = gson.toJson(l);
        // Build the URI for the "Location:" header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id.toString());

        // The response includes header and body data
        return Response.created(builder.build()).entity(s).build();
    }
    
  @Path("/accounts/{uid}")
  @PUT
  public Response changeAccount(@PathParam("uid") String lid, String json) {
      // call the "Update lamp" use case
      Gson gson = new Gson();
      Accounts il = gson.fromJson(json, Accounts.class);
      bi.replaceAccount(lid, il);
      //return Response.ok().build();
      if(bi.isChangingActiveStatus()) {
    	  //return 400
    	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
    	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
    	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
    	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
    	  		+ "\"detail\": \"You may not use PUT to activate an account, use GET /accounts/" + lid + "/activate instead\",\n"
    	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
    	  		+ "\"instance\": \"/accounts/" + lid +"\"\n"
    	  		+ "}").build();
      }
      
      return Response.status(Response.Status.NO_CONTENT).build();
  }

    @Path("/accounts/{uid}")
    @GET
    public Response getSpecificAccount(@PathParam("uid") String lid) {
        // call the "Get Account Detail" use case
        Accounts l = bi.getAccountDetail(lid);
        
        if (l.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}/activate")
    @GET
    public Response getActivateAccount(@PathParam("uid") String lid) {
        // call the "Get Account Detail" use case
        Accounts l = bi.activateAccountDetail(lid);
        
        if (l.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}")
    @DELETE
    public Response deleteAccount(@PathParam("uid") String lid) {
        // call the "Delete Lamp" use case
    	try {
    		bi.deleteAccount(lid);
    		// return a 204
    	    return Response.status(Response.Status.NO_CONTENT).build();
    		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //String s = gson.toJson(l);
            //return Response.ok(s).build();
    	} catch (Exception e) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } 
    }
    
    /**
     * TODO: ASKS
     */
    
    @Path("/accounts/{uid}/asks")
    @POST
    public Response makeAsk(@Context UriInfo uriInfo, String json,@PathParam("uid") String lid) {
    	System.out.println("makeAsks");
    	Accounts a = bi.getAccountDetail(lid);
    	if(!a.getIsActive()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"This account " + lid + " is not active an may not create an ask.\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid +"\"\n"
      	  		+ "}").build();
        }
        String id;
        //String aid;
        // calls the "Create Lamp" use case
        Gson gs = new Gson();
        Asks il = gs.fromJson(json, Asks.class);
        Asks l = bi.createAsks(il);
        
        //id = l.getUid();
        id = l.getAid();
        Gson gson = new Gson();
        String s = gson.toJson(l);
        // Build the URI for the "Location:" header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id.toString());

        // The response includes header and body data
        return Response.created(builder.build()).entity(s).build();
    }
    
    @Path("/asks/{aid}")
    @GET
    public Response getSpecificAsk(@PathParam("aid") String lid) {
        // call the "Get Account Detail" use case
    	Asks l = bi.getAskDetail(lid);
        //Accounts l = bi.getAccountDetail(lid);
        
        if (l.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}/asks/{aid}")
    @PUT
    public Response changeAsk(@PathParam("uid") String lid,@PathParam("aid") String aid, String json) {
        // call the "Update lamp" use case
        Gson gson = new Gson();
        Asks il = gson.fromJson(json, Asks.class);
        bi.replaceAsk(aid,il);//lid, 
        //return Response.ok().build();
        if(bi.isChangingAskActiveStatus()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"You may not use PUT to deactivate an ask, use GET /accounts/" + lid + "/asks/"+aid+"/deactivate instead\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid + "/asks/"+aid+"\"\n"
      	  		+ "}").build();
        }
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @Path("/accounts/{uid}/asks/{aid}/deactivate")
    @GET
    public Response getDeactivateAsk(@PathParam("uid") String lid,@PathParam("aid") String aid) {
        // call the "Get Account Detail" use case
        Asks l = bi.deactivateAskDetail(aid);
        
        if (l.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + aid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}/asks/")
    @GET
    public Response getMyAsks(@PathParam("uid") String lid, @QueryParam("is_active") String is_active) {
        // call the "Get Account Detail" use case
    	
        List<Asks> l;

        l = bi.searchAsksByUidAndActiveStatus(lid,is_active);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }

    @Path("/accounts/{uid}/asks/{aid}")
    @DELETE
    public Response deleteAsk(@PathParam("uid") String lid,@PathParam("aid") String aid) {
        // call the "Delete Lamp" use case
    	try {
    		bi.deleteAsk(aid);
    		// return a 204
    	    return Response.status(Response.Status.NO_CONTENT).build();
    		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //String s = gson.toJson(l);
            //return Response.ok(s).build();
    	} catch (Exception e) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } 
    }
    
    @Path("/asks/")
    @GET
    public Response getAllAsks(@QueryParam("v_by") String lid, @QueryParam("is_active") String is_active, @QueryParam("key") String key,@QueryParam("start_date") String start_date,@QueryParam("end_date") String end_date) {
        // call the "Get Account Detail" use case
    	
        List<Asks> l;
//        boolean b;
//        if(is_active!=null) {
//        	b = Boolean.parseBoolean(is_active);
//        	
//        }else {
//        	b = false;
//        }
        if(key!=null || (start_date!=null && end_date !=null)) {
    		
    		List<Asks> searchAsks = bi.searchAsks(key, start_date, end_date);
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(searchAsks);
    		return Response.status(Response.Status.OK).entity(s).build();
    	}else if(lid != null) {
        	l = bi.searchAsksByUidAndActiveStatusAndZipCodes(lid,is_active);
//        	if(lid.equals(bi.getAllAccounts().get(2).getUid())) {
//        		l = bi.getAllAsks();
//        	}
        }else {
        	l = new ArrayList<Asks>();
        	return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
          	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
          	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
          	  		+ "\"detail\": \"Missing query string, please consult the API documentation\",\n"
          	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
          	  		+ "\"instance\": \"asks\"\n"
          	  		+ "}").build();
        	
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    
    /**
     * TODO: GIVES
     */
    @Path("/accounts/{uid}/gives")
    @POST
    public Response makeGive(@Context UriInfo uriInfo, String json,@PathParam("uid") String lid) {
    	System.out.println("makeGive");
    	Accounts a = bi.getAccountDetail(lid);
    	if(!a.getIsActive()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"This account " + lid + " is not active an may not create a give.\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid +"\"\n"
      	  		+ "}").build();
        }
        String id;
        
        // calls the "Create Gives" use case
        Gson gs = new Gson();
        Gives il = gs.fromJson(json, Gives.class);
        Gives l = bi.createGives(il);
        
        id = l.getGid();
        Gson gson = new Gson();
        String s = gson.toJson(l);
        // Build the URI for the "Location:" header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id.toString());

        // The response includes header and body data
        return Response.created(builder.build()).entity(s).build();
    }
    
    @Path("/accounts/{uid}/gives/{gid}")
    @PUT
    public Response changeGive(@PathParam("uid") String lid,@PathParam("gid") String gid, String json) {
        // call the "Update lamp" use case
        Gson gson = new Gson();
        Gives il = gson.fromJson(json, Gives.class);
        bi.replaceGive(gid,il);//lid, 
        //return Response.ok().build();
        if(bi.isChangingGiveActiveStatus()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"You may not use PUT to deactivate an ask, use GET /accounts/" + lid + "/asks/"+gid+"/deactivate instead\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid + "/asks/"+gid+"\"\n"
      	  		+ "}").build();
        }
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @Path("/gives/{gid}")
    @GET
    public Response getSpecificGive(@PathParam("gid") String lid) {
        // call the "Get Account Detail" use case
    	Gives l = bi.getGivesDetail(lid);
        //Accounts l = bi.getAccountDetail(lid);
        
        if (l.isIs_Nil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}/gives/{gid}/deactivate")
    @GET
    public Response getDeactivateGive(@PathParam("uid") String lid,@PathParam("gid") String gid) {
        // call the "Get Account Detail" use case
        Gives l = bi.deactivateGivesDetail(gid);
        
        if (l.isIs_Nil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + gid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/accounts/{uid}/gives/")
    @GET
    public Response getMyGives(@PathParam("uid") String lid, @QueryParam("is_active") String is_active) {
        // call the "Get Account Detail" use case
    	
    	
        List<Gives> l;
        l = bi.searchGivesByUidAndActiveStatus(lid,is_active);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    
    @Path("/gives/")
    @GET
    public Response getAllGives(@QueryParam("v_by") String lid, @QueryParam("is_active") String is_active, @QueryParam("key") String key,@QueryParam("start_date") String start_date,@QueryParam("end_date") String end_date) {
        // call the "Get Account Detail" use case
    	
        List<Gives> l;
        if(key!=null || (start_date!=null && end_date !=null)) {
    		
    		List<Gives> searchAsks = bi.searchGives(key, start_date, end_date);
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(searchAsks);
    		return Response.status(Response.Status.OK).entity(s).build();
    	}else if(lid != null) {
        	l = bi.searchGivesByUidAndActiveStatusAndZipCodes(lid,is_active);
//        	if(lid.equals(bi.getAllAccounts().get(2).getUid())) {
//        		l = bi.getAllGives();
//        	}
        }else {
        	l = new ArrayList<Gives>();
        	return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
          	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
          	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
          	  		+ "\"detail\": \"Missing query string, please consult the API documentation\",\n"
          	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
          	  		+ "\"instance\": \"asks\"\n"
          	  		+ "}").build();
        	
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    
    @Path("/accounts/{uid}/gives/{gid}")
    @DELETE
    public Response deleteGive(@PathParam("uid") String lid,@PathParam("gid") String gid) {
        // call the "Delete Lamp" use case
    	try {
    		bi.deleteGive(gid);
    		// return a 204
    	    return Response.status(Response.Status.NO_CONTENT).build();
    		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //String s = gson.toJson(l);
            //return Response.ok(s).build();
    	} catch (Exception e) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } 
    }
    
    /**
     * TODO: THANKS
     */
    @Path("/accounts/{uid}/thanks")
    @POST
    public Response makeThank(@Context UriInfo uriInfo, String json,@PathParam("uid") String lid) {
    	System.out.println("makeThank");
    	Accounts a = bi.getAccountDetail(lid);
    	if(!a.getIsActive()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"This account " + lid + " is not active an may not create a give.\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid +"\"\n"
      	  		+ "}").build();
        }
        String id;
        
        // calls the "Create Gives" use case
        Gson gs = new Gson();
        Thanks il = gs.fromJson(json, Thanks.class);
        Thanks l = bi.createThanks(il);
        
        id = l.getTid();
        Gson gson = new Gson();
        String s = gson.toJson(l);
        // Build the URI for the "Location:" header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id.toString());

        // The response includes header and body data
        return Response.created(builder.build()).entity(s).build();
    }
    
    @Path("/accounts/{uid}/thanks/{tid}")
    @PUT
    public Response changeThanks(@PathParam("uid") String lid,@PathParam("tid") String tid, String json) {
        // call the "Update lamp" use case
        Gson gson = new Gson();
        Thanks il = gson.fromJson(json, Thanks.class);
        bi.replaceThanks(tid,il);//lid, 
        //return Response.ok().build();
        if(il.getUid().isEmpty() || il.getThank_to().isEmpty() || il.getDescription().isEmpty()) {
      	  //return 400
      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
      	  		+ "\"detail\": \"Look at API documentation for more information\",\n"
      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
      	  		+ "\"instance\": \"/accounts/" + lid + "/thanks/"+tid+"\"\n"
      	  		+ "}").build();
        }
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @Path("/accounts/{uid}/thanks/")
    @GET
    public Response getMyThanks(@PathParam("uid") String lid) {
        // call the "Get Account Detail" use case
    	
        List<Thanks> l = bi.searchThanksByUid(lid);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    
    @Path("/thanks/{tid}")
    @GET
    public Response getSpecificThanks(@PathParam("tid") String lid) {
        // call the "Get Account Detail" use case
    	Thanks l = bi.getThanksDetail(lid);
        //Accounts l = bi.getAccountDetail(lid);
        
        if (l.isIs_Nil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(l);
            return Response.ok(s).build();
        }
    }
    
    @Path("/thanks/")
    @GET
    public Response getAllThanks(@QueryParam("key") String key,@QueryParam("start_date") String start_date,@QueryParam("end_date") String end_date) {
    	
    	if(key!=null || (start_date!=null && end_date !=null)) {
    		
    		List<Thanks> searchAccs = bi.searchThanks(key, start_date, end_date);
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(searchAccs);
    		return Response.status(Response.Status.OK).entity(s).build();
    	}else {
	        // calls the "Get All Lamps" use case
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        List<Thanks> searchAccs = bi.getAllThanks();
	        String s = gson.toJson(searchAccs);
	        return Response.status(Response.Status.OK).entity(s).build();
    	}
        
    }
    
    @Path("/thanks/received/{uid}")
    @GET
    public Response getAllThanksTo(@PathParam("uid") String lid) {
    	
    	List<Thanks> l = bi.searchThanksByThankTo(lid);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    
    /**
     * TODO: NOTES
     */
    @Path("/notes")
    @POST
    public Response makeNote(@Context UriInfo uriInfo, String json) {
    	System.out.println("makeNote");
//    	Accounts a = bi.getAccountDetail(lid);
//    	if(!a.getIsActive()) {
//      	  //return 400
//      	  //return Response.status(Response.Status.BAD_REQUEST).type("http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation").build();
//      	  return Response.status(Response.Status.BAD_REQUEST).entity("{\n"
//      	  		+ "\"type\": \"http://cs.iit.edu/~virgil/cs445/mail.spring2022/project/api/problems/data-validation\",\n"
//      	  		+ "\"title\": \"Your request data didn\'t pass validation\",\n"
//      	  		+ "\"detail\": \"This account " + lid + " is not active an may not create a give.\",\n"
//      	  		+ "\"status\": "+ Response.Status.BAD_REQUEST.getStatusCode() +",\n"
//      	  		+ "\"instance\": \"/accounts/" + lid +"\"\n"
//      	  		+ "}").build();
//        }
        String id;
        
        // calls the "Create Gives" use case
        Gson gs = new Gson();
        Notes il = gs.fromJson(json, Notes.class);
        Notes l = bi.createNotes(il);
        
        id = l.getNid();
        Gson gson = new Gson();
        String s = gson.toJson(l);
        // Build the URI for the "Location:" header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id.toString());

        // The response includes header and body data
        return Response.created(builder.build()).entity(s).build();
    }
}

