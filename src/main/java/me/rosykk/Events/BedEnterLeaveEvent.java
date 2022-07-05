package me.rosykk.Events;

import me.rosykk.Main;
import me.rosykk.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Objects;

public class BedEnterLeaveEvent implements Listener {

    private final Main plugin;

    public BedEnterLeaveEvent(final Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void requestFromAutism(PlayerBedEnterEvent event) {

        PlayerBedEnterEvent.BedEnterResult result = event.getBedEnterResult();
        Player player = event.getPlayer();

        if ( result.equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            event.setUseBed(Event.Result.DENY);

            Objects.requireNonNull(Bukkit.getWorld("world")).setTime(1000L);
        }
        else {
            // TODO hardcoded - remake
            player.sendMessage(Util.colorize("&7Just read the message above your &citem bar."));
        }
    }

}
