package me.rosykk;

import org.bukkit.plugin.java.*;
import me.rosykk.Config.*;
import me.rosykk.Utils.CommandAPI.*;
import com.sk89q.worldguard.*;
import me.rosykk.Enums.*;
import me.rosykk.WorldGuardAPI.*;
import me.rosykk.Commands.*;
import me.rosykk.CustomItems.*;
import me.rosykk.Utils.*;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Config cfg;
    private CommandFramework framework;
    private WorldGuard worldGuard;
    private State state;
    private RegionChecks checks;

    public void onEnable() {

        instance = this;

        this.cfg = new Config(this);
        cfg.loadConfiguration();

        this.state = new State(State.Type.NONE);
        this.worldGuard = WorldGuard.getInstance();
        this.checks = new RegionChecks(this);

        this.framework = new CommandFramework(this);
        this.framework.registerCommands(new RcoreCommand());
        this.framework.registerCommands(new ReloadCommand());
        this.framework.registerCommands(new SetupCommand());
        this.framework.registerCommands(new RemoveCommand());
        this.framework.registerHelp();

        Util.registerEvents();

        CustomCraftings.initialize();
    }

    public static Main getInstance() {
        return Main.instance;
    }

    public Config getCfg() {
        return this.cfg;
    }

    public CommandFramework getFramework() {
        return this.framework;
    }

    public WorldGuard getWorldGuard() {
        return this.worldGuard;
    }

    public State getState() {
        return this.state;
    }

    public RegionChecks getChecks() {
        return this.checks;
    }
}
