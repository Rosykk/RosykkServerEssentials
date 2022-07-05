package me.rosykk.Config;

import me.rosykk.Main;
import me.rosykk.Utils.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;

public class Config {
    private final Main plugin;

    public Config(final Main plugin) {
        this.plugin = plugin;
    }

    public void loadConfiguration() {
        this.createFolder();
        ConfigManager.setup();
    }

    public void createFolder() throws SecurityException {
        final File path = this.plugin.getDataFolder();
        boolean pathExists = path.exists();
        if (!pathExists) {
            pathExists = path.mkdir();
        }
        if (pathExists) {
            this.plugin.getLogger().info(Util.colorize("&anacitam config.yml!"));
        }
    }

    public FileConfiguration getConfig() {
        return ConfigManager.getConfig();
    }

    public String getString(final String s) {
        return this.getConfig().getString(s);
    }

    public ConfigurationSection getPath(final String s) {
        return this.getConfig().getConfigurationSection(s);
    }

    public Boolean getBoolean(final String s) {
        return this.getConfig().getBoolean(s);
    }

    public List<String> getStringList(final String s) {
        return (List<String>) this.getConfig().getStringList(s);
    }
}
