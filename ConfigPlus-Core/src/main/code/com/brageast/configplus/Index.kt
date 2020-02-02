package com.brageast.configplus

import com.brageast.configplus.bean.ConfigBean
import com.brageast.configplus.util.ConfigFile
import com.brageast.configplus.util.LibraryLoaderUtil
import com.brageast.configplus.util.Metrics
import com.brageast.configplus.util.enableBStats
import org.bukkit.plugin.java.JavaPlugin

class Index : JavaPlugin() {

    override fun onEnable() {

        // 前置下载
        DownloadLib.run()

        //将jar加入到ClassLoader
        LibraryLoaderUtil.addLibraryOnClassLoader()

        //启动BStats统计
        enableBStats(6406)

        // 获得Bean
        val config = ConfigFile().create(beanClass = ConfigBean::class.java)

        logger.info("ConfigPlus成功启动")
    }
}
