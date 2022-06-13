package io.github.nexuskrop.delight;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class BlastProtectListeners implements Listener {
    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        event.setRadius(0f);
        event.setFire(false);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
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
        BlastUtil.createExplosion(event.getBlock().getWorld(), null, event.getBlock().getLocation(), BlastUtil.BLAST_POWER_BED);
        event.setCancelled(true);
    }
}
