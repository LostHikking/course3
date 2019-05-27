package ru.omsu.imit.course3;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Application;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Client;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.SelectionCommitteeServer;

public class CommitteeClientServerTest {

    private Client client;
    private Gson gson;

    @Before
    public void startServer(){
        SelectionCommitteeServer.createServer();
        client = new Client();
        gson = new Gson();
    }

    @Test
    public void postApplicationTest(){
        Application application = new Application(15, 5, 2);
        Application response = (Application) client.post("http://localhost:8888/api/committee/application/1", application, Application.class);
        System.out.println(gson.toJson(response));

    }

    @After
    public void stopServer(){
        SelectionCommitteeServer.stopServer();
    }
}
