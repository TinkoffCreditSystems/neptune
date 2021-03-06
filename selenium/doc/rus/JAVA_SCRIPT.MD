## Выполнение JavaScript

О принципах работы шагов, которые возвращают результат, можно
прочитать [здесь](./../../../core.api/doc/rus/IDEA.MD#Шаги,-которые-возвращают-результат).

```java
import static java.time.Duration.*;

import static ru.tinkoff.qa.neptune.selenium.SeleniumStepContext.inBrowser;
import static ru.tinkoff.qa.neptune.selenium.functions.java.script.GetJavaScriptResultSupplier.javaScript;
import static ru.tinkoff.qa.neptune.selenium.functions.java.script.GetJavaScriptResultSupplier.asynchronousJavaScript;

public class Tezzt {

    @Test
    public void test() {
        var scriptArgs = new Object[]{/**/}; //аргументы скрипта. Использовать только по необходимости 
        
        //выполнение синхронного js
        var result = inBrowser().evaluate(javaScript("js script", scriptArgs) //Скрипт и аргументы
                .criteria("Критерий ожидаемого результата", o -> {/*Предикат, описывающий критерий*/}) //при необходимости
                .timeOut(ofSeconds(5)) //время на получение ожидаемого результата. Скрипт будет выполняться пока не получится нужный результат 
                // или не кончится время. Использовать только по необходимости 
                .pollingInterval(ofMillis(500)) //Интервал времени между попытками получить ожидаемый результат в рамках времени ожидания
                .throwOnEmptyResult(() -> new WebDriverException("Ожидаемый результат не был получен"))); //Если нужный результат 
        // не был получен, описание выбрасываемого исключения

        //выполнение асинхронного js
        var result2 = inBrowser().evaluate(asynchronousJavaScript("js script", scriptArgs) //Скрипт и аргументы
                .criteria("Критерий ожидаемого результата", o -> {/*Предикат, описывающий критерий*/}) //при необходимости
                .timeOut(ofSeconds(5)) //время на получение ожидаемого результата. Скрипт будет выполняться пока не получится нужный результат 
                // или не кончится время. Использовать только по необходимости 
                .pollingInterval(ofMillis(500)) //Интервал времени между попытками получить ожидаемый результат в рамках времени ожидания
                .throwOnEmptyResult(() -> new WebDriverException("Ожидаемый результат не был получен"))); //Если нужный результат 
        // не был получен, описание выбрасываемого исключения
    }
}
```