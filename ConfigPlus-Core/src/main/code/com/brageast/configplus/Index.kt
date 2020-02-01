package com.brageast.configplus

import com.brageast.configplus.bean.ConfigBean
import com.brageast.configplus.util.ConfigFile
import com.brageast.configplus.util.LibraryLoaderUtil
import com.brageast.configplus.util.getPluginBeans
import com.brageast.configplus.util.getUsePluginClassName
import org.bukkit.plugin.java.JavaPlugin

class Index : JavaPlugin() {

    override fun onEnable() {

        DownloadLib.run()

        //将jar加入到ClassLoader
        LibraryLoaderUtil.addLibraryOnClassLoader()
        // 获得Bean
        val config = ConfigFile().create(ConfigBean::class.java)


        logger.info("ConfigPlus成功启动")
    }
}
