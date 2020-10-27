/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApp.resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author catal
 */
@WebServlet(name = "CapthcaServlet", urlPatterns = {"/CapthcaServlet"})
public class CapthcaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static final String CAPTCHA_KEY = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CapthcaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CapthcaServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
    //Expire response
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Max-Age", 0);
    
    BufferedImage image = new BufferedImage(30, 10, BufferedImage.TYPE_INT_RGB); 
    Graphics2D graphics2D = image.createGraphics();
    Hashtable map = new Hashtable();
    Random r = new Random();
    String token = Long.toString(Math.abs(r.nextLong()), 36);
    String ch = token.substring(0,6);
    Color c = new Color(0.6662f, 0.4569f, 0.3232f);
    //GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
    graphics2D.setPaint(Color.BLACK);
    Font font=new Font("Arial", Font.CENTER_BASELINE , 26);
    graphics2D.setFont(font);
    graphics2D.drawString(ch,2,20);
    graphics2D.dispose();
    
    HttpSession session = req.getSession(true);
    session.setAttribute(CAPTCHA_KEY,ch);

    OutputStream outputStream = response.getOutputStream();
    ImageIO.write(image, "jpeg", outputStream);
    outputStream.close();



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
