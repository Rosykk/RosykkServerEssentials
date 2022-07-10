package me.rosykk.WorldGuardAPI;

import com.sk89q.worldedit.world.World;
import org.bukkit.*;
import me.rosykk.*;
import com.sk89q.worldguard.*;
import org.bukkit.entity.*;
import com.sk89q.worldedit.bukkit.*;
import org.bukkit.configuration.file.*;
import com.sk89q.worldguard.protection.managers.*;
import me.rosykk.Utils.*;
import com.sk89q.worldedit.math.*;
import com.sk89q.worldguard.domains.*;
import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.protection.managers.storage.*;
import com.sk89q.worldguard.protection.regions.*;
import com.sk89q.worldguard.protection.flags.registry.*;

public class Region
{
    private static final Main plugin;
    private final RegionContainer container;

    public Region() {
        WorldGuard worldGuard = Region.plugin.getWorldGuard();
        this.container = worldGuard.getPlatform().getRegionContainer();
    }

    public void removeRegion(final Player player) {
        World world = BukkitAdapter.adapt(player.getWorld());
        FileConfiguration config = Region.plugin.getConfig();

        String regionName = config.getString("REGION_NAME");
        RegionManager regionManager = this.container.get(world);

        if (regionManager.getRegion(regionName) == null) {
            player.sendMessage(Util.colorize("&cNeexistuje zadny region k odstraneni!"));
            return;
        }

        regionManager.removeRegion(config.getString("REGION_NAME"));
        player.sendMessage(Util.colorize("&aRegion &2" + regionName + " &aodstranen!"));
    }

    public void createRegion(Player player) {
        FileConfiguration config = Region.plugin.getConfig();
        WorldGuard worldGuard = Region.plugin.getWorldGuard();
        RegionContainer container = worldGuard.getPlatform().getRegionContainer();

        if (Position.isEmpty()) {
            player.sendMessage(Util.colorize(config.getString("MESSAGE_POSITION_EMPTY")));
            return;
        }

        Location loc1 = Position.getStartPosition();
        Location loc2 = Position.getEndPosition();

        BlockVector3 startPosition = BlockVector3.at(loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ());
        BlockVector3 endPosition = BlockVector3.at(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());

        ProtectedRegion region = new ProtectedCuboidRegion(config.getString("REGION_NAME"), startPosition, endPosition);

        World world = BukkitAdapter.adapt(player.getWorld());
        RegionManager regionManager = container.get(world);

        if (regionManager.getRegion(config.getString("REGION_NAME")) != null) {
            player.sendMessage(Util.colorize("&cTenhle region jiz existuje, pouzij &4/refworld remove &cpro odstraneni a pote &4/refworld setup"));
            return;
        }

        regionManager.addRegion(region);
        DefaultDomain owners = new DefaultDomain();
        owners.addPlayer(player.toString());

        final FlagRegistry flagRegistry = worldGuard.getFlagRegistry();
        for (int i = 0; i < flagRegistry.size(); ++i) {
            final Flag<?> flag = flagRegistry.getAll().get(i);
            if (flag.hasConflictStrategy()) {
                final StateFlag stateFlag = (StateFlag) flag;
                region.setFlag(stateFlag, StateFlag.State.ALLOW);
            }
        }

        region.setFlag(Flags.BLOCK_PLACE, StateFlag.State.DENY);
        region.setFlag(Flags.PVP, StateFlag.State.ALLOW);

        try {
            regionManager.save();
            player.sendMessage(Util.colorize(Region.plugin.getConfig().getString("MESSAGE_REGION").replace("{region}", config.getString("REGION_NAME"))));
        }
        catch (StorageException exception) {
            player.sendMessage(Util.colorize(Region.plugin.getConfig().getString("MESSAGE_EXCEPTION")));
            Region.plugin.getLogger().info(exception.getMessage());
        }
    }

    static {
        plugin = Main.getInstance();
    }
}
