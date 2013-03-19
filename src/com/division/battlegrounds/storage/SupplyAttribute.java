package com.division.battlegrounds.storage;

import java.io.Serializable;
import org.bukkit.enchantments.Enchantment;

/**
 *
 * @author Evan
 */
public class SupplyAttribute implements Serializable {

    private final int id;

    public SupplyAttribute(Enchantment enchantment) {
        this.id = enchantment.getId();
    }

    /**
     * Returns the stored enchantment.
     *
     * @return The stored Enchantment
     */
    public Enchantment uncrate() {
        return Enchantment.getById(this.id);
    }
}
