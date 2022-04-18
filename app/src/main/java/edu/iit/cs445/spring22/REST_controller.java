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
        boolean b;
        if(is_active!=null) {
        	b = Boolean.parseBoolean(is_active);
        	
        }else {
        	b = false;
        }
        l = bi.searchAsksByUidAndActiveStatus(lid,b);//else {
//        	l = bi.searchAsksByUid(lid);
//        }
        //l = bi.getAllAsks();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
        
    }
    

//    
//    @Path("/lamps/{id}")
//    @DELETE
//    public Response deleteLamp(@PathParam("id") String lid) {
//        // call the "Delete Lamp" use case
//    	try {
//    		bi.deleteLamp(lid);
//    		// return a 204
//    	    return Response.status(Response.Status.NO_CONTENT).build();
//    		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            //String s = gson.toJson(l);
//            //return Response.ok(s).build();
//    	} catch (Exception e) {
//            // return a 404
//            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
//        } 
//    }
}

