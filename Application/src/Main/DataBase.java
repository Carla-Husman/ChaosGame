package Main;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataBase {
    public LocalDateTime time1 = LocalDateTime.now();
    public LocalDateTime time2;
    GamePanel gp;
    Connection c = null;
    Statement stmt = null;
    String sql;
    ResultSet rs;
    String DATE, START_TIME, END_TIME, GM_REASON;
    int POINTS, COINS, APPLES;
    boolean flag = true;

    public DataBase(GamePanel gp) {
        this.gp = gp;
    }

    public void openDataBase() {
        try {
            //conectare la baza de date
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:CHAOS.db");
            c.setAutoCommit(true);
            stmt = c.createStatement();
        } catch (Exception e) {
            System.out.println("Nu s-a deschid baza de date.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void writeInDataBase() {
        try {
            sql = "INSERT INTO CHAOS (DATE,START_TIME,END_TIME,POINTS,COINS,APPLES,GM_REASON) " +
                    "VALUES ('" + (DateTimeFormatter.ISO_LOCAL_DATE).format(time1) +
                    "','" + (DateTimeFormatter.ISO_LOCAL_TIME).format(time1) + "','"
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(time2) + "',"
                    + gp.player.points + ","
                    + gp.player.coin + ","
                    + gp.player.apple + ",'"
                    + gp.player.reason + "');";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Nu s-a putut adauga o informatie in baza de date.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void writeFromDataBase() {
        try {
            rs = stmt.executeQuery("SELECT * FROM CHAOS;");
            while (rs.next()) {
                DATE = rs.getString("DATE");
                START_TIME = rs.getString("START_TIME");
                END_TIME = rs.getString("END_TIME");
                POINTS = rs.getInt("POINTS");
                APPLES = rs.getInt("APPLES");
                COINS = rs.getInt("COINS");
                GM_REASON = rs.getString("GM_REASON");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDataBase() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
