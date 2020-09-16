package net.kemitix.camel.trello;

import java.util.List;

public interface TrelloService {

    List<TrelloCard> listGetCards(String boardName, String listName);

    String lookUpBoardId(String boardName);

    String lookUpListId(String listName, String boardId);

}
