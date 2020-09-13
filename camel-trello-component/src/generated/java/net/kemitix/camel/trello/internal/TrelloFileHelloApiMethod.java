
/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package net.kemitix.camel.trello.internal;

import java.lang.reflect.Method;
import java.util.List;

import net.kemitix.camel.trello.api.TrelloFileHello;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for net.kemitix.camel.trello.api.TrelloFileHello
 */
public enum TrelloFileHelloApiMethod implements ApiMethod {

    GREETME(
        String.class,
        "greetMe",
        arg("name", String.class)),

    GREETUS(
        String.class,
        "greetUs",
        arg("name1", String.class),
        arg("name2", String.class)),

    SAYHI(
        String.class,
        "sayHi");

    

    private final ApiMethod apiMethod;

    private TrelloFileHelloApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(TrelloFileHello.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    @Override
    public Class<?> getResultType() { return apiMethod.getResultType(); }

    @Override
    public List<String> getArgNames() { return apiMethod.getArgNames(); }

    @Override
    public List<Class<?>> getArgTypes() { return apiMethod.getArgTypes(); }

    @Override
    public Method getMethod() { return apiMethod.getMethod(); }
}
