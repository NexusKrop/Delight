package io.github.nexuskrop.delight.util;

import org.bukkit.Chunk;
import org.bukkit.Material;

public final class MaterialUtil {
    private MaterialUtil() {
        throw new IllegalStateException("No MaterialUtil instances for you!");
    }

    private static final Material[] BANNERS = {
            // Standing banners
            Material.BLACK_BANNER,
            Material.BLUE_BANNER,
            Material.BROWN_BANNER,
            Material.CYAN_BANNER,
            Material.GRAY_BANNER,
            Material.GREEN_BANNER,
            Material.LIGHT_BLUE_BANNER,
            Material.LIGHT_GRAY_BANNER,
            Material.LIME_BANNER,
            Material.MAGENTA_BANNER,
            Material.YELLOW_BANNER,
            Material.RED_BANNER,
            Material.PURPLE_BANNER,
            Material.WHITE_BANNER,
            Material.ORANGE_BANNER,
            Material.PINK_BANNER,
            // Wall banners
            Material.BLACK_WALL_BANNER,
            Material.BLUE_WALL_BANNER,
            Material.BROWN_WALL_BANNER,
            Material.CYAN_WALL_BANNER,
            Material.GRAY_WALL_BANNER,
            Material.GREEN_WALL_BANNER,
            Material.LIGHT_BLUE_WALL_BANNER,
            Material.LIGHT_GRAY_WALL_BANNER,
            Material.LIME_WALL_BANNER,
            Material.MAGENTA_WALL_BANNER,
            Material.YELLOW_WALL_BANNER,
            Material.RED_WALL_BANNER,
            Material.PURPLE_WALL_BANNER,
            Material.WHITE_WALL_BANNER,
            Material.ORANGE_WALL_BANNER,
            Material.PINK_WALL_BANNER
    };

    private static final Material[] SKULLS = {
            Material.PLAYER_HEAD,
            Material.PLAYER_WALL_HEAD,
            Material.CREEPER_HEAD,
            Material.CREEPER_WALL_HEAD,
            Material.DRAGON_HEAD,
            Material.DRAGON_WALL_HEAD,
            Material.ZOMBIE_HEAD,
            Material.ZOMBIE_WALL_HEAD,
            Material.SKELETON_SKULL,
            Material.SKELETON_WALL_SKULL,
            Material.WITHER_SKELETON_SKULL,
            Material.WITHER_SKELETON_WALL_SKULL
    };

    private static final Material[] ORDINARY = {
            Material.CHEST,
            Material.ENDER_CHEST,
            Material.FURNACE,
            Material.BLAST_FURNACE,
            Material.SMOKER,
            Material.BELL,
            Material.ENCHANTING_TABLE,
            Material.HOPPER,
            Material.DROPPER,
            Material.DISPENSER,
            Material.BREWING_STAND,
            Material.BEACON,
            Material.TRAPPED_CHEST,
            Material.FLOWER_POT
    };

    private static final Material[] SIGNS = {
            Material.ACACIA_SIGN,
            Material.BIRCH_SIGN,
            Material.CRIMSON_SIGN,
            Material.SPRUCE_SIGN,
            Material.JUNGLE_SIGN,
            Material.DARK_OAK_SIGN,
            Material.OAK_SIGN,
            Material.MANGROVE_SIGN,
            Material.ACACIA_WALL_SIGN,
            Material.BIRCH_WALL_SIGN,
            Material.CRIMSON_WALL_SIGN,
            Material.SPRUCE_WALL_SIGN,
            Material.JUNGLE_WALL_SIGN,
            Material.DARK_OAK_WALL_SIGN,
            Material.OAK_WALL_SIGN,
            Material.MANGROVE_WALL_SIGN
    };

    public static boolean isBanner(Material material) {
        for (var banner : BANNERS) {
            if (material == banner) {
                return true;
            }
        }

        return false;
    }

    public static boolean isRegularBlockEntity(Material material) {
        return isMaterialIn(material, ORDINARY);
    }

    public static boolean isMaterialIn(Material material, Material[] list) {
        for (var item : list) {
            if (material == item) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSign(Material material) {
        for (var sign : SIGNS) {
            if (material == sign) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSkull(Material material) {
        for (var skull : SKULLS) {
            if (material == skull) {
                return true;
            }
        }

        return false;
    }

    public static int countBlockPerChunk(Chunk chunk, Material lookingFor) {
        int count = 0;
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 256; ++y) {
                    if (chunk.getBlock(x, y, z).getType() != lookingFor) continue;
                    ++count;
                }
            }
        }
        return count;
    }
}
