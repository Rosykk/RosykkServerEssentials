package me.rosykk.Utils;

import me.rosykk.Events.*;
import me.rosykk.Main;
import org.bukkit.ChatColor;

public class Util {
    private static final Main plugin;

    static {
        plugin = Main.getInstance();
    }

    public static void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(new PlayerClickEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EndermanDeathEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EnableDropEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LightBreakEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BedEnterLeaveEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ChatCommandEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ExplodeEvent(plugin), plugin);
    }

    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
