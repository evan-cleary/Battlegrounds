package com.division.battlegrounds.config;

import com.division.battlegrounds.core.BattlegroundCore;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Evan
 */
public abstract class BattlegroundConfig {

    protected File configFile;
    private String battleground;
    protected YamlConfiguration config = new YamlConfiguration();

    public BattlegroundConfig(BattlegroundCore anc, String battleground) {
        configFile = new File(anc.getDataFolder(), battleground + "/config.yml");
        this.battleground = battleground;
    }

    public abstract void load();
}
