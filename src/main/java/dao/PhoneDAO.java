package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BookPhone;
import model.Customer;
import model.Kho;
import model.PhoneModel;
import utils.DbUtils;

public class PhoneDAO {

    public ArrayList<PhoneModel> getAll() {
        ArrayList<PhoneModel> listPhone = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "select * from phone";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setDescription(rss.getString("des"));
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
                listPhone.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPhone;
    }
    public static void main(String[] args) {
        PhoneDAO p = new PhoneDAO();
        System.out.println(p.getAll());
    }

    public PhoneModel getPhoneById(int id) {
        PhoneModel phone = new PhoneModel();
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "select * from phone where idPhone= ?";
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id);
            ResultSet rss = ps.executeQuery();
            if (rss.next()) {
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setDescription(rss.getString("des"));
                int luotTruyCap = rss.getInt("luotTruyCap") + 1;
                rss.updateInt("luotTruyCap", luotTruyCap);
                rss.updateRow();
                conn.commit();
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }
        return phone;
    }

    public ArrayList<PhoneModel> getAll(String nhaSanXuat) {
        ArrayList<PhoneModel> listPhone = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "select * from phone where nhaSanXuat= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nhaSanXuat);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setDescription(rss.getString("des"));
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
                listPhone.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPhone;
    }

    public boolean savePhone(PhoneModel phone) {
        boolean bl = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into phone(phoneName,typePhone,price,nhaSanXuat,img_url,ngaySanXuat,des,soLuong) value (?,?,?,?,?,?,?,?) ");
            ps.setString(1, phone.getName());
            ps.setString(2, phone.getTypeTel());
            ps.setDouble(3, phone.getPrice());
            ps.setString(4, phone.getNhaSanXuat());
            ps.setString(5, phone.getUrl_img());
            ps.setDate(6, phone.getNgaySanXuat());
            ps.setString(7, phone.getDescription());
            ps.setInt(8, phone.getSoLuong());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                bl = true;
                conn.commit();
            }

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

                bl = false;
            }
        }
        return bl;
    }

    public boolean deletePhone(PhoneModel phone) {
        boolean bl = false;
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from phone where id = ?");
            ps.setInt(1, phone.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                bl = true;
                conn.commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bl;
    }

    public boolean updatePhone(PhoneModel phone) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("update phone set typePhone = ?, "
                    + "nhaSanXuat = ?, price = ?,des= ?, ngaySanXuat = ?, soLuong = ?, img_url = ? where idPhone = ?");
            ps.setString(1, phone.getTypeTel());
            ps.setString(2, phone.getNhaSanXuat());
            ps.setDouble(3, phone.getPrice());
            ps.setString(4, phone.getDescription());
            ps.setDate(5, phone.getNgaySanXuat());
            ps.setInt(6, phone.getSoLuong());
            ps.setString(7, phone.getUrl_img());
            ps.setInt(8, phone.getId());

            int kq = ps.executeUpdate();
            if (kq > 0) {
                result = true;
                conn.commit();

            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                result = false;
            }
        }

        return result;
    }

    public ArrayList<PhoneModel> sortByPrice(int limit) {
        ArrayList<PhoneModel> list = new ArrayList<>();
        String sql = "SELECT  DISTINCT * FROM phone ORDER BY price desc LIMIT ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setPrice(rss.getDouble("price"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setSoLuong(rss.getInt("soLuong"));
                list.add(phone);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<PhoneModel> sortByNgaySanXuat(int limit) {
        ArrayList<PhoneModel> list = new ArrayList<>();
        String sql = "SELECT  DISTINCT * FROM phone ORDER BY ngaySanXuat desc LIMIT ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setPrice(rss.getDouble("price"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setSoLuong(rss.getInt("soLuong"));
                list.add(phone);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;

    }

    public int getCountPhone() {
        int total = 0;
        String sql = "select count(*) as total from phone";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rss = ps.executeQuery();
            if (rss.next()) {
                total = rss.getInt("total");
            }
        } catch (SQLException e) {

        }
        return total;
    }

    public ArrayList<PhoneModel> sortByView(int limit) {
        ArrayList<PhoneModel> list = new ArrayList<>();
        String sql = "SELECT  DISTINCT * FROM phone ORDER BY luotTruyCap desc LIMIT ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setPrice(rss.getDouble("price"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
                list.add(phone);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<PhoneModel> search(String text) {
        ArrayList<PhoneModel> list = new ArrayList<>();
        String sql = "SELECT * FROM phone WHERE phoneName LIKE ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setPrice(rss.getDouble("price"));
                phone.setUrl_img(rss.getString("img_url"));
//				phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
                list.add(phone);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;

    }

    /**
     *
     * @param limit:  quantity of post list
     * @param offset: starting index
     * @return
     */
    public ArrayList<PhoneModel> listPhone(int limit, int offset) {
        ArrayList<PhoneModel> list = new ArrayList<>();
        String sql = "SELECT * FROM phone LIMIT ? , ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setPrice(rss.getDouble("price"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setDescription(rss.getString("des"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setSoLuong(rss.getInt("soLuong"));
                list.add(phone);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;

    }

    public boolean thanhToan(BookPhone pay) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into thanhtoan(customerId,phone_id,trang_thai,date_create,address,phone) value (?,?,0,?,?,?)");
            ps.setInt(1, pay.getCustomer().getCustomerId());
            ps.setDate(3, (java.sql.Date) pay.getDateCreate());
            ps.setString(4, pay.getAddress());
            ps.setString(5, pay.getPhone());
            for (PhoneModel phone : pay.getListPhone()) {
                ps.setInt(2, phone.getId());
                int kq = ps.executeUpdate();
                if (kq > 0) {
                    result = true;
                    conn.commit();
                }
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

    public boolean confirm(int id) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("update thanhtoan set trang_thai = 1 where idThanhToan = ?");
            ps.setInt(1, id);
            int kq = ps.executeUpdate();
            if (kq > 0) {
                int phoneId = getPhoneId(id);
                if (soLuong(phoneId)) {
                    result = true;
                    conn.commit();
                }
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                result = false;
            }
        }

        return result;
    }


    public ArrayList<BookPhone> getListBookPhone(int trangThai) {
        ArrayList<BookPhone> lisBookPhones = new ArrayList<>();
        ArrayList<PhoneModel> listPhone = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from thanhtoan as t inner join customer as c on c.idcustomer = t.customerId"
                            + " inner join phone as p on t.phone_id = p.idPhone " + " where trang_thai = ?");
            ps.setInt(1, trangThai);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                BookPhone bookPhone = new BookPhone();
                Customer customer = new Customer();
                customer.setCustomerId(rss.getInt("idcustomer"));
                customer.setName(rss.getString("customer_name"));
                customer.setAddress(rss.getString("customer_address"));
                customer.setEmail(rss.getString("customer_email"));
                customer.setPhone(rss.getString("customer_phone"));
                bookPhone.setCustomer(customer);
                bookPhone.setDateCreate(rss.getDate("date_create"));
                bookPhone.setAddress(rss.getString("address"));
                bookPhone.setPhone(rss.getString("phone"));
                bookPhone.setId(rss.getInt("idThanhToan"));
                bookPhone.setStatus(rss.getBoolean("trang_thai"));
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setDescription(rss.getString("des"));
                phone.setLuotTruyCap(rss.getInt("luotTruyCap"));
                phone.setSoLuong(rss.getInt("soLuong"));
                listPhone.add(phone);
                bookPhone.setListPhone(listPhone);
                lisBookPhones.add(bookPhone);
            }
        } catch (SQLException ex) {

        }
        return lisBookPhones;
    }

    public boolean deleteBookPhone(int id) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM thanhtoan WHERE idThanhToan = ?");
            ps.setInt(1, id);
            int kq = ps.executeUpdate();
            if (kq > 0) {
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

    private int getPhoneId(int id) {
        int phoneId = 0;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("select phone_id from thanhtoan where idThanhToan = ?");
            ps.setInt(1, id);
            ResultSet rss = ps.executeQuery();
            if (rss.next()) {
                phoneId = rss.getInt("phone_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return phoneId;
    }

    private boolean soLuong(int id) {
        boolean bl = false;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "select * from phone where idPhone= ?";
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id);
            ResultSet rss = ps.executeQuery();
            if (rss.next()) {
                int soLuong = (rss.getInt("soLuong") - 1);
                rss.updateInt("soLuong", soLuong);
                rss.updateRow();
                conn.commit();
                bl = true;
            }
        } catch (SQLException e) {
            try {
                bl = false;
                conn.rollback();

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }
        return bl;
    }



    public ArrayList<Kho> getKho() {
        ArrayList<Kho> listKho = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "select * , count(*) as daBan"
                    + " from phone p inner join thanhtoan t on t.phone_id = p.idPhone  where t.trang_thai = 1   group by phone_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setDescription(rss.getString("des"));
                phone.setSoLuong(rss.getInt("soLuong"));
                Kho kho = new Kho();
                kho.setSoLuongDaBan(rss.getInt("daBan"));
                kho.setPhone(phone);
                listKho.add(kho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKho;
    }

    public ArrayList<PhoneModel> getPhoneBySoLuong() {
        ArrayList<PhoneModel> listPhone = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "select * from phone where soLuong > 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                PhoneModel phone = new PhoneModel();
                phone.setId(rss.getInt("idPhone"));
                phone.setName(rss.getString("phoneName"));
                phone.setTypeTel(rss.getString("typePhone"));
                phone.setPrice(rss.getDouble("price"));
                phone.setNhaSanXuat(rss.getString("nhaSanXuat"));
                phone.setUrl_img(rss.getString("img_url"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                phone.setDescription(rss.getString("des"));
                phone.setSoLuong(rss.getInt("soLuong"));
                phone.setNgaySanXuat(rss.getDate("ngaySanXuat"));
                listPhone.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPhone;
    }
}