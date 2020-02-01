package com.brageast.configplus.read

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.InputStream
import java.io.OutputStream

object ReadJson : ReadConfig {

    val gson: Gson by lazy { GsonBuilder().setPrettyPrinting().create(); }

    override fun read(inputStream: InputStream): Map<String, Any> {
        return gson.fromJson(inputStream.reader(), Map::class.java) as Map<String, Any>?
                ?: emptyMap()
    }

    override fun <E> read(inputStream: InputStream, beanClass: Class<E>): E {
        return gson.fromJson(inputStream.reader(), beanClass)
                ?: throw IllegalAccessException("Can't load this inputStream to beanClass")
    }

    override fun wirte(outputStream: OutputStream, entity: Any) {
        val json = gson.toJson(entity)
        outputStream.write(json.toByteArray())
        outputStream.close()
    }

}