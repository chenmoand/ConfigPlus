package com.brageast.configplus.util

import com.brageast.configplus.read.ReadConfig
import com.brageast.configplus.read.ReadJson
import com.brageast.configplus.read.ReadYaml
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.FileNotFoundException

class ConfigFile {
    private val plugin: Plugin = getUseMethodPlugin()
    private val dataFolder: File = plugin.dataFolder

    private val configMap: HashMap<String, File> = hashMapOf()

    init {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
        configFileManagement.putIfAbsent(plugin, this)
    }

    /**
     * 创建文件并且序列化
     */
    @JvmOverloads
    fun <E : Any> read(beanClass: Class<E>, filename: String = "config", fileType: FileType = FileType.YAML): E {
        val child = filename + fileType
        val file = File(dataFolder, child)
        if (!file.exists()) {
            file.createNewFile()
        }
        configMap.putIfAbsent(child, file)
        return fileType.readConfig.read(file.inputStream(), beanClass)
    }

    @JvmOverloads
    fun <E : Any> create(beanClass: Class<E>, filename: String = "config", fileType: FileType = FileType.YAML): E {
        val child = filename + fileType
        val useMethodPluginFile = getUseMethodPluginFile()
        val file = File(dataFolder, child)
        val fileInput = useMethodPluginFile.getFileInput(child)
        if (!file.exists()) {
            file.createNewFile()
            fileInput.copyTo(file.outputStream())
        }
        configMap.putIfAbsent(child, file)
        return fileType.readConfig.read(file.inputStream(), beanClass)
    }

    @JvmOverloads
    fun delete(filename: String, fileType: FileType = FileType.YAML): Boolean {
        val child = filename + fileType
        val file = configMap[child]
        if (file != null && file.exists()) {
            configMap.remove(child)
            return file.delete()
        }
        return true
    }

    @JvmOverloads
    fun <E> write(entity: E, filename: String = "config", fileType: FileType = FileType.YAML): E {
        val child = filename + fileType
        val file = configMap[child] ?: throw FileNotFoundException("$child 文件没有从ConfigFile创建, 请您先创建在写入")
        val readConfig = fileType.readConfig
        readConfig.wirte(file.outputStream(), entity as Any)
        return readConfig.read(file.inputStream(), entity.javaClass) as E
    }

    companion object {

        val configFileManagement = HashMap<Plugin, ConfigFile>()

        /**
         * 获得ConfigFile实例
         */
        @JvmOverloads
        @JvmStatic
        fun getConfigFile(plugin: Plugin = getUseMethodPlugin()): ConfigFile {
            return configFileManagement[plugin] ?: throw NullPointerException("You must instantiate ConfigFile in the onEnable method of javaPlugin.")
        }
    }

}

enum class FileType(private val suffix: String, val readConfig: ReadConfig) {
    YAML(".yml", ReadYaml.yamlbeans), JSON(".json", ReadJson);

    override fun toString(): String {
        return suffix
    }

}