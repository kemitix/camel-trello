package net.kemitix.camel.trello;

import org.apache.camel.Processor;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum TrelloAction {

    LIST_GET_CARDS(
            "Get Cards in a List",
            "https://developer.atlassian.com/cloud/trello/rest/api-group-lists/#api-lists-id-cards-get",
            null,
            ListGetCardsProducer::new),

    ;

    private final String name;
    private final String docsUri;
    private final BiFunction<TrelloEndpoint, Processor, TrelloConsumer> consumerFactory;
    private final Function<TrelloEndpoint, TrelloProducer> producerFactory;

    TrelloAction(
            String name,
            String docsUri,
            BiFunction<TrelloEndpoint, Processor, TrelloConsumer> consumerFactory,
            Function<TrelloEndpoint, TrelloProducer> producerFactory
            ) {
        this.name = name;
        this.docsUri = docsUri;
        this.consumerFactory = consumerFactory;
        this.producerFactory = producerFactory;
    }

    public Optional<TrelloProducer> createProducer(
            TrelloEndpoint endpoint,
            TrelloService trelloService
    ) {
        return Optional.ofNullable(producerFactory)
                .map(f -> f.apply(endpoint))
                .stream()
                .peek(producer -> producer.setTrelloService(trelloService))
                .findFirst();
    }

    public Optional<TrelloConsumer> createConsumer(
            TrelloEndpoint endpoint,
            Processor processor,
            TrelloService trelloService
    ) {
        return Optional.ofNullable(consumerFactory)
                .map(f -> f.apply(endpoint, processor))
                .stream()
                .peek(consumer -> consumer.setTrelloService(trelloService))
                .findFirst();
    }

}
