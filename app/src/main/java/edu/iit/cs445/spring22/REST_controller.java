package edu.iit.cs445.spring22;

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
    
    @Path("/accounts")
    @GET
    public Response getAllAccounts() {
    	if(bi.getAllAccounts().size() == 0) {
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
    		String id;
    		Gson gs = new Gson();
            Accounts il = gs.fromJson(json, Accounts.class);
            Accounts l = bi.createAccounts(il);
            
            Accounts il2 = gs.fromJson(json2, Accounts.class);
            Accounts l2 = bi.createAccounts(il2);
            
            Accounts il3 = gs.fromJson(json3, Accounts.class);
            Accounts l3 = bi.createAccounts(il3);
            
            System.out.println(bi.getAllAccounts().size());
    	}
        // calls the "Get All Lamps" use case
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(bi.getAllAccounts());
        return Response.status(Response.Status.OK).entity(s).build();
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
  public Response controlLamp(@PathParam("uid") String lid, String json) {
      // call the "Update lamp" use case
      Gson gson = new Gson();
      Accounts il = gson.fromJson(json, Accounts.class);
      bi.replaceAccount(lid, il);
      //return Response.ok().build();
      return Response.status(Response.Status.NO_CONTENT).build();
  }

    @Path("/accounts/{uid}")
    @GET
    public Response getSpecificLamp(@PathParam("uid") String lid) {
        // call the "Get Lamp Detail" use case
        Accounts l = bi.getAccountDetail(lid);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(l);
        return Response.ok(s).build();
//        if (l.isNil()) {
//            // return a 404
//            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
//        } else {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String s = gson.toJson(l);
//            return Response.ok(s).build();
//        }
    }
//

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

