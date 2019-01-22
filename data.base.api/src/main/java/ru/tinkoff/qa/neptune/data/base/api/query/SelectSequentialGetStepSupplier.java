package ru.tinkoff.qa.neptune.data.base.api.query;

import ru.tinkoff.qa.neptune.data.base.api.DBSequentialGetStepSupplier;

import java.time.Duration;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;
import static ru.tinkoff.qa.neptune.data.base.api.properties.WaitingForQueryResultDuration.SLEEPING_TIME;
import static ru.tinkoff.qa.neptune.data.base.api.properties.WaitingForQueryResultDuration.WAITING_FOR_SELECTION_RESULT_TIME;

@SuppressWarnings("unchecked")
public abstract class SelectSequentialGetStepSupplier<T, Q, R extends SelectSequentialGetStepSupplier<T, Q, R>>
        extends DBSequentialGetStepSupplier<T, Q, R> {

    Supplier<NothingIsSelectedException> nothingIsSelectedExceptionSupplier;
    Duration timeToGetResult = WAITING_FOR_SELECTION_RESULT_TIME.get();
    Duration sleepTime = SLEEPING_TIME.get();

    /**
     * This method sets specific time to get valuable result.
     *
     * @param timeToGetValue time to get valuable result.
     * @return self-reference
     */
    public R withTimeToGetValue(Duration timeToGetValue) {
        checkArgument(nonNull(timeToGetValue), "Time to get value should be defined");
        this.timeToGetResult = timeToGetValue;
        return (R) this;
    }

    /**
     * This method sets specific time of the sleeping between attempts to get valuable result.
     *
     * @param sleepingTime time of the sleeping between attempts to get valuable result.
     * @return self-reference
     */
    public R sleepigTime(Duration sleepingTime) {
        checkArgument(nonNull(sleepingTime), "The sleeping time should be defined");
        this.sleepTime = sleepingTime;
        return (R) this;
    }

    /**
     * This methods says that an exception should be thrown when query returns an empty result.
     *
     * @param nothingIsSelectedExceptionSupplier is a suppler of exception to be thrown.
     * @return self-reference.
     */
    public R toThrowOnEmptyResult(Supplier<NothingIsSelectedException> nothingIsSelectedExceptionSupplier) {
        checkArgument(nonNull(nothingIsSelectedExceptionSupplier), "Supplier of an exception should be defined");
        this.nothingIsSelectedExceptionSupplier = nothingIsSelectedExceptionSupplier;
        return (R) this;
    }
}