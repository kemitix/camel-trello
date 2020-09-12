package net.kemitix.camel.trello;

import java.util.Map;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.component.AbstractApiEndpoint;
import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodPropertiesHelper;

import net.kemitix.camel.trello.api.TrelloFileHello;
import net.kemitix.camel.trello.api.TrelloJavadocHello;
import net.kemitix.camel.trello.internal.TrelloApiCollection;
import net.kemitix.camel.trello.internal.TrelloApiName;
import net.kemitix.camel.trello.internal.TrelloConstants;
import net.kemitix.camel.trello.internal.TrelloPropertiesHelper;

/**
 * Trello component which does bla bla.
 *
 * TODO: Update one line description above what the component does.
 */
@UriEndpoint(firstVersion = "0.1.0", scheme = "trello", title = "Trello", syntax="trello:name", 
             consumerClass = TrelloConsumer.class, label = "custom")
public class TrelloEndpoint extends AbstractApiEndpoint<TrelloApiName, TrelloConfiguration> {

    @UriPath @Metadata(required = true)
    private String name;

    // TODO create and manage API proxy
    private Object apiProxy;

    public TrelloEndpoint(String uri, TrelloComponent component,
                         TrelloApiName apiName, String methodName, TrelloConfiguration endpointConfiguration) {
        super(uri, component, apiName, methodName, TrelloApiCollection.getCollection().getHelper(apiName), endpointConfiguration);
    }

    public Producer createProducer() throws Exception {
        return new TrelloProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        // make sure inBody is not set for consumers
        if (inBody != null) {
            throw new IllegalArgumentException("Option inBody is not supported for consumer endpoint");
        }
        final TrelloConsumer consumer = new TrelloConsumer(this, processor);
        // also set consumer.* properties
        configureConsumer(consumer);
        return consumer;
    }

    @Override
    protected ApiMethodPropertiesHelper<TrelloConfiguration> getPropertiesHelper() {
        return TrelloPropertiesHelper.getHelper(getCamelContext());
    }

    protected String getThreadProfileName() {
        return TrelloConstants.THREAD_PROFILE_NAME;
    }

    @Override
    protected void afterConfigureProperties() {
        // TODO create API proxy, set connection properties, etc.
        switch (apiName) {
            case HELLO_FILE:
                apiProxy = new TrelloFileHello();
                break;
            case HELLO_JAVADOC:
                apiProxy = new TrelloJavadocHello();
                break;
            default:
                throw new IllegalArgumentException("Invalid API name " + apiName);
        }
    }

    @Override
    public Object getApiProxy(ApiMethod method, Map<String, Object> args) {
        return apiProxy;
    }

    /**
     * Some description of this option, and what it does
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
