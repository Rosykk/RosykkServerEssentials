package me.rosykk.Utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Holograms {
    private static ArrayList<ArmorStand> list;

    static {
        Holograms.list = new ArrayList<ArmorStand>();
    }

    public static void createHologram(final String message, final Location location, final Player player) {
        final ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location.add(0.5, -1.8, 0.5), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.getUniqueId();
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(Util.colorize(message));
        armorStand.setGravity(false);
        Holograms.list.add(armorStand);
    }

    public static void removeHologram() {
        if (!Holograms.list.isEmpty()) {
            final ArmorStand armorStand = Holograms.list.get(0);
            armorStand.remove();
            Holograms.list.remove(0);
        }
    }
}
