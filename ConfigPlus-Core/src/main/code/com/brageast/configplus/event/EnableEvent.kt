package com.brageast.configplus.event

import com.brageast.configplus.util.Metrics
import com.brageast.configplus.util.getAllPluginDescription
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginEnableEvent

class EnableEvent : Listener {

    private val all = getAllPluginDescription()

    @EventHandler
    fun onEnablePlugin(event: PluginEnableEvent) {
        val plugin = event.plugin!!
        val main = plugin.description.main
        all.find { it.main == main }?.apply {
            val bStatsId = this.plus?.bStatsId
            if (bStatsId != null) Metrics(plugin, bStatsId)
        }
    }

}