package me.rosykk.Commands;

import me.rosykk.WorldGuardAPI.*;
import org.bukkit.entity.*;
import me.rosykk.Utils.CommandAPI.*;

public class RemoveCommand extends BaseCommand
{
    @Command(name = "rcore.remove", isAdminOnly = true)
    @Override
    public void onCommand(final CommandArgs args) {
        Player player = args.getPlayer();

        Region region = new Region();
        region.removeRegion(player);
    }
}
