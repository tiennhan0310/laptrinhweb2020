package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Customer;
import model.UserModel;

/**
 * Servlet implementation class SettingInformationController
 */
@WebServlet(urlPatterns = { "/sua-thong-tin" })
public class SettingInformationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingInformationController() {
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
        RequestDispatcher rd = request.getRequestDispatcher("user/chinhsuathongtin.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String error = "";
        if (validateName(name) && checkPhone(phone) && address != null && name != null) {
            HttpSession session = request.getSession();
            UserModel user = (UserModel) session.getAttribute("user");
            Customer customer = user.getCustomer();
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setName(name);
            user.setCustomer(customer);
            UserDAO usDAO = new UserDAO();
            if (usDAO.updateUser(customer)) {
                session.setAttribute("user", user);
                error = "Cap nhat thanh cong";
                error(request, response, error);
            } else {
                error = "Loi nhap lieu";
                error(request, response, error);

            }
        } else {
            error = "Loi nhap lieu";
            error(request, response, error);
        }

    }

    private void error(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        RequestDispatcher rd = request.getRequestDispatcher("user/chinhsuathongtin.jsp");
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
