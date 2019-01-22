package ru.tinkoff.qa.neptune.data.base.api.delete;

import ru.tinkoff.qa.neptune.data.base.api.DBSequentialGetStepSupplier;
import ru.tinkoff.qa.neptune.data.base.api.DataBaseStepContext;
import ru.tinkoff.qa.neptune.data.base.api.PersistableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static ru.tinkoff.qa.neptune.core.api.steps.StoryWriter.toGet;

/**
 * This class is designed to perform delete operations. A built function performs an operation and returns
 * deleted persistable objects.
 *
 * @param <T> is a type of objects to be deleted.
 */
@SuppressWarnings("unchecked")
public final class GetDeletedSequentialSupplier<T extends PersistableObject>
        extends DBSequentialGetStepSupplier<List<T>, DataBaseStepContext, GetDeletedSequentialSupplier<T>> {

    private final List<T> toBeDeleted;

    private GetDeletedSequentialSupplier(List<T> toBeDeleted) {
        checkArgument(nonNull(toBeDeleted), "Objects to be deleted should not be defined as a null-value");
        checkArgument(toBeDeleted.size() > 0, "Should be defined at least one object to delete");
        this.toBeDeleted = toBeDeleted;
    }

    /**
     * Creates a supplier of a function that performs a delete operation and returns deleted persistable
     * objects.
     *
     * @param toBeDeleted is an array of objects to be deleted
     * @param <T> is a type of objects to be deleted
     * @return a new instance of {@link GetDeletedSequentialSupplier}
     */
    @SafeVarargs
    public static <T extends PersistableObject> GetDeletedSequentialSupplier<T> deleted(T... toBeDeleted) {
        return deleted(asList(toBeDeleted));
    }

    /**
     * Creates a supplier of a function that performs a delete operation and returns deleted persistable
     * objects.
     *
     * @param toBeDeleted is a collection of objects to be deleted
     * @param <T> is a type of objects to be deleted
     * @return a new instance of {@link GetDeletedSequentialSupplier}
     */
    public static <T extends PersistableObject> GetDeletedSequentialSupplier<T> deleted(List<T> toBeDeleted) {
        return new GetDeletedSequentialSupplier<>(toBeDeleted);
    }

    @Override
    public Function<DataBaseStepContext, List<T>> get() {
        super.from(dataBaseSteps -> dataBaseSteps);
        return super.get();
    }

    @Override
    protected Function<DataBaseStepContext, List<T>> getEndFunction() {
        var description = format("Deleted objects: \n %s", toBeDeleted.toString());
        return toGet(description, dataBaseSteps -> {
            dataBaseSteps.getCurrentPersistenceManager().deletePersistentAll(toBeDeleted);
            var result = new ArrayList<T>();
            toBeDeleted.forEach(o -> result.add((T) o.clone()));
            return result;
        });
    }
}