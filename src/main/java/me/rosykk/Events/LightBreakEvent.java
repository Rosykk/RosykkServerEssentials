package me.rosykk.Events;

import me.rosykk.Main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LightBreakEvent implements Listener {
    private final Main plugin;

    public LightBreakEvent(final Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLightBreak(final BlockPlaceEvent event) {
        final World world = event.getPlayer().getWorld();
        if (event.getBlockReplacedState().getType() == Material.LIGHT) {
            final ItemStack itemStack = new ItemStack(Material.LIGHT);
            world.dropItemNaturally(event.getBlock().getLocation(), itemStack);
        }
    }
}
