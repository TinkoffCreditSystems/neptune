package ru.tinkoff.qa.neptune.selenium.test.capability.suppliers;

import org.openqa.selenium.MutableCapabilities;
import ru.tinkoff.qa.neptune.selenium.properties.CapabilitySupplier;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTestSupplierWithOptions3 implements CapabilitySupplier {
    @Override
    public MutableCapabilities get() {
        return new ChromeOptions().addArguments("--test-type",
                "--credentials_enable_service=false");
    }
}