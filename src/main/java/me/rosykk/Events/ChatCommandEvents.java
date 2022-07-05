package me.rosykk.Events;

import me.rosykk.Main;
import me.rosykk.Utils.Util;
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

    // Just in case it was needed, useless for now.
    @EventHandler
    public void chatRestriction(PlayerCommandPreprocessEvent event) {
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

    }
}