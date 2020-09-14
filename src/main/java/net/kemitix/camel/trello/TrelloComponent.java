package net.kemitix.camel.trello;

import java.util.Map;

import com.julienvey.trello.Trello;
import lombok.Setter;
import org.apache.camel.Endpoint;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;

@Component("trello")
public class TrelloComponent extends DefaultComponent {

    @Metadata
    @Setter
    private final TrelloConfiguration trelloConfiguration = new TrelloConfiguration();

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        getCamelContext()
                .getRegistry()
                .findByType(Trello.class)
                .stream()
                .findFirst()
                .ifPresent(trelloConfiguration::setTrello);
    }

    protected Endpoint createEndpoint(
            String uri,
            String remaining,
            Map<String, Object> parameters
    ) throws Exception {
        System.out.println("uri = " + uri);
        System.out.println("remaining = " + remaining);
        System.out.println("parameters = " + parameters);
        Endpoint endpoint = new TrelloEndpoint(uri, this, trelloConfiguration);
        setProperties(endpoint, parameters);
        return endpoint;
    }

}
