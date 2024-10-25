package javaBeans;

import java.io.IOException;
import java.io.PrintWriter;
import javaBeans.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CadUser", urlPatterns = {"/CadUser.php"})
@MultipartConfig
public class CadUser extends HttpServlet {

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

        String sHTML = "";

        HttpSession session = request.getSession(true);
        if (String.valueOf(session.getAttribute("nome")) == null) {
            response.sendRedirect("../index.html");
        }

        Usuario user = new Usuario(); // Instancia o objeto Usuario
        user.email = request.getParameter("email");
        user.nome = request.getParameter("nome");
        user.celular = request.getParameter("celular");
        user.senha = request.getParameter("senha");
        user.nivel = request.getParameter("nivel");

          if ( request.getParameter("gravar").equals("Gravar") )
          {
              if (!user.buscarEmail()) {
                user.incluir();
            } else {
                user.alterar();
            }
        }

          
          if ( request.getParameter("deletar").equals("Deletar") )
          {
             user.deletar();
             session.invalidate();
        }
          
        String url = request.getContextPath() + "/javaJSP/cadastro.jsp";
        if (user.buscarEmail()) {
            response.sendRedirect(url);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> "+sHTML+"A sessão foi finalizada por que você deletou seu usuário..!</h1>");
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
