package me.rosykk.CustomItems;

import me.rosykk.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import me.rosykk.Config.*;

public class CustomCraftings
{
    private static final Main plugin = Main.getInstance();

    private static void addLightBlock() {
        ItemStack itemStack = new ItemStack(Material.LIGHT, 2);
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.minecraft("light"), itemStack);

        shapelessRecipe.addIngredient(2, Material.TORCH);
        shapelessRecipe.addIngredient(2, Material.IRON_NUGGET);

        CustomCraftings.plugin.getServer().addRecipe(shapelessRecipe);
    }

    public static void initialize() {
        Config config = CustomCraftings.plugin.getCfg();

        if (config.getBoolean("LIGHT_CRAFTING")) addLightBlock();
    }
}
