package io.github.aeckar.toml

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime

/**
 * A placeholder for a TOML key-value pair.
 *
 * Keys assigned to pairs via the functions in [TableDefinition] must follow these conventions:
 * - Cannot be empty
 * - Cannot start with a digit
 * - Must consist of alphanumeric characters, digits, underscores, or hyphens
 */
public sealed class TomlSpecifier(key: String) {
    internal var parent: TableSpecifier? = null
    internal val key = with(key) {
        if (isEmpty()) {
            throw IllegalArgumentException("Key cannot be empty")
        }
        if (first() == '"' && last() == '"') substring(1, key.length - 1) else ensureValidKey()
    }

    private companion object {
        fun String.ensureValidKey(): String {
            if (first() in '0'..'9') {
                throw IllegalArgumentException("Key cannot start with a digit")
            }
            if (any { it !in 'a'..'z' && it !in 'A'..'Z' && it !in '0'..'9' && it != '_' && it != '-' }) {
                throw IllegalArgumentException("Key must contain only alphanumeric characters, digits, underscores, or hyphens")
            }
            return this
        }
    }
}

/**
 * Specifies a TOML entry whose value is not a table.
 *
 * Instances of this class may define default values given when the associated key is queried, but not found.
 *
 * In cases where the key is found, an object of type [T] is returned by the decoder.
 */
public sealed class TomlLeafSpecifier<T>(key: String) : TomlSpecifier(key) {
    internal var defaultValue: T? = null

    public infix fun default(value: T) {
        defaultValue?.let { throw IllegalStateException("Default value of '$key' already defined as $defaultValue") }
        defaultValue = value
    }
}

/** Specifies a TOML entry whose value is either `true` or `false`. */
public class BooleanSpecifier(key: String) : TomlLeafSpecifier<Boolean>(key)

/**
 * Specifies a TOML entry whose value is an integer in the range `−9223372036854775808L..9223372036854775807L`.
 *
 * Corresponds to the 'Integer' type in the TOML specification.
 */
public class IntegerSpecifier(key: String) : TomlLeafSpecifier<Long>(key)

/**
 * Specifies a TOML entry whose value is a floating-point number in the range
 * `−1.7976931348623157e308..1.7976931348623157e308`.
 *
 * Corresponds to the 'Float' type in the TOML specification.
 */
public class FloatSpecifier(key: String) : TomlLeafSpecifier<Double>(key)

/** Specifies a TOML entry whose value is a string */
public class StringSpecifier(key: String) : TomlLeafSpecifier<String>(key)

/** Specifies a TOML entry whose value is a list of other values. */
public class ArraySpecifier(key: String) : TomlLeafSpecifier<List<Any>>(key)

/** Specifies a TOML entry whose value  */
public class LocalDateSpecifier(key: String) : TomlLeafSpecifier<LocalDate>(key)

/** Specifies a TOML entry whose value  */
public class LocalTimeSpecifier(key: String) : TomlLeafSpecifier<LocalTime>(key)

/** Specifies a TOML entry whose value  */
public class LocalDateTimeSpecifier(key: String) : TomlLeafSpecifier<LocalDateTime>(key)

/** Specifies a TOML entry whose value  */
public class OffsetDateTimeSpecifier(key: String) : TomlLeafSpecifier<OffsetDateTime>(key)

/** Specifies a TOML entry whose value is table of other key-value pairs. */
public class TableSpecifier(key: String) : TomlSpecifier(key) {
    private val children = mutableListOf<TomlSpecifier>()

    /** Designates the given entry as a child, and this table as its parent. */
    internal operator fun plusAssign(entry: TomlSpecifier) {
        children += entry
        entry.parent = this
    }
}