package ru.tinkoff.qa.neptune.selenium.functions.searching;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.WebElement;
import ru.tinkoff.qa.neptune.selenium.api.widget.ScrollsIntoView;

import java.lang.reflect.Method;

import static java.util.Optional.ofNullable;

abstract class AbstractElementInterceptor implements MethodInterceptor {

    final String description;
    final WebElement element;
    private boolean isScrollerSetUp;

    Object realObject;
    ScrollsIntoView scrollsIntoView;

    AbstractElementInterceptor(String description, WebElement element) {
        this.description = description;
        this.element = element;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if ("toString".equals(method.getName()) &&
                method.getParameterTypes().length == 0
                && String.class.equals(method.getReturnType())) {
            return description;
        } else {
            realObject = ofNullable(realObject)
                    .orElseGet(this::createRealObject);

            if (!isScrollerSetUp) {
                setScroller();
                isScrollerSetUp = true;
            }

            Class<?>[] parameters;
            if ("equals".equals(method.getName()) && (parameters = method.getParameterTypes()).length == 1
                    && parameters[0].equals(Object.class)) {
                var result = realObject.equals(args[0]);
                //it may be another proxy
                if (!result) {
                    result = (boolean) proxy.invokeSuper(obj, args);
                }
                return result;
            }

            if (toPerformTheScrolling(method)) {
                ofNullable(scrollsIntoView)
                        .ifPresent(ScrollsIntoView::scrollIntoView);
            }

            return method.invoke(realObject, args);
        }
    }

    abstract void setScroller();

    abstract Object createRealObject();

    abstract boolean toPerformTheScrolling(Method method);
}