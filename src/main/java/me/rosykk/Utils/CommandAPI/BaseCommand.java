package me.rosykk.Utils.CommandAPI;

import me.rosykk.Main;

public abstract class BaseCommand {

    public Main main = Main.getInstance();

    public BaseCommand() {
        main.getFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);
}
