<%-- 
    Document   : students
    Created on : 16-jun-2016, 16:40:38
    Author     : Jesús Aragón
--%>
<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="etiq.txtstudents"/></title>
        <style>
            .contenedorpropiedadesestudiante
            {
                height: 60px;
                padding-top: 10px;
                padding-left: 5px;
                padding-right: 5px;
            }
            .contenedorNombre
            {
                padding-left: 30px;
            }
            .contenedorRadio
            {
                padding: 5px 0px 0px 0px;
            }
            
        </style>
    </head>
    <body>
        <div class="container">
        <!--barra de navegación-->
        
            <ul class="nav nav-tabs">
                <li><a href="#"><spring:message code="etiq.txtprofile"/></a></li>
                <li class="active"><a href="#asistencia"><spring:message code="etiq.txtattendance"/></a></li>
                <li><a href="#"><spring:message code="etiq.txtcomments"/></a></li>
                <li><a href="#"><spring:message code="etiq.txtreports"/></a></li>
            </ul>
        <div id="asistencia">
            <div class="h3"><spring:message code="etiq.txtliststudents"/></div>
            <div class="list-group">
                <div class="list-group-item contenedorpropiedadesestudiante">
                    <div class="col-sm-3 col-sm-offset-1 text-center">
                        <spring:message code="etiq.txtstudents"/>
                    </div>
                    <div class="col-sm-8 text-center">
                        <div class="col-sm-1"><spring:message code="etiq.txtstatus"/></div>
                        <div class="col-sm-11">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-4"><spring:message code="etiq.txtnote"/></div>
                            <div class="col-sm-5"><spring:message code="etiq.txttime"/></div>
                        </div>
                    </div>
                </div>    
        <!--FORMULARIO DE ASISTENCIAS-->            
        <form>
            <div class="list-group-item contenedorpropiedadesestudiante">
                <div class="col-sm-1">
                    <div class="col-sm-6">2</div>
                    <div class="col-sm-6"><img src="recursos/img/casillas.jpg" alt="" class="imagenlista"/></div>
                </div>
                <div class="col-sm-3 contenedorNombre">
                    <a href="editstudent.htm">compi, Mauricio</a>
                </div>
                <!--DATOS DE ASISTENCIA-->
                <div class="col-sm-8 text-center">
                    <div id="estado" class="col-sm-1"><h4 style="color: black; font-weight: bold;">?</h4></div>
                    <div id="asistio" class="col-sm-1" hidden="hidden">
                        <h4 style="color: greenyellow;">V</h4>
                    </div>
                        <div class="col-sm-11" id="entradaleccion" hidden="hidden">
                        <div class="col-sm-3 contenedorRadio">
                            <label class="radio-inline"><input type="radio" name="opcionasistencia" value="llegada">Llegada</label>
                            <label class="radio-inline"><input type="radio" name="opcionasistencia" value="salida">Salida</label>
                        </div>
                        <div class="col-sm-4">
                            <input type="text" name="observaciones" class="input-group">
                        </div
                        <div class="col-sm-5 form-horizontal">                                
                            <div class='input-group date datetimepicker' id='hora'>
                                <input type='text' name="hora" class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-time"></span>
                                </span>
                            </div>
                        </div>
                    <div id="tarde" class="col-sm-1" hidden="hidden"><h4 style="color: orange;">T</h4></div>
                    <div id="falto" class="col-sm-1" hidden="hidden"><h4 style="color: red;">F</h4></div>        
                </div>
                        
                    </div>   
                </div>
                <!--SCRIPT CAMBIO DE ESTADO DE ASISTENCIA-->
                <script>
                    $("#estado").click(function(){
                        $("#asistio").show();
                        $("#entradaleccion").show();
                        $("#estado").hide();
                    });
                    $("#asistio").click(function(){
                        $("#asistio").hide();
                        $("#entradaleccion").hide();
                        $("#tarde").show();
                    });
                    $("#tarde").click(function(){
                        $("#falto").show();
                        $("#tarde").hide();
                    });
                    $("#falto").click(function(){
                        $("#estado").show();
                        $("#falto").hide();
                    });
                </script>
            </div>
        </form>
        </div>
        </div>
    </body>
</html>
