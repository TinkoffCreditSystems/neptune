package ru.tinkoff.qa.neptune.selenium.hamcrest.matchers.browser.proxy;

import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HttpMethod;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import ru.tinkoff.qa.neptune.selenium.hamcrest.matchers.TypeSafeDiagnosingMatcher;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;

public final class RequestHasMethod extends TypeSafeDiagnosingMatcher<HarEntry> {

    private final Matcher<? super HttpMethod> methodMatcher;

    private RequestHasMethod(Matcher<? super HttpMethod> methodMatcher) {
        checkNotNull(methodMatcher, "HTTP method matcher is not defined");
        this.methodMatcher = methodMatcher;
    }

    /**
     * Creates matcher that checks HTTP method of a request.
     *
     * @param methodMatcher criteria that describes expected method
     * @return a new instance of {@link RequestHasMethod}
     */
    public static RequestHasMethod requestHasMethod(Matcher<? super HttpMethod> methodMatcher) {
        return new RequestHasMethod(methodMatcher);
    }

    /**
     * Creates matcher that checks HTTP method of a request.
     *
     * @param method is the expected method of the request
     * @return a new instance of {@link RequestHasMethod}
     */
    public static RequestHasMethod requestHasMethod(HttpMethod method) {
        return new RequestHasMethod(is(method));
    }

    @Override
    protected boolean matchesSafely(HarEntry item, Description mismatchDescription) {
        if (item == null) {
            mismatchDescription.appendText("Proxied entry is null");
            return false;
        }

        var requestMethod = item.getRequest().getMethod();
        var result = methodMatcher.matches(requestMethod);

        if (!result) {
            methodMatcher.describeMismatch(requestMethod, mismatchDescription);
        }

        return result;
    }

    @Override
    public String toString() {
        return format("request has method %s", methodMatcher);
    }
}
