package com.brageast.configplus.test;

import com.brageast.configplus.bean.ConfigBean;
import com.brageast.configplus.bean.PluginDescription;
import com.brageast.configplus.read.ReadConfig;
import com.brageast.configplus.read.ReadYaml;
import lombok.val;
import org.junit.Test;

public class Index {

    @Test
    public void name() {
        final ReadConfig instance = ReadYaml.INSTANCE.getYamlbeans();
        final PluginDescription read = instance.read(ClassLoader.getSystemResourceAsStream("plugin.yml"), PluginDescription.class);
        System.out.println(read);
    }

    @Test
    public void Test2() {
        final val systemResourceAsStream = ClassLoader.getSystemResourceAsStream("config.yml");
        final ConfigBean read = ReadYaml.INSTANCE.getYamlbeans().read(systemResourceAsStream, ConfigBean.class);
        System.out.println(read);

    }

}

