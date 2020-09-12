package net.kemitix.camel.trello;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.support.component.AbstractApiComponent;

import net.kemitix.camel.trello.internal.TrelloApiCollection;
import net.kemitix.camel.trello.internal.TrelloApiName;

@org.apache.camel.spi.annotations.Component("trello")
public class TrelloComponent extends AbstractApiComponent<TrelloApiName, TrelloConfiguration, TrelloApiCollection> {

    public TrelloComponent() {
        super(TrelloEndpoint.class, TrelloApiName.class, TrelloApiCollection.getCollection());
    }

    public TrelloComponent(CamelContext context) {
        super(context, TrelloEndpoint.class, TrelloApiName.class, TrelloApiCollection.getCollection());
    }

    @Override
    protected TrelloApiName getApiName(String apiNameStr) throws IllegalArgumentException {
        return TrelloApiName.fromValue(apiNameStr);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String methodName, TrelloApiName apiName,
                                      TrelloConfiguration endpointConfiguration) {
        TrelloEndpoint endpoint = new TrelloEndpoint(uri, this, apiName, methodName, endpointConfiguration);
        endpoint.setName(methodName);
        return endpoint;
    }

    /**
     * To use the shared configuration
     */
    @Override
    public void setConfiguration(TrelloConfiguration configuration) {
        super.setConfiguration(configuration);
    }

}
