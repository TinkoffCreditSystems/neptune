package ru.tinkoff.qa.neptune.http.api.properties.proxy;

import ru.tinkoff.qa.neptune.core.api.properties.PropertyDescription;
import ru.tinkoff.qa.neptune.core.api.properties.PropertyName;
import ru.tinkoff.qa.neptune.core.api.properties.object.ObjectPropertySupplier;

import java.net.ProxySelector;
import java.util.function.Supplier;

@PropertyDescription(description = {"Defines full name of a class which extends DefaultHttpProxySelectorProperty.ProxySelectorSupplier",
        "and whose objects supply instances of java.net.ProxySelector"},
        section = "Http client. General")
@PropertyName("DEFAULT_HTTP_PROXY_SELECTOR")
public final class DefaultHttpProxySelectorProperty implements ObjectPropertySupplier<ProxySelector, DefaultHttpProxySelectorProperty.ProxySelectorSupplier> {
    /**
     * This instance reads value of the property {@code 'DEFAULT_HTTP_PROXY_SELECTOR'} and returns a
     * {@link ProxySelector}.
     */
    public static final DefaultHttpProxySelectorProperty DEFAULT_HTTP_PROXY_SELECTOR_PROPERTY =
            new DefaultHttpProxySelectorProperty();

    private DefaultHttpProxySelectorProperty() {
        super();
    }

    public static abstract class ProxySelectorSupplier implements Supplier<ProxySelector> {
    }
}
