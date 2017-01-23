<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
      <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
       <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="recursos/img/logo.ico">
        <link rel="shortcut icon" href="recursos/img/logo.ico">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link href="recursos/css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
        <link href="recursos/css/main.css" rel="stylesheet" type="text/css" />
        <link href="recursos/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/menu-lateral.css" rel="stylesheet" type="text/css"/>
            
               
        <script src="recursos/js/jquery-2.2.0.js" type="text/javascript"></script>
        <script src="recursos/js/jquery-2.2.0.min.js" type="text/javascript"></script>
                
        <!--<script src="recursos/js/jquery-1.12.4.js" type="text/javascript"></script>-->
        <script src="recursos/js/jquery-ui-1.11.4.custom/jquery-ui.js" type="text/javascript"></script>
        
        <script src="recursos/js/bootstrap.js" type="text/javascript"></script>
        <script src="recursos/js/moment.js" type="text/javascript"></script>
        <script src="recursos/js/transition.js" type="text/javascript"></script>
        <script src="recursos/js/collapse.js" type="text/javascript"></script>
        
        <script src="recursos/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
        <script src="recursos/js/bootstrap.min.js" type="text/javascript"></script>   
        <script src="recursos/js/es.js" type="text/javascript"></script>
        <script src="recursos/js/ar.js" type="text/javascript"></script>

        
        <!--<script src="recursos/js/formulario.js" type="text/javascript"></script>
        <script src="recursos/js/moment-with-locales.js" type="text/javascript"></script>
        <script src="recursos/js/moment-with-locales.min.js" type="text/javascript"></script>-->
        
        <script src="recursos/js/moment.min.js" type="text/javascript"></script>
<script>
    var userLang = navigator.language || navigator.userLanguage;
</script>

        <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <style>
            .btnBandera
            {
                padding-left: 2px;
                padding-right: 2px;
                margin-left: 0px;
                margin-right: 0px;
            }
            .contenedorBandera
            {
                padding-left: 0px;
                padding-right: 0px;
            }
            hr{
                padding-top: 8px;
                padding-bottom: 8px;
                color: white;
                size: 2px;
                margin-top: 0px;
                margin-bottom: 0px;
            }
        </style>
</head>

<body>


    <%-- <%@ include file="loginForum.jsp" %> --%>

    <div class="col-sm-12" style="margin-top: 10px;">

        <div class="panel panel-success">

            <div class="panel-body"align="center">

                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">

                    <div class="panel panel-success" style="max-width: 35%;" align="left">

                        <div class="panel-heading form-group fondoGris">
                            <img class="img-responsive center-block" src="recursos/img/logoeduweb.png" alt="logo"/>
                        </div>

                        <div class="panel-body" >

                            <form name ="loginForum" action="check.htm" method="post" >
                                <div
                                    <c:if test="${errorusuario != null}">
                                    <h5 style="color:blue">
                                        <c:out value="${errorusuario}"/>
                                    </h5>
                                </c:if>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1"><spring:message code="etiq.txtuser"/></label> 
                                    <input type="text" class="form-control" name="txtuser" id="txtuser" placeholder="<spring:message code='etiq.txtinsertuser'/>" required="required">    
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1"><spring:message code="etiq.txtpassword"/></label> 
                                    <input type="password" class="form-control" name="txtpassword" id="txtpassword" placeholder="<spring:message code='etiq.txtinsertpassword'/>" required="required">
                                </div>
                                <button type="submit" name="submit" value='<spring:message code="etiq.txtlogin"/>' style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>

                            </form>
                            <div class="center-block text-center">
                                <a class="btn" href="?lenguaje=en"><spring:message code="etiq.txtenglish"/></a>
                                <a class="btn" href="?lenguaje=es"><spring:message code="etiq.txtspanish"/></a>
                                <a class="btn" href="?lenguaje=ar"><spring:message code="etiq.txtarabic"/></a>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2016, EduWeb Group, All Rights Reserved. </font></div>
        </div>
    </div>
</div> 
</body>

