package net.kemitix.camel.trello;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;

import java.util.function.BiFunction;

public enum TrelloAction {

    LIST_GET_CARDS(
            "Get Cards in a List",
            "https://developer.atlassian.com/cloud/trello/rest/api-group-lists/#api-lists-id-cards-get",
            TrelloListGetCardsConsumer::new),

    ;

    private final String name;
    private final String docsUri;
    private final BiFunction<TrelloEndpoint, Processor, TrelloConsumer> creator;

    TrelloAction(
            String name,
            String docsUri,
            BiFunction<TrelloEndpoint, Processor, TrelloConsumer> creator
            ) {
        this.name = name;
        this.docsUri = docsUri;
        this.creator = creator;
    }

    public Consumer createConsumer(TrelloEndpoint endpoint, Processor processor) {
        return creator.apply(endpoint, processor);
    }
}
