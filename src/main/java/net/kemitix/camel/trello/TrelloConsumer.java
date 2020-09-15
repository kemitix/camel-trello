package net.kemitix.camel.trello;

import org.apache.camel.Consumer;
import org.apache.camel.RouteAware;
import org.apache.camel.spi.RouteIdAware;

public interface TrelloConsumer extends Consumer, RouteAware, RouteIdAware {
    void setTrelloService(TrelloService trelloService);
}
