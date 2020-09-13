package net.kemitix.camel.trello;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;

import org.apache.camel.support.DefaultComponent;

@org.apache.camel.spi.annotations.Component("trello")
public class TrelloComponent extends DefaultComponent {
    
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new TrelloEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
