package io.github.nexuskrop.delight;

import io.github.nexuskrop.delight.config.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Delight extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Configuration.load(this.getConfig());

        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new BlastProtectListeners(), this);
        this.getLogger().info("Unit okay");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
