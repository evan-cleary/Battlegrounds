package com.division.battlegrounds.event;

import com.division.battlegrounds.core.Battleground;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Evan
 */
public class RoundStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Battleground battleground;
    private List<Player> playersToBeAdded;

    public RoundStartEvent(Battleground battleground, List<Player> players) {
        this.battleground = battleground;
        this.playersToBeAdded = players;
    }

    /**
     * Returns the Battleground involved in this event.
     *
     * @return Battleground which is involved in this evet.
     */
    public Battleground getBattleground() {
        return battleground;
    }

    /**
     * Gets the players that are going to be added to battleground
     *
     * @return All players being added to the battleground
     */
    public List<Player> getPlayersToBeAdded() {
        return playersToBeAdded;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
