package com.division.battlegrounds.storage;

import java.io.Serializable;
import java.util.HashMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author Evan
 */
public class SupplyCrate implements Serializable {

    private final HashMap<Integer, SupplyElement> c_armor, c_items;

    public SupplyCrate(PlayerInventory inventory) {
        ItemStack[] armor, items;

        armor = inventory.getArmorContents();
        items = inventory.getContents();

        c_armor = new HashMap<Integer, SupplyElement>();
        c_items = new HashMap<Integer, SupplyElement>();

        for (int i = 0; i < armor.length; i++) {
            SupplyElement val = null;
            if (armor[i] != null) {
                val = new SupplyElement(armor[i]);
            }
            c_armor.put(i, val);
        }

        for (int i = 0; i < items.length; i++) {
            SupplyElement val = null;
            if (items[i] != null) {
                val = new SupplyElement(items[i]);
            }
            c_items.put(i, val);
        }
    }

    /**
     * Returns all stored items destined for the armor slots.
     *
     * @return ItemStack[]
     */
    public ItemStack[] uncrateArmor() {
        ItemStack[] armor = new ItemStack[c_armor.size()];
        for (int i = 0; i < c_armor.size(); i++) {
            if (c_armor.get(i) == null) {
                armor[i] = null;
            } else {
                armor[i] = c_armor.get(i).uncrate();
            }
        }
        return armor;
    }

    /**
     * Returns all stored items destined for the main inventory slots
     *
     * @return ItemStack[]
     */
    public ItemStack[] uncrateContents() {
        ItemStack[] items = new ItemStack[c_items.size()];
        for (int i = 0; i < c_items.size(); i++) {
            if (c_items.get(i) == null) {
                items[i] = null;
            } else {
                items[i] = c_items.get(i).uncrate();
            }
        }
        return items;
    }
}
