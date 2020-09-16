package net.kemitix.camel.trello;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListGetCardsTest extends CamelTestSupport
        implements WithAssertions {

    public static final String BOARD_ID = "Board Id";
    public static final String LIST_ID = "List Id";
    public static final String BOARD_NAME = "Test Board";
    public static final String LIST_NAME = "Test List";

    @Mock
    private TrelloService trelloService;

    Object body = "";
    Map<String, Object> headers = new HashMap<>();
    Exception e;

    @Test
    @DisplayName("valid with board name and list name")
    public void validNameName() throws Exception {
        //given
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        headers.put(TrelloHeaders.BOARD_NAME, BOARD_NAME);
        headers.put(TrelloHeaders.LIST_NAME, LIST_NAME);
        given(trelloService.lookUpBoardId(BOARD_NAME)).willReturn(BOARD_ID);
        given(trelloService.lookUpListId(LIST_NAME, BOARD_ID)).willReturn(LIST_ID);
        //when
        template.sendBodyAndHeaders("direct:start", body, headers);
        //then
        mock.await();
        verify(trelloService).listGetCards(BOARD_ID, LIST_ID);
    }

    @Test
    @DisplayName("valid with board id and list name")
    public void validIdName() throws Exception {
        //given
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        headers.put(TrelloHeaders.BOARD_ID, BOARD_ID);
        headers.put(TrelloHeaders.LIST_NAME, LIST_NAME);
        given(trelloService.lookUpListId(LIST_NAME, BOARD_ID)).willReturn(LIST_ID);
        //when
        template.sendBodyAndHeaders("direct:start", body, headers);
        //then
        mock.await();
        verify(trelloService).listGetCards(BOARD_ID, LIST_ID);
    }

    @Test
    @DisplayName("valid with board name and list id")
    public void validNameId() throws Exception {
        //given
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        headers.put(TrelloHeaders.BOARD_NAME, BOARD_NAME);
        headers.put(TrelloHeaders.LIST_ID, LIST_ID);
        given(trelloService.lookUpBoardId(BOARD_NAME)).willReturn(BOARD_ID);
        //when
        template.sendBodyAndHeaders("direct:start", body, headers);
        //then
        mock.await();
        verify(trelloService).listGetCards(BOARD_ID, LIST_ID);
    }

    @Test
    @DisplayName("valid with board id and list id")
    public void validIdId() throws Exception {
        //given
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        headers.put(TrelloHeaders.BOARD_ID, BOARD_ID);
        headers.put(TrelloHeaders.LIST_ID, LIST_ID);
        //when
        template.sendBodyAndHeaders("direct:start", body, headers);
        //then
        mock.await();
        verify(trelloService).listGetCards(BOARD_ID, LIST_ID);
    }

    @Test
    @DisplayName("missing board name and id")
    public void missingBoardNameAndId() throws Exception {
        //given
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        headers.put(TrelloHeaders.LIST_ID, LIST_ID);
        //when
        template.sendBodyAndHeaders("direct:start", body, headers);
        //then
        mock.await();
        verify(trelloService, never()).listGetCards(BOARD_ID, LIST_ID);
        assertThat(e).isNotNull();
    }

    //    @Test @DisplayName("missing list name and id")
    //    @Test @DisplayName("board id not found for board name")
    //    @Test @DisplayName("list id not found for list name")

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        context().getRegistry()
                .bind("trelloService", TrelloService.class, trelloService);
        return new RouteBuilder() {
            public void configure() {
                onException(IllegalArgumentException.class)
                        .process(exchange -> e = exchange.getException());
                from("direct:start")
                        .to("trello:test" +
                                "?action=list_get_cards")
                        .to("mock:result");
            }
        };
    }

}
