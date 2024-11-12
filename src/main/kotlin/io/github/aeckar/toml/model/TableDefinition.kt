package io.github.aeckar.toml.model

/** Defines the key-value pairs of a TOML table. */
public class TableDefinition internal constructor() {
    internal var curTable: TableSpecifier = TableSpecifier("")

    /** Adds a boolean entry to the table. */
    public fun boolean(key: String): BooleanSpecifier = BooleanSpecifier(key).also { curTable += it }

    /** Adds an integer entry to the table. */
    public fun integer(key: String): IntegerSpecifier = IntegerSpecifier(key).also { curTable += it }

    /** Adds a floating-point entry to the table. */
    public fun float(key: String): FloatSpecifier = FloatSpecifier(key).also { curTable += it }

    /** Adds a string entry to the table. */
    public fun string(key: String): StringSpecifier = StringSpecifier(key).also { curTable += it }

    /** Adds an array entry to the table. */
    public fun array(key: String): ArraySpecifier = ArraySpecifier(key).also { curTable += it }

    /** Adds a table entry to the table. */
    public fun table(key: String, definition: TableDefinition.() -> Unit) {
        val newTable = TableSpecifier(key).also { curTable += it }
        val oldTable = curTable
        curTable = newTable
        apply(definition)
        curTable = oldTable
    }

    /** Adds a local date entry to the table. */
    public fun localDate(key: String): LocalDateSpecifier = LocalDateSpecifier(key).also { curTable += it }

    /** Adds a local time entry to the table. */
    public fun localTime(key: String): LocalTimeSpecifier = LocalTimeSpecifier(key).also { curTable += it }

    /** Adds a local date-time entry to the table. */
    public fun localDateTime(key: String): LocalDateTimeSpecifier = LocalDateTimeSpecifier(key).also { curTable += it }

    /** Adds an offset date-time entry to the table. */
    public fun offsetDateTime(key: String): OffsetDateTimeSpecifier = OffsetDateTimeSpecifier(key).also { curTable += it }
}