
package javaBeans;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author lab04aluno
 */

@MultipartConfig
@WebServlet(name = "ServletImagemADO", urlPatterns = {"/ServletImagemADO"})
public class ServletImagemADO extends HttpServlet {

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
        
    String oper     = request.getParameter("oper");            
    String nome     = request.getParameter("nome");
    String email    = request.getParameter("email");
    String celular  = request.getParameter("celular");
    String senha    = request.getParameter("senha");
    String nivel    = request.getParameter("nivel");

//    Part filePart = request.getPart("arquivo"); // Pega o arquivo
  
    Usuario user = new Usuario(); // Instancia o objeto Usuario

    if ( oper.equals("Gravar") ) {
         user.nome     = nome;
         user.celular  = celular;
         user.senha    = senha;
         user.nivel    = nivel;
         user.email    = email; 

        if (!user.buscarEmail()) 
            user.incluir();
         else 
            user.alterar();        
    }

    if (  oper.equals("Deletar") ) 
        user.deletar();

    if ( user.buscarEmail() ) {
        String urls = request.getContextPath() + "/javaJSP/cadastro.jsp";
        response.sendRedirect(urls);
    }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletImagemADO</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletImagemADO at " + request.getContextPath() + "</h1>");
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
