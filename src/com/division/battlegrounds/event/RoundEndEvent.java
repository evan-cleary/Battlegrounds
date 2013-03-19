package com.division.battlegrounds.event;

import com.division.battlegrounds.core.Battleground;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Evan
 */
public class RoundEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Battleground battleground;

    public RoundEndEvent(Battleground battleground) {
        this.battleground = battleground;
    }

    /**
     * Returns the Battleground involved in this event.
     *
     * @return Battleground which is involved in this evet.
     */
    public Battleground getBattleground() {
        return battleground;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
