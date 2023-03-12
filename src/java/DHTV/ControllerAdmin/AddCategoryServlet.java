/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.category.CategoryDAO;
import DHTV.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mthin
 */
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/AddCategoryServlet"})
public class AddCategoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "showCategoryServlet";
        try {
            String CategoryName = request.getParameter("txtCategoryName");
            byte[] bytes1 = CategoryName.getBytes(StandardCharsets.ISO_8859_1);
            CategoryName = new String(bytes1, StandardCharsets.UTF_8);
            
            String Gender = request.getParameter("txtGender");
            byte[] bytes3 = Gender.getBytes(StandardCharsets.ISO_8859_1);
            Gender = new String(bytes3, StandardCharsets.UTF_8);  
            
            String Description = request.getParameter("txtDescription:");
            byte[] bytes2 = Description.getBytes(StandardCharsets.ISO_8859_1);
            Description = new String(bytes2, StandardCharsets.UTF_8);

            if(CategoryName ==  null  ){
                 String message = "Please enter your category";
                if (!message.isEmpty()) {
                    request.setAttribute("NULLADDCATEGORY", message);
                }
            }else if (Gender == null) {
                  String message = "Please enter your gender";
                if (!message.isEmpty()) {
                    request.setAttribute("NULLGENDER", message);
                }
            }else if (Description == null){
                  String message = "Please enter your Description";
                if (!message.isEmpty()) {
                    request.setAttribute("NULLDES", message);
                }
            }else {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryName(CategoryName);
            dto.setGender(Gender);
            dto.setDescription(Description);
            CategoryDAO dao = new CategoryDAO();
            dao.addCategoryAdmin(dto);
            }
        } catch (NamingException ex) {
            log(" _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log(" _ SQL _ " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
