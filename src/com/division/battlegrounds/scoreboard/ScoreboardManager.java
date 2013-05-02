package com.division.battlegrounds.scoreboard;

import com.division.battlegrounds.core.Battleground;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Manager to handle a battleground to scoreboard relationship. This manager
 * also allows other players to watch a specific battleground's scoreboard even
 * if they are not in the battleground.
 *
 * @author Evan
 */
public class ScoreboardManager {

    private Map<Battleground, Scoreboard> scoreboards = new HashMap<Battleground, Scoreboard>();

    /**
     * Creates a new Scoreboard instance for use by the given battleground
     *
     * @param bg The Battleground the new Scoreboard is bound to
     * @return the new Scoreboard
     */
    public Scoreboard registerNewScoreboard(Battleground bg) {
        Scoreboard sb = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        scoreboards.put(bg, sb);
        return sb;
    }

    /**
     * Gets the Scoreboard bound to the given Battleground. If no Scoreboard is
     * bound we bind a new one.
     *
     * @param bg The Battleground whose Scoreboard we are getting.
     * @return The Scoreboard bound to the given Battleground.
     */
    public Scoreboard getScoreboard(Battleground bg) {
        if (scoreboards.containsKey(bg)) {
            return scoreboards.get(bg);
        }
        return registerNewScoreboard(bg);
    }

    /**
     * Hacky way to force a client side update on the given Scoreboard.
     *
     * @param board Scoreboard we are updating.
     */
    public void forceScoreboardUpdate(Scoreboard board) {
        //Trick it into refreshing.
        board.registerNewObjective("RFRSH", "NONE");
        board.getObjective("RFRSH").unregister();
    }
}
