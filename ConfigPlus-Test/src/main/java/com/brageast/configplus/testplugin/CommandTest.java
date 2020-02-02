package com.brageast.configplus.testplugin;

import com.brageast.configplus.util.ConfigFile;
import com.brageast.configplus.util.Global;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandTest implements CommandExecutor {
    private final ConfigFile configFile = ConfigFile.getConfigFile();
    private final ConfigEntity config = configFile.read(ConfigEntity.class);


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Global.getLog().info(config.toString());
        return false;
    }
}
