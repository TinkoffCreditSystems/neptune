package ru.tinkoff.qa.neptune.data.base.api.query;

import ru.tinkoff.qa.neptune.data.base.api.DataBaseStepContext;
import ru.tinkoff.qa.neptune.data.base.api.PersistableObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static ru.tinkoff.qa.neptune.core.api.steps.conditions.ToGetSubIterable.getIterable;

public final class SelectListByIdsSupplier<T extends PersistableObject>
        extends ByIdsSequentialGetStepSupplier<T, List<T>, SelectListByIdsSupplier<T>> {

    private static final String DESCRIPTION = "List of stored database objects. Type %s. By ids: %s";

    private SelectListByIdsSupplier(Class<T> ofType, Object... ids) {
        super(ofType, ids);
    }

    /**
     * Creates a supplier of a function that performs selection from a data base by ids and returns the result as a list.
     *
     * @param ofType is a class of objects to be found.
     * @param ids of objects to be found.
     * @param <T> is a type of an item from the result list.
     * @return created supplier of a function.
     */
    public static <T extends PersistableObject> SelectListByIdsSupplier<T> listOfTypeByIds(Class<T> ofType, Object... ids) {
        return new SelectListByIdsSupplier<>(ofType, ids);
    }

    @Override
    protected Function<DataBaseStepContext, List<T>> getEndFunction() {
        Function<DataBaseStepContext, List<T>> listFunction = dataBaseSteps -> {
            var result = new LoggableElementList<T>() {
                public String toString() {
                    return format("%s stored elements of type %s", size(), ofType.getName());
                }
            }.setQuery(format("Known Ids: %s", Arrays.toString(ids)));
            var manager = dataBaseSteps.getCurrentPersistenceManager();

            for (Object id : ids) {
                try {
                    result.add(manager.getObjectById(ofType, id));
                }
                catch (RuntimeException ignored) {
                }
            }
            return result;
        };

        var description = format(DESCRIPTION, ofType.getName(), Arrays.toString(ids));

        return ofNullable(condition).map(tPredicate ->
                ofNullable(nothingIsSelectedExceptionSupplier).map(nothingIsSelectedExceptionSupplier1 ->
                        getIterable(description, listFunction, tPredicate,
                                timeToGetResult, sleepTime, false, true, nothingIsSelectedExceptionSupplier1))
                        .orElseGet(() -> getIterable(description, listFunction, tPredicate,
                                timeToGetResult, sleepTime, false, true)))

                .orElseGet(() -> ofNullable(nothingIsSelectedExceptionSupplier)
                        .map(nothingIsSelectedExceptionSupplier1 -> getIterable(description,
                                listFunction, timeToGetResult, sleepTime,  nothingIsSelectedExceptionSupplier1)
                        ).orElseGet(() ->
                                getIterable(description, listFunction, timeToGetResult, sleepTime)
                        ));
    }
}