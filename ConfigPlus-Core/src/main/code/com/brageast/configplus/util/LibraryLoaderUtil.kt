package com.brageast.configplus.util

import com.brageast.configplus.DownloadLib
import com.brageast.configplus.util.PluginUtil.thisPlugin
import java.net.URL
import java.net.URLClassLoader

object LibraryLoaderUtil {

    private val method by lazy {
        // 这是我见过最玻璃心的类了, WDNMD
        val clazz = Class.forName("org.bukkit.plugin.java.PluginClassLoader").superclass
        val declaredMethod = clazz.getDeclaredMethod("addURL", URL::class.java);
        declaredMethod.isAccessible = true
        return@lazy declaredMethod!!
    }

    internal fun addLibraryOnClassLoader(classLoader: URLClassLoader = thisPlugin.javaClass.classLoader as URLClassLoader) {
        val arrayOfURLs = DownloadLib.libfolder
                .listFiles()
                .toListOnNotNull()
                .filterIsJar()
                .map {
                    it.toURI().toURL()
                }.toTypedArray()
        arrayOfURLs.forEach {
            addLibraryOnURL(it, classLoader)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun addLibraryOnURL(url: URL, classLoader: URLClassLoader = thisPlugin.javaClass.classLoader as URLClassLoader) {
        method.invoke(classLoader, url)
    }
}