package ru.omsu.imit.course3.lab4.server.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.omsu.imit.course3.lab4.Person;
import ru.omsu.imit.course3.lab4.server.rest.request.ServerRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api")
public class ServerResource {
    private static final ServerRequest request = new ServerRequest();
    private static final Gson GSON = new GsonBuilder().create();

    @POST
    @Path("/person")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTodoItem(String json) {
        if (request.add(json)) {
            String response = request.get(json);
            return Response.ok(response).build();
        } else {
            return Response.status(405).build();
        }
    }

    @GET
    @Path("/person")
    @Produces("application/json")
    public Response getById(@QueryParam(value = "firstName") String firstName,
                            @QueryParam(value = "lastName") String lastName,
                            @QueryParam(value = "age") Integer age) {
        Person person = new Person(firstName, lastName, age);
        String response = request.get(GSON.toJson(person));
        //String response = GSON.toJson(new TodoItemInfoResponse(1, "Сдать экзамен"));
        return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
    }

}

