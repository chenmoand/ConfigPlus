package com.brageast.configplus.util

import com.brageast.configplus.read.ReadConfig
import com.brageast.configplus.read.ReadJson
import com.brageast.configplus.read.ReadYaml
import org.bukkit.plugin.Plugin
import java.io.File

class ConfigFile {
    private val plugin: Plugin = getUseMethodPlugin()
    private val dataFolder: File = plugin.dataFolder

    init {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
    }

    /**
     * 创建文件并且序列化
     */
    @JvmOverloads
    fun <E : Any> read(beanClass: Class<E>, filename: String = "config", fileType: FileType = FileType.YAML): E {
        val _filename = filename + fileType
        val file = File(dataFolder, _filename)
        if (!file.exists()) {
            file.createNewFile()
        }
        return fileType.readConfig.read(file.inputStream(), beanClass)
    }

    @JvmOverloads
    fun <E : Any> create(beanClass: Class<E>, filename: String = "config", fileType: FileType = FileType.YAML): E {
        val _filename = filename + fileType
        val useMethodPluginFile = getUseMethodPluginFile()
        val file = File(dataFolder, _filename)
        val fileInput = useMethodPluginFile.getFileInput(_filename)
        if (!file.exists()) {
            file.createNewFile()
            fileInput.copyTo(file.outputStream())
        }
        return fileType.readConfig.read(fileInput, beanClass)
    }

}

enum class FileType(val suffix: String, val readConfig: ReadConfig) {
    YAML(".yml", ReadYaml.yamlbeans), JSON(".json", ReadJson);

    override fun toString(): String {
        return suffix
    }

}