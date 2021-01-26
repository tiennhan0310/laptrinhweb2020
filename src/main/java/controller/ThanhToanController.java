package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PhoneDAO;
import model.BookPhone;
import model.Customer;
import model.PhoneModel;
import model.UserModel;

/**
 * Servlet implementation class ThanhToanController
 */
@WebServlet(urlPatterns = "/thanh-toan")
public class ThanhToanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanhToanController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher rd = request.getRequestDispatcher("user/thanhtoan.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String error = "";
        if (checkPhone(phone) && validateName(name) && !address.isEmpty()) {
            HttpSession session = request.getSession();
            ArrayList<PhoneModel> listPhone = (ArrayList<PhoneModel>) session.getAttribute("listPhone");
            UserModel user = (UserModel) session.getAttribute("user");
            Customer customer = user.getCustomer();
            long millis = System.currentTimeMillis();

            java.sql.Date dateCreate = new java.sql.Date(millis);
            BookPhone pay = new BookPhone(customer, listPhone, false, dateCreate,address,phone,0);
            PhoneDAO phDAO = new PhoneDAO();
            if (listPhone == null) {
                error = "Khong ton tai san pham";
                error(request, response, error);
            } else if (listPhone != null) {
                if (phDAO.thanhToan(pay)) {
                    response.sendRedirect(request.getContextPath() + "/trang-chu");
                    session.setAttribute("user", user);
                    session.removeAttribute("listPhone");
                } else {
                    error = "Thanh toan that bai";
                    error(request, response, error);
                }
            }
        } else {
            error = "Vui long nhap lai thong tin";
            error(request, response, error);
        }
    }

    private void error(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        RequestDispatcher rd = request.getRequestDispatcher("user/thanhtoan.jsp");
        rd.forward(request, response);
    }

    private boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            return false;
        } else if (phone.length() == 10) {
            if (phone.substring(0, 1).equals("0")) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    private boolean validateName(String name) {
        // p{L}là thuộc tính ký tự Unicode phù hợp với bất kỳ loại chữ nào từ bất kỳ
        // ngôn ngữ nào
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();

    }

}
