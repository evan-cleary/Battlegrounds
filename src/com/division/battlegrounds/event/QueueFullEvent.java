package com.division.battlegrounds.event;

import com.division.battlegrounds.core.Battleground;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Evan
 */
public class QueueFullEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Battleground battleground;

    public QueueFullEvent(Battleground bg) {
        this.battleground = bg;
    }

    /**
     * Returns the Battleground involved in this event.
     *
     * @return Battleground which is involved in this event.
     */
    public Battleground getBattleground() {
        return this.battleground;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
