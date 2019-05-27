package ru.omsu.imit.course3.lab5.server.selectioncommittee.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Applicant;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Application;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.rest.request.DBItemRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("/api")
public class SelectionCommitteeResource {
    private static final DBItemRequest request = new DBItemRequest();
    private static final Gson GSON = new GsonBuilder().create();

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
    @Path("/committe/applicant/{id}")
    @Produces("application/json")
    public Response deleteApplicantById(@PathParam(value = "id") int id) {
        int _id = request.DeleteApplicant(id);
        return Response.ok("DELETED").build();
    }

    @POST
    @Path("/committee/application")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addApplication(String json) {
        try {
            Application application = GSON.fromJson(json, Application.class);
            int id = request.AddApplication(application);
            if (id>0) {
                return Response.created(URI.create("api/committee/application/" + id)).build();
            }
        } catch (JsonSyntaxException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Path("/committee/application/{id}")
    @Produces("application/json")
    public Response getApplicationById(@PathParam(value = "id") int id) {
        String response = GSON.toJson(request.getApplicationByID(id));
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
        int _id = request.UpdateApplication(application, id);
        if (_id >= 0) {
            return Response.ok(request.getApplicationByID(_id), MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(400).build();
    }

    @DELETE
    @Path("/committe/application/{id}")
    @Produces("application/json")
    public Response deleteApplicationById(@PathParam(value = "id") int id) {
        int _id = request.DeleteApplication(id);
        return Response.ok("DELETED", MediaType.APPLICATION_JSON).build();
    }
}

