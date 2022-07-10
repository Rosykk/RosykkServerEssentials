package me.rosykk.Commands;

import me.rosykk.*;
import me.rosykk.Config.*;
import me.rosykk.Utils.*;
import me.rosykk.Utils.CommandAPI.Command;
import org.bukkit.command.*;
import me.rosykk.Utils.CommandAPI.*;

public class ReloadCommand extends BaseCommand
{
    private static final Main plugin = Main.getInstance();;

    @Command(name = "rcore.reload", inGameOnly = false, isAdminOnly = true)
    @Override
    public void onCommand(final CommandArgs args) {
        CommandSender sender = args.getSender();

        ConfigManager.reloadConfig();

        sender.sendMessage(Util.colorize(plugin.getCfg().getString("MESSAGE_RELOAD")));
    }
}
