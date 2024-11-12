package io.github.aeckar.toml

import java.io.Reader
import java.io.Writer

/**
 * Writes the TOML equivalent of [obj] to
 */
public fun Writer.encodeToml(obj: Any) {
    if (obj is Map<*,*>) {

    }
}

private class Encoder(private val dest: Writer) {
    fun encodeBoolean() {

    }

    fun encodeInt() {

    }

    fun encodeFloat() {

    }

    fun encodeArray() {

    }

    fun encodeTable() {

    }

    fun encodeDate() {

    }

    fun encodeDateTime() {

    }

    fun encodeOffsetDateTime() {

    }
}

private class Decoder(private val src: Reader) {
    fun decodeEntry() {

    }
}