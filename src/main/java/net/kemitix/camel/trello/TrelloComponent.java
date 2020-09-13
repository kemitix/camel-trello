package net.kemitix.camel.trello;

import java.util.Map;

import com.julienvey.trello.Trello;
import org.apache.camel.Endpoint;

import org.apache.camel.spi.Metadata;
import org.apache.camel.support.DefaultComponent;

@org.apache.camel.spi.annotations.Component("trello")
public class TrelloComponent extends DefaultComponent {

    @Metadata
    private final TrelloConfiguration trelloConfiguration = new TrelloConfiguration();

    protected Endpoint createEndpoint(
            String uri,
            String remaining,
            Map<String, Object> parameters
    ) throws Exception {
        getCamelContext()
                .getRegistry()
                .findByType(Trello.class)
                .stream()
                .findFirst()
                .ifPresent(trelloConfiguration::setTrello);
        Endpoint endpoint = new TrelloEndpoint(uri, this, trelloConfiguration);
        setProperties(endpoint, parameters);
        return endpoint;
    }

}
