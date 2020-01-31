package com.brageast.configplus

import com.brageast.configplus.bean.ConfigBean
import com.brageast.configplus.util.ConfigFile
import com.brageast.configplus.util.LibraryLoaderUtil
import org.bukkit.plugin.java.JavaPlugin

class Index : JavaPlugin() {

    override fun onEnable() {

        //将jar加入到ClassLoader
        LibraryLoaderUtil.addLibraryOnClassLoader()

        val create = ConfigFile().create(ConfigBean::class.java)

        /*if (create.isEnableDownloadLib.toBoolean()) {
            DownloadLib.run()
        } else {
            logger.info("已关闭自动下载Lib功能")
        }*/

        logger.info("ConfigPlus成功启动")
    }
}
