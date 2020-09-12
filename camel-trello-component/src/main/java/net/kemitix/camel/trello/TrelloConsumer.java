package net.kemitix.camel.trello;

import org.apache.camel.Processor;
import org.apache.camel.support.component.AbstractApiConsumer;

import net.kemitix.camel.trello.internal.TrelloApiName;

public class TrelloConsumer extends AbstractApiConsumer<TrelloApiName, TrelloConfiguration> {

    public TrelloConsumer(TrelloEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }

}
