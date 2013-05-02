package com.division.battlegrounds.core;

import com.division.battlegrounds.commands.BattlegroundsCommand;
import com.division.battlegrounds.config.BattlegroundsConfig;
import com.division.battlegrounds.datasource.DataInterface;
import com.division.battlegrounds.datasource.MySQLConnector;
import com.division.battlegrounds.economy.Ledger;
import com.division.battlegrounds.listeners.PlayerListener;
import com.division.battlegrounds.mech.BattlegroundQueueManager;
import com.division.battlegrounds.mech.BattlegroundRegistrar;
import com.division.battlegrounds.region.RegionManager;
import com.division.battlegrounds.scoreboard.ScoreboardManager;
import java.sql.SQLException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core class that initializes the Battlegrounds environment.
 *
 *
 * @author Evan
 */
public class BattlegroundCore extends JavaPlugin {

    private BattlegroundRegistrar bgr;
    public ConsoleCommandSender console;
    public static String logFormat = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " %s";
    private static BattlegroundCore instance;
    public BattlegroundQueueManager bgqm;
    private ScoreboardManager sbManager;
    private RegionManager rgManager;
    private BattlegroundsConfig config;
    private DataInterface dataInterface;
    private Ledger ledger;

    @Override
    public void onEnable() {
        console = this.getServer().getConsoleSender();
        instance = this;
        this.config = new BattlegroundsConfig(this);
        config.load();
        console.sendMessage(ChatColor.YELLOW + "=====================================");
        console.sendMessage(String.format(logFormat, "Starting main battlegrounds protocols."));
        console.sendMessage(ChatColor.YELLOW + "=====================================");
        this.bgr = new BattlegroundRegistrar(this);
        console.sendMessage(String.format(logFormat, "Registrar listening for Battleground servers."));
        bgqm = new BattlegroundQueueManager(bgr);
        this.getServer().getPluginManager().registerEvents(bgqm, this);
        console.sendMessage(String.format(logFormat, "Queue Manager awaiting players."));
        this.rgManager = new RegionManager(this);
        console.sendMessage(String.format(logFormat, "RegionManager is re-building barriers."));
        this.sbManager = new ScoreboardManager();
        console.sendMessage(String.format(logFormat, "ScoreboardManager has been started."));

        if (config.isUsingTokens()) {
            console.sendMessage(String.format(logFormat, "Attempting to access battleground dataservers..."));
            try {
                dataInterface = new MySQLConnector(this);
                this.ledger = new Ledger(dataInterface);
                console.sendMessage(String.format(logFormat, "Successfully connected to battleground dataserver!"));
            } catch (ClassNotFoundException ex) {
                console.sendMessage(String.format(logFormat, "Error while loading database drivers: "));
                console.sendMessage(String.format(logFormat, ex.getMessage()));
            } catch (SQLException ex) {
                console.sendMessage(String.format(logFormat, "Error while connecting to dataserver: "));
                console.sendMessage(String.format(logFormat, ex.getMessage()));
            }
        }
        PlayerListener pl = new PlayerListener(this);
        console.sendMessage(ChatColor.YELLOW + "=====================================");
        console.sendMessage(String.format(logFormat, "Battlegrounds actively scanning for combatants."));
        console.sendMessage(ChatColor.YELLOW + "=====================================");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (sender == null) {
            return true;
        }
        if (args.length >= 1) {
            try {
                if (!sender.hasPermission("battlegrounds." + args[0].toLowerCase())) {
                    sender.sendMessage(String.format(logFormat, "You do not have the required permissions."));
                    return true;
                }
                BattlegroundsCommand bgCmd;
                String clazzpath = "com.division.battlegrounds.commands.";
                bgCmd = (BattlegroundsCommand) BattlegroundCore.class.getClassLoader().loadClass(clazzpath + "Command" + args[0].toLowerCase()).newInstance();
                bgCmd.run(player, args);
            } catch (ClassNotFoundException ex) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " Unknown sub command.");
                return true;
            } catch (Exception ex) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Battlegrounds" + ChatColor.GRAY + "]" + ChatColor.RED + " A(n) " + ex.getClass().getName() + " has occured. Contact an admin.");
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the plugin's main instance.
     *
     * @return BattlegroundCore singleton
     */
    public static BattlegroundCore getInstance() {
        return instance;
    }

    /**
     * Returns the battleground registrar used in this instance
     *
     * @return BattlegroundRegistrar used in this instance
     */
    public BattlegroundRegistrar getRegistrar() {
        return bgr;
    }

    /**
     * Returns the region manager used in this instance
     *
     * @return RegionManager used in this instance
     */
    public RegionManager getRegionManager() {
        return rgManager;
    }

    /**
     * Returns the main config used in this instance
     *
     * @return BattlegroundConfig used in this instance
     */
    public BattlegroundsConfig getBCConfig() {
        return config;
    }

    /**
     * Returns the data engine used in this instance.
     *
     * @return DataInterface used in this instance.
     */
    public DataInterface getDataInterface() {
        return dataInterface;
    }

    /**
     * Returns the Ledger used in this instance
     *
     * @return Ledger used in this instance
     */
    public Ledger getLedger() {
        return ledger;
    }

    /**
     * Sets the data engine used in this instance.
     *
     * @param dataInterface New DataInterface to be used in this instance.
     */
    public void setDataInterface(DataInterface dataInterface) {
        this.dataInterface = dataInterface;
        if (getLedger() != null) {
            getLedger().setDataInterface(dataInterface);
        }
    }

    /**
     * Gets the ScoreboardManager used in this instance
     *
     * @return ScoreboardManager used in this instance
     */
    public ScoreboardManager getScoreboardManager() {
        return sbManager;
    }
}
