package com.brageast.configplus

import com.brageast.configplus.util.filterIsJar
import com.brageast.configplus.util.thisPlugin
import com.brageast.configplus.util.toListOnNotNull
import java.net.URL
import java.net.URLClassLoader

/**
 * 这个貌似添加不了 尴尬
 *
 */
@Deprecated(message = "这个类并不能添加jar文件")
class LibClassLoader(urls: Array<out URL>, pat: URLClassLoader) : URLClassLoader(urls, pat) {

    fun haveLibrary(className: String): Boolean {
//        print(Class.forName(className, false, this))
        return Class.forName(className, false, this) != null
    }

    companion object {
        fun addLibrarys(): LibClassLoader {
            val arrayOfURLs = DownloadLib.libfolder
                    .listFiles()
                    .toListOnNotNull()
                    .filterIsJar()
                    .map {
                        it.toURI().toURL()
                    }.toTypedArray()
            return LibClassLoader(arrayOfURLs, thisPlugin.javaClass.classLoader as URLClassLoader)
        }
    }
}