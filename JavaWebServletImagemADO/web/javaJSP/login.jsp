<%@page import="javaBeans.Usuario"%>
<%
    String email = request.getParameter("email"); // captura email do form 
    String senha = request.getParameter("senha"); // captura senha do form 

    Usuario user = new Usuario();// instancia Usuario
    user.email = email; // mude para user.setEmail(email);
    user.senha = senha;  // mude para user.setSenha(senha);
%>


<html lang = "pt-br">
    <head>
        <title> Registro de Usu�rios </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="background-color: greenyellow;" >
        <%
            if (user.getLogin() == true) { // faz o login no objeto user
                response.sendRedirect("sistema.jsp");// carrega a p�gina de sistema
            } else {
                String sHTML = "<center>Opa! Login ou Senha n�o encontrados! Tente Novamente! <br>"
                        + "<a href = '../index.html'> Voltar </a></center>";
                out.println(sHTML);
            }%>
    </body>
</html>
