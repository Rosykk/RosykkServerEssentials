package me.rosykk.Commands;

import me.rosykk.*;
import me.rosykk.WorldGuardAPI.*;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.*;
import me.rosykk.Utils.CommandAPI.*;

public class RemoveCommand extends BaseCommand
{
    private final Main plugin;

    public RemoveCommand() {
        this.plugin = Main.getInstance();
    }

    @Command(name = "rcore.remove", isAdminOnly = true)
    @Override
    public void onCommand(final CommandArgs args) {
        final FileConfiguration config = this.plugin.getConfig();
        final Player player = args.getPlayer();
        final Region region = new Region(config.getString("REGION_NAME"));
        region.removeRegion(player);
    }
}
