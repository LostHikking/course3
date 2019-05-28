package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.config.SelectionCommitteeResourceConfig;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.config.Settings;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class SelectionCommitteeServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectionCommitteeServer.class);

    private static Server jettyServer;

    private static void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                stopServer();
            }
        });
    }

    public static void createServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(Settings.getRestHTTPPort()).build();
        SelectionCommitteeResourceConfig config = new SelectionCommitteeResourceConfig();
        jettyServer = JettyHttpContainerFactory.createServer(baseUri, config);
        LOGGER.info("Server started at port " + Settings.getRestHTTPPort());
    }

    public static void createServer(int port) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();
        SelectionCommitteeResourceConfig config = new SelectionCommitteeResourceConfig();
        jettyServer = JettyHttpContainerFactory.createServer(baseUri, config);
        LOGGER.info("Server started at port " + port);
    }

    public static void stopServer() {
        LOGGER.info("Stopping server");
        try {
            jettyServer.stop();
            jettyServer.destroy();
        } catch (Exception e) {
            LOGGER.error("Error stopping service", e);
            System.exit(1);
        }
        LOGGER.info("Server stopped");
    }


    public static void main(String[] args) {
        attachShutDownHook();
        createServer();
    }


}
