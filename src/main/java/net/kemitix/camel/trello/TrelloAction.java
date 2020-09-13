package net.kemitix.camel.trello;

public enum TrelloAction {

    LIST_GET_CARDS(
            "Get Cards in a List",
            "https://developer.atlassian.com/cloud/trello/rest/api-group-lists/#api-lists-id-cards-get"),

    ;

    private final String name;
    private final String docsUri;

    TrelloAction(String name, String docsUri) {
        this.name = name;
        this.docsUri = docsUri;
    }

}
