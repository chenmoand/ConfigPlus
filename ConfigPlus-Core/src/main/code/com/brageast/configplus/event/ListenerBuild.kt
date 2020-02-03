package com.brageast.configplus.event

import com.brageast.configplus.util.getUseMethodPlugin
import org.bukkit.Bukkit
import org.bukkit.event.Listener

fun Listener.build() {
    Bukkit.getServer().pluginManager.registerEvents(this, getUseMethodPlugin())
}

