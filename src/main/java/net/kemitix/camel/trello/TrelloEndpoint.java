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

    @UriParam
    private TrelloConfiguration trelloConfiguration;

    public TrelloEndpoint(
            String uri,
            TrelloComponent component,
            TrelloConfiguration configuration
    ) {
        super(uri, component);
        this.trelloConfiguration = configuration;
    }

    public Producer createProducer() throws Exception {
        return new TrelloProducer(this);
    }

    public Consumer createConsumer(
            Processor processor
    ) throws Exception {
        Consumer consumer =
                trelloConfiguration
                        .getAction()
                        .createConsumer(this, processor);
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
}
