package io.github.aeckar.toml

import java.io.Reader

/**
 *
 */
public fun Reader.decodeToml(schema: TomlSchema, ignoreUnknownEntries: Boolean = true): Map<String,Entry> {

}

public fun Reader.decodeTomlToObject()

/**
 * Reads all TOML information before closing the reader.
 * @throws
 */
public fun Reader.decodeToml(): Map<String,Any> {

}

public inline fun <reified T> Reader.decodeTomlToObject(): T {

}