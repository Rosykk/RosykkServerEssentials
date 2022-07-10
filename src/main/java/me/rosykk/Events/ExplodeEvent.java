package me.rosykk.Events;

import me.rosykk.Main;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplodeEvent implements Listener {

    private final Main plugin;

    public ExplodeEvent(Main plugin) {
        this.plugin = plugin;
    }

    // AntiCreeper
    @EventHandler
    private void onCreeperExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Creeper) {
            if ( !event.blockList().isEmpty() ) {
                event.blockList().clear();
            }
        }
    }
}
