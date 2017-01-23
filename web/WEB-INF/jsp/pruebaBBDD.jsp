<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="estilotabla.css"/>
    </head>
    <body>
        <h1>Tabla departamentos SPRING</h1>
        <table border="1">
            <tr>
                <th>NUMERO</th>
                <th>NOMBRE</th>
                <th>LOCALIDAD</th>
            </tr>
        
            <jstl:forEach items="${listasubjects}" var="subject">
                <tr>
                    <td>
                        <jstl:out value="${subject.id}"/>
                    </td>
                    <td>
                        <jstl:out value="${subject.id_level}"/>
                    </td>
                    <td>
                        <jstl:out value="${subject.nombre}"/>
                    </td>                    
                </tr>
            </jstl:forEach>
        </table>
    </body>
</html>
