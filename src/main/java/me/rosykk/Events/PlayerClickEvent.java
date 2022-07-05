package me.rosykk.Events;

import me.rosykk.Enums.State;
import me.rosykk.Main;
import me.rosykk.Utils.Position;
import me.rosykk.Utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerClickEvent implements Listener {
    private final Main plugin;

    public PlayerClickEvent(final Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent event) {

        FileConfiguration config = this.plugin.getConfig();
        State.Type state = this.plugin.getState().getType();

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        Action action = event.getAction();

        PlayerInventory pi = player.getInventory();

        // todo check itemmeta tool


        if (state == State.Type.SETUP) {
            if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                event.setCancelled(true);
                player.sendMessage(Util.colorize(config.getString("FIRST_POSITION")));

                Location startLocation = new Location(player.getWorld(), block.getX(), block.getY(), (double) block.getZ());
                Position.setStartPosition(startLocation);
            }
            else if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                event.setCancelled(true);
                player.sendMessage(Util.colorize(config.getString("SECOND_POSITION")));

                Location endLocation = new Location(player.getWorld(), block.getX(), block.getY(), block.getZ());
                Position.setEndPosition(endLocation);
            }
        }
    }
}
