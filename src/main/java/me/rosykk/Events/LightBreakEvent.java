package me.rosykk.Events;

import me.rosykk.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;

public class LightBreakEvent implements Listener {

    private final HashSet<Player> playerList = new HashSet<>();

    private final Main plugin;

    public LightBreakEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLightBreak(BlockPlaceEvent event) {

        World world = event.getPlayer().getWorld();

        if (event.getBlockReplacedState().getType() == Material.LIGHT) {

            ItemStack itemStack = new ItemStack(Material.LIGHT);
            world.dropItemNaturally(event.getBlock().getLocation(), itemStack);

        }
    }

    @EventHandler
    public void onHandBreak(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getClickedBlock() != null && event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (block.getType().equals(Material.LIGHT)) {
                Location l = block.getLocation();

                event.getClickedBlock().setType(Material.AIR);
                World world = plugin.getServer().getWorld(player.getWorld().getName());
                world.dropItem(l, new ItemStack(Material.LIGHT, 1));

                world.playSound(l, Sound.BLOCK_STONE_BREAK, 1F, 1F);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            @NotNull Material itemStack = player.getInventory().getItemInMainHand().getType();

            if (itemStack.equals(Material.LIGHT) && !playerList.contains(player)) {
                HashSet<Location> lightLoc = getNearbyBlocks(player, 8);

                playerList.add(player);

                int times = 1;
                new BukkitRunnable() {
                    int i = 1;

                    public void run() {
                        if (this.i > times) {
                            playerList.remove(player);
                            this.cancel();
                        }
                        else {
                            ++this.i;

                            lightLoc.forEach(loc -> player.spawnParticle(Particle.BLOCK_MARKER, loc, 1, loc.getBlock().getBlockData()));
                        }
                    }
                }.runTaskTimer(plugin, 0L, 30L);
            }
        }
    }

    // Returns all LIGHT blocks in radius.
    private static HashSet<Location> getNearbyBlocks(Player player, int radius) {
        HashSet<Location> blocks = new HashSet<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    Location loc = player.getLocation().getBlock().getLocation().clone().add(x, y, z);
                    if (loc.getBlock().getType().equals(Material.LIGHT)) {
                        blocks.add(loc.add(0.5, 0.5, 0.5));
                    }
                }
            }
        }
        return blocks;
    }
}
