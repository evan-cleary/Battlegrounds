package com.division.battlegrounds.commands;

import com.division.battlegrounds.core.BattlegroundCore;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Evan
 */
public class Commandpos extends BattlegroundsCommand {

    /**
     *
     * @param sender
     * @param args
     */
    @Override
    public void run(Player sender, String[] args) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("copy")) {
                Location loc = sender.getLocation();
                Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable text = new StringSelection("X=" + loc.getBlockX() + "\nY=" + loc.getBlockY() + "\nZ=" + loc.getBlockZ());
                sysClip.setContents(text, null);
                BattlegroundCore.sendMessage(sender, "Your current location has been coppied to your server's clipboard.");
            } else {
                BattlegroundCore.sendMessage(sender, "Unknown arguement: " + args[1]);
            }
        } else {
            BattlegroundCore.sendMessage(sender, "Invalid number of arguements.");
            BattlegroundCore.sendMessage(sender, "Usage: /bg pos copy");
        }
    }
}
