package ru.tinkoff.qa.neptune.data.base.api.properties;

import ru.tinkoff.qa.neptune.core.api.properties.enums.EnumPropertySuppler;
import ru.tinkoff.qa.neptune.core.api.properties.longs.LongValuePropertySupplier;
import ru.tinkoff.qa.neptune.core.api.properties.waiting.time.DurationSupplier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.time.Duration.ofSeconds;
import static ru.tinkoff.qa.neptune.data.base.api.properties.WaitingForSelectionResultTimeUnitProperty
        .DEFAULT_WAITING_FOR_SELECTION_RESULT_TIME_UNIT_PROPERTY;
import static ru.tinkoff.qa.neptune.data.base.api.properties.WaitingForSelectionResultTimeValueProperty
        .DEFAULT_WAITING_FOR_SELECTION_RESULT_TIME_VALUE_PROPERTY;

public final class DefaultWaitingForSelectionResultProperty extends DurationSupplier {

    /**
     * Reads properties {@code 'waiting.db.selection.result.time.unit'} and {@code 'waiting.db.selection.result.time.value'}
     * and builds an instance of {@link Duration}. When any of properties is not defined then in builds a duration of
     * 5 seconds.
     */
    public static final DefaultWaitingForSelectionResultProperty DEFAULT_WAITING_FOR_SELECTION_RESULT_PROPERTY =
            new DefaultWaitingForSelectionResultProperty(DEFAULT_WAITING_FOR_SELECTION_RESULT_TIME_UNIT_PROPERTY,
                    DEFAULT_WAITING_FOR_SELECTION_RESULT_TIME_VALUE_PROPERTY);

    private DefaultWaitingForSelectionResultProperty(EnumPropertySuppler<ChronoUnit> durationUnitPropertySupplier,
                                                     LongValuePropertySupplier durationValuePropertySupplier) {
        super(durationUnitPropertySupplier, durationValuePropertySupplier, ofSeconds(5));
    }
}
