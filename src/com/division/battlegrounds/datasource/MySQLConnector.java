package com.division.battlegrounds.datasource;

import com.division.battlegrounds.config.BattlegroundsConfig;
import com.division.battlegrounds.core.BattlegroundCore;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Evan
 */
public class MySQLConnector extends MainSQL {

    private BattlegroundCore bga;
    private BattlegroundsConfig BC;

    public MySQLConnector(BattlegroundCore instance) throws ClassNotFoundException, SQLException {
        super(instance);
        this.bga = instance;
        this.BC = bga.getBCConfig();
        getConnection();
    }

    final protected synchronized void getConnection()
            throws ClassNotFoundException, SQLException {

        String host = BC.getMySQLHost();
        String username = BC.getMySQLUsername();
        String password = BC.getMySQLPassword();
        String databaseName = BC.getMySQLDatabase();
        String port = BC.getMySQLPort();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port
                + "/" + databaseName, username, password);
        setup();
    }

    final protected synchronized void setup() throws SQLException {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS `" + BC.getTablePrefix() + "players` ("
                    + "`id` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`player_name` varchar(255) NOT NULL,"
                    + "PRIMARY KEY (`id`),"
                    + "UNIQUE KEY `Name` (`player_name`) USING BTREE"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS `" + BC.getTablePrefix() + "tokens` ("
                    + "`id` int(11) NOT NULL,"
                    + "`tokens` int(11) DEFAULT '0',"
                    + "PRIMARY KEY (`id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
}
