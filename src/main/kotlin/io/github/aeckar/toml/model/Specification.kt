package io.github.aeckar.toml.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/** Returns a TOML schema with the given definition. */
@OptIn(ExperimentalContracts::class)
public fun toml(definition: TableDefinition.() -> Unit): TomlSchema {
    contract { callsInPlace(definition, InvocationKind.EXACTLY_ONCE) }
    return TomlSchema(TableDefinition().apply(definition))
}

public open class TomlSchema internal constructor(private val root: TableDefinition) {
    
}

/**
 * Passed to [decodeToml][io.github.aeckar.toml.io.decodeToml] and
 * [decodeTomlToObject][io.github.aeckar.toml.io.decodeTomlToObject]
 * to specify the key-value pairs that are expected to be found.
 *
 * Optionally, defines the default values for certain keys when they are not found.
 */
public open class MutableTomlSchema internal constructor(private val root: TableDefinition) {

    public fun markFinal(): FinalizedTomlSchema {
        return FinalizedTomlS
    }

    /** Extends this schema to expect the entries in the given definition. */
    public operator fun plusAssign(definition: TableDefinition.() -> Unit) {

    }
}

