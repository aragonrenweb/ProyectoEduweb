<%-- 
    Document   : bannerinfo
    Created on : 12-jul-2016, 16:23:16
    Author     : Jesús Aragón
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <style>
            
            
        </style>
    </head>
    
        <div class="infousuario">
                    <c:if test="${usuarioregistrado != null}">
                        <h4 class="pull-right">
                            <spring:message code="etiq.wellcome"/>
                            
                            <c:out value="${usuarioregistrado.nombre}"/>
                            <c:out value="${usuarioregistrado.primer_apellido}"/>
                            <c:out value="${usuarioregistrado.segundo_apellido}"/>
                            <c:out value="${usuarioregistrado.typeuser}"/>
                        </h4>
                        <button class="btn"><a href="cerrarLogin.htm"><spring:message code="etiq.closesession"/></a></button>
                    </c:if>
            
        </div>        
   
</html>
