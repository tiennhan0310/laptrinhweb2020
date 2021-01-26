package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Role;
import model.UserModel;
import utils.DbUtils;

public class UserDAO {

    public static void main(String[] args) {
        UserDAO us = new UserDAO();
        System.out.println(us.register("xcv", "123"));
    }

    public boolean register(String username, String password) {
        boolean bl = false;
        ArrayList<String> listUser = getUser();
        for (String user : listUser) {
            if (user.equalsIgnoreCase(username)) {
                bl = false;
                break;
            } else {
                Connection conn = DbUtils.getConnection();
                try {
                    String sqlCus = "insert into customer (customer_name) values ('')";
                    PreparedStatement pt = conn.prepareStatement(sqlCus, Statement.RETURN_GENERATED_KEYS);
                    pt.execute();
                    ResultSet rs = pt.getGeneratedKeys();
                    int idValue = 0;
                    if (rs.next()) {
                        // Giá trị của ID (Vị trí 1 theo thiết kế của bảng)
                        idValue = rs.getInt(1);
                    }
                    String sqlUs = "insert into user_db (user_name, user_password,role_id,customerId) values (?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sqlUs);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setInt(3, 2);
                    ps.setInt(4, idValue);
                    if (ps.executeUpdate() > 0) {
                        bl = true;
                        conn.commit();
                        break;
                    }
                } catch (SQLException ex) {
                    try {
                        conn.rollback();
                    } catch (SQLException e) {
                        bl = false;
                    }

                }
            }
        }

        return bl;
    }

    public UserModel login(String username, String password) {
        UserModel user = new UserModel();
        ArrayList<Role> roles = new ArrayList<>();
        Connection conn = DbUtils.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select * ");
        sql.append("from user_db as u ");
        sql.append("inner join role_db as r ");
        sql.append("on u.role_id = r.role_id inner join customer as t on t.idcustomer = u.customerId ");
        sql.append("  where user_name = ? and user_password = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                user.setUser_id(rss.getInt("user_id"));
                user.setUser_name(rss.getString("user_name"));
                Customer customer = new Customer();
                customer.setCustomerId(rss.getInt("idcustomer"));
                customer.setName(rss.getString("customer_name"));
                customer.setAddress(rss.getString("customer_address"));
                customer.setEmail(rss.getString("customer_email"));
                customer.setPhone(rss.getString("customer_phone"));
                user.setCustomer(customer);
                user.setUser_password(rss.getString("user_password"));
                Role role = new Role();
                role.setRole_name(rss.getString("role_name"));
                roles.add(role);
                user.setRoles(roles);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return user;

    }

    public boolean updateUser(Customer customer) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "update customer set customer_name = ? , customer_address = ? ,"
                    + " customer_phone = ? , customer_email = ? where idcustomer = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getEmail());
            ps.setInt(5, customer.getCustomerId());
            if (ps.executeUpdate() > 0) {
                result = true;
                conn.commit();
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return result;
    }

    private ArrayList<String> getUser() {
        Connection conn = null;
        ArrayList<String> listUser = new ArrayList<>();

        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("select user_name from user_db");
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                String result = rss.getString("user_name");
                listUser.add(result);
            }
        } catch (SQLException ex) {

        }
        return listUser;
    }

}
