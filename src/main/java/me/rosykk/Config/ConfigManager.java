package me.rosykk.Config;

import me.rosykk.Main;
import me.rosykk.Utils.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {
    private static final Main plugin;
    private static FileConfiguration fileConfiguration;
    private static File file;

    static {
        plugin = Main.getInstance();
    }

    public static void setup() {
        ConfigManager.file = new File(ConfigManager.plugin.getDataFolder(), "config.yml");
        if (!ConfigManager.file.exists()) {
            try {
                ConfigManager.plugin.saveResource("config.yml", false);
                ConfigManager.plugin.getLogger().info(Util.colorize("&aconfig.yml byl uspesne vytvoren!"));
            } catch (Exception e) {
                ConfigManager.plugin.getLogger().info(Util.colorize("&cchyba pri vytvareni config.yml!"));
            }
        }
        ConfigManager.fileConfiguration = (FileConfiguration) YamlConfiguration.loadConfiguration(ConfigManager.file);
    }

    public static void reloadConfig() {
        ConfigManager.fileConfiguration = (FileConfiguration) YamlConfiguration.loadConfiguration(ConfigManager.file);
    }

    public static FileConfiguration getConfig() {
        return ConfigManager.fileConfiguration;
    }
}
