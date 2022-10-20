/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.socket

interface Mapper<T> {
    fun mapper(data: List<*>): T
}
