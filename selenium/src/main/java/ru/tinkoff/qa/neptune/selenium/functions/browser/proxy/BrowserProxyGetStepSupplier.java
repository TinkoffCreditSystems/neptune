package ru.tinkoff.qa.neptune.selenium.functions.browser.proxy;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.harreader.model.HarEntry;
import ru.tinkoff.qa.neptune.core.api.steps.Criteria;
import ru.tinkoff.qa.neptune.core.api.steps.SequentialGetStepSupplier;
import ru.tinkoff.qa.neptune.selenium.SeleniumStepContext;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;
import static ru.tinkoff.qa.neptune.selenium.SeleniumStepContext.GetProxy.getBrowserProxy;

public class BrowserProxyGetStepSupplier extends SequentialGetStepSupplier.GetIterableChainedStepSupplier<SeleniumStepContext, List<HarEntry>, BrowserUpProxy, HarEntry, BrowserProxyGetStepSupplier> {

    private BrowserProxyGetStepSupplier() {
        super("Proxied requests", proxy -> ofNullable(proxy)
                .map(p -> {
                    if (!p.isStarted()) {
                        return new ArrayList<HarEntry>();
                    }

                    var har = p.getHar();
                    if (har == null) {
                        System.err.println("HAR recording is not started");
                        return new ArrayList<HarEntry>();
                    }

                    return har.getLog().getEntries();
                })
                .orElseGet(ArrayList::new));
        from(getBrowserProxy());
    }

    public static BrowserProxyGetStepSupplier proxiedRequests() {
        return new BrowserProxyGetStepSupplier();
    }

    @Override
    public BrowserProxyGetStepSupplier criteria(Criteria<? super HarEntry> criteria) {
        return super.criteria(criteria);
    }

    @Override
    public BrowserProxyGetStepSupplier criteria(String description, Predicate<? super HarEntry> predicate) {
        return super.criteria(description, predicate);
    }

    @Override
    public BrowserProxyGetStepSupplier timeOut(Duration timeOut) {
        return super.timeOut(timeOut);
    }

    @Override
    public BrowserProxyGetStepSupplier pollingInterval(Duration pollingTime) {
        return super.pollingInterval(pollingTime);
    }

    public BrowserProxyGetStepSupplier throwOnEmptyResult(String exceptionMessage) {
        return super.throwOnEmptyResult(() -> new IllegalStateException(exceptionMessage));
    }
}
