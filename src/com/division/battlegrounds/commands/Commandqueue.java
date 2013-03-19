package com.division.battlegrounds.commands;

import com.division.battlegrounds.core.Battleground;
import com.division.battlegrounds.core.BattlegroundCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Evan
 */
public class Commandqueue extends BattlegroundsCommand {

    /**
     *
     * @param sender
     * @param args
     */
    @Override
    public void run(Player sender, String[] args) {
        if (args.length == 2) {
            String queueCommand = args[1];
            if (queueCommand.equalsIgnoreCase("leave")) {
                Battleground bg = BattlegroundCore.getInstance().bgqm.getQueuedBG(sender);
                if (bg != null) {
                    bg.removeFromQueue(sender);
                } else {
                    sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " You are not in a queue.");
                }
                return;
            } else {
                if (!BattlegroundCore.getInstance().bgqm.isInQueue(sender)) {
                    Battleground bg = BattlegroundCore.getInstance().getRegistrar().getBattleground(args[1]);
                    if (bg != null) {
                        bg.addToQueue(sender);
                    } else {
                        sender.sendMessage(String.format(BattlegroundCore.logFormat, "Unknown battleground: " + args[1]));
                    }
                    return;
                } else {
                    sender.sendMessage(String.format(BattlegroundCore.logFormat, "You are already in a queue."));
                    return;
                }
            }
        }
        if (args.length == 3) {
            String queueCommand = args[1];
            String battleground = args[2];
            if (queueCommand.startsWith("j")) {
                if (!BattlegroundCore.getInstance().bgqm.isInQueue(sender)) {
                    Battleground BG = BattlegroundCore.getInstance().getRegistrar().getBattleground(battleground);
                    if (BG != null) {
                        BG.addToQueue(sender);
                    } else {
                        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " Unknown battleground: " + battleground);
                    }
                } else {
                    sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " You are already in a queue.");
                }
                return;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid number of arguements.");
            return;
        }
        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " Unkown sub command...");
    }
}
