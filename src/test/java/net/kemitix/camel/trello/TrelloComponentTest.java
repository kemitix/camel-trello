package net.kemitix.camel.trello;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrelloComponentTest extends CamelTestSupport {

    public static final String BOARD_NAME = "Test Board";
    public static final String LIST_NAME = "Test List";

    @Mock
    private TrelloService trelloService;

    @Test
    public void testTrello() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        Object body = "";
        Map<String, Object> headers = new HashMap<>();
        headers.put(TrelloHeaders.ACTION, TrelloAction.LIST_GET_CARDS);
        headers.put(TrelloHeaders.BOARD, BOARD_NAME);
        headers.put(TrelloHeaders.LIST, LIST_NAME);

        template.sendBodyAndHeaders("direct:start", body, headers);

        mock.await();

        verify(trelloService).listGetCards(BOARD_NAME, LIST_NAME);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        context().getRegistry()
                .bind("trelloService", TrelloService.class, trelloService);
        return new RouteBuilder() {
            public void configure() {
                from("direct:start")
                        .to("trello:test" +
                                "?action=list_get_cards" +
                                "&board=Test Board" +
                                "&list=Test List")
                        .to("mock:result");
            }
        };
    }

}
