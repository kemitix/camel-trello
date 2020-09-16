package net.kemitix.camel.trello;

import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultProducer;

import java.util.List;
import java.util.Optional;

public class ListGetCardsProducer
        extends DefaultProducer
        implements TrelloProducer {

    @Setter
    private TrelloService trelloService;

    public ListGetCardsProducer(TrelloEndpoint endpoint) {
        super(endpoint);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String boardId = getBoardId(in);
        String listId = getListId(in, boardId);
        List<TrelloCard> trelloCards =
                trelloService.listGetCards(boardId, listId);
        in.setBody(trelloCards);
    }

    private String getBoardId(Message in) {
        return Optional.ofNullable(in.getHeader(TrelloHeaders.BOARD_ID, String.class))
                .orElseGet(() ->
                        trelloService.lookUpBoardId(
                                in.getHeader(TrelloHeaders.BOARD_NAME, String.class)));
    }

    private String getListId(Message in, String boardId) {
        return Optional.ofNullable(in.getHeader(TrelloHeaders.LIST_ID, String.class))
                .orElseGet(() ->
                        trelloService.lookUpListId(
                                in.getHeader(TrelloHeaders.LIST_NAME, String.class),
                                boardId));
    }

}
