package com.brageast.configplus.test;

public class PluginTest {
    private String name;
    private String main;
    private String version;

    @Override
    public String toString() {
        return "PluginTest{" +
                "name='" + name + '\'' +
                ", main='" + main + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}