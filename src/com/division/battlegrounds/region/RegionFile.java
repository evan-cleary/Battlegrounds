package com.division.battlegrounds.region;

import com.division.battlegrounds.core.BattlegroundCore;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.BlockVector;

/**
 *
 * @author Evan
 */
public class RegionFile {

    private File configFile;
    private YamlConfiguration config = new YamlConfiguration();
    private boolean changed = false;
    private Region region;

    public RegionFile(BattlegroundCore instance, String battleground) {
        configFile = new File(instance.getDataFolder(), battleground + "/region.yml");
    }

    public RegionFile(File fileLoc) {
        configFile = fileLoc;
    }

    public void load() {
        try {
            config.load(configFile);
        } catch (Exception ex) {
        }
        if (!config.contains("region")) {
            config.set("region.world", "DEFAULT");
            config.set("region.p1.x", 0);
            config.set("region.p1.y", 0);
            config.set("region.p1.z", 0);
            config.set("region.p2.x", 0);
            config.set("region.p2.y", 0);
            config.set("region.p2.z", 0);
            changed = true;
        } else {
            this.region = registerRegion();
        }
        if (changed == true) {
            try {
                config.save(configFile);
                changed = false;
            } catch (Exception ex) {
            }
        }
    }

    public Region registerRegion() {
        World world = Bukkit.getWorld(config.getString("region.world", "world"));
        double p1x = config.getDouble("region.p1.x");
        double p1y = config.getDouble("region.p1.y");
        double p1z = config.getDouble("region.p1.z");
        double p2x = config.getDouble("region.p2.x");
        double p2y = config.getDouble("region.p2.y");
        double p2z = config.getDouble("region.p2.z");
        BlockVector p1 = new BlockVector(p1x, p1y, p1z);
        BlockVector p2 = new BlockVector(p2x, p2y, p2z);
        return new Region(world, p1, p2);
    }

    public Region getRegion() {
        return region;
    }
}
