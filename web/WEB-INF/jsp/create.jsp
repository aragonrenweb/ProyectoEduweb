 <%-- 
    Document   : create
    Created on : 21-nov-2016, 20:36:02
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <%@ include file="header.jsp" %>
<%@ include file="bannerinfo.jsp" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="etiq.txtlessons"/></title>
    
    <link href="recursos/css/lessons.css" rel="stylesheet" type="text/css"/>
    
    <!--style-->
    <script>
       
    //Implementa el boton para pasar estudiantes de un cuadro a otro
    
    var ajax;

    
    $(function (){
        'use stric';
        $('#todoslosestudiantes').click(function() {
           $('.elemento-movil').appendTo('#sortable2');
           
        });
        $('#ningunestudiante').click(function() {
           $('.elemento-movil').appendTo('#sortable1'); 
        }); 
    });

    /*$(function () {  
        'use strict';
        $('.select-level-students').on('change', function () {
            //alert(document.getElementById("sortable2").innerHTML);
            //document.getElementById('misAlumnos').value = conseguirAlumnos();
            $('#form').attr('action', 'create.htm?accion=' + $('#levelStudentSelecccionado').val());
            $('#createOnClick').attr('disabled', false);
            $('.btn').click();
        });
    });*/
    
    function funcionCallBackLevelStudent()
    {
           if (ajax.readyState==4){
                if (ajax.status==200){
                    document.getElementById("sortable1").innerHTML= ajax.responseText;
                    }
                }
            }

    function comboSelectionLevelStudent()
    {
        if (window.XMLHttpRequest) //mozilla
        {
            ajax = new XMLHttpRequest(); //No Internet explorer
        }
        else
        {
            ajax = new ActiveXObject("Microsoft.XMLHTTP");
        }
        
        ajax.onreadystatechange=funcionCallBackLevelStudent;
        var seleccion = document.getElementById("levelSelecccionado").value;
        var alumnos = document.getElementById("sortable2").innerHTML;
        ajax.open("GET","levelStudentsAjax.htm?idLevelStudents="+seleccion+"&TXTalumnosSeleccionados="+alumnos,true);
        ajax.send("");
    }
    
    function GetElementInsideContainer(containerID, childID) {
    var elm = {};
    var elms = document.getElementById(containerID).getElementsByTagName("*");
    for (var i = 0; i < elms.length; i++) {
        if (elms[i].id === childID) {
            elm = elms[i];
            break;
        }
    }
    return elm;
}
    //Level
    /*$(function () {
        'use strict';
        $('.select-level').on('change', function () {
            //alert(document.getElementById("sortable2").innerHTML);
            //document.getElementById('misAlumnos').value = conseguirAlumnos();
            $('#form').attr('action', 'create.htm?accion=' + $('#levelOnClick').val());
            $('#createOnClick').attr('disabled', false);
            $('.btn').click();
        });
    });*/
    
    //LEVEL AJAX
    
    
    function funcionCallBackLevel()
    {
           if (ajax.readyState==4){
                if (ajax.status==200){
                    document.getElementById("idsubjects").innerHTML= ajax.responseText;
                    document.getElementById("idsubsection").innerHTML= "<option value=\"0\" selected><spring:message code="etiq.selectsubsection"/></option>";
                    document.getElementById("idequipment").innerHTML= "<option value=\"0\"><spring:message code="etiq.selectequipment"/></option>";
                    }
                }
            }

    function comboSelectionLevel()
    {
        if (window.XMLHttpRequest) //mozilla
        {
            ajax = new XMLHttpRequest(); //No Internet explorer
        }
        else
        {
            ajax = new ActiveXObject("Microsoft.XMLHTTP");
        }
        
        $('#createOnClick').attr('disabled', true);
        ajax.onreadystatechange=funcionCallBackLevel;
        var seleccion = document.getElementById("idlevel").value;
        ajax.open("GET","levelAjax.htm?idLevel="+seleccion+"&etiqSubject=<spring:message code="etiq.selectedSubject"/>",true);
        ajax.send("");
    }
    
    
    
    //Subject
    /*$(function () {
        "use strict";
        $(".select-subjects").on("change", function () {
            //document.getElementById('misAlumnos').value = conseguirAlumnos();
            $("#form").attr('action', 'create.htm?accion=' + $("#subjectOnClick").val());
            $('#createOnClick').attr('disabled', false);
            $(".btn").click();
        });
    });*/
    
    //SUBJECT AJAX
    
    function funcionCallBackSubject()
    {
           if (ajax.readyState==4){
                if (ajax.status==200){
                    document.getElementById("idsubsection").innerHTML= ajax.responseText;
                    document.getElementById("idequipment").innerHTML= "<option value=\"0\"><spring:message code="etiq.selectequipment"/></option>"
                    }
                }
            }
    
    function comboSelectionSubject()
    {
        if (window.XMLHttpRequest) //mozilla
        {
            ajax = new XMLHttpRequest(); //No Internet explorer
        }
        else
        {
            ajax = new ActiveXObject("Microsoft.XMLHTTP");
        }

        $('#createOnClick').attr('disabled', true);
        ajax.onreadystatechange=funcionCallBackSubject;
        var seleccion = document.getElementById("idsubjects").value;
        ajax.open("GET","subjectAjax.htm?idSubject="+seleccion+"&etiqSubsection=<spring:message code="etiq.selectedSubsection"/>",true);
        ajax.send("");
    }
    
    //Subsection
    /*$(function () {
        "use strict";
        $(".select-subsection").on("change", function () {
            //document.getElementById('misAlumnos').value = document.getElementById("sortable2").innerHTML;
            $("#form").attr('action', 'create.htm?accion=' + $("#subsectionOnClick").val());
            $('#createOnClick').attr('disabled', false);
            $(".btn").click();
            //$( "#createOnClick" ).prop( "disabled", false );
        });
    });*/
    
    //Subsection Ajax
    
    function funcionCallBackEquipment()
    {
           if (ajax.readyState==4){
                if (ajax.status==200){
                    document.getElementById("idequipment").innerHTML= ajax.responseText;
                    }
                }
            }
    
    function comboSelectionSubsection()
    {
        if (window.XMLHttpRequest) //mozilla
        {
            ajax = new XMLHttpRequest(); //No Internet explorer
        }
        else
        {
            ajax = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if(document.getElementById("idsubsection").value == 0){
            $('#createOnClick').attr('disabled', true);
        }else{
            $('#createOnClick').attr('disabled', false);
        }
        //$('#createOnClick').attr('disabled', false);
        ajax.onreadystatechange=funcionCallBackEquipment;
        var seleccion = document.getElementById("idsubsection").value;
        ajax.open("GET","subsectionAjax.htm?idSubsec="+seleccion+"&etiqEquipment=<spring:message code="etiq.noEquipment"/>",true);
        ajax.send("");
    }
    
    //File
    $(document).ready(function() {
        $('#adjuntarRecursos').click(function() {
            //document.getElementById('misAlumnos').value = document.getElementById("sortable2").innerHTML;
            var $this = $(this);
        // $this will contain a reference to the checkbox   
            if ($this.is(':checked')) {
                $('#contenedorRecursos').show(); // the checkbox was checked
                $('#archivo').attr("disabled", false);
            } else {
                $('#contenedorRecursos').hide(); // the checkbox was unchecked
            }
        });    
    });
    
    /*$(function () {
        $("#form").attr('action', 'create.htm?accion=' + $("#createOnClick").val());
    });*/
    
    /*$(function () {
        //document.getElementById("#createOnClick").disabled = true;
        $("#createOnClick").on("click", function () {
            setTimeout("location.href='lessonCreate.htm'", 5000);
        });
        //    document.getElementById('misAlumnos').value = document.getElementById("sortable2").innerHTML;
        //});
        //$("#form").attr('action', 'create.htm?accion=' + $("#createOnClick").val());
        
    });*/
    
    function conseguirAlumnos(){
        var h = document.getElementById("titulo").value;
        document.getElementById('misAlumnos').value = document.getElementById("sortable2").innerHTML;
    }
    

        
    </script>
</head>
    <body>
        <div class="container">
            <form:form id="form" method="POST" action="lessonCreate.htm">
            <div class="col-sm-12" id="maincontainer">
                <div class="col-sm-12 center-block text-center">
                    <h2><spring:message code="etiq.txtactivities"/></h2>
                </div>
                <div id="navtoplistline">&nbsp;</div>
                <div class="col-sm-5 contenedorAlumno text-center" id="contenedorAlumnos">
                    <div class="col-sm-6"><h4><spring:message code="etiq.txtstudents"/></h4></div>
                    <div class="col-sm-6">
                        <c:choose>
                            <c:when test="${empty levelStudentsSeleccionado or levelStudentsSeleccionado.id==0}">
                                <c:if test="${not empty listalevelsFor}">
                                    <select class="form-control select-level-students" id="levelSelecccionado" name="TXTlevelStudents" onchange="comboSelectionLevelStudent()">
                                        <option value="0" selected><spring:message code="etiq.allStudents"/>
                                        </option>
                                        <c:forEach var="levelStudents" items="${listalevelsFor}">
                                            <option value="${levelStudents.id}" >${levelStudents.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </c:when>    
                            <c:otherwise>
                                <select class="form-control select-level-students" id="levelSelecccionado" name="TXTlevelStudents" onchange="comboSelectionLevelStudent()">           
                                    <option value="${levelStudentsSeleccionado.id}" selected>${levelStudentsSeleccionado.nombre}</option>
                                    <c:forEach var="levelStudents" items="${listalevelsFor}">
                                        <option value="${levelStudents.id}" >${levelStudents.nombre}</option>
                                    </c:forEach>
                                        <option value="0"><spring:message code="etiq.allStudents"/>
                                            <%--<spring:message code="etiq.selectlevel"/>--%>
                                        </option>
                                </select>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" class="btn btn-default" name='accion' id="levelStudentSelecccionado" value="levelStudentSelecccionado"/>
                    </div>
                    <div id="sortable1" class='col-sm-12 droptrue'>
                    <c:forEach var="alumnos" items="${listaAlumnos}">
                        <img src="${alumnos.foto}"  class="elemento-movil img-circle" data-toggle="tooltip" data-placement="bottom" title="${alumnos.nombre_students}" data-id="${alumnos.id_students}" />
                    </c:forEach>
                    </div>
                    <div class="col-sm-12">
                        <button type="button" class="btn" id="todoslosestudiantes"><spring:message code="etiq.allStudents"/></button>
                    </div>
                </div>
                <div class="col-sm-5 col-sm-offset-2 contenedorAlumno text-center" id="contenedorAlumnosIncluidosLeccion">
                    <h4><spring:message code="etiq.txtincludedstudents"/></h4>
                    <div id="sortable2" class='droptrue'>
                        <c:if test="${not empty alumnosSeleccionados}">
                            <c:forEach var="alumnos2" items="${alumnosSeleccionados}">
                                <img src="${alumnos2.foto}"  class="elemento-movil img-circle" data-toggle="tooltip" data-placement="bottom" title="${alumnos2.nombre_students}" data-id="${alumnos2.id_students}" />
                            </c:forEach>
                        </c:if>
                        <input type="hidden" id="misAlumnos" name="TXTalumnosSeleccionados" value="" />
                    </div>
                        <button type="button" class="btn" id="ningunestudiante"><spring:message code="etiq.noneStudents"/></button>
                    <br clear="both" />
                </div>
            </div>
                    
        <div class="col-sm-12">
            <!--FORMULARIO CREAR ACTIVIDAD-->
            <fieldset>
                <legend><spring:message code="etiq.txtscheduleactivities"/></legend>
                <!--campos especificos-->
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-8">
                            <label class="control-label"><spring:message code="etiq.txtname"/></label>
                            <input type="hidden" class="form-control" name="TXTidLessons"/>
                            <input id="titulo" type="text" class="form-control" name="TXTnombreLessons" value="${nombrelessons}" required="required"/>
                        </div>
                    </div>
                </div>            
                <!--campos colectivos-->
                <div class="form-group">
                    <div class="row">
                        <!--COMBO LEVEL-->
                        <div class="col-xs-2 center-block">
                            <label class="control-label"><spring:message code="etiq.txtlevels"/></label>
                            <c:choose>
                                <c:when test="${empty levelselection}">
                                    <c:if test="${not empty listalevels}">
                                        <select class="form-control select-level" id="idlevel" name="TXTlevel" onchange="comboSelectionLevel()">
                                            <option value="0" selected><spring:message code="etiq.selectlevel"/></option>
                                            <c:forEach var="level" items="${listalevels}">
                                                <option value="${level.id}" >${level.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </c:when>    
                                <c:otherwise>
                                    <select class="form-control select-level" id="idlevel" name="TXTlevel" onchange="comboSelectionLevel()">          
                                        <option value="${levelselection.id}" selected>${levelselection.nombre}</option>
                                        <c:forEach var="level" items="${listalevels}">
                                            <option value="${level.id}" >${level.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" class="btn btn-default" name='accion' id="levelOnClick" value="levelOnClick"/>
                        </div>

                        <!--COMBO MATERIA-->

                        <div class="col-xs-3 center-block">
                            <label class="control-label"><spring:message code="etiq.txtsubject"/></label>
                            <select class="form-control select-subjects" id="idsubjects" name="TXTsubjects" onchange="comboSelectionSubject()">
                                <option value ="0" selected><spring:message code="etiq.selectsubject"/></option>
                            </select>
                            
                            <input type="hidden" class="btn btn-default" name='accion' id="subjectOnClick" value="subjectOnClick"/>

                        </div>   
                        <!--COMBO SECCION--> 
                        <div class="col-xs-3 center-block">
                            <label class="control-label"><spring:message code="etiq.txtsubsection"/></label>
                            <select class="form-control select-subsection" id="idsubsection" name="TXTsubsection" onchange="comboSelectionSubsection()">
                                <option value="0" selected><spring:message code="etiq.selectsubsection"/></option>
                            </select>
                            
                            <input type="hidden" class="btn btn-default" name='accion' id="subsectionOnClick" value="subsectionOnClick"/>
                        </div>                               
                        <!--COMBO Activity/Equipment-->                            
                        <div class="col-xs-3 center-block">
                            <label class="control-label"><spring:message code="etiq.txtequipment"/></label>
                            <select multiple="true" class="form-control select-equipment" id="idequipment" name="TXTequipment" >
                                <option value="0"><spring:message code="etiq.selectequipment"/></option>
                            </select>
                            <!--<c:choose>
                                <c:when test="${empty SubsectionSelection}">
                                    <select class="form-control select-equipment" id="idequipment" name="TXTequipment">
                                        <option value="0" selected><spring:message code="etiq.selectequipment"/></option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select multiple="true" class="form-control select-equipment" id="idequipment" name="TXTequipment" >
                                        <c:forEach var="equipment" items="${listaequipment}">
                                            <option value="${equipment.id_activity_equipment}" >${equipment.nombre_activity_equipment}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>-->
                        </div>                               
                    </div>
                </div>
                <!--Fechas-->
                <div class="form-group">
                    <div class="row">

                        <div class='col-xs-4'>
                            <div class="form-group">
                                <label class="control-label" for="fechainicio"><spring:message code="etiq.txtstartdate"/></label>
                                <div class='input-group date' id='datetimepickerinicio'>
                                    <input type='text' name="TXTfechainicio" class="form-control" id="fechainicio" required="required"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class='col-xs-4'>
                            <div class="form-group">
                                <label class="control-label" for="fechafin"><spring:message code="etiq.txtenddate"/></label>
                                <div class='input-group date' id='datetimepickerfin'>
                                    <input type='text' name="TXTfechafin" class="form-control" required="required"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
<!--script-->                          
                        <div class="col-xs-3 checkbox">
                            <label>
                            <input type="checkbox" id="adjuntarRecursos"> <spring:message code="etiq.resource"/>
                            </label>
                        </div>
                        <div class="col-xs-6" id="contenedorRecursos" hidden="hidden">
                            <div class="form-group">
                                <label class="control-label"></label>
                                <div class='input-group' style="margin-top:19px;">
                                   <!--Adjuntar archivos-->
                                   <input type="hidden" class="form-control" name="TXTidArchivo"/>
                                   <input type="file" name="TXTnombreArchivo" id="archivo" class="btn btn-naranja" disabled="disabled" />
                                   <span class='label label-info' id="upload-file-info" style="color: blue"></span>

                                </div>
                            </div>
                        </div>        
                        <div class="col-xs-2 pull-right">
                            <div class="form-group">
                                <label class="control-label"></label>
                                <div class='input-group' style="margin-top:19px;">
                                    
                                        <button type="submit" id="createOnClick" value="createOnClick" class="btn btn-naranja" disabled onclick="conseguirAlumnos()"><spring:message code="etiq.txtcreate"/></button>
                                
                                    <!--<button type="submit" id="Cancel" value="Cancel" class="btn btn-naranja"><spring:message code="etiq.txtcancel"/></button>-->
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>
            </fieldset>
        </div>
        
        <div class="col-sm-12" style="margin:15px;"> 
            <c:if test="${LessonsCreadas != null}">
                <h5 style="color:coral">
                    <p><spring:message code="etiq.lessonscreated"/><c:out value="${LessonsCreadas}"/> <spring:message code="etiq.assignedmaterials"/><c:out value="${MaterialesAsignados}"/></p>
                </h5>
            </c:if>
        </div>
        </form:form>
        </div>    
    </body>       
</html>