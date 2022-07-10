package me.rosykk.Events;

import me.rosykk.Main;
import me.rosykk.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class ChatCommandEvents implements Listener {

    private final Main plugin;

    public ChatCommandEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chatRestriction(PlayerCommandPreprocessEvent event) {

        // Just in case, useless for now
        List<String> commands = Arrays.asList(
                "/day", "/bc",
                "/night", "/ban-ip",
                "/ban", "/kick"
        );

        for (String command : commands) {
            if (event.getMessage().startsWith(command)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void chatStyle(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        event.setFormat(Util.colorize(player.getName() + " &7» &r" + message.replace("%", "%%")));

        if (message.toLowerCase().startsWith("negr")) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(Util.colorize("&a" + player.getName() + " says:"), "Negr", 20, 100, 20);
            }
        }
    }
}