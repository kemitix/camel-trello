package net.kemitix.camel.trello;

import com.julienvey.trello.Trello;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.spi.UriPath;

@Setter
@Getter
@UriParams
public class TrelloConfiguration {

    @UriParam(description = "Trello Client")
    private Trello trello;

    @UriPath(description = "Name of the endpoint") @Metadata(required = true)
    private String name;
    @UriParam(description = "The action to perform with Trello")
    private TrelloAction action;
    @UriParam(description = "The name of a Trello board")
    private String board;
    @UriParam(description = "The name of a list on the Trello board")
    private String list;
    @UriParam(description = "How to group cards into message(s)")
    private Grouping grouping;

    public enum Grouping {
        LIST, // return cards as List<Card>
        CARD, // return each card in its own message
    }

}
