package com.division.battlegrounds.config;

import com.division.battlegrounds.core.BattlegroundCore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Evan
 */
public class BattlegroundsConfig {

    private File configLoc;
    private YamlConfiguration config = new YamlConfiguration();
    private String mysqlUsername;
    private String mysqlHost;
    private String mysqlPassword;
    private String mysqlPort;
    private String mysqlDatabase;
    private String mysqlTablePrefix;
    private boolean usingTokens = false;

    public BattlegroundsConfig(BattlegroundCore instance) {
        this.configLoc = new File(instance.getDataFolder(), "config.yml");
        if (!configLoc.exists()) {
            try {
                configLoc.createNewFile();
                setup();
            } catch (IOException ex) {
                Logger.getLogger(BattlegroundsConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setup() {
        try {
            FileOutputStream fos = new FileOutputStream(configLoc);
            InputStream is = this.getClass().getResourceAsStream("/config.yml");
            byte[] bytes = new byte[4096];
            int totBytes;
            while ((totBytes = is.read(bytes, 0, bytes.length)) != 1) {
                fos.write(bytes, 0, totBytes);
            }
            is.close();
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(BattlegroundsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load() {
        try {
            config.load(configLoc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.mysqlDatabase = config.getString("settings.mysql.database");
        this.mysqlHost = config.getString("settings.mysql.host");
        this.mysqlPassword = config.getString("settings.mysql.password");
        this.mysqlUsername = config.getString("settings.mysql.username");
        this.mysqlPort = config.getString("settings.mysql.port");
        this.mysqlTablePrefix = config.getString("settings.mysql.tableprefix");
        this.usingTokens = config.getBoolean("settings.general.useTokens");
    }

    public String getMySQLDatabase() {
        return mysqlDatabase;
    }

    public String getMySQLPassword() {
        return mysqlPassword;
    }

    public String getMySQLUsername() {
        return mysqlUsername;
    }

    public String getMySQLHost() {
        return mysqlHost;
    }

    public String getMySQLPort() {
        return mysqlPort;
    }

    public String getTablePrefix() {
        return mysqlTablePrefix;
    }

    public boolean isUsingTokens() {
        return usingTokens;
    }
}
