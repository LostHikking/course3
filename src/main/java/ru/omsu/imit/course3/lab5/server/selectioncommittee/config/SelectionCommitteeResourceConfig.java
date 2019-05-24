package ru.omsu.imit.course3.lab5.server.selectioncommittee.config;

import org.glassfish.jersey.server.ResourceConfig;

public class SelectionCommitteeResourceConfig extends ResourceConfig {
    public SelectionCommitteeResourceConfig() {
        packages("ru.omsu.imit.course3.lab5.server.selectioncommittee.resources");
    }
}
