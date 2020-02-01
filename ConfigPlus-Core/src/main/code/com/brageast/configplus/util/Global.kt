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
    if (!this.isNullOrEmpty()) {
        return filterNotNull()
    }
    return emptyList()
}

fun List<File>.filterIsJar(): List<File> {
    return this.filter {
        it.isFile
    }.filter {
        it.name.endsWith(".jar", true)
    }
}


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
    val pluginClassName = getUsePluginClassName()
    return Bukkit.getPluginManager().plugins.find {
        it.javaClass.name == pluginClassName
    } ?: throw ClassNotFoundException("can not find extends org.bukkit.plugin.java.JavaPlugin")
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
    return pluginJarFiles.mapNotNull {
        it to it.getFileInput("plugin.yml")
    }.map {
        it.first to ReadYaml.yamlbeans.read(it.second, PluginDescription::class.java)
    }.find {
        it.second.main == getUsePluginClassName()
    } ?: throw FileNotFoundException("can not find plugin")
}

fun JarFile.getFileInput(fileName: String) = getInputStream(getJarEntry(fileName))
        ?: throw FileNotFoundException("$name in $fileName does not exist")