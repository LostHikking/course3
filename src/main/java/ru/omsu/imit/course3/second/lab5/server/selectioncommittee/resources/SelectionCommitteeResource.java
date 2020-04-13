package ru.omsu.imit.course3.second.lab5.server.selectioncommittee.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import ru.omsu.imit.course3.second.lab5.server.selectioncommittee.Applicant;
import ru.omsu.imit.course3.second.lab5.server.selectioncommittee.Application;
import ru.omsu.imit.course3.second.lab5.server.selectioncommittee.rest.request.DBItemRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("/api")
public class SelectionCommitteeResource {
    private static final SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
    private static final DBItemRequest request = new DBItemRequest();
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(java.util.Date.class, sqlAdapter)
            .setDateFormat("yyyy-MM-dd")
            .create();

    @POST
    @Path("/committee/applicant")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addApplicantItem(String json) {
        try {
            Applicant applicant = GSON.fromJson(json, Applicant.class);
            int id = request.AddApplicant(applicant);
            return Response.created(URI.create("api/committee/" + id)).build();
        } catch (JsonSyntaxException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/committee/applicant/{id}")
    @Produces("application/json")
    public Response getApplicantById(@PathParam(value = "id") int id) {
        String response = GSON.toJson(request.getApplicantByID(id));
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/committee/applicant")
    @Produces("application/json")
    public Response getApplicantAll() {
        String response = GSON.toJson(request.getAllApplicants());
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/committee/applicant/{id}")
    @Produces("application/json")
    public Response editApplicantById(@PathParam(value = "id") int id, String json) {
        Applicant applicant = GSON.fromJson(json, Applicant.class);
        int _id = request.UpdateApplicant(applicant, id);
        return Response.created(URI.create("api/committee/applicant" + _id)).build();
    }

    @DELETE
    @Path("/committee/applicant/{id}")
    @Produces("text/html")
    public Response deleteApplicantById(@PathParam(value = "id") int id) {
        int _id = request.DeleteApplicant(id);
        switch (_id){
            case 1:
                return Response.ok("Success del").build();
            case -1:
                return Response.status(405).encoding("Error in your request").build();
        }
        return Response.status(500).build();
    }

    @POST
    @Path("/committee/application")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addApplication(String json) {
        try {
            Application application = GSON.fromJson(json, Application.class);
            if ((application.getApplicantID() == null) || (application.getSpecialtyID() == null) || (application.getId() == null))
            {
                return Response.status(400).entity("{}").build();
            }
            int id = request.AddApplication(application);
            if (id>0) {
                return Response.ok(request.getApplicationByID(application.getId())).build();
            }
            return Response.status(400).entity("{}").build();
        } catch (JsonSyntaxException e) {
            return Response.status(400).entity("{}").build();
        }
    }

    @GET
    @Path("/committee/application/{id}")
    @Produces("application/json")
    public Response getApplicationById(@PathParam(value = "id") int id) {
        Application requestedApplication = request.getApplicationByID(id);
        String response;
        if (requestedApplication == null)
            response = GSON.toJson(new Application());
        else
        response = GSON.toJson(requestedApplication);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/committee/application")
    @Produces("application/json")
    public Response getApplicationAll() {
        String response = GSON.toJson(request.getAllApplications());
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/committee/application/{id}")
    @Produces("application/json")
    public Response editApplicationById(@PathParam(value = "id") int id, String json) {
        Application application = GSON.fromJson(json, Application.class);
        if ((application.getApplicantID() == null) || (application.getSpecialtyID() == null) || (application.getId() == null))
        {
            return Response.status(400).entity("{}").build();
        }
        Integer st = request.UpdateApplication(application, id);
        if (st == 1) {
            return Response.ok(GSON.toJson(request.getApplicationByID(application.getId())), MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(400).entity("{}").build();
    }

    @DELETE
    @Path("/committee/application/{id}")
    @Produces("application/json")
    public Response deleteApplicationById(@PathParam(value = "id") int id) {
        int _id = request.DeleteApplication(id);
        switch (_id){
            case 1:
                return Response.ok(GSON.toJson("Success del")).build();
            case -1:
                return Response.status(405).entity(GSON.toJson("Error in your request")).build();
            case 0:
                return Response.ok(GSON.toJson("Row doesn't exist")).build();
        }
        return Response.status(500).build();
    }
}

