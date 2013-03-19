package com.division.battlegrounds.mech;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Evan
 */
public class BattlegroundRegistrar {

    private BattlegroundCore bga;
    private Map<Plugin, Battleground> registrar = new HashMap<Plugin, Battleground>();

    public BattlegroundRegistrar(BattlegroundCore bga) {
        this.bga = bga;
    }

    /**
     * Registers a battleground and all of its events to the
     * BattlegroundAnnouncer
     *
     * @param plugin Plugin that runs the battleground
     * @param battleground Battleground to be registered
     */
    public void registerBattleground(Plugin plugin, Battleground battleground) {
        String logFormat = BattlegroundCore.logFormat;
        bga.console.sendMessage(ChatColor.YELLOW + "=====================================");
        bga.console.sendMessage(String.format(logFormat, "Battleground: " + battleground.getName() + " detected. Registering hook..."));
        if (!registrar.containsValue(battleground)) {
            registrar.put(plugin, battleground);
            bga.getServer().getPluginManager().registerEvents(battleground, plugin);
            bga.console.sendMessage(String.format(logFormat, battleground.getName() + " hook has been established."));
            bga.console.sendMessage(String.format(logFormat, "Battleground: " + battleground.getName() + " Status:" + ChatColor.GREEN + " ACTIVE"));
        } else {
            bga.console.sendMessage(String.format(logFormat, "Error while establishing hook with Battleground: " + battleground));
        }
        bga.console.sendMessage(ChatColor.YELLOW + "=====================================");
    }

    /**
     * Gets a map of all registered battlegrounds
     *
     * @return All of the registered battlegrounds
     */
    public Map<Plugin, Battleground> getRegistrar() {
        return registrar;
    }

    /**
     * Returns a registered battleground if one is registered under the name,
     * otherwise, we return null.
     *
     * @param name Name of the desired battleground
     * @return Registered battleground.
     */
    public Battleground getBattleground(String name) {
        for (Battleground bg : this.registrar.values()) {
            if (bg.getName().equalsIgnoreCase(name)) {
                return bg;
            }
        }
        return null;
    }
}
