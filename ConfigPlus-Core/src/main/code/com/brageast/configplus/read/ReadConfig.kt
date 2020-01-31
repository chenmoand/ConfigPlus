package com.brageast.configplus.read

import java.io.InputStream

interface ReadConfig {
    fun read(inputStream: InputStream): Map<String, Any>
    fun <E> read(inputStream: InputStream, beanClass: Class<E>): E
}