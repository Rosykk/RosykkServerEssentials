package me.rosykk.Commands;

import me.rosykk.Utils.*;
import me.rosykk.Utils.CommandAPI.*;
import org.bukkit.command.CommandSender;

public class RefworldCommand extends BaseCommand
{
    @Command(name = "rcore", inGameOnly = false, isAdminOnly = true)
    @Override
    public void onCommand(final CommandArgs args) {

        CommandSender sender = args.getSender();

        sender.sendMessage(Util.colorize("&4/rcore        &7- &cukaze pomoc"));
        sender.sendMessage(Util.colorize("&4/rcore reload &7- &cobnovi konfiguraci"));
        sender.sendMessage(Util.colorize("&4/rcore remove &7- &codstrani region"));
        sender.sendMessage(Util.colorize("&4/rcore setup  &7- &czapne setup mode pro nastaveni regionu"));
    }
}
