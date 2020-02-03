@file:JvmName("Global")

package com.brageast.configplus.util

import com.brageast.configplus.Index
import com.brageast.configplus.ScanFile
import com.brageast.configplus.bean.PluginBean
import com.brageast.configplus.bean.PluginDescription
import com.brageast.configplus.read.ReadYaml
import okhttp3.OkHttpClient
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.FileNotFoundException
import java.util.jar.JarFile

val log = Bukkit.getLogger()!!

val pluginJarFiles = ScanFile.getPluginFiles(PluginUtil.dataFolder.parentFile)

var okHttpClient = OkHttpClient()

fun <E : Any> Array<E>?.toListOnNotNull(): List<E> {
    return if (!this.isNullOrEmpty()) filterNotNull() else emptyList()
}

fun List<File>.filterIsJar(): List<File> {
    return filter(File::isFile).filter {
        it.name.endsWith(".jar", true)
    }
}

fun enableBStats(pluginId: Int) = Metrics(getUseMethodPlugin(), pluginId)

/**
 * 推荐在服务器加载完成后在使用
 */
fun getPluginBeans() = pluginJarFiles.run {
    this.map {
        val description = ReadYaml.yamlbeans.read(it.getFileInput("plugin.yml"), PluginDescription::class.java)
        val plugin = Bukkit.getPluginManager().getPlugin(description.name)!!
        return@map PluginBean(plugin, it, description)
    }
}

/**
 * 得到使用此方法得Plugin 得main className
 * 魔法方法, 爽爽就完事了
 */
@Throws(ClassNotFoundException::class)
fun getUsePluginClassName(): String {
    var pluginClassName: String? = null
    val stackTrace = Thread.currentThread().stackTrace!!
    for (stack in stackTrace) {
        try {
            val className = stack.className!!
            if (Class.forName(className, false, Index::class.java.classLoader).getDeclaredMethod("onEnable") != null) {
                pluginClassName = className
                break
            }
        } catch (e: Exception) {
            // no no no
        }
    }
    return pluginClassName ?: throw ClassNotFoundException("can not find extends org.bukkit.plugin.java.JavaPlugin")
}

/**
 * 得到使用此方法得Plugin实例
 */
fun getUseMethodPlugin(): Plugin {
    val pluginBeans = getPluginBeans()
    val usePluginClassName = getUsePluginClassName()
    return pluginBeans.find {
        it.pluginDescription.main == usePluginClassName
    }?.plugin ?: throw ClassNotFoundException("can not find extends org.bukkit.plugin.java.JavaPlugin")

}

/**
 * 得到使用此方法得JarFile 实例
 */
fun getUseMethodPluginFile(): JarFile = getUseMethodPluginPair().first

/**
 * 得到使用此方法得描述信息
 */
fun getUseMethodDescription(): PluginDescription = getUseMethodPluginPair().second

fun getUseMethodPluginPair(): Pair<JarFile, PluginDescription> {
    val usePluginClassName = getUsePluginClassName()
    return getAllPluginPair().find {
        it.second.main == usePluginClassName
    } ?: throw FileNotFoundException("can not find plugin")
}

fun getAllPluginPair(): List<Pair<JarFile, PluginDescription>> {
    return pluginJarFiles.mapNotNull {
        val fileInput = it.getFileInput("plugin.yml")
        it to ReadYaml.yamlbeans.read(fileInput, PluginDescription::class.java)
    }
}

fun getAllPluginDescription(): List<PluginDescription> = getAllPluginPair().map { it.second }

fun JarFile.getFileInput(fileName: String) = getInputStream(getJarEntry(fileName))
        ?: throw FileNotFoundException("$name in $fileName does not exist")