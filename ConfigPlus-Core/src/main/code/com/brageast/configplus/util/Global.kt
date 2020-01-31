package com.brageast.configplus.util

import com.brageast.configplus.Index
import com.brageast.configplus.ScanFile
import com.brageast.configplus.bean.PluginDescription
import com.brageast.configplus.read.ReadYaml
import okhttp3.OkHttpClient
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileNotFoundException
import java.util.jar.JarFile


fun <E : Any> Array<E>?.toListOnNotNull(): List<E> {
    if (!this.isNullOrEmpty()) {
        return filterNotNull()
    }
    return emptyList()
}

fun List<File>.filterIsJar(): List<File> = filter { it.isFile }.filter { it.name.endsWith(".jar", true) }

val thisPlugin = Bukkit.getPluginManager().getPlugin("ConfigPlus")

val log = Bukkit.getLogger()!!

val dataFolder = thisPlugin.dataFolder

val pluginJarFiles = ScanFile.getPluginFiles(dataFolder.parentFile)

var okHttpClient = OkHttpClient()


@Throws(ClassNotFoundException::class)
fun getPluginClassName(): String {
    val stackTrace = RuntimeException().stackTrace
    for (stack in stackTrace) {
        val className = stack.className
        try {
            if (Class.forName(className, false, Index::class.java.classLoader).getDeclaredMethod("onEnable") != null ) {
                return className
            }

        } catch (e: Exception) {
            // no no no
        }

    }
    throw ClassNotFoundException("can not find extends org.bukkit.plugin.java.JavaPlugin")
//    return ""
}

fun getUseMethodPlugin(): Plugin {
    val pluginClassName = getPluginClassName()
    return Bukkit.getPluginManager().plugins.find {
        it.javaClass.name == pluginClassName
    } ?: throw ClassNotFoundException("can not find extends org.bukkit.plugin.java.JavaPlugin")
}

fun getUseMethodPluginFile(): JarFile {
    val _p = pluginJarFiles.mapNotNull {
        it to it.getInputStream(it.getJarEntry("plugin.yml"))
    }.map {
        it.first to ReadYaml.yamlbeans.read(it.second, PluginDescription::class.java)
    }.find {
        it.second.main == getPluginClassName()
    }

    if (_p != null) {
        return _p.first
    }

    throw FileNotFoundException("can not find plugin file")

}

fun JarFile.getFileInput(fileName: String) = getInputStream(getJarEntry(fileName)) ?: throw FileNotFoundException("$name in $fileName does not exist")