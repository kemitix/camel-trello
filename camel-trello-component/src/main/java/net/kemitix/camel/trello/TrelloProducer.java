package net.kemitix.camel.trello;

import org.apache.camel.support.component.AbstractApiProducer;

import net.kemitix.camel.trello.internal.TrelloApiName;
import net.kemitix.camel.trello.internal.TrelloPropertiesHelper;

public class TrelloProducer extends AbstractApiProducer<TrelloApiName, TrelloConfiguration> {

    public TrelloProducer(TrelloEndpoint endpoint) {
        super(endpoint, TrelloPropertiesHelper.getHelper(endpoint.getCamelContext()));
    }
}
