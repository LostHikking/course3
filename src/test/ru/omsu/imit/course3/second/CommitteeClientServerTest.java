package ru.omsu.imit.course3.second;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.omsu.imit.course3.second.lab5.server.selectioncommittee.*;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class CommitteeClientServerTest {

    private static final int PORT = 8899;
    private Client client;
    private SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
    private Gson GSON = new GsonBuilder()
            .registerTypeAdapter(java.util.Date.class, sqlAdapter)
            .setDateFormat("yyyy-MM-dd")
            .create();

    @Before
    public void startServer(){
        SelectionCommitteeServer.createServer(PORT);
        client = new Client();
    }

    @Before
    public void resetDB() throws SQLException {
        DatabaseReseter.resetDatabase();
    }

    @After
    public void stopServer(){
        SelectionCommitteeServer.stopServer();
    }

    @Test
    public void getApplicant(){
        int id = 3;
        Applicant applicantFromGet = GSON.fromJson(client.get("http://localhost:" + PORT + "/api/committee/applicant/" + id), Applicant.class);
        assertEquals(GSON.toJson(applicantFromGet), "{\"id\":3,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"birhdate\":\"1990-01-01\",\"applications\":[{\"id\":6,\"applicantID\":3,\"specialtyID\":5}],\"score\":[{\"disciplineID\":5,\"score\":100}]}");
    }

    @Test
    public void deleteApplication(){
        int id = 3;
        Application applicationFromGet = GSON.fromJson(client.get("http://localhost:" + PORT + "/api/committee/application/" + id), Application.class);
        assertEquals(GSON.toJson(applicationFromGet), "{\"id\":3,\"applicantID\":1,\"specialtyID\":5}");
        String responseFromDel = client.delete("http://localhost:" + PORT + "/api/committee/application/" + id);
        assertEquals(responseFromDel, "\"Success del\"");
        responseFromDel = client.delete("http://localhost:" + PORT + "/api/committee/application/" + id);
        assertEquals(responseFromDel, "\"Row doesn\\u0027t exist\"");
        applicationFromGet = GSON.fromJson(client.get("http://localhost:" + PORT + "/api/committee/application/" + id), Application.class);
        assertEquals(GSON.toJson(applicationFromGet), GSON.toJson(new Application()));
    }

    @Test
    public void putApplication(){
        int id = 3;
        Application applicationForPut = new Application(15, 3, 2);
        String responseFromPut = client.put("http://localhost:" + PORT + "/api/committee/application/" + id, applicationForPut);
        assertEquals(responseFromPut, GSON.toJson(applicationForPut));
        Application applicationForAnotherPut = new Application(10, 3, 95);
        responseFromPut = client.put("http://localhost:" + PORT + "/api/committee/application/" + applicationForPut.getId(), applicationForAnotherPut);
        assertEquals(responseFromPut, "{}");
        String responseFromGet = client.get("http://localhost:" + PORT + "/api/committee/application/" + applicationForPut.getId());
        assertEquals(responseFromGet, GSON.toJson(applicationForPut));
        applicationForAnotherPut = new Application();
        responseFromPut = client.put("http://localhost:" + PORT + "/api/committee/application/" + applicationForPut.getId(), applicationForAnotherPut);
        assertEquals(responseFromPut, "{}");
    }

    @Test
    public void postApplication(){
        Application applicationForPost = new Application(15,3,2);
        String responseFromPost = client.post("http://localhost:" + PORT + "/api/committee/application/", applicationForPost);
        assertEquals(GSON.toJson(applicationForPost), responseFromPost);
        Application applicationForAnotherPost = new Application(16, 4, 95);
        String responseFromAnotherPost = client.post("http://localhost:" + PORT + "/api/committee/application/", applicationForAnotherPost);
        assertEquals("{}", responseFromAnotherPost);
        applicationForAnotherPost = new Application();
        responseFromAnotherPost = client.post("http://localhost:" + PORT + "/api/committee/application/", applicationForAnotherPost);
        assertEquals("{}", responseFromAnotherPost);
        responseFromAnotherPost = client.post("http://localhost:" + PORT + "/api/committee/application/", "asdfgadsf");
        assertEquals("{}", responseFromAnotherPost);
    }
}
