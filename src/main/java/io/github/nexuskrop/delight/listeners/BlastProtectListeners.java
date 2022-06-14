package io.github.nexuskrop.delight.listeners;

import io.github.nexuskrop.delight.BlastUtil;
import io.github.nexuskrop.delight.Delight;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class BlastProtectListeners implements Listener {
    private final Delight delight;
    private static final String PREVENT_EXPLOSIONS = "antigrief.prevent-explosions";

    public BlastProtectListeners(Delight delight) {
        this.delight = delight;
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (!this.delight.getConfig().getBoolean(PREVENT_EXPLOSIONS, true)) {
            return;
        }

        event.setRadius(0f);
        event.setFire(false);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (!this.delight.getConfig().getBoolean(PREVENT_EXPLOSIONS, true)) {
            return;
        }

        if (event.getEntity() instanceof TNTPrimed tnt) {
            BlastUtil.createExplosion(tnt.getWorld(), tnt.getSource(), event.getLocation(), 4f);
            tnt.remove();
        } else if (event.getEntity() instanceof Creeper creeper) {
            var power = BlastUtil.BLAST_POWER_REGULAR_CREEPER;

            if (creeper.isPowered()) {
                power = BlastUtil.BLAST_POWER_CHARGED_CREEPER;
            }

            BlastUtil.createExplosion(creeper.getWorld(), creeper, event.getLocation(), power);
        } else if (event.getEntity() instanceof Explosive exp) {
            BlastUtil.createExplosion(exp.getWorld(), null, event.getLocation(), 1f);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if (!this.delight.getConfig().getBoolean(PREVENT_EXPLOSIONS, true)) {
            return;
        }

        BlastUtil.createExplosion(event.getBlock().getWorld(), null, event.getBlock().getLocation(), BlastUtil.BLAST_POWER_BED);
        event.setCancelled(true);
    }
}
