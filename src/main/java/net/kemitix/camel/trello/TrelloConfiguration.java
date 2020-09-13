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

    private Trello trello;

    @UriPath(description = "Name of the endpoint") @Metadata(required = true)
    private String name;
    @UriParam(secret = true, description = "The Trello API Key")
    private String apiKey;
    @UriParam(secret = true, description = "The Trello Secret Key")
    private String apiSecret;
    @UriParam(description = "The action to perform with Trello")
    private TrelloAction action;
    @UriParam(description = "The name of a Trello board")
    private String boardName;
    @UriParam(description = "The name of a list on the Trello board")
    private String listName;
    @UriParam(description = "How to group cards into message(s)")
    private CardChunk cardChunk;

    public enum CardChunk {
        LIST_CARD, // return cards as List<Card>
        CARD  // return each card in its own message
    }

}
