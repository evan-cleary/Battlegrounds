package com.division.battlegrounds.datasource;

import com.division.battlegrounds.core.BattlegroundCore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MySQL data engine. This engine is the default for battlegrounds, but may be
 * overriden if desired.
 *
 * @author Evan
 */
public class MainSQL implements DataInterface {

    private BattlegroundCore bga;
    protected Connection connection;
    private String prefix;

    public MainSQL(BattlegroundCore instance) {
        this.bga = instance;
        this.prefix = bga.getBCConfig().getTablePrefix();
    }

    @Override
    public Connection getRawConnection() {
        return connection;
    }

    @Override
    public int getPlayerId(String player_name) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement("SELECT id FROM `" + prefix + "players` WHERE player_name=?");
            pst.setString(1, player_name);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") != 0) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;
    }

    @Override
    public int getTokens(String player_name) {
        return getTokens(getPlayerId(player_name));
    }

    @Override
    public int getTokens(int player_id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement("SELECT tokens FROM `" + prefix + "tokens` WHERE id=?");
            pst.setInt(1, player_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt("tokens");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;
    }

    @Override
    public void addPlayer(String player_name) {
        if (getPlayerId(player_name) != 0) {
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("INSERT INTO `" + prefix + "players`(player_name) VALUES (?)");
            pst.setString(1, player_name);
            pst.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void addTokens(String player_name, int amount) {
        addTokens(getPlayerId(player_name), amount);
    }

    @Override
    public void addTokens(int player_id, int amount) {
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("UPDATE `" + prefix + "tokens SET tokens=? WHERE id=?");
            pst.setInt(1, getTokens(player_id) + amount);
            pst.setInt(2, player_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void subtractTokens(String player_name, int count) {
        subtractTokens(getPlayerId(player_name), count);
    }

    @Override
    public void subtractTokens(int player_id, int count) {
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("UPDATE `" + prefix + "tokens` SET tokens=? WHERE id=?");
            pst.setInt(1, getTokens(player_id) - count);
            pst.setInt(2, player_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainSQL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
