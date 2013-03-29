package com.division.battlegrounds.datasource;

import java.sql.Connection;

/**
 * The basis for all battlegrounds data types. This interface must be
 * implemented if you are making a custom data engine.
 *
 * @author Evan
 */
public interface DataInterface {

    public Connection getRawConnection();

    /**
     * Gets the player ID associated with this name.
     *
     * @param player_name Player whos id we are retrieving.
     * @return Integer
     */
    public int getPlayerId(String player_name);

    /**
     * Gets the amount of tokens the specified player has.
     *
     * @param player_name Player whos tokens we are getting.
     * @return Number of tokens
     */
    public int getTokens(String player_name);

    /**
     * Gets the amount of tokens the specified player ID has.
     *
     * @param player_id Id of the player whos tokens we are getting.
     * @return Number of tokens
     */
    public int getTokens(int player_id);

    /**
     * Adds a player to the database and assigns them a player id
     *
     * @param player_name Player to be added.
     */
    public void addPlayer(String player_name);

    /**
     * Adds tokens to the player's token balance.
     *
     * @param player_name Player who we are adding tokens to.
     * @param amount Amount of tokens we are adding.
     */
    public void addTokens(String player_name, int amount);

    /**
     * Adds tokens to the player's token balance.
     *
     * @param player_id ID of the player we are adding tokens to.
     * @param amount Amount of tokens we are adding.
     */
    public void addTokens(int player_id, int amount);

    /**
     * Subtracts tokens from the player's token balance.
     *
     * @param player_name Player who we are subtracting tokens from.
     * @param amount Amount of tokens we are subtracting.
     */
    public void subtractTokens(String player_name, int count);

    /**
     * Subtracts tokens from the player's token balance.
     *
     * @param player_id ID of the player we are subtracting tokens from.
     * @param amount Amount of tokens we are subtracting.
     */
    public void subtractTokens(int player_id, int count);
}
