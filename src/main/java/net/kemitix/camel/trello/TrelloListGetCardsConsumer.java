package net.kemitix.camel.trello;

import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;

import java.util.List;

public class TrelloListGetCardsConsumer
        extends DefaultConsumer
        implements TrelloConsumer {

    private final TrelloEndpoint endpoint;
    private final String boardName;
    private final String listName;

    @Setter
    private TrelloService trelloService;

    public TrelloListGetCardsConsumer(
            TrelloEndpoint endpoint,
            Processor processor
    ) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.boardName = endpoint.getBoard();
        this.listName = endpoint.getList();
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        Exchange exchange = endpoint.createExchange();
        try {
            List<TrelloCard> trelloCards =
                    trelloService.listGetCards(boardName, listName);
            exchange.getIn().setBody(trelloCards);
            getProcessor().process(exchange);
        } finally {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException(String.format(
                        "Error getting cards from list '%s' on board '%s'",
                        listName, boardName),
                        exchange, exchange.getException());
            }
        }

    }

}
