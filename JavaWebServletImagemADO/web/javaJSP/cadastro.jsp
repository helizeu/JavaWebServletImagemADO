<%@page import="javaBeans.Usuario"%>
<%  String nome = "", email = "", celular = "", senha = "", nivel = "", sHTML = "";
    String oper = request.getParameter("oper");
    Usuario user = new Usuario(); // Instancia o objeto Usuario
    user.email = request.getParameter("email");
    if (!(user.statusSQL == null)) {
        out.println(user.statusSQL);
    }

    // Se for gravar pega as requisições e faz a consistencia 
    if (oper.equals("Gravar")) {
        nome = request.getParameter("nome");
        email = request.getParameter("email");
        celular = request.getParameter("celular");
        senha = request.getParameter("senha");
        nivel = request.getParameter("nivel");
        if (nome.isBlank() || email.isBlank() || celular.isBlank() || senha.isBlank() || nivel.isBlank()) {
            sHTML = "Advertência: Todos os campos devem ser preenchidos na gravação!";
        }
    }
    // Se for gravar pega as requisições e faz a consistencia

    // coloca os dados dentro do objeto User antes de atualizar o banco de dados
    if (oper.equals("Gravar")) {
        if (!user.buscarEmail() && sHTML.isBlank()) {
            user.nome = nome;
            user.celular = celular;
            user.senha = senha;
            user.nivel = nivel;
            user.incluir();
        } else {
            user.nome = nome;
            user.celular = celular;
            user.senha = senha;
            user.nivel = nivel;
            user.alterar();
        }
    } else if (oper.equals("Deletar")) {
        user.deletar();
        user.nome = nome = "";
        user.email = email = "";
        user.celular = celular = "";
        user.senha = senha = "";
        user.nivel = nivel = "";
    } else if (oper.equals("Buscar")) {
        if (!user.buscarEmail()) {
            sHTML = "Advertência: Usuário não localizado com esse email!";
        } else {
            nome = user.nome;
            email = user.email;
            celular = user.celular;
            senha = user.senha;
            nivel = user.nivel;
        }
    }
    if (!(user.statusSQL == null))
        out.println(user.statusSQL);%>

<html lang = "pt-br"><head>
<title> Cadastro de Usuários </title> <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"></head>
<body style="background-color: greenyellow;" onload="formreg.nome.focus()">
<br><br> <h1 align = center> Atualização Cadastral de Usuários </h1> <br>
<center> <%=sHTML%> </center>
<div>   <form action="cadastro.jsp" name = formreg method="get" >
        <input type="hidden" name = oper >
        <table align = center style="border-style: solid;">
        <tr><td></td><td></tr>
        <tr><td align="right">Digite seu nome...:</td><td><input type="text" name = nome size = 40 value = "<%
        if (oper.equals("Buscar")) { out.print(nome); }%>"> </td></tr>
                <tr><td align="right">Digite seu email..:</td><td><input type="email" name = email size = 40 value = "<%
                    if (oper.equals("Buscar")) { out.print(email);  }%>"> </td></tr>
                <tr><td align="right">Digite seu Celular:</td><td>
                        <input type="text" name = celular size = 40 value = "<%
                 if (oper.equals("Buscar")) {  out.print(celular); }%>"> </td></tr>
                <tr><td align="right">Digite sua Senha..:</td><td>
                        <input type="password" name = senha size = 40 value = "<%
                            if (oper.equals("Buscar")) {out.print(senha); }%>"> </td></tr>
                <tr>  <td align="right">Tipo de acesso....:</td><td><select name="nivel" id="idnivel" >
                <option value="<% if (oper.equals("Buscar")) { out.print(nivel); }%>" selected><%
                  if (oper.equals("Buscar")) {out.print(nivel);} %></option>
                            <option value="administrador"> Administrador </option>
                            <option value="gerente"> Gerente </option>
                            <option value="normal"> Normal </option>
                        </select> </td></tr>  </table>      <br>   <table align="center">   <tr><td align="center">    <input type="reset" value="Novo" onclick="formreg.nome.focus()"  style="height: 30px; width: 70px;background-color: rgba(92, 117, 116, 0.26);border-radius: 20%;">
                    </td>   <td align="center">
                        <input type="submit" value="Gravar" name="gravar" onclick="formreg.oper.value = 'Gravar';" style="height: 30px; width: 70px;background-color: rgba(92, 117, 116, 0.26);border-radius: 20%;">
                    </td>  <td align="center">
                        <input type="submit" value="Buscar" name="buscar" onclick="formreg.oper.value = 'Buscar';" style="height: 30px; width: 70px;background-color: rgba(92, 117, 116, 0.26);border-radius: 20%;">
                    </td> <td align="center">  <input type="submit" value="Deletar" name = "deletar" onclick="formreg.oper.value = 'Deletar';" style="height: 30px; width: 70px;background-color: rgba(92, 117, 116, 0.26);border-radius: 20%;">
                    </td> </tr>  </table>        </form> <h1 align = center> Criando o Servlet Uploader </h1></div></body></html>