package com.brageast.configplus.testplugin;

import com.brageast.configplus.DownloadLib;
import com.brageast.configplus.bean.Library;
import com.brageast.configplus.util.ConfigFile;
import com.brageast.configplus.util.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ConfigFile configFile;

    @Override
    public void onEnable() {
        configFile = new ConfigFile();

        final ConfigEntity configEntity = configFile.create(ConfigEntity.class);

        configEntity.setEmail("你在想屁吃");

        final ConfigEntity write = configFile.write(configEntity);

//        DownloadLib.download(new Library(), true);

        System.out.println(write);

    }
}
