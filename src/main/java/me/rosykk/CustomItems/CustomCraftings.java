package me.rosykk.CustomItems;

import me.rosykk.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import me.rosykk.Config.*;

public class CustomCraftings
{
    private static final Main plugin;

    private static void addLightBlock() {
        final ItemStack itemStack = new ItemStack(Material.LIGHT, 2);
        final ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.minecraft("light"), itemStack);
        shapelessRecipe.addIngredient(2, Material.TORCH);
        shapelessRecipe.addIngredient(2, Material.IRON_NUGGET);
        CustomCraftings.plugin.getServer().addRecipe((Recipe)shapelessRecipe);
    }

    public static void initialize() {
        final Config config = CustomCraftings.plugin.getCfg();
        if (config.getBoolean("LIGHT_CRAFTING")) {
            addLightBlock();
        }
    }

    static {
        plugin = Main.getInstance();
    }
}
