package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhoneDAO;
import model.PhoneModel;

/**
 * Servlet implementation class AdminListRemainingProductsController
 */
@WebServlet("/admin-san-pham-con-lai")
public class AdminListRemainingProductsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListRemainingProductsController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PhoneDAO phDAO = new PhoneDAO();
        ArrayList<PhoneModel> listPhone = phDAO.getPhoneBySoLuong();
        request.setAttribute("listPhone", listPhone);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/sanphamconlai.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
