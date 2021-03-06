# Переключение контента. Прочее.

О принципах работы шагов, которые возвращают результат, можно
прочитать [здесь](./../../../core.api/doc/rus/IDEA.MD#Шаги,-которые-возвращают-результат).

О принципах работы шагов, которые выполняют действие, можно
прочитать [здесь](./../../../core.api/doc/rus/IDEA.MD#Шаги,-которые-выполняют-действие).

Здесь описаны случаи, выходящие за рамки того, что описано в следующих главах:

- [Работа с фреймами. Поиск и переключение](./FRAMES.MD)

- [Работа с окнами. Поиск и переключение](./WINDOWS.MD)

- [Работа с алертами](./ALERTS.MD)

## Переключение в родительский фрейм

```java
import static ru.tinkoff.qa.neptune.selenium.SeleniumStepContext.inBrowser;
import static ru.tinkoff.qa.neptune.selenium.functions.target.locator.frame.parent.ParentFrameSupplier.parentFrame;

public class Tezzt {
    
    @Test
    public void test() {
        inBrowser().switchTo(parentFrame());
    }
}
```

## Переключение в активный элемент

```java
import static java.time.Duration.ofSeconds;

import static ru.tinkoff.qa.neptune.selenium.SeleniumStepContext.inBrowser;
import static ru.tinkoff.qa.neptune.selenium.functions.target.locator.active.element.GetActiveElementSupplier.activeElement;

public class Tezzt {
    
    @Test
    public void test() {
        inBrowser().switchTo(activeElement());
    }
}
```

## Переключение в дефолтный контент 

```java
import static ru.tinkoff.qa.neptune.selenium.SeleniumStepContext.inBrowser;
import static ru.tinkoff.qa.neptune.selenium.functions.target.locator.content.DefaultContentSupplier.defaultContent;

public class Tezzt {
    
    @Test
    public void test() {
        //переключение либо в первый фрейм на странице, либо в основной документ
        //Фокус остается в текущем окне/вкладке
        inBrowser().switchTo(defaultContent());
    }
}
```

