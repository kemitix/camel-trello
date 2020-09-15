package net.kemitix.camel.trello;

import java.util.Map;

import org.apache.camel.Endpoint;

import org.apache.camel.spi.Registry;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;

@Component("trello")
public class TrelloComponent extends DefaultComponent {

    @Override
    protected void doStart() throws Exception {
        super.doStart();    }

    protected Endpoint createEndpoint(
            String uri,
            String remaining,
            Map<String, Object> parameters
    ) throws Exception {
        Registry registry = getCamelContext().getRegistry();
        Endpoint endpoint =
                registry
                        .findByType(TrelloService.class)
                        .stream()
                        .findFirst()
                        .map(trelloService ->
                                new TrelloEndpoint(
                                        uri,
                                        this,
                                        trelloService))
                        .orElseThrow(() -> new IllegalStateException(
                                "No TrelloService availing in Camel Context"));
        setProperties(endpoint, parameters);
        return endpoint;
    }

}
