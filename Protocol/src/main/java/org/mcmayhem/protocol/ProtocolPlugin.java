package org.mcmayhem.protocol;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public class ProtocolPlugin {

    private double weight;
    private String name;

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
        return Protocol.getInstance().getConfig();
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
        return Protocol.getInstance().getDataFolder();
    }

    public void saveConfig() {
        Protocol.getInstance().saveConfig();
    }

    public ClassLoader getClassLoader() {
        return Protocol.getInstance().getPluginClassLoader();
    }

    public void saveDefaultConfig() {
        Protocol.getInstance().saveDefaultConfig();
    }

    public void registerListener(Listener listener) {
        Protocol.getInstance().registerEvent(listener);
    }

    public void saveResource(String resourcePath, boolean replace) {
        Protocol.getInstance().saveResource(resourcePath, replace);
    }

    public InputStream getResource(String filename) {
        return Protocol.getInstance().getResource(filename);
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
    }

    public Logger getLogger() {
        return Protocol.getInstance().getLogger();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }
}
