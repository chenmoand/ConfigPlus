package com.brageast.configplus.bean

import org.bukkit.plugin.Plugin
import java.util.jar.JarFile

data class PluginBean (
        val plugin: Plugin,
        val jarFile: JarFile,
        val pluginDescription: PluginDescription
)