/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author catal
 */
@WebServlet(urlPatterns = {"/generator"})
public class WordGenerator extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>WordGenerator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WordGenerator at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String letters = request.getParameter("letters");
        char[] letArray = letters.toCharArray();

        String txtFilePath = "C:\\Users\\catal\\Documents\\NetBeansProjects\\Lab1\\words.txt";

        BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (checkWordContainsAllLettersGiven(line, letters)) {
                sb.append(line + "\n");
            }
        }

        try (PrintWriter out = response.getWriter()) {
            if (! request.getHeader("User-Agent").contains("python-requests/2.24.0")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>WordGenerator</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Inserted letters: </h1>");
                out.println("<ul>");
                for (char c : letArray) {
                    out.print("<li>" + c + "</li>");
                }
                out.println("</ul>");

                //out.println(checkWordContainsAllLettersGiven("test","test"));
                out.println("<h1>Words that could be created with the given letters: </h1>");

                out.println(sb.toString());

                out.println("</body>");
                out.println("</html>");
            }
            else {
                out.println(sb.toString());
            }

        }
        //Server log
        System.out.println("HTTP method used: " + request.getMethod());
        String ip = request.getRemoteAddr();
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ipAddress = inetAddress.getHostAddress();
            ip = ipAddress;
        }
        System.out.println("IP address of the client: " + ip);
        System.out.println("User-agent: " + request.getHeader("User-Agent"));
        System.out.println("Language: " + request.getLocales().nextElement().getLanguage());
        System.out.println("Parameters: " + request.getParameterNames().nextElement());
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

    }

    public boolean checkWordContainsAllLettersGiven(String word, String letters) {
        //char[] copy = letters;
        for (char c : word.toCharArray()) {
            if (letters.indexOf(c) >= 0) {
                letters = letters.replaceFirst(String.valueOf(c), "");
            } else {
                return false;
            }
            //System.out.println(c);
            //System.out.println(letters);
        }
        return true;
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
