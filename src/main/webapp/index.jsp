<%-- 
    Document   : index
    Created on : Mar 5, 2017, 5:53:44 PM
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação</title>
    </head>
    <body>
        <div align="center">
            <h2>Autenticação de Usuário!</h2><br>
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <font color="red"> <%=msg%></font>
            <%}%>
            <br>
            <form action="AcessoController" method="POST">
                Login: <input type="text" name="txtLogin"><br>
                <br>
                Senha: <input type="password" name="txtSenha"><br>
                <br>
                <input type="submit" value="Entrar" name="acao">
                </div>
            </form>
    </body>
</html>
