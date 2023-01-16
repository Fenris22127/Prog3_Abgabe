package de.medieninformatik.server;

import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class BookApplication extends Application {
    private Set<Object> singletons = new HashSet<>();

    private Set<Class<?>> classes = new HashSet<>();

    public BookApplication() {
        singletons.add(new Rest());
        classes.add(Rest.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
