package com.division.battlegrounds.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Used as work around to Bukkit throwing out each Player instance after a
 * death.
 *
 * @author Evan
 */
public class BattlegroundPlayer {

    private final String name;

    public BattlegroundPlayer(String name) {
        this.name = name;
    }

    /**
     * Get the battleground player's name
     *
     * @return Name of the BattlegroundPlayer
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Bukkit Player
     *
     * @return Current Player instance for this BattlegroundPlayer
     */
    public Player getPlayer() {
        return Bukkit.getServer().getPlayerExact(name);
    }
}
