package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class Client {

    private SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(java.util.Date.class, sqlAdapter)
            .setDateFormat("yyyy-MM-dd")
            .create();

    private static javax.ws.rs.client.Client createClient() {
        return ClientBuilder.newClient();
    }

    public String get(String url) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String body = response.readEntity(String.class);
        client.close();
        return body;
    }

    public String post(String url, Object object) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.json(object));
        String body = response.readEntity(String.class);
        client.close();
        return body;
    }

    public String put(String url, Object object) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.put(Entity.json(object));

        String body = response.readEntity(String.class);
        client.close();
        return body;
    }

    public String delete(String url) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.delete();
        String body = response.readEntity(String.class);
        client.close();
        return body;
    }
}
