package me.rosykk.Events;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.rosykk.Enums.State;
import me.rosykk.Main;
import me.rosykk.Utils.Holograms;
import me.rosykk.Utils.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnableDropEvent implements Listener {

    private final Main plugin;

    public EnableDropEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.LEVER && action == Action.RIGHT_CLICK_BLOCK) {

            ApplicableRegionSet set = this.plugin.getChecks().isInRegion(event.getClickedBlock());
            State state = this.plugin.getState();
            State.Type type = this.plugin.getState().getType();
            FileConfiguration config = this.plugin.getConfig();

            for (ProtectedRegion region : set) {
                if (region.getId().equals(config.getString("REGION_NAME"))) {
                    switch (type) {
                        case NONE -> {
                            state.setType(State.Type.DROP);

                            Holograms.removeHologram();
                            Holograms.createHologram("&a\u2714", event.getClickedBlock().getLocation(), player);

                            player.sendMessage(Util.colorize(config.getString("DROPS_ACTIVE")));
                        }
                        case DROP -> {
                            state.setType(State.Type.NONE);

                            Holograms.removeHologram();
                            Holograms.createHologram("&c\u2716", event.getClickedBlock().getLocation(), player);

                            player.sendMessage(Util.colorize(config.getString("DROPS_INACTIVE")));
                        }
                    }
                }
            }
        }
    }

    // Removes Hologram onBreak
    @EventHandler
    public void onLeverBreak(BlockBreakEvent event) {

        Block block = event.getBlock();

        FileConfiguration config = this.plugin.getConfig();
        ApplicableRegionSet set = this.plugin.getChecks().isInRegion(block);

        for (ProtectedRegion region : set) {
            if (region.getId().equals(config.getString("REGION_NAME"))) {
                Holograms.removeHologram();
            }
        }
    }
}
