<%-- 
    Document   : listarVacinas
    Created on : Apr 24, 2017, 1:52:05 PM
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Show All Users</title>
    </head>
    <body>
        <center>
            <h3>Gerenciamentos de Vacinas</h3>
            <table border=1>

                <tr>
                    <th>Id</th>
                    <th>Data de Validade</th>
                    <th>Data de Fabricaçao</th>
                    <th>Nome</th>
                    <th>Tipo</th>
                    <th>Quantidade</th>
                    <th>Lote</th>
                    <th>Id Laboratorio</th>
                    <th colspan=2>Açao</th>
                </tr>


                <c:forEach var="vacina" items="${listarVacinas}" >
                    <tr>

                        <td><c:out value="${vacina.id}" /></td>
                        <td><c:out value="${vacina.dataValidade}" /></td>
                        <td><c:out value="${vacina.dataFabricacao}" /></td>
                        <td><c:out value="${vacina.nome}" /></td>
                        <td><c:out value="${vacina.tipo}" /></td>
                        <td><c:out value="${vacina.quantidade}" /></td>
                        <td><c:out value="${vacina.lote}" /></td>
                        <td><c:out value="${vacina.idLaboratorio}" /></td>
                        
                        <td><a href="editVacina?id=<c:out value="${vacina.id}"/>">Update</a></td>
                        <td><a href="deleteVacina?id=<c:out value='${vacina.id}'/>">Delete</a></td>

                    </tr>
                </c:forEach>

            </table><br>
            <div>
                <a href="newVacina">Adicionar Nova Vacina</a>
            &nbsp; &nbsp;
                <a href="listVacina">List All Vacinas</a>
            &nbsp; &nbsp;
                <a href="principal">Home</a>
            </div>
        </center>
    </body>
</html>

