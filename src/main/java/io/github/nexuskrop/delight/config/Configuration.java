package io.github.nexuskrop.delight.config;

import org.bukkit.configuration.file.FileConfiguration;

public final class Configuration {
    private static boolean enableBlastProtection;

    public static boolean isExplosionProtectionEnabled() {
        return enableBlastProtection;
    }

    public static void load(FileConfiguration config) {
        enableBlastProtection = config.getBoolean("antigrief.prevent-explosions", true);
    }
}
