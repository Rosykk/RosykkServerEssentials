package me.rosykk.Events;

import me.rosykk.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}