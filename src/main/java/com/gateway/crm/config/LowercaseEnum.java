package com.gateway.crm.config;

import com.fasterxml.jackson.annotation.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Base interface for all enums since we want to be in lowercase but also handle both upper and lowercase values
 * <p>
 * Child class needs to add JsonCreator method using deserialize() method. This will ensure we can deserialize any
 * case. Example (for enum class ExampleEnum:
 * </p>
 * \@JsonCreator
 * public static ExampleEnum deserialize(String name) {
 *     return LowercaseEnum.deserialize(ExampleEnum.class, name);
 * }
 */
public interface LowercaseEnum {
    Logger log = LoggerFactory.getLogger(LowercaseEnum.class);

    String name();

    /**
     * Convert to lowercase on serialization as we want to use lowercase for all enum values
     */
    @JsonValue
    default String serialize() {
        return name().toLowerCase();
    }

    /**
     * Deserializer
     * <p>
     * Use in implementation class:
     * </p>
     * \@JsonCreator
     * public static ExampleEnum deserialize(String name) {
     *     return LowercaseEnum.deserialize(ExampleEnum.class, name);
     * }
     * Static methods are not enforced it is left to developer to create deserialization method and call this one
     * @throws IllegalArgumentException if Enum matching name is not found
     */
    static <T extends Enum<T>>T deserialize(Class<T> clazz, String name) {
        return Enum.valueOf(clazz, name.toUpperCase());
    }

    /**
     * Returns Enum of input value. This method does not throw IllegalArgumentException instead returns null if Enum
     * matching value is not found.
     * @param enumType Enum class in which search for value to be performed
     * @param value enum name
     * @return Enum matching value or null if not found.
     */
    static <T extends Enum<T>> T forValue(Class<T> enumType, String value) {
        T result = Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
        if (result == null)
            log.warn("No Enum matching {} is found in EnumClass:{}", value, enumType);
        return result;
    }
}
