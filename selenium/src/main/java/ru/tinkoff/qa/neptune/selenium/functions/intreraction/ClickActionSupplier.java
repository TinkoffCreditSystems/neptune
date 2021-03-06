package ru.tinkoff.qa.neptune.selenium.functions.intreraction;

import org.openqa.selenium.interactions.Actions;
import ru.tinkoff.qa.neptune.core.api.steps.parameters.StepParameter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Builds an action that performs the clicking by left mouse button.
 */
abstract class ClickActionSupplier extends InteractiveAction {

    ClickActionSupplier() {
        super("Click by left mouse button");
    }

    static final class ClickSimpleActionSupplier extends ClickActionSupplier {

        @Override
        void addAction(Actions value) {
            value.click();
        }
    }

    static final class ClickOnElementActionSupplier extends ClickActionSupplier {

        @StepParameter("Element to click on")
        private final Object e;

        ClickOnElementActionSupplier(Object e) {
            super();
            checkNotNull(e);
            this.e = e;
        }

        @Override
        void addAction(Actions value) {
            value.click(getElement(e));
        }
    }
}
