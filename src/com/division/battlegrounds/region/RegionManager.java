package com.division.battlegrounds.region;

import com.division.battlegrounds.core.BattlegroundCore;
import java.io.File;

/**
 *
 * @author Evan
 */
public class RegionManager {

    private BattlegroundCore bga;

    public RegionManager(BattlegroundCore instance) {
        this.bga = instance;
    }

    public RegionFile loadRegionFile(String battleground) {
        File regionLoc = new File(bga.getDataFolder(), battleground + "/region.yml");
        if (!regionLoc.exists()) {
            RegionFile crRegion = new RegionFile(bga, battleground);
            crRegion.load();
            return crRegion;
        } else {
            return new RegionFile(regionLoc);
        }
    }
}
