package com.division.battlegrounds.listeners;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import com.division.battlegrounds.event.BattlegroundQuitEvent;
import com.division.battlegrounds.storage.StorageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Evan
 */
public class PlayerListener implements Listener {
    
    private BattlegroundCore core;
    
    public PlayerListener(BattlegroundCore core) {
        this.core = core;
        core.getServer().getPluginManager().registerEvents(this, core);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer();
        core.getDataInterface().addPlayer(player.getName());
        StorageManager.loadOfflineStorage(evt.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent evt) {
        for (Battleground bg : core.getRegistrar().getRegistrar().values()) {
            if (bg.isPlayerInBattleground(evt.getPlayer())) {
                core.getServer().getPluginManager().callEvent(new BattlegroundQuitEvent(bg, evt.getPlayer()));
                return;
            }
        }
    }
}
