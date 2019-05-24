package ru.omsu.imit.course3.lab4.server.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerResourceConfig extends ResourceConfig {
    public ServerResourceConfig() {
        packages("ru.omsu.imit.course3.lab4.server.resources");
    }
}
