package net.kemitix.camel.trello;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultConsumer;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultProducer;

import java.util.Objects;

/**
 * Trello component which provides access to cards on
 * <a href="https://trello.com/">Trello</a>.
 */
@Setter
@Getter
@UriEndpoint(
        firstVersion = "0.1.0",
        scheme = "trello",
        title = "Trello",
        syntax="trello:name",
        label = "custom"
)
@NoArgsConstructor
public class TrelloEndpoint extends DefaultEndpoint {

    private TrelloService trelloService;

    @UriPath(description = "Name of the endpoint") @Metadata(required = true)
    private String name;

    @UriParam(description = "The action to perform with Trello")
    private TrelloAction action;

    @UriParam(description = "The name of a Trello board")
    private String board;

    @UriParam(description = "The name of a list on the Trello board")
    private String list;

    public TrelloEndpoint(
            String uri,
            TrelloComponent component,
            TrelloService trelloService
    ) {
        super(uri, component);
        this.trelloService = trelloService;
    }

    public Producer createProducer() throws Exception {
        return Objects.requireNonNull(action, "Action not set")
                .createProducer(this, trelloService)
                .orElseGet(() -> new EmptyProducer(this));
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return Objects.requireNonNull(action, "Action not set")
                .createConsumer(this, processor, trelloService)
                .orElseGet(() -> new EmptyConsumer(this, processor));
    }

    private static class EmptyProducer extends DefaultProducer implements TrelloProducer {
        public EmptyProducer() {
            super(null);
        }

        public EmptyProducer(Endpoint endpoint) {
            super(endpoint);
        }

        @Override
        public void process(Exchange exchange) throws Exception {
        }

        @Override
        public void setTrelloService(TrelloService trelloService) {
        }

    }

    private static class EmptyConsumer extends DefaultConsumer implements TrelloConsumer {

        public EmptyConsumer(TrelloEndpoint endpoint, Processor processor) {
            super(endpoint, processor);
        }

        @Override
        public void setTrelloService(TrelloService trelloService) {
        }

    }

}
