package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class DbUtils {
        public static Connection getConnection() {
            Connection conn = null;
            try {
                //"jdbc:sqlserver://localhost;databaseName=EMPDB;user=sa;password=sa";
                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banhang", "root", "");
                conn.setAutoCommit(false);
            } catch (SQLException ex) {
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);

            }
            return conn;
        }

        public static void main(String[] args) {
            if (getConnection() != null) {
                System.out.println("Connect success!");
            } else {
                System.out.println("Connect fail!!!");
            }
        }

    }


