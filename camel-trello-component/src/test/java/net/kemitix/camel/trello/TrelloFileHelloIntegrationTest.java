
/*
 * Camel Api Route test generated by camel-api-component-maven-plugin
 */
package net.kemitix.camel.trello;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.kemitix.camel.trello.internal.TrelloApiCollection;
import net.kemitix.camel.trello.internal.TrelloFileHelloApiMethod;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link net.kemitix.camel.trello.api.TrelloFileHello} APIs.
 * TODO Move the file to src/test/java, populate parameter values, and remove @Ignore annotations.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class TrelloFileHelloIntegrationTest extends AbstractTrelloTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloFileHelloIntegrationTest.class);
    private static final String PATH_PREFIX = TrelloApiCollection.getCollection().getApiName(TrelloFileHelloApiMethod.class).getName();

    @Test
    public void testGreetMe() throws Exception {
        // using String message body for single parameter "name"
        final String result = template().requestBody("direct://GREETME", "Bob", String.class);

        assertNotNull(result, "greetMe result");
        LOG.debug("greetMe: " + result);
    }

    @Test
    public void testGreetUs() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is String
        headers.put("CamelTrello.name1", "Bob");
        // parameter type is String
        headers.put("CamelTrello.name2", "Alice");

        final String result = template().requestBodyAndHeaders("direct://GREETUS", null, headers, String.class);

        assertNotNull(result, "greetUs result");
        LOG.debug("greetUs: " + result);
    }

    @Test
    public void testSayHi() throws Exception {
        final String result = template().requestBody("direct://SAYHI", null, String.class);

        assertNotNull(result, "sayHi result");
        LOG.debug("sayHi: " + result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for greetMe
                from("direct://GREETME")
                    .to("trello://" + PATH_PREFIX + "/greetMe?inBody=name");

                // test route for greetUs
                from("direct://GREETUS")
                    .to("trello://" + PATH_PREFIX + "/greetUs");

                // test route for sayHi
                from("direct://SAYHI")
                    .to("trello://" + PATH_PREFIX + "/sayHi");

            }
        };
    }
}
