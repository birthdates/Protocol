package com.birthdates.protocol;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;

public class Protocol extends JavaPlugin {

    private static Protocol instance;
    private final List<ProtocolPlugin> plugins = new ArrayList<>();

    public static Protocol getInstance() {
        return instance;
    }

    public void onLoad() {
        instance = this;
        saveDefaultConfig();
        registerPlugins();
        pluginsAction("Loading", "Loaded", ProtocolPlugin::onLoad);
    }

    private void registerPlugins() {
        Reflections reflections = new Reflections();
        List<String> blacklistedPlugins = getConfig().getStringList("blacklisted-plugins");

        for (Class<?> aClass : reflections.getTypesAnnotatedWith(UsePlugin.class)) {
            if (!ProtocolPlugin.class.isAssignableFrom(aClass)) { //somehow if class is not a protocol plugin but uses the annotation
                continue;
            }
            UsePlugin usePlugin = aClass.getAnnotation(UsePlugin.class);
            String name = usePlugin.name();
            if (blacklistedPlugins.contains(name)) {
                log("Skipping " + name + "."); //blacklisted plugin
                continue;
            }
            try {
                ProtocolPlugin plugin = (ProtocolPlugin) aClass.newInstance(); //create new instance of this plugin (init later)
                plugins.add(plugin);
                plugin.setWeight(usePlugin.weight());
                plugin.setName(name);
            } catch (InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        plugins.sort(Comparator.comparingDouble(ProtocolPlugin::getWeight));
    }

    private void pluginsAction(String preAction, String action, Consumer<ProtocolPlugin> callback) {
        log(preAction + " a total of " + plugins.size() + " plugins...");
        plugins.forEach((plugin) -> {
            log(preAction + " " + plugin.getName() + "...");
            callback.accept(plugin);
            log(action + " " + plugin.getName() + ".");
        });
        log(action + " a total of " + plugins.size() + " plugins.");
    }

    public void onEnable() {
        pluginsAction("Enabling", "Enabled", ProtocolPlugin::onEnable);
    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void onDisable() {
        plugins.forEach(ProtocolPlugin::onDisable);
    }

    public File getPluginFile() {
        return this.getFile();
    }

    public ClassLoader getPluginClassLoader() {
        return this.getClassLoader();
    }

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }
}
