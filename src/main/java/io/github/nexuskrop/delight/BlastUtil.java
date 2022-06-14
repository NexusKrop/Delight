package io.github.nexuskrop.delight;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public final class BlastUtil {
    public static final float BLAST_POWER_CHARGED_CREEPER = 6f;
    public static final float BLAST_POWER_REGULAR_CREEPER = 3f;
    public static final float BLAST_POWER_TNT = 4f;
    public static final float BLAST_POWER_BED = 5f;

    private BlastUtil() {
        throw new IllegalStateException("No BlastUtil instances for you!");
    }

    public static void createExplosion(World world, @Nullable Entity source, Location loc, float power) {
        world.createExplosion(source, loc, power, false, false);
    }
}
