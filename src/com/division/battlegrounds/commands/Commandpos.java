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
public class Commandpos extends BattlegroundsCommand{

    /**
     * 
     * @param sender
     * @param args 
     */
    @Override
    public void run(Player sender, String[] args) {
        if(args.length == 2){
            if(args[1].equalsIgnoreCase("copy")){
                Location loc = sender.getLocation();
                Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable text = new StringSelection("X="+loc.getBlockX()+"\nY="+loc.getBlockY()+"\nZ="+loc.getBlockZ());
                sysClip.setContents(text, null);
                sender.sendMessage(String.format(BattlegroundCore.logFormat, "Your current location has been coppied to your server's clipboard."));
            } else{
                sender.sendMessage(String.format(BattlegroundCore.logFormat, "Unknown arguement: "+args[1]));
            }
        } else{
            sender.sendMessage(String.format(BattlegroundCore.logFormat, "Invalid number of arguements."));
            sender.sendMessage(String.format(BattlegroundCore.logFormat, "Usage: /bg pos copy"));
        }
    }

}
