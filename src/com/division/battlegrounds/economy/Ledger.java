package com.division.battlegrounds.economy;

import com.division.battlegrounds.datasource.DataInterface;

/**
 *
 * @author Evan
 */
public class Ledger {

    private DataInterface accounts;

    public Ledger(DataInterface accounts) {
        this.accounts = accounts;
    }

    /**
     * Sets the data engine used for this ledger.
     *
     * @param accounts DataInterface to be used.
     */
    public void setDataInterface(DataInterface accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns whether the specified player can afford the cost.
     *
     * @param player_name Player's balance we will be looking at
     * @param cost Cost of the thing in question
     * @return If the player can afford the cost
     */
    public boolean canAfford(String player_name, int cost) {
        return accounts.getTokens(player_name) >= cost;
    }

    /**
     * Returns the Token Balance of the player.
     *
     * @param player_name Player who's balance we are getting.
     * @return The number of tokens this player has.
     */
    public int getBalance(String player_name) {
        return accounts.getTokens(player_name);
    }

    /**
     * Adds to the player's balance then returns the new balance.
     *
     * @param player_name Player who's balance we are adding to.
     * @param amount Number of tokens to be added.
     * @return The new balance.
     */
    public int depositTokens(String player_name, int amount) {
        accounts.addTokens(player_name, amount);
        return accounts.getTokens(player_name);
    }

    /**
     * Subtracts from the player's balance then returns the new balance.
     *
     * @param player_name Player who's balance we are subtracting from.
     * @param amount Number of tokens to be subtracted.
     * @return The new balance.
     */
    public int withdrawTokens(String player_name, int amount) {
        accounts.subtractTokens(player_name, amount);
        return accounts.getTokens(player_name);
    }
}
