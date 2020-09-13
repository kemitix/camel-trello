package net.kemitix.camel.trello;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrelloProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(TrelloProducer.class);
    private TrelloEndpoint endpoint;

    public TrelloProducer(TrelloEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
