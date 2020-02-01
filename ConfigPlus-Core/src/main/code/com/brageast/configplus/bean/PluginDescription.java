package com.brageast.configplus.bean;

import com.google.common.collect.ImmutableList;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginAwareness;
import org.bukkit.plugin.PluginLoadOrder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PluginDescription {
    private String name;
    private String version;
    private String main;
    private String author;
    private List<String> authors;
    private String description;
    private String website;
    private String prefix;
    private PluginLoadOrder order = PluginLoadOrder.POSTWORLD;
    private List<String> depend = ImmutableList.of();
    private List<String> softdepend = ImmutableList.of();
    private List<String> loadbefore = ImmutableList.of();
    private Map<String, Map<String, Object>> commands;
    private List<Permission> permissions;
    private PermissionDefault permissionDefault;
    private Set<PluginAwareness> awareness;

    private PlusBean plus;

    @Override
    public String toString() {
        return "PluginDescription{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", main='" + main + '\'' +
                ", author='" + author + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", prefix='" + prefix + '\'' +
                ", order=" + order +
                ", depend=" + depend +
                ", softdepend=" + softdepend +
                ", loadbefore=" + loadbefore +
                ", commands=" + commands +
                ", permissions=" + permissions +
                ", permissionDefault=" + permissionDefault +
                ", awareness=" + awareness +
                ", plus=" + plus +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public PermissionDefault getPermissionDefault() {
        return permissionDefault;
    }

    public void setPermissionDefault(PermissionDefault permissionDefault) {
        this.permissionDefault = permissionDefault;
    }

    public Set<PluginAwareness> getAwareness() {
        return awareness;
    }

    public void setAwareness(Set<PluginAwareness> awareness) {
        this.awareness = awareness;
    }

    public PlusBean getPlus() {
        return plus;
    }

    public void setPlus(PlusBean plus) {
        this.plus = plus;
    }
}
