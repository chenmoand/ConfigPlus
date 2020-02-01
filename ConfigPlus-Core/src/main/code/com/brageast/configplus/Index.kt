package com.brageast.configplus

import com.brageast.configplus.bean.ConfigBean
import com.brageast.configplus.util.ConfigFile
import com.brageast.configplus.util.LibraryLoaderUtil
import com.brageast.configplus.util.getPluginBeans
import com.brageast.configplus.util.getUsePluginClassName
import org.bukkit.plugin.java.JavaPlugin

class Index : JavaPlugin() {

    override fun onEnable() {
        // 获得Bean
        val config = ConfigFile().create(ConfigBean::class.java)

        if(config.isEnableDownloadLib.toBoolean()) {
            // 下载前置
            DownloadLib.run()
        } else {
            logger.info("以关闭自动下载前置功能")
        }

        //将jar加入到ClassLoader
        LibraryLoaderUtil.addLibraryOnClassLoader()


        logger.info(getPluginBeans().toString())

        logger.info("ConfigPlus成功启动")
    }
}
