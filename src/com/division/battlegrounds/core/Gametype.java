package com.division.battlegrounds.core;

/**
 *
 * @author Evan
 */
public class Gametype {

    private String scoringFormat;
    private int argCount;
    private String gametypeDescription = "Not Specified";

    public Gametype(String scoringFormat, int argCount) {
        this.scoringFormat = scoringFormat;
        this.argCount = argCount;
    }

    /**
     * Returns the number of arguments in the Scoring Format
     *
     * @return Number of arguments in Scoring Format
     */
    public int getArgCount() {
        return argCount;
    }

    /**
     * Returns the gametype's description.
     *
     * @return This gametypes description.
     */
    public String getDescription() {
        return gametypeDescription;
    }

    /**
     * Returns the scoring format specified by the Gamemode
     *
     * @return Scoring format specified by the Gamemode
     */
    public String getScoringFormat() {
        return scoringFormat;
    }

    /**
     * Sets the number of arguements expected by the format
     *
     * @param count Number of arguements in Scoring Format
     */
    public void setArgCount(int count) {
        argCount = count;
    }

    /**
     * Sets the gametypes description
     *
     * @param description Gametype Description
     */
    public void setDescription(String description) {
        gametypeDescription = description;
    }

    /**
     * Sets the scoring format for this gametype
     *
     * @param format Scoring format
     */
    public void setScoringFormat(String format) {
        scoringFormat = format;
    }

    /**
     * Returns the formatted scoring format based on the data given to the
     * method.
     *
     * @param args Data to be formatted with the scoring format.
     * @return Formatted scoring format.
     * @throws IllegalArgumentException Thrown when the number of arguments does
     * not match the number of arguments in the scoring format.
     */
    public String format(Object[] args) throws IllegalArgumentException {
        if (args.length != argCount) {
            throw new IllegalArgumentException("Expected " + argCount + " arguments. Found " + args.length + ".");
        }
        return String.format(scoringFormat, args);
    }
}
