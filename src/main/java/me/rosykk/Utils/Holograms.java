package me.rosykk.Utils;

import me.rosykk.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Holograms {

    private static final ArrayList<ArmorStand> list = new ArrayList<>();
    private static final Main plugin = Main.getInstance();

    public static void createHologram(String message, Location location, Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location.add(0.5, -1.8, 0.5), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.getUniqueId();
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(Util.colorize(message));
        armorStand.setGravity(false);

        Holograms.list.add(armorStand);
    }

    public static void removeHologram() {
        if (!Holograms.list.isEmpty()) {
            ArmorStand armorStand = Holograms.list.get(0);
            armorStand.remove();

            Holograms.list.remove(0);
        }
    }
}
