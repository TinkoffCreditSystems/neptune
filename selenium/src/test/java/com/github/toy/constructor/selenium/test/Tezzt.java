package com.github.toy.constructor.selenium.test;

import com.github.toy.constructor.selenium.SeleniumSteps;
import com.github.toy.constructor.selenium.api.widget.drafts.Button;
import com.github.toy.constructor.selenium.api.widget.drafts.Link;
import com.github.toy.constructor.selenium.api.widget.drafts.TextField;

import java.util.List;

import static com.github.toy.constructor.core.api.StoryWriter.action;
import static com.github.toy.constructor.core.api.proxy.ConstructorParameters.params;
import static com.github.toy.constructor.core.api.proxy.Substitution.getSubstituted;
import static com.github.toy.constructor.selenium.functions.click.ClickActionSupplier.on;
import static com.github.toy.constructor.selenium.functions.edit.EditActionSupplier.valueOf;
import static com.github.toy.constructor.selenium.functions.searching.MultipleSearchSupplier.links;
import static com.github.toy.constructor.selenium.functions.searching.MultipleSearchSupplier.textFields;
import static com.github.toy.constructor.selenium.functions.searching.SearchSupplier.*;
import static com.github.toy.constructor.selenium.functions.searching.SequentialMultipleSearchSupplier.elements;
import static com.github.toy.constructor.selenium.functions.searching.SequentialSearchSupplier.element;
import static com.github.toy.constructor.selenium.functions.value.SequentialGetValueSupplier.ofThe;
import static com.google.common.collect.ImmutableList.of;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.HOME;

public class Tezzt {

    public void tezzt() throws Exception {
        SeleniumSteps selenium = getSubstituted(SeleniumSteps.class, params());

        Button button = selenium.find(element(button("B1"))
                .foundFrom(link("L1"))
                .foundFrom(webElement(xpath(""))));

        List<Link> links = selenium.find(elements(links())
                .foundFrom(button)
                .foundFrom(webElement(xpath(""))));

        List<TextField> textFields =
                selenium.click(on(element(button("submit", ofSeconds(50))).foundFrom(webElement(xpath(""))))
                        .andOn(element(link()))
                        .andOn(button))
                .find(elements(textFields()));

        selenium.perform(action("High-level complex step", seleniumSteps -> {
            //everything below will be documented as sub steps
            seleniumSteps.click(on(element(tab("Some tab")))
                    .andOn(element(button("Some button", ofSeconds(50)))
                            .foundFrom(webElement(xpath("some path")))));

            String text = selenium.getValue(ofThe(element(textField("Some text field"))));

            selenium.edit(valueOf(element(textField()), of("123", HOME))
                    .andValueOf(element(flag()), true));
        }));
    }
}