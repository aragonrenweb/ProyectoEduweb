<%-- 
    Document   : buttonFragment
    Created on : 08-may-2016, 20:28:20
    Author     : Jesús Aragón
--%>

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
    <!--<script src="recursos/js/LessonsJS.js" type="text/javascript"></script>-->
</head>
<body>
    <!--container-->
    <div class="container">
        
        <div class="col-sm-12" id="maincontainer">
            <div class="col-sm-12 center-block text-center">
                <h2><spring:message code="etiq.txtactivities"/></h2>
            </div>
            <div id="navtoplistline">&nbsp;</div>


            <div class="col-sm-5 contenedorAlumno text-center" id="contenedorAlumnos">
                <div class="col-sm-6"><h4><spring:message code="etiq.txtstudents"/></h4></div>
                <div class="col-sm-6">
                <form:form id="form" method="POST" action='lessons.htm?accion='>
                                <c:choose>
                                    <c:when test="${empty levelStudentsSeleccionado}">
                                        <c:if test="${not empty listalevelsFor}">
                                            <select class="form-control select-level-students" id="levelSelecccionado" name="TXTlevelStudents" >
                                                <option value="0" selected>ALL STUDENTS
                                                    <%--<spring:message code="etiq.selectlevel"/>--%>
                                                </option>
                                                <c:forEach var="levelStudents" items="${listalevelsFor}">
                                                    <option value="${levelStudents.id}" >${levelStudents.nombre}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </c:when>    
                                    <c:otherwise>
                                        <select class="form-control select-level-students" id="levelSelecccionado" name="TXTlevelStudents">
                                          
                                            <c:forEach var="levelStudentsSeleccionado" items="${levelStudentsSeleccionado}">            
                                                <option value="${levelStudentsSeleccionado.id}" selected>${levelStudentsSeleccionado.nombre}</option>
                                            </c:forEach>
                                            <c:forEach var="levelStudents" items="${listalevelsFor}">
                                                <option value="${levelStudents.id}" >${levelStudents.nombre}</option>
                                            </c:forEach>
                                                <option value="0">ALL STUDENTS
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
                    <button type="button" class="btn" id="todoslosestudiantes">all students</button>
                </div>
            </div>
            <div class="col-sm-5 col-sm-offset-2 contenedorAlumno text-center" id="contenedorAlumnosIncluidosLeccion">
                <h4><spring:message code="etiq.txtincludedstudents"/></h4>
                <div id="sortable2" class='droptrue'>
                    <input type="hidden" id="misAlumnos" name="TXTalumnosSeleccionados" value="" />
                </div>
                <button type="button" class="btn" id="ningunestudiante">none students</button>
                <br clear="both" />
            </div>
        </div>

<!--script-->

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
                                <input id="titulo" type="text" class="form-control" name="TXTnombreLessons" value="${nombrelessons}"/>
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
                                            <select class="form-control select-level" id="idlevel" name="TXTlevel" >
                                                <option value="0" selected><spring:message code="etiq.selectlevel"/></option>
                                                <c:forEach var="level" items="${listalevels}">
                                                    <option value="${level.id}" >${level.nombre}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </c:when>    
                                    <c:otherwise>
                                        <select class="form-control select-level" id="idlevel" name="TXTlevel">
                                            <c:forEach var="levelseleccionado" items="${levelselection}">            
                                                <option value="${levelseleccionado.id}" selected>${levelseleccionado.nombre}</option>
                                            </c:forEach>
                                            <c:forEach var="level" items="${listalevels}">
                                                <option value="${level.id}" >${level.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" class="btn btn-default" name='accion' id="comboSubject" value="comboSubject"/>
                            </div>
                                
                            <!--COMBO MATERIA-->
                            
                            <div class="col-xs-3 center-block">
                                <label class="control-label"><spring:message code="etiq.txtsubject"/></label>
                                <c:choose>
                                    <c:when test="${empty subjectselection}">
                                        <select class="form-control select-subjects" id="idsubjects" name="TXTsubjects">
                                            <option value="0" selected><spring:message code="etiq.selectsubject"/></option>
                                            <c:forEach var="subjects" items="${listasubjects}">
                                                <option value="${subjects.id}" >${subjects.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select class="form-control select-subjects" id="idsubjects" name="TXTsubjects" >
                                            <c:forEach var="subject" items="${subjectselection}">
                                                <option value="${subject.id}" selected>${subject.nombre}</option>
                                            </c:forEach>
                                            <c:forEach var="subjects" items="${listasubjects}">
                                                <option value="${subjects.id}" >${subjects.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" class="btn btn-default" name='accion' id="comboSubsection" value="comboSubsection"/>

                            </div>
                                
                            <!--COMBO SECCION--> 
                            
                            <div class="col-xs-3 center-block">
                                <label class="control-label"><spring:message code="etiq.txtsubsection"/></label>
                                <c:choose>
                                    <c:when test="${empty SubsectionSelection}">
                                        <select class="form-control select-subsection" id="idsubsection" name="TXTsubsection">
                                            <option value="0" selected><spring:message code="etiq.selectsubsection"/></option>
                                            <c:forEach var="subsection" items="${listasubsection}">
                                                <option value="${subsection.id_subsection}" >${subsection.nombre_sub_section}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select class="form-control select-subsection" id="subsection" name="TXTsubsection" >
                                            <c:forEach var="subsection" items="${SubsectionSelection}">
                                                <option value="${subsection.id_subsection}" selected>${subsection.nombre_sub_section}</option>
                                            </c:forEach>
                                            <c:forEach var="subsections" items="${listasubsection}">
                                                <option value="${subsections.id_subsection}" >${subsections.nombre_sub_section}</option>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" class="btn btn-default" name='accion' id="comboEquipment" value="comboEquipment"/>
                            </div>
                                
                            <!--COMBO Activity/Equipment-->
                            
                            <div class="col-xs-3 center-block">
                                <label class="control-label"><spring:message code="etiq.txtequipment"/></label>
                                <c:choose>
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
                                </c:choose>
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
                                        <input type='text' name="TXTfechainicio" class="form-control" id="fechainicio" value="${fecha_init}"/>
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
                                        <input type='text' name="TXTfechafin" class="form-control" value="${fecha_fin}"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
<!--script-->
<script>
    //ENVIA EL FORMULARIO PARA CREAR LA LECCION
    $(function () {
        $("#crearLessons").on("click", function () {
            document.getElementById('misAlumnos').value = document.getElementById("sortable2").innerHTML;
            //alert(document.getElementById('misAlumnos').value);
        });
    $("#form").attr('action', 'lessons.htm?accion=' + $("#crearLessons").val());
    });
</script>
                            <div class="col-xs-3 checkbox">
                                <label>
                                <input type="checkbox" id="adjuntarRecursos"> attach resource
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
                                        <button type="submit" id="crearLessons" value="crear" class="btn btn-naranja"><spring:message code="etiq.txtcreate"/></button>
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
            <!--TABLA LESSONS-->
            <table class="table table-striped">
                <tr>
                    <td><spring:message code="etiq.namelessons"/></td>
                    <td><spring:message code="etiq.levellessons"/></td>
                    <td><spring:message code="etiq.subjectlessons"/></td>
                    <td><spring:message code="etiq.subsectionlessons"/></td>
                    <td><spring:message code="etiq.equipmentlessons"/></td>
                    <td><spring:message code="etiq.actionlessons"/></td>
                </tr>
                
                    <c:forEach var="lecciones" items="${listalecciones}">
                        <tr>
                            <td>${lecciones.nombre_lessons}</td>
                            <td>${lecciones.nombre}</td>
                            <td>${lecciones.nombre_subject}</td>
                            <td>${lecciones.nombre_subsection}</td>
                            <td>
                                <c:forEach var="materiales" items="${listamateriales}">
                                    <c:if test="${lecciones.id_lessons == materiales.id_lessons}">
                                        <div class="nombreActividad">${materiales.id_equipment} </div>  
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>

                                <div class="col-xs-6 col-xs-offset-3">
                                    <div class="col-xs-4">
                                        <button name="TXTid_lessons_detalles" value="${lecciones.id_lessons}" class="btn btn-detalles" id="details" data-target=".bs-example-modal-lg"><!--<a href= javascript:popUp('/details.jsp')  target="_blank" onClick="window.open(this.href, this.target, 'width=300,height=400'); return false;">-->
                                            <span class="glyphicon glyphicon-list-alt" data-toggle="tooltip" data-placement="bottom" title="Detalles"></span>
                                        </button>
                                    </div>
                                    <div class="col-xs-4">
                                        <form id="form2" action='modify.htm'>
                                            <button name="TXTid_lessons_modificar" type="submit" class="btn btn-modificar" id="modificarLessons" data-toggle="tooltip" data-placement="bottom" title="modify" value="${lecciones.id_lessons}">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
                                        </form>    
                                    </div>
                                    <div class="col-xs-4">
                                        
                                        <button name="TXTid_lessons_eliminar" value="${lecciones.id_lessons}" class="btn btn-eliminar" data-toggle="tooltip" data-placement="bottom" title="Eliminar">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                        
                                    </div>
                                </div>

                            </td>
                        </tr>
                        
                    </c:forEach>
               


            </table>
 
        </div>      

    </form:form>
                    


    <!--VENTANA DETALLES-->
    <!--<a href="/details.jsp" target="_blank" onClick="window.open(this.href, this.target, 'width=300,height=400'); return false;">-->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content col-xs-8">
        <c:forEach var="detallesleccion" items="${detalles}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel" data-idLessonsVal="${detallesleccion.id_lessons}">${detallesleccion.nombre_lessons}</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-xs-3"><strong>start date:</strong></div>
                <div class="col-xs-6">${detallesleccion.fecha_inicio}</div>
            </div>    
            <div class="row">
                <div class="col-xs-3"><strong>Date end:</strong></div>
                <div class="col-xs-6">${detallesleccion.fecha_fin}</div>
            </div>
                <c:if test="${detallesleccion.nombre_archivo != ''}">
                    <div class="row">
                        <div class="col-xs-3"><strong>Name archive:</strong></div>
                        <div class="col-xs-6 nombreArchivo">${detallesleccion.nombre_archivo}</div>
                    </div> 
                </c:if> 
            <div class="row">
                <div class="col-xs-3" data-levelVal="${detallesleccion.id_level}"><strong>Level:</strong></div>
                <div class="col-xs-6 nombreLevel">${detallesleccion.nombre}</div>    
            </div>    
            <div class="row">
                <div class="col-xs-3" data-subjectVal="${detallesleccion.id_subject}"><strong>Subject:</strong></div>
                <div class="col-xs-6 nombreSubject">${detallesleccion.nombre_subject}</div>
            </div>    
            <div class="row">
                <div class="col-xs-3" data-subsectionVal="${detallesleccion.id_subsection}"><strong>SubSection:</strong></div>
                <div class="col-xs-6 nombreSubsection">${detallesleccion.nombre_sub_section}</div>
            </div>
            
                <div class="col-xs-12">LIST OF STUDENTS</div>
                <c:forEach var="alumnosleccion" items="${detallesAlumnos}">
                    <div class="row">
                        <div class="col-xs-3" data-subsectionVal="${alumnosleccion.id_students}"><strong>Name</strong></div>
                        <div class="col-xs-6 nombreSubsection">${alumnosleccion.nombre_students}</div>
                    </div>
                </c:forEach>
            
        </div>
    </c:forEach>
       <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary">Save changes</button>-->
      </div>
    </div>
  </div>
</div>

    <!--script-->
    
<input id="botondetalles" hidden="hidden" type="button" data-toggle="modal" data-target=""/>
</div>
                
<div class="panel-footer" align="center"><font style="color: #111">Copyright @2016, EduWeb Group, All Rights Reserved. </font></div>
    <!-- /container -->        
</div>

</body>
</html>


