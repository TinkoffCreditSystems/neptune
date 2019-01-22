package ru.tinkoff.qa.neptune.selenium.properties;

import org.openqa.selenium.MutableCapabilities;
import ru.tinkoff.qa.neptune.core.api.properties.PropertySupplier;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;
import ru.tinkoff.qa.neptune.core.api.properties.object.MultipleObjectPropertySupplier;

import java.util.List;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isBlank;

public enum CapabilityTypes implements PropertySupplier<MutableCapabilities> {
    /**
     * Capabilities for the starting of {@link org.openqa.selenium.remote.RemoteWebDriver}
     */
    REMOTE("remote") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.browserName} to define browser. This is the necessary property
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link Capabilities}
         */
        @Override
        public MutableCapabilities get() {
            var browser = CommonCapabilityProperties.BROWSER_NAME.get();
            if (CommonCapabilityProperties.BROWSER_NAME.get() == null ||
                    isBlank(String.valueOf(CommonCapabilityProperties.BROWSER_NAME.get()))) {
                throw new IllegalArgumentException(format("The property %s should be defined",
                        CommonCapabilityProperties.BROWSER_NAME.getPropertyName()));
            }
            var result =  super.get();
            result.setCapability(CapabilityType.BROWSER_NAME, browser);
            return result;
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.chrome.ChromeDriver}
     */
    CHROME("chrome") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link ChromeOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new ChromeOptions().merge(super.get());
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.edge.EdgeDriver}
     */
    EDGE("edge") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link EdgeOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new EdgeOptions().merge(super.get());
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.firefox.FirefoxDriver}
     */
    FIREFOX("firefox") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link FirefoxOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new FirefoxOptions().merge(super.get());
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.ie.InternetExplorerDriver}
     */
    IE("ie") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link InternetExplorerOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new InternetExplorerOptions().merge(super.get());
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.opera.OperaDriver}
     */
    OPERA("opera") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link OperaOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new OperaOptions().merge(super.get());
        }
    },

    /**
     * Capabilities for the starting of {@link org.openqa.selenium.safari.SafariDriver}
     */
    SAFARI("safari") {
        /**
         * Creates {@link Capabilities} with following properties:
         *     <p>{@code web.driver.capability.platformName} to define name of a supported platform.
         *     Windows, Linux etc. This is not the necessary property. @see org.openqa.selenium.Platform
         *     <p>{@code web.driver.capability.javascriptEnabled} to enable or to disable js. Possible values are
         *     {@code true} or {@code false}. By default js is enabled. This is not the necessary property.
         *     <p>{@code web.driver.capability.browserVersion} to define a version of browser. This is not the necessary
         *     property.
         *     <p>{@code remote.capability.suppliers} to define additional capabilities. It is a string with name of a
         *     supplier.
         *     @see CapabilitySupplier
         *
         * @return built {@link SafariOptions}
         */
        @Override
        public MutableCapabilities get() {
            return new SafariOptions().merge(super.get());
        }
    };

    private static final String CAPABILITY_SUPPLIERS = "capability.suppliers";
    private final String name;
    private final CapabilityReader capabilityReader;

    CapabilityTypes(String name) {
        this.name = format("%s.%s", name, CAPABILITY_SUPPLIERS);
        capabilityReader = new CapabilityReader();
    }

    @Override
    public MutableCapabilities get() {
        var desiredCapabilities = new MutableCapabilities();
        ofNullable(CommonCapabilityProperties.PLATFORM_NAME.get()).ifPresent(o ->
                desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, o));

        desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,
                CommonCapabilityProperties.SUPPORTS_JAVASCRIPT.get());

        ofNullable(CommonCapabilityProperties.BROWSER_VERSION.get()).ifPresent(o ->
                desiredCapabilities.setCapability(CapabilityType.BROWSER_VERSION, o));

        ofNullable(capabilityReader.get())
                .orElse(List.of())
                .forEach(capabilitySupplier -> desiredCapabilities.merge(capabilitySupplier.get()));

        return desiredCapabilities;
    }

    @Override
    public String getPropertyName() {
        return name;
    }

    private class CapabilityReader implements MultipleObjectPropertySupplier<CapabilitySupplier> {
        @Override
        public String getPropertyName() {
            return name;
        }
    }

    public enum CommonCapabilityProperties implements PropertySupplier<Object> {
        /**
         * Reads property {@code web.driver.capability.browserName} and returns string value.
         * Should be the same as following:
         *     <p>{@link BrowserType#CHROME}
         *     <p>{@link BrowserType#EDGE}
         *     <p>{@link BrowserType#FIREFOX}
         *     <p>{@link BrowserType#IEXPLORE}
         *     <p>{@link BrowserType#OPERA_BLINK}
         *     <p>{@link BrowserType#SAFARI}
         *
         * It has sense to define value of the property when value of the property {@link SupportedWebDriverProperty#SUPPORTED_WEB_DRIVER_PROPERTY_PROPERTY}
         * is {@link SupportedWebDrivers#REMOTE_DRIVER}
         */
        BROWSER_NAME(format("web.driver.capability.%s", CapabilityType.BROWSER_NAME)),

        /**
         * Reads property {@code web.driver.capability.platformName} and returns string value.
         * Should be the same as an item of {@link org.openqa.selenium.Platform}
         */
        PLATFORM_NAME(format("web.driver.capability.%s", CapabilityType.PLATFORM_NAME)),

        /**
         * Reads property {@code web.driver.capability.javascriptEnabled} and returns boolean value.
         * Should be {@code true} or {@code false}. By default it returns {@code true}.
         */
        SUPPORTS_JAVASCRIPT(format("web.driver.capability.%s", CapabilityType.SUPPORTS_JAVASCRIPT)) {
            @Override
                public Boolean get() {
                return returnOptionalFromEnvironment().map(Boolean::parseBoolean).orElse(true);
            }
        },

        /**
         * Reads property {@code web.driver.capability.browserVersion} and returns string value.
         */
        BROWSER_VERSION(format("web.driver.capability.%s", CapabilityType.BROWSER_VERSION));

        private final String name;

        CommonCapabilityProperties(String name) {
            this.name = name;
        }

        @Override
        public String getPropertyName() {
            return name;
        }

        @Override
        public Object get() {
            return returnOptionalFromEnvironment().orElse(null);
        }
    }
}