package com.division.battlegrounds.commands;

import org.bukkit.entity.Player;

/**
 *
 * @author Evan
 */
public abstract class BattlegroundsCommand {

    /**
     * 
     * @param sender
     * @param args
     */
    public abstract void run(Player sender, String[] args);
}
