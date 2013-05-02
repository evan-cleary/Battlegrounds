package com.division.battlegrounds.commands;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Command to allow players not within a battleground to watch the scoreboard of
 * an active battleground.
 *
 * @author Evan
 */
public class Commandwatch extends BattlegroundsCommand {

    @Override
    public void run(Player sender, String[] args) {
        if (args.length == 2) {
            String bgName = args[1];
            Battleground bg = BattlegroundCore.getInstance().getRegistrar().getBattleground(bgName);
            if (bg == null) {
                BattlegroundCore.sendMessage(sender, "Unable to find battleground: " + bgName);
                return;
            }
            if (!bg.isActive()) {
                BattlegroundCore.sendMessage(sender, "That battleground is not currently active.");
                return;
            }
            Scoreboard scoreboard = BattlegroundCore.getInstance().getScoreboardManager().getScoreboard(bg);
            sender.setScoreboard(scoreboard);
            BattlegroundCore.sendMessage(sender, "You are now watching the " + bgName + " scoreboard.");
        }
    }
}
