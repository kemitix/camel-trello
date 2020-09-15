package net.kemitix.camel.trello;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

import java.util.concurrent.ExecutorService;

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
    @UriParam(description = "How to group cards into message(s)")
    private Grouping grouping;

    public TrelloEndpoint(
            String uri,
            TrelloComponent component,
            TrelloService trelloService
    ) {
        super(uri, component);
        this.trelloService = trelloService;
    }

    public Producer createProducer() throws Exception {
        return new TrelloProducer(this);
    }

    public Consumer createConsumer(
            Processor processor
    ) throws Exception {
        Consumer consumer = action.createConsumer(this, processor, trelloService);
        configureConsumer(consumer);
        return consumer;
    }

    public enum Grouping {
        LIST, // return cards as List<Card>
        CARD, // return each card in its own message
    }

}
