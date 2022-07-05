package me.rosykk.Commands;

import me.rosykk.Enums.State;
import me.rosykk.Main;
import me.rosykk.Utils.CommandAPI.BaseCommand;
import me.rosykk.Utils.CommandAPI.Command;
import me.rosykk.Utils.CommandAPI.CommandArgs;
import me.rosykk.Utils.Util;
import me.rosykk.WorldGuardAPI.Region;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SetupCommand extends BaseCommand {

    private static final Main plugin;

    static {
        plugin = Main.getInstance();
    }

    @Command(name = "rcore.setup", isAdminOnly = true)
    @Override
    public void onCommand(CommandArgs args) {

        State.Type state = SetupCommand.plugin.getState().getType();
        FileConfiguration config = SetupCommand.plugin.getConfig();

        Player player = args.getPlayer();
        PlayerInventory pi = player.getInventory();

        if (pi.firstEmpty() == -1) {
            player.sendMessage(Util.colorize("&cMusis mit alespon 1 volny slot v inventari!"));
            return;
        }

        ItemStack wandTool = new ItemStack(Material.DIRT, 1);
        wandTool.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        ItemMeta wandMeta = wandTool.getItemMeta();
        wandMeta.setDisplayName(Util.colorize("&c&lSetup Tool"));

        wandTool.setItemMeta(wandMeta);

        if (state.equals(State.Type.NONE)) {
            SetupCommand.plugin.getState().setType(State.Type.SETUP);

            pi.setItem(pi.firstEmpty(), wandTool);

            player.sendMessage(Util.colorize(config.getString("SETUP_MODE")));
        }
        else if (state.equals(State.Type.SETUP)) {
            SetupCommand.plugin.getState().setType(State.Type.NONE);

            Region region = new Region();
            region.createRegion(player);

            player.sendMessage(Util.colorize(config.getString("SETUP_MODE_LEAVE")));
        }
    }
}
