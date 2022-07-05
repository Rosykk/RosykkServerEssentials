package me.rosykk.Events;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.rosykk.Enums.State;
import me.rosykk.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EndermanDeathEvent implements Listener {
    private final Main plugin;

    public EndermanDeathEvent(final Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {

        Entity entity = event.getEntity();

        if (this.plugin.getState().getType().equals(State.Type.DROP)) {
            return;
        }

        FileConfiguration config = this.plugin.getConfig();

        if (entity instanceof Enderman) {

            ApplicableRegionSet set = this.plugin.getChecks().isInRegion(entity);

            for (ProtectedRegion region : set) {
                if (region.getId().equals(config.getString("REGION_NAME"))) {
                    event.getDrops().clear();
                }
            }
        }
    }
}
