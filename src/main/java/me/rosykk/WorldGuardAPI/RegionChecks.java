package me.rosykk.WorldGuardAPI;

import com.sk89q.worldedit.world.World;
import me.rosykk.*;
import org.bukkit.entity.*;
import com.sk89q.worldguard.protection.*;
import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldedit.math.*;
import com.sk89q.worldguard.*;
import com.sk89q.worldguard.protection.regions.*;
import org.bukkit.*;
import com.sk89q.worldguard.protection.managers.*;
import org.bukkit.block.*;

public class RegionChecks {
    private final Main plugin;

    public RegionChecks(final Main plugin) {
        this.plugin = plugin;
    }

    public ApplicableRegionSet isInRegion(final Entity entity) {
        WorldGuard worldGuard = this.plugin.getWorldGuard();
        RegionContainer container = worldGuard.getPlatform().getRegionContainer();
        Location loc = entity.getLocation();
        World world = BukkitAdapter.adapt(entity.getWorld());
        RegionManager regionManager = container.get(world);
        BlockVector3 blockVector3 = BlockVector3.at(loc.getX(), loc.getY(), loc.getZ());
        return regionManager.getApplicableRegions(blockVector3);
    }

    public ApplicableRegionSet isInRegion(final Block block) {
        WorldGuard worldGuard = this.plugin.getWorldGuard();
        RegionContainer container = worldGuard.getPlatform().getRegionContainer();
        Location loc = block.getLocation();
        World world = BukkitAdapter.adapt(block.getWorld());
        RegionManager regionManager = container.get(world);
        BlockVector3 blockVector3 = BlockVector3.at(loc.getX(), loc.getY(), loc.getZ());
        return regionManager.getApplicableRegions(blockVector3);
    }
}
