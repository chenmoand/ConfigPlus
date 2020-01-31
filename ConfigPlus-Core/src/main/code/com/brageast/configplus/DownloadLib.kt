package com.brageast.configplus

import com.brageast.configplus.bean.Library
import com.brageast.configplus.read.ReadJson
import com.brageast.configplus.util.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

typealias ArrayLibrary = Array<Library>

object DownloadLib {

    val libfolder = File(dataFolder, "libraries").run {
        if (!exists()) mkdirs()
        this
    }

    fun run() {
        log.info("===========[检查Lib开始]==========")
        pluginJarFiles.mapNotNull {
            ScanFile.getPluginFile(it, "lib.json")
        }.forEach {input ->

            val groupBy = ReadJson.read(
                    input, ArrayLibrary::class.java
            ).filter {
                !isHaveLib(it.artifactId + ".jar")
            }.filter {
                it.artifactId != null && it.groupId != null && it.version != null
            }.groupBy {
                it.toGroupBy()
            }

            val map = groupBy.values.map { it[0] }
            map.forEach {
                println("${it.artifactId} : ${it.toLink()}")
                download(it, false)
            }
        }

        log.info("===========[检查Lib完成]==========")
    }

    private fun isHaveLib(name: String): Boolean {
        val names = libfolder.listFiles()
                .toListOnNotNull()
                .filterIsJar()
                .map { it.name }


        for (_n in names) {
            if (_n.equals(name, true)) {
                return true
            }
        }

        return false
    }

    /**
     * 下载库
     */
    @JvmStatic
    fun download(library: Library, async: Boolean = true) {
        val request: Request = Request.Builder()
                .url(library.toLink())
                .addHeader("Connection", "close")
                .build()
        val fileOutputStream = FileOutputStream(File(libfolder, "${library.artifactId}.jar"), true)
        okHttpClient
                .newCall(request)
                .apply {
                    if (async) enqueue(
                            object : Callback {
                                override fun onFailure(call: Call, e: IOException) {
                                    log.info("在尝试下载库的时候出现了网路异常")
                                    e.printStackTrace()
                                }

                                override fun onResponse(call: Call, response: Response) {
                                    response.body?.apply {
                                        byteStream().copyTo(fileOutputStream)
                                        fileOutputStream.flush()
                                    }
                                }

                            }
                    )
                    else execute().also {
                        it.body?.byteStream()?.copyTo(fileOutputStream)
                        fileOutputStream.flush()
                    }

                }
    }

}