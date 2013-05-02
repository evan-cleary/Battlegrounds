package com.division.battlegrounds.core;

import com.division.battlegrounds.event.*;
import com.division.battlegrounds.region.Region;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 *
 * @author Evan
 */
public abstract class Battleground implements Listener {

    protected Region region;
    final private String name;
    private List<Player> queue;
    protected Map<BattlegroundPlayer, Location> inBattleground;
    private int minPlayers;
    private int maxPlayers;
    private Gametype gametype;
    private boolean isBattlegroundActive = false;
    private boolean isDynamic = false;
    private boolean isBroadcasting = true;

    public Battleground(final String name, final Gametype gametype, final Region region) {
        this.region = region;
        this.name = name;
        this.gametype = gametype;
        this.inBattleground = new HashMap<BattlegroundPlayer, Location>();
        this.queue = new ArrayList<Player>();
    }

    /**
     * Returns maximum number of players allowed in this battleground
     *
     * @return maximum numbers of player allowed in battleground
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Sets the maximum number of players allowed in this battleground
     *
     * @param maxPlayers
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Returns minimum number of players to start this battleground
     *
     * @return Minimum numbers of player to start battleground
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Sets the minimum number of players to start this battleground
     *
     * @param minPlayers
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Returns the region involved with this battleground
     *
     * @return Region involved with this battleground
     */
    public Region getRegion() {
        return this.region;
    }

    /**
     * Returns the gametype involved with this battleground
     *
     * @return {@link Gametype} which is involved with this battleground
     */
    public Gametype getGametype() {
        return gametype;
    }

    /**
     * Sets the region involved with this battleground
     *
     * @param region New region that battleground will use
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Returns the name of the battleground
     *
     * @return The name of the battleground
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the battleground is full or not
     *
     * @return If battleground is full
     */
    public boolean isFull() {
        return inBattleground.size() == maxPlayers;
    }

    /**
     * Returns if the battleground is broadcasting it's queue status
     *
     * @return If battleground is broadcasting it's queue status
     */
    public boolean isBroadcasting() {
        return isBroadcasting;
    }

    /**
     * Sets whether the battleground is broadcasting it's queue status.
     *
     * @param broadcasting
     */
    public void setBroadcasting(boolean broadcasting) {
        isBroadcasting = broadcasting;
    }

    /**
     * Gets a list of players that are in queue for this battleground
     *
     * @return All Players who are in queue for this battleground
     */
    public List<Player> getQueue() {
        return queue;
    }

    /**
     * Returns the number of players in queue
     *
     * @return Numbers of players in queue
     */
    public int getQueueSize() {
        return queue.size();
    }

    /**
     * Returns if battleground is active
     *
     * @return If battleground is active
     */
    public boolean isActive() {
        return isBattlegroundActive;
    }

    /**
     * Returns if the battleground is a dynamic battleground.<br />
     * This option determines whether a player can join in progress.
     *
     * @return If battleground is dynamic
     */
    public boolean isDynamic() {
        return isDynamic;
    }

    /**
     * Sets whether the battleground is dynamic
     *
     * @param bool
     */
    public void setDynamic(boolean bool) {
        isDynamic = bool;
    }

    /**
     * Adds a player to the queue for this battleground
     *
     * @param player Player to be added to queue
     */
    public void addToQueue(Player player) {
        if (!queue.contains(player)) {
            queue.add(player);
            Bukkit.getServer().getPluginManager().callEvent(new EnteredQueueEvent(this, player));
        }
        if (queue.size() == minPlayers) {
            Bukkit.getServer().getPluginManager().callEvent(new QueueFullEvent(this));
        }
    }

    /**
     * Removes a player from the queue for this battleground
     *
     * @param player Player to be remove from queue
     */
    public void removeFromQueue(Player player) {
        if (queue.contains(player)) {
            queue.remove(player);
            Bukkit.getServer().getPluginManager().callEvent(new LeftQueueEvent(this, player));
        }
    }

    /**
     * Returns if a player is in the battleground
     *
     * @param player
     * @return If player is in battleground
     */
    public boolean isPlayerInBattleground(Player player) {
        for (BattlegroundPlayer bgPlayer : inBattleground.keySet()) {
            if (bgPlayer.getName().equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the battleground player in this battleground
     *
     * @param name Player's name
     * @return BattlegroundPlayer if they are in this battleground.
     */
    public BattlegroundPlayer getBattlegroundPlayer(String name) {
        for (BattlegroundPlayer player : inBattleground.keySet()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Adds a player to the battleground
     *
     * @param player Player to be added
     */
    public void addPlayerToBattleground(Player player) {
        BattlegroundPlayer bgPlayer = new BattlegroundPlayer(player.getName());
        inBattleground.put(bgPlayer, player.getLocation());
        Bukkit.getServer().getPluginManager().callEvent(new BattlegroundJoinEvent(this, player));
    }

    /**
     * Removes a player from the battleground
     *
     * @param player Player to be removed.
     */
    public void removePlayerFromBattleground(Player player) {
        BattlegroundPlayer bgPlayer = getBattlegroundPlayer(player.getName());
        player.teleport(inBattleground.get(bgPlayer));
        inBattleground.remove(bgPlayer);
        BattlegroundCore.sendMessage(player, "You have been removed from " + getName());
        Bukkit.getServer().getPluginManager().callEvent(new BattlegroundQuitEvent(this, player));
    }

    /**
     * Broadcasts a message to everyone in the arena.
     *
     * @param message Message you want to broadcast
     */
    public void broascastToArena(String message) {
        for (BattlegroundPlayer player : inBattleground.keySet()) {
            player.getPlayer().sendMessage(message);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBattlegroundQuit(BattlegroundQuitEvent evt) {
        if (evt.getBattleground() == this) {
            if (inBattleground.isEmpty()) {
                Bukkit.getServer().getPluginManager().callEvent(new RoundEndEvent(this));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRoundEnd(RoundEndEvent evt) {
        if (evt.getBattleground() == this) {
            this.isBattlegroundActive = false;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRoundStart(RoundStartEvent evt) {
        if (evt.getBattleground() == this) {
            this.isBattlegroundActive = true;
        }
    }
}
