package me.rosykk.Utils;

import org.bukkit.Location;

public class Position {
    private static Location startPosition;
    private static Location endPosition;

    public static boolean isEmpty() {
        return Position.startPosition == null || Position.endPosition == null;
    }

    public static Location getStartPosition() {
        return Position.startPosition;
    }

    public static void setStartPosition(final Location startPosition) {
        Position.startPosition = startPosition;
    }

    public static Location getEndPosition() {
        return Position.endPosition;
    }

    public static void setEndPosition(final Location endPosition) {
        Position.endPosition = endPosition;
    }
}
