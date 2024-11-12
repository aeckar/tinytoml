package io.github.aeckar.toml

import java.time.*

/**
 * A TOML value.
 *
 * Can be cast to its Kotlin equivalent using the appropriate member function, according to the TOML it was read from.
 *
 * Integer values cannot be converted to floating-point using [float],
 * and neither can floating-point values cannot be converted to integers using [integer].
 *
 * All date-times can be represented in either [local][localDateTime] or [offset][offsetDateTime] form.
 */
public sealed class TomlValue {
    internal abstract val value: Any

    private fun unsupportedCast(): Nothing {
        throw IllegalArgumentException("Cast failed for value $value of type ${value::class}")
    }

    public open fun boolean(): Boolean = unsupportedCast()
    public open fun integer(): Long = unsupportedCast()
    public open fun float(): Double = unsupportedCast()
    public open fun string(): String = unsupportedCast()
    public open fun array(): List<TomlValue> = unsupportedCast()
    public open fun table(): Map<String, TomlValue> = unsupportedCast()
    public open fun localDate(): LocalDate = unsupportedCast()
    public open fun localDateTime(): LocalDateTime = unsupportedCast()
    public open fun localTime(): LocalTime = unsupportedCast()
    public open fun offsetDateTime(): OffsetDateTime = unsupportedCast()
}

internal class BooleanValue(override val value: Boolean) : TomlValue() {
    override fun boolean(): Boolean = value
}

internal class IntegerValue(override val value: Long) : TomlValue() {
    override fun integer(): Long = value
}

internal class FloatValue(override val value: Double) : TomlValue() {
    override fun float(): Double = value
}

internal class StringValue(override val value: String) : TomlValue() {
    override fun string(): String = value
}

internal class ArrayValue(override val value: List<TomlValue>) : TomlValue() {
    override fun array(): List<TomlValue> = value
}

internal class TableValue(override val value: Map<String, TomlValue>) : TomlValue() {
    override fun table(): Map<String, TomlValue> = value
}

internal class LocalDateValue(override val value: LocalDate) : TomlValue() {
    override fun localDate(): LocalDate = value
}

internal class LocalTimeValue(override val value: LocalTime) : TomlValue() {
    override fun localTime(): LocalTime = value
}

internal class LocalDateTimeValue(override val value: LocalDateTime) : TomlValue() {
    override fun localDateTime(): LocalDateTime = value
    override fun offsetDateTime(): OffsetDateTime = value.atOffset(ZonedDateTime.now().offset)
}

internal class OffsetDateTimeValue(override val value: OffsetDateTime) : TomlValue() {
    override fun localDateTime(): LocalDateTime = value.toLocalDateTime()
    override fun offsetDateTime(): OffsetDateTime = value
}