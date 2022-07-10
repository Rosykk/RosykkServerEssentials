package me.rosykk.Events;

import me.rosykk.Enums.State;
import me.rosykk.Main;
import me.rosykk.Utils.Position;
import me.rosykk.Utils.Util;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
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

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        Action action = event.getAction();

        PlayerInventory playerInventory = player.getInventory();
        ItemMeta itemMeta = playerInventory.getItemInMainHand().getItemMeta();

        State.Type state = plugin.getState().getType();

        if (state == State.Type.SETUP) {
            if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE) && itemMeta.hasEnchants()) {
                if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                    event.setCancelled(true);

                    Location startLocation = new Location(player.getWorld(), block.getX(), block.getY(), block.getZ());
                    Position.setStartPosition(startLocation);

                    player.sendMessage(Util.colorize(config.getString("FIRST_POSITION")));
                }

                else if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    event.setCancelled(true);

                    Location endLocation = new Location(player.getWorld(), block.getX(), block.getY(), block.getZ());
                    Position.setEndPosition(endLocation);

                    player.sendMessage(Util.colorize(config.getString("SECOND_POSITION")));
                }
            }
        }
    }
}
