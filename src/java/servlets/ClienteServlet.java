/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aluno
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {

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

        Cliente c = new Cliente();
        c.setNome(request.getParameter("nome"));
        c.setCpf(request.getParameter("cpf"));
        c.setEmail(request.getParameter("email"));
        c.setTelefone(request.getParameter("telefone"));
        c.setEndereco(request.getParameter("endereco"));
        c.setGenero(request.getParameter("genero"));

        String dateStr = request.getParameter("date");

        SimpleDateFormat strFormatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            System.out.println(dateStr);
            c.setDataNascimento(strFormatter.parse(dateStr));
        } catch (Exception e) {

        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cadastro de Cliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Nome: " + c.getNome() + "</h1>");
            out.println("<h1>CPF: " + c.getCpf() + "</h1>");
            out.println("<h1>Data de Nascimento: " + c.getDataNascimento() + "</h1>");
            out.println("<h1>E-mail: " + c.getEmail() + "</h1>");
            out.println("<h1>Telefone: " + c.getTelefone() + "</h1>");
            out.println("<h1>Endereco: " + c.getEndereco() + "</h1>");
            out.println("<h1>Gênero: " + c.getGenero() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }

        List<Cliente> lista = (List<Cliente>) request
                .getSession()
                .getAttribute("lista");

        if (lista == null) {
            lista = new ArrayList<Cliente>();
        }
        
        lista.add(c);
        request.getSession().setAttribute("lista", lista);
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
