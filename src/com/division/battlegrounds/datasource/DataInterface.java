package com.division.battlegrounds.datasource;

import java.sql.Connection;

/**
 *
 * @author Evan
 */
public interface DataInterface {

    public Connection getRawConnection();

    public int getPlayerId(String player_name);

    public int getTokens(String player_name);

    public int getTokens(int player_id);

    public void addPlayer(String player_name);

    public void addTokens(String player_name, int amount);

    public void addTokens(int player_id, int amount);

    public void subtractTokens(String player_name, int count);

    public void subtractTokens(int player_id, int count);
}
