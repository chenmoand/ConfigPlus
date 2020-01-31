package com.brageast.configplus

import com.brageast.configplus.util.toListOnNotNull
import java.io.File
import java.io.InputStream
import java.util.jar.JarFile

object ScanFile {


    fun getPluginFiles(folder: File): Array<JarFile> {
        return folder.listFiles()
                .toListOnNotNull()
                .filter { it.isFile }
                .filter { it.name.endsWith(".jar", true) }
                .map { JarFile(it) }
                .toTypedArray()
    }

    fun getPluginFile(pluginFile: JarFile, fileName: String): InputStream? {
        val jarEntry = pluginFile.getJarEntry(fileName)
        if (jarEntry != null) {
            return pluginFile.getInputStream(jarEntry)
        }
        return null
    }

}