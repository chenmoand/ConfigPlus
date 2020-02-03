package com.brageast.configplus

import com.brageast.configplus.bean.ConfigBean
import com.brageast.configplus.event.EnableEvent
import com.brageast.configplus.event.build
import com.brageast.configplus.util.ConfigFile
import com.brageast.configplus.util.LibraryLoaderUtil
import org.bukkit.plugin.java.JavaPlugin

class Index : JavaPlugin() {

    override fun onEnable() {
        // 添加事件
        EnableEvent().build()

        // 获得Bean
        val config = ConfigFile().create(beanClass = ConfigBean::class.java)

        logger.info("ConfigPlus成功启动")
    }

    override fun onLoad() {
        // 前置下载
        DownloadLib.run()
        //将jar加入到ClassLoader
        LibraryLoaderUtil.addLibraryOnClassLoader()
    }
}
