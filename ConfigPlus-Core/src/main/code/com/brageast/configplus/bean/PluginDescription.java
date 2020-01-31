package com.brageast.configplus.bean;

import com.google.common.collect.ImmutableList;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginLoadOrder;

import java.util.List;
import java.util.Map;

public class PluginDescription {
    private String name = null;
    private String main = null;
    private String version = null;
    private List<String> depend = ImmutableList.of();
    private List<String> softdepend = ImmutableList.of();
    private List<String> loadbefore = ImmutableList.of();
    private Map<String, Map<String, Object>> commands = null;
    private String description = null;
    private List<String> authors = null;
    private String website = null;
    private String prefix = null;
    private PluginLoadOrder order = PluginLoadOrder.POSTWORLD;
    private List<Permission> permissions = null;

    @Override
    public String toString() {
        return "PluginDescription{" +
                "name='" + name + '\'' +
                ", main='" + main + '\'' +
                ", version='" + version + '\'' +
                ", depend=" + depend +
                ", softdepend=" + softdepend +
                ", loadbefore=" + loadbefore +
                ", commands=" + commands +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", website='" + website + '\'' +
                ", prefix='" + prefix + '\'' +
                ", order=" + order +
                ", permissions=" + permissions +
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

    public List<String> getDepend() {
        return depend;
    }

    public void setDepend(List<String> depend) {
        this.depend = depend;
    }

    public List<String> getSoftdepend() {
        return softdepend;
    }

    public void setSoftdepend(List<String> softdepend) {
        this.softdepend = softdepend;
    }

    public List<String> getLoadbefore() {
        return loadbefore;
    }

    public void setLoadbefore(List<String> loadbefore) {
        this.loadbefore = loadbefore;
    }

    public Map<String, Map<String, Object>> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, Map<String, Object>> commands) {
        this.commands = commands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public PluginLoadOrder getOrder() {
        return order;
    }

    public void setOrder(PluginLoadOrder order) {
        this.order = order;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
