package com.brageast.configplus.read

import java.io.InputStream
import java.io.OutputStream

interface ReadConfig {
    fun read(inputStream: InputStream): Map<String, Any>
    fun <E> read(inputStream: InputStream, beanClass: Class<E>): E
    fun write(outputStream: OutputStream, entity: Any)
}