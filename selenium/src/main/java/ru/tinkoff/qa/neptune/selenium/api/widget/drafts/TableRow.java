package ru.tinkoff.qa.neptune.selenium.api.widget.drafts;

import ru.tinkoff.qa.neptune.selenium.api.widget.HasValue;
import ru.tinkoff.qa.neptune.selenium.api.widget.Name;
import ru.tinkoff.qa.neptune.selenium.api.widget.Widget;
import org.openqa.selenium.WebElement;
import java.util.List;

@Name("Row")
public abstract class TableRow extends Widget implements HasValue<List<String>> {
    public TableRow(WebElement wrappedElement) {
        super(wrappedElement);
    }
}
