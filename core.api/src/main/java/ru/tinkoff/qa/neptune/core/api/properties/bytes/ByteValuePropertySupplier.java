package ru.tinkoff.qa.neptune.core.api.properties.bytes;

import ru.tinkoff.qa.neptune.core.api.properties.PropertySupplier;

/**
 * This interface is designed to read properties and return byte values.
 */
public interface ByteValuePropertySupplier extends PropertySupplier<Byte> {
    @Override
    default Byte get() {
        return returnOptionalFromEnvironment()
                .map(Byte::parseByte)
                .orElse(null);
    }
}
