package io.github.nexuskrop.delight.listeners;

import io.github.nexuskrop.delight.Delight;
import io.github.nexuskrop.delight.util.MaterialUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEntityCapping implements Listener {
    private final Delight delight;

    private static final String CONFIG_ENABLED = "antigrief.cap-block-entities";
    private static final String CONFIG_MAX_GENERIC = "block-entities.max-block-entities";
    private static final String CONFIG_MAX_SKULL = "block-entities.max-skulls";
    private static final String CONFIG_MAX_BANNERS = "block-entities.max-banners";
    private static final String MESSAGE_TOO_MANY_ENTITIES = "messages.too-many-entities";

    public BlockEntityCapping(Delight delight) {
        this.delight = delight;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!delight.getConfig().getBoolean(CONFIG_ENABLED, true)) {
            return;
        }

        var max = maxBlockEntities();

        var chunk = event.getBlock().getChunk();
        var material = event.getBlock().getType();
        var player = event.getPlayer();

        if (MaterialUtil.isBanner(material)) {
            event.setCancelled(processBanner(chunk, material, player));
        } else if (MaterialUtil.isSkull(material)) {
            event.setCancelled(processSkull(chunk, material, player));
        } else if (MaterialUtil.isSign(material) || MaterialUtil.isRegularBlockEntity(material)) {
            event.setCancelled(processMax(chunk, material, player, max));
        }
    }

    public boolean processBanner(Chunk chunk, Material material, Player player) {
        return processMax(chunk, material, player, delight.getConfig().getInt(CONFIG_MAX_BANNERS, 5));
    }

    public boolean processMax(Chunk chunk, Material material, Player player, int max) {
        if (MaterialUtil.countBlockPerChunk(chunk, material) > max) {
            sendCappedMessage(material, player, max);
            return true;
        }

        return false;
    }

    public boolean processSkull(Chunk chunk, Material material, Player player) {
        return processMax(chunk, material, player, delight.getConfig().getInt(CONFIG_MAX_SKULL, 10));
    }

    public int maxBlockEntities() {
        return delight.getConfig().getInt(CONFIG_MAX_GENERIC, 100);
    }

    public void sendCappedMessage(Material material, Player target, int max) {
        var component = MiniMessage.miniMessage().deserialize(delight.getConfig().getString(MESSAGE_TOO_MANY_ENTITIES),
                Placeholder.component("e-type", Component.translatable(material.translationKey())),
                Placeholder.unparsed("e-max", Integer.toString(max)));

        target.sendMessage(component);
    }
}
