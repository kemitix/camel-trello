package net.kemitix.camel.trello;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListGetCardsTest extends CamelTestSupport {

    public static final String BOARD_ID = "Board Id";
    public static final String LIST_ID = "List Id";
    public static final String BOARD_NAME = "Test Board";
    public static final String LIST_NAME = "Test List";

    @Mock
    private TrelloService trelloService;

    Object body = "";
    Map<String, Object> headers = new HashMap<>();

//    @Test
//    @DisplayName("valid with board name and list name")
//    public void validNameName() throws Exception {
//        //given
//        MockEndpoint mock = getMockEndpoint("mock:result");
//        mock.expectedMinimumMessageCount(1);
//        headers.put(TrelloHeaders.BOARD_NAME, BOARD_NAME);
//        headers.put(TrelloHeaders.LIST, LIST_NAME);
//        //when
//        template.sendBodyAndHeaders("direct:start", body, headers);
//        //then
//        mock.await();
//        verify(trelloService).listGetCards(BOARD_ID, LIST_ID);
//    }

    @Test @DisplayName("valid with board id and list name")
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

    @Test @DisplayName("valid with board name and list id")
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

    @Test @DisplayName("valid with board id and list id")
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


//    @Test @DisplayName("missing board name or id")
//    @Test @DisplayName("missing list name or id")
//    @Test @DisplayName("missing board name or id and list name or id")

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        context().getRegistry()
                .bind("trelloService", TrelloService.class, trelloService);
        return new RouteBuilder() {
            public void configure() {
                from("direct:start")
                        .to("trello:test" +
                                "?action=list_get_cards")
                        .to("mock:result");
            }
        };
    }

}
