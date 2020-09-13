package net.kemitix.camel.trello;

import org.apache.camel.test.junit5.CamelTestSupport;

import java.util.Map;

public class AbstractTrelloTestSupport extends CamelTestSupport {

    protected String requestBody(String route, Object body) {
        return "";
    }

    protected String requestBodyAndHeaders(String route, Object body, Map<String, Object> headers) {
        return "";
    }

}
