package net.kemitix.camel.trello;

import org.apache.camel.Producer;

public interface TrelloProducer extends Producer {
    void setTrelloService(TrelloService trelloService);
}
