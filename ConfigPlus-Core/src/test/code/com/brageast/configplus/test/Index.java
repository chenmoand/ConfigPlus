package com.brageast.configplus.test;

import com.brageast.configplus.bean.ConfigBean;
import com.brageast.configplus.bean.PluginDescription;
import com.brageast.configplus.read.ReadConfig;
import com.brageast.configplus.read.ReadYaml;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void Test3() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {

            System.out.println(stackTraceElement.getClassName());
        }
    }

    @Test
    public void Test4() {
        final PluginTest pluginTest = new PluginTest();
        pluginTest.setName("java");

        List<PluginTest> pluginTestList = new ArrayList<>();
        pluginTestList.add(pluginTest);

        pluginTest.setName("hello");

        pluginTestList.forEach(System.out::println);

    }

}

