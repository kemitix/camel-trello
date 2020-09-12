package net.kemitix.camel.trello.internal;

import org.apache.camel.CamelContext;
import org.apache.camel.support.component.ApiMethodPropertiesHelper;

import net.kemitix.camel.trello.TrelloConfiguration;

/**
 * Singleton {@link ApiMethodPropertiesHelper} for Trello component.
 */
public final class TrelloPropertiesHelper extends ApiMethodPropertiesHelper<TrelloConfiguration> {

    private static TrelloPropertiesHelper helper;

    private TrelloPropertiesHelper(CamelContext context) {
        super(context, TrelloConfiguration.class, TrelloConstants.PROPERTY_PREFIX);
    }

    public static synchronized TrelloPropertiesHelper getHelper(CamelContext context) {
        if (helper == null) {
            helper = new TrelloPropertiesHelper(context);
        }
        return helper;
    }
}
