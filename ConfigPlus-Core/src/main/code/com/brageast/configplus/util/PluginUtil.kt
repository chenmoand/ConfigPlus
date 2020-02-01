package com.brageast.configplus.util

import org.bukkit.Bukkit

object PluginUtil {
    val thisPlugin = Bukkit.getPluginManager().getPlugin("ConfigPlus")!!
    val dataFolder = thisPlugin.dataFolder!!
}