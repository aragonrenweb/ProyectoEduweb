<%-- 
    Document   : detalles
    Created on : 08-nov-2016, 15:31:07
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="lessons.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles</title>
    </head>
    <body>
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
                <div class="row">
                    <div class="col-xs-3"><strong>Name archive:</strong></div>
                    <div class="col-xs-6 nombreArchivo">${detallesleccion.nombre_archivo}</div>
                </div>    
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
                <button type="button" class="btn btn-primary">Save changes</button>
              </div>
            </div>
          </div>
        </div>

    <!--<input id="botondetalles" hidden="hidden" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"/>-->
    </div>
    </body>
</html>
