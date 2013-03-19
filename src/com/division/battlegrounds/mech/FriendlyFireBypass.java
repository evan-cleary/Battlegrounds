package com.division.battlegrounds.mech;

import com.division.common.utils.ItemArmor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Evan
 */
public class FriendlyFireBypass {

    /**
     * Performs the bypass for friendly fire. i.e Factions, mcMMO
     *
     * @param player Player targeted by the bypass
     * @param armor Flag if bypass should account for armor
     * @param damage Base damage to be dealt to player
     */
    public static void damage(Player player, boolean armor, int damage) {
        if (armor) {
            player.damage(getArmorRedox(player, damage));
        } else {
            player.damage(damage);
        }
    }

    private static int aO(Player p) {
        return l(p);
    }

    private static int l(Player p) {
        int i = 0;
        ItemStack[] aitemstack = p.getInventory().getArmorContents();
        int j = aitemstack.length;

        for (int k = 0; k < j; ++k) {
            ItemStack itemstack = aitemstack[k];

            int l = ItemArmor.valueOf(itemstack.getType().name()).b();

            i += l;
        }
        return i;
    }

    private static int getArmorRedox(Player p, int i) {
        int j = 25 - aO(p);
        int k = i * j;

        k(p, i);
        i = k / 25;

        return i;
    }

    private static void k(Player p, int i) {
        i /= 4;
        if (i < 1) {
            i = 1;
        }
        ItemStack[] armor = p.getInventory().getArmorContents();

        for (int j = 0; j < armor.length; ++j) {
            if (armor[j] != null) {
                armor[j].setDurability((short) (armor[j].getDurability() - i));
                if (armor[j].getDurability() == 0) {
                    armor[j] = null;
                }
            }
        }
    }
}
