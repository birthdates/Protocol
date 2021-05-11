package com.birthdates.protocol;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public abstract class ProtocolPlugin {

    private double weight;
    private String name;
    private YamlConfiguration configuration;
    private File configurationFile;
    private File dataFolder;

    public ProtocolPlugin() {
    }

    public ProtocolPlugin(String name) {
        this.name = name;
    }

    public PluginLoader getPluginLoader() {
        return Protocol.getInstance().getPluginLoader();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public FileConfiguration getConfig() {
        return configuration;
    }

    public JavaPlugin getPlugin() {
        return Protocol.getInstance();
    }

    public void reloadConfig() {
        Protocol.getInstance().reloadConfig();
    }

    public Server getServer() {
        return Protocol.getInstance().getServer();
    }

    public PluginDescriptionFile getDescription() {
        return Protocol.getInstance().getDescription();
    }

    public File getFile() {
        return Protocol.getInstance().getPluginFile();
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public void saveConfig() {
        Protocol.getInstance().saveConfig();
    }

    public ClassLoader getClassLoader() {
        return Protocol.getInstance().getPluginClassLoader();
    }

    public void registerListener(Listener listener) {
        Protocol.getInstance().registerEvent(listener);
    }

    public void saveResource(String resourcePath, boolean replace) {
        Protocol.getInstance().saveResource(getPath() + resourcePath, replace);
    }

    public InputStream getResource(String filename) {
        return Protocol.getInstance().getResource(getPath() + filename);
    }

    public PluginCommand getCommand(String command) {
        return Protocol.getInstance().getCommand(command);
    }

    public void onLoad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        loadConfig();
        this.dataFolder = new File(Protocol.getInstance().getDataFolder(), name);
    }

    private String getPath() {
        return name + "/";
    }

    public void saveDefaultConfig() {
        if (!configurationFile.exists()) {
            Protocol.getInstance().saveResource(getPath() + configurationFile.getName(), false);
        }
    }

    private void loadConfig() {
        configurationFile = new File(Protocol.getInstance().getDataFolder(), getPath() + "config.yml");
        saveDefaultConfig();
        configuration = YamlConfiguration.loadConfiguration(configurationFile);
    }

    public Logger getLogger() {
        return Protocol.getInstance().getLogger();
    }

    public abstract void onEnable();

    public void onDisable() {
    }
}
