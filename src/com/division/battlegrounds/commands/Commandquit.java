package com.division.battlegrounds.commands;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Evan
 */
public class Commandquit extends BattlegroundsCommand {

    @Override
    public void run(Player sender, String[] args) {
        Map<Plugin, Battleground> battlegrounds = BattlegroundCore.getInstance().getRegistrar().getRegistrar();
        for (Battleground bg : battlegrounds.values()) {
            if (bg.isPlayerInBattleground(sender)) {
                bg.removePlayerFromBattleground(sender);
                return;
            }
        }
        BattlegroundCore.sendMessage(sender, "You are not in a battleground.");
    }
}