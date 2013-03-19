package com.division.battlegrounds.event;

import com.division.battlegrounds.core.Battleground;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Evan
 */
public class BattlegroundQuitEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Battleground battleground;
    private Player player;

    public BattlegroundQuitEvent(Battleground bg, Player player) {
        this.battleground = bg;
        this.player = player;
    }

    /**
     * Returns the Battleground involved in this event.
     *
     * @return Battleground which is involved in this event.
     */
    public Battleground getBattleground() {
        return battleground;
    }

    /**
     * Returns the player involved in this event
     *
     * @return Player who is involved in this event
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
