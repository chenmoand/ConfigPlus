package com.brageast.configplus.read

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import java.io.OutputStream

object ReadYaml {

    val yamlbeans = object : ReadConfig {
        override fun read(inputStream: InputStream): Map<String, Any> {
            val reader = YamlReader(inputStream.reader())
            return reader.read(Map::class.java) as Map<String, Any>?
                    ?: emptyMap()

        }

        override fun <E> read(inputStream: InputStream, beanClass: Class<E>): E {
            val reader = YamlReader(inputStream.reader())
            return reader.read(beanClass) ?: throw IllegalAccessException("Can't load this inputStream")
        }

        override fun wirte(outputStream: OutputStream, entity: Any) {
            val yamlWriter = YamlWriter(outputStream.bufferedWriter())
            yamlWriter.write(entity)
            yamlWriter.close()
        }

        inline fun <reified E> readToBean(inputStream: InputStream): E {
            val reader = YamlReader(inputStream.reader())
            return reader.read(E::class.java) ?: throw IllegalAccessException("Can't load this inputStream")
        }
    }

    /**
     * 在Bukkit 不要用这个 两个不同的classloader 致解析失败
     * 会出现can not find class 异常
     */
    val snakeyaml = object : ReadConfig {
        val ymal = Yaml()
        override fun read(inputStream: InputStream): Map<String, Any> {
            return ymal.loadAs(inputStream, Map::class.java) as Map<String, Any>?
                    ?: emptyMap()
        }

        override fun <E> read(inputStream: InputStream, beanClass: Class<E>): E {
            return ymal.loadAs(inputStream, beanClass)
                    ?: throw IllegalAccessException("Can't load this inputStream")
        }

        override fun wirte(outputStream: OutputStream, entity: Any) {
            ymal.dump(entity, outputStream.bufferedWriter())
        }

    }

}