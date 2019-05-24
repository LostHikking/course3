package ru.omsu.imit.course3.lab4.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class PersonClient {

    private static final Gson GSON = new GsonBuilder().create();

    private static javax.ws.rs.client.Client createClient() {
        return ClientBuilder.newClient();
    }

    public Object get(String url, Class<?> classResponse) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String body = response.readEntity(String.class);
        Object obj = GSON.fromJson(body, classResponse);
        client.close();
        return obj;
    }

    public Object post(String url, Object object, Class<?> classResponse) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.json(object));
        String body = response.readEntity(String.class);
        Object obj = GSON.fromJson(body, classResponse);
        client.close();
        return obj;
    }


    public Object put(String url, Object object, Class<?> classResponse) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.put(Entity.json(object));

        String body = response.readEntity(String.class);
        Object obj = GSON.fromJson(body, classResponse);
        client.close();
        return obj;
    }

    public Object delete(String url, Class<?> classResponse) {
        javax.ws.rs.client.Client client = createClient();
        WebTarget myResource = client.target(url);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.delete();
        String body = response.readEntity(String.class);
        Object obj = GSON.fromJson(body, classResponse);
        client.close();
        return obj;
    }
}
