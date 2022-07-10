package me.rosykk.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import java.util.Iterator;

public class Holograms {

    public static void createHologram(String message, Location location, Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location.add(0.5, -1.8, 0.5), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(Util.colorize(message));
        armorStand.setGravity(false);
    }

    public static void removeHologram() {
        Iterator<World> wi = Bukkit.getWorlds().iterator();
        while (wi.hasNext()) {
            for (Entity entity : wi.next().getEntities()) {

                String name = entity.getName();

                if (name.equals(Util.colorize("&a\u2714")) || name.equals(Util.colorize("&c\u2716"))) {
                    entity.remove();
                }
            }
        }
    }
}
