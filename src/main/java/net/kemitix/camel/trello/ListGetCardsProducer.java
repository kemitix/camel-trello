package net.kemitix.camel.trello;

import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;

import java.util.List;

public class ListGetCardsProducer
        extends DefaultProducer
        implements TrelloProducer {

    private final String boardName;
    private final String listName;

    @Setter
    private TrelloService trelloService;

    public ListGetCardsProducer(TrelloEndpoint endpoint) {
        super(endpoint);
        this.boardName = endpoint.getBoard();
        this.listName = endpoint.getList();
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<TrelloCard> trelloCards =
                trelloService.listGetCards(boardName, listName);
        exchange.getIn().setBody(trelloCards);
    }
}
