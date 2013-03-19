package com.division.battlegrounds.storage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Evan
 */
public class SupplyElement implements Serializable {

    private final int type, amount;
    private final short damage;
    private final HashMap<SupplyAttribute, Integer> attributes;

    public SupplyElement(ItemStack item) {
        this.type = item.getTypeId();
        this.amount = item.getAmount();
        this.damage = item.getDurability();
        HashMap<SupplyAttribute, Integer> attMap = new HashMap<SupplyAttribute, Integer>();

        Map<Enchantment, Integer> enchantments = item.getEnchantments();

        for (Enchantment enchantment : enchantments.keySet()) {
            attMap.put(new SupplyAttribute(enchantment), enchantments.get(enchantment));
        }

        this.attributes = attMap;
    }

    /**
     * Returns the ItemStack stored by this element.
     *
     * @return ItemStack stored by this element.
     */
    public ItemStack uncrate() {
        ItemStack item = new ItemStack(type, amount, damage);
        HashMap<Enchantment, Integer> attMap = new HashMap<Enchantment, Integer>();
        for (SupplyAttribute sAttribute : attributes.keySet()) {
            attMap.put(sAttribute.uncrate(), attributes.get(sAttribute));
        }
        item.addUnsafeEnchantments(attMap);

        return item;
    }
}
