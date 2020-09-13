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
        syntax="trello:name"
)
@NoArgsConstructor
public class TrelloEndpoint extends DefaultEndpoint {
    @UriPath @Metadata(required = true)
    private String name;
    @UriParam(secret = true)
    private String apiKey;
    @UriParam(secret = true)
    private String apiSecret;
    @UriParam(description = "The action to perform with Trello")
    private TrelloAction action;
    @UriParam(description = "The name of a Trello board")
    private String boardName;
    @UriParam(description = "The name of a list on the Trello board")
    private String listName;
    @UriParam(description = "How to group cards into message(s)")
    private CardChunk cardChunk;

    public TrelloEndpoint(
            String uri,
            TrelloComponent component
    ) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new TrelloProducer(this);
    }

    public Consumer createConsumer(
            Processor processor
    ) throws Exception {
        Consumer consumer = new TrelloConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
    }

    public ExecutorService createExecutor() {
        // TODO: Delete me when you implementy your custom component
        return getCamelContext()
                .getExecutorServiceManager()
                .newSingleThreadExecutor(
                        this,
                        "TrelloConsumer");
    }

    private enum CardChunk {
        LIST_CARD, // return cards as List<Card>
        CARD  // return each card in its own message
    }
}
