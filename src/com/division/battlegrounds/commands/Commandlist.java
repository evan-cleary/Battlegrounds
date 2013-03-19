package com.division.battlegrounds.commands;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Evan
 */
public class Commandlist extends BattlegroundsCommand {

    String bgFormat = ChatColor.DARK_AQUA + "%s" + ChatColor.GOLD + " ---" + ChatColor.DARK_AQUA + " Queued: (" + ChatColor.RED + "%s" + ChatColor.DARK_AQUA + "/%s)(Min: " + ChatColor.RED + "%s" + ChatColor.DARK_AQUA + ")";

    @Override
    public void run(Player sender, String[] args) {
        sender.sendMessage(ChatColor.RED + "---------[" + ChatColor.GRAY + "Battlegrounds" + ChatColor.RED + "]---------");
        Map<Plugin, Battleground> battlegrounds = BattlegroundCore.getInstance().getRegistrar().getRegistrar();
        for (Battleground bg : battlegrounds.values()) {
            sender.sendMessage(String.format(bgFormat, bg.getName(), bg.getQueueSize(), bg.getMaxPlayers(), bg.getMinPlayers()));
        }
        sender.sendMessage(ChatColor.RED+"-------------------------------");
    }
}
