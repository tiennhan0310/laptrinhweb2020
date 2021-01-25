package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.PhoneDAO;
import model.PhoneModel;

/**
 * Servlet implementation class AdminListPhoneController
 */
@WebServlet("/admin-quan-ly-dien-thoai")
public class AdminListPhoneController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "D:\\LTW\\WEB\\LTW\\WebContent\\img";
    private static final int LIMIT = 10;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListPhoneController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // TODO Auto-generated method stub
        String edit = request.getParameter("edit");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");
        PhoneDAO phDAO = new PhoneDAO();
        if (edit == null && delete == null && add == null) {
            String offset = "";
            offset = request.getParameter("page");
            if (offset == null) {
                offset = "0";
            }

            int count = phDAO.getCountPhone();
            int countpage = count / 10;
            if (count % 10 != 0) {
                countpage++;
            }
            request.setAttribute("countpage", countpage);
            ArrayList<PhoneModel> listPhone = phDAO.listPhone(LIMIT, Integer.parseInt(offset) * 10);
            request.setAttribute("listPhone", listPhone);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/quanlydienthoai.jsp");
            rd.forward(request, response);
        } else if (delete != null && edit == null && add == null) {
            if (phDAO.deletePhone(phDAO.getPhoneById(Integer.parseInt(delete)))) {
                response.sendRedirect(request.getContextPath() + "/admin-quan-ly-dien-thoai");
            }
        } else if (delete == null && edit != null && add == null) {
            PhoneModel phone = phDAO.getPhoneById(Integer.parseInt(edit));
            request.setAttribute("phone", phone);
            RequestDispatcher rd = request.getRequestDispatcher("admin/add.jsp");
            rd.forward(request, response);
        } else if (delete == null && edit == null && add != null) {
            RequestDispatcher rd = request.getRequestDispatcher("admin/add.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PhoneDAO phDAO = new PhoneDAO();
        FileItemFactory factory = new DiskFileItemFactory();
        // Set factory constraints
        // factory.setSizeThreshold(yourMaxMemorySize);
        // factory.setRepository(yourTempDirectory);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // upload.setSizeMax(yourMaxRequestSize);
        // Parse the request
        List<FileItem> uploadItems = null;
        String name = "";
        String typePhone = "";
        String nhaSanXuat = "";
        String price = "";
        String ngaySanXuat = "";
        String soLuong = "";
        String img = "";
        String des = "";
        String error = "";
        String id = "";
        try {
            HashMap<String, String> data = new HashMap<String, String>();
            uploadItems = upload.parseRequest(request);
            for (FileItem uploadItem : uploadItems) {
                if (uploadItem.isFormField()) {
                    String fieldName = uploadItem.getFieldName();
                    String value = uploadItem.getString("UTF-8");
                    data.put(fieldName, value);

                } else {
                    String fieldName = uploadItem.getFieldName();
                    String fileName = new File(uploadItem.getName()).getName();
                    String filePath = DATA_DIRECTORY + File.separator + fileName;
                    String path = "img/" + fileName;
                    File uploadedFile = new File(filePath);
                    data.put(fieldName, path);
                    // saves the file to upload director

                    uploadItem.write(uploadedFile);

                }
            }

            for (String fieldName : data.keySet()) {
                switch (fieldName) {
                    case "name":
                        name = data.get(fieldName);
                        break;
                    case "typePhone":
                        typePhone = data.get(fieldName);
                        break;
                    case "nhaSanXuat":
                        nhaSanXuat = data.get(fieldName);
                        break;
                    case "price":
                        price = data.get(fieldName);
                        break;
                    case "des":
                        des = data.get(fieldName);
                        break;
                    case "soLuong":
                        soLuong = data.get(fieldName);
                        break;
                    case "ngaySanXuat":
                        ngaySanXuat = data.get(fieldName);
                        break;
                    case "img":
                        img = data.get(fieldName);
                        break;
                    case "id":
                        id = data.get(fieldName);
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected value: " + fieldName);
                }
            }

            PhoneModel phone = new PhoneModel();
            phone.setDescription(des);

            phone.setName(name);
            phone.setTypeTel(typePhone);
            phone.setPrice(Double.parseDouble(price));
            phone.setNhaSanXuat(nhaSanXuat);
            phone.setUrl_img(img);
            phone.setNgaySanXuat(Date.valueOf(ngaySanXuat));
            phone.setDescription(des);
            phone.setSoLuong(Integer.parseInt(soLuong));
            if (id.isBlank()) {
                if (phDAO.savePhone(phone)) {
                    response.sendRedirect(request.getContextPath() + "/admin-quan-ly-dien-thoai");
                } else {
                    error = "Loi nhap du lieu";
                    error(request, response, error);
                }
            } else {
                phone.setId(Integer.parseInt(id));
                if (phDAO.updatePhone(phone)) {
                    response.sendRedirect(request.getContextPath() + "/admin-quan-ly-dien-thoai");
                } else {
                    error = "Loi nhap du lieu";
                    error(request, response, error);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void error(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        RequestDispatcher rd = request.getRequestDispatcher("/admin-quan-ly-dien-thoai");
        rd.forward(request, response);
    }

}
