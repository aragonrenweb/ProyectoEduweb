﻿﻿﻿﻿﻿﻿﻿<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
      <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
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
        <link href="recursos/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/menu-lateral.css" rel="stylesheet" type="text/css"/>
        
        <script src="recursos/js/jquery-2.2.0.js" type="text/javascript"></script>
        <script src="recursos/js/jquery-2.2.0.min.js" type="text/javascript"></script>
        <script src="recursos/js/jquery-ui-1.11.4.custom/jquery-ui.js" type="text/javascript"></script>
        
        <script src="recursos/js/bootstrap.js" type="text/javascript"></script>

        <script src="recursos/js/moment.js" type="text/javascript"></script>
        <script src="recursos/js/transition.js" type="text/javascript"></script>
        <script src="recursos/js/collapse.js" type="text/javascript"></script>
        
        <script src="recursos/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
        <script src="recursos/js/es.js" type="text/javascript"></script>
        <script src="recursos/js/ar.js" type="text/javascript"></script>
        <script src="recursos/js/moment.min.js" type="text/javascript"></script>

        <script>
    var userLang = navigator.language || navigator.userLanguage;

// Check if the li for the browsers language is available
// and set active if it is available
    
//FUNCION PARA ELEGIR VARIOS NIÑOS Y ARRASTRARLOS A LA ACTIVIDAD
    $(function () {
    $('.droptrue').on('click', 'img', function () {
        $(this).toggleClass('selected');
    });

    $("div.droptrue").sortable({
        connectWith: 'div.droptrue',
        opacity: 0.6,
        revert: true,
        helper: function (e, item) {
            console.log('parent-helper');
            console.log(item);
            if(!item.hasClass('selected'))
               item.addClass('selected');
            var elements = $('.selected').not('.ui-sortable-placeholder').clone();
            var helper = $('<div/>');
            item.siblings('.selected').addClass('hidden');
            return helper.append(elements);
        },
        start: function (e, ui) {
            var elements = ui.item.siblings('.selected.hidden').not('.ui-sortable-placeholder');
            ui.item.data('items', elements);
        },
        receive: function (e, ui) {
            ui.item.before(ui.item.data('items'));
        },
        stop: function (e, ui) {
            ui.item.siblings('.selected').removeClass('hidden');
            $('.selected').removeClass('selected');
        },
        update: updatePostOrder
    });

    $("#sortable1, #sortable2").disableSelection();
    $("#sortable1, #sortable2").css('minHeight', $("#sortable1").height() + "px");
    updatePostOrder();
});

    function updatePostOrder() {
    var arr = [];
    $("#sortable2 img").each(function () {
        arr.push($(this).attr('id'));
    });
    $('#postOrder').val(arr.join(','));
}
            var locale = 'es';
            
    //CAMBIAR IDIOMA CALENDARIO
    $("#idioma").change(function () {


        var idioma = $('#idioma option:selected').val();

        if(idioma === "arabe")
        {
            locale = 'ar';
        }
        else if(idioma === "espanol")
        {
            locale = 'es';
        }
        else{
            locale = 'en';
        }

    }); 
    
    var username = "colegio2";
    var myDate = new Date();
    
    
    $(document).ready(function(){
        
        if (username === "colegio1") {
            $("head").append('<link rel="stylesheet" href="recursos/css/colegio1.css" type="text/css" />');
        } else {
            $("head").append('<link rel="stylesheet" href="recursos/css/colegio2.css" type="text/css" />');
        }
        //Muestra etiquetas
        $('[data-toggle="tooltip"]').tooltip();
        
         //Muestra calendario
       $('.datetimepicker').datetimepicker({
            
            format: 'HH:mm',
            locale: userLang.valueOf(),
            daysOfWeekDisabled: [0, 6],
//            date: horaActual
            defaultDate: myDate
        });
        $('#datetimepickerinicio').datetimepicker({
            
            format: 'DD-MM-YYYY HH:mm',
            locale: userLang.valueOf(),
            daysOfWeekDisabled: [0, 6],
            useCurrent: false //Important! See issue #1075
        });
        $('#datetimepickerfin').datetimepicker({
            
            format: 'DD-MM-YYYY HH:mm',
            locale: userLang.valueOf(),
            daysOfWeekDisabled: [0, 6],
            useCurrent: false //Important! See issue #1075
        });
        
        $("#datetimepickerinicio").on("dp.change", function (e) {
            $('#datetimepickerfin').data("DateTimePicker").minDate(e.date);
        });
        
        $("#datetimepickerfin").on("dp.change", function (e) {
            $('#datetimepickerinicio').data("DateTimePicker").maxDate(e.date);
        });
        
    
       //Menu lateral
        $('#nav-expander').on('click',function(e){
      		e.preventDefault();
      		$('body').toggleClass('nav-expanded');
      	});
      	$('#nav-close').on('click',function(e){
      		e.preventDefault();
      		$('body').removeClass('nav-expanded');
      	});
        $('#barralateral').mouseleave(function(o){
      		o.preventDefault();
      		$('body').removeClass('nav-expanded');
      	});
 
     
    });
        </script>

        
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
    
        <!--MENU LATERAL-->
        <div id="barralateral">
        <nav>
<!--<div class="main-menu">
 
            Include your navigation here-->
            
            <div class="col-xs-12 iconosmenulateral"><a href="index.htm"><input type="image" src="recursos/img/iconos/home-01.svg" data-toggle="tooltip" data-placement="top" title="<spring:message code="etiq.txthome"/>"></a></div>
            <div class="col-xs-12 iconosmenulateral"><a href="calendar.htm"><div class="center-block"><input type="image" src="recursos/img/iconos/Calendar-01.svg" data-toggle="tooltip" data-placement="top" title="<spring:message code="etiq.txtcalendar"/>"></div></a></div>
            <div class="col-xs-12 iconosmenulateral"><a href="lessonList.htm?accion=inicio"><div class="center-block"><input type="image" src="recursos/img/iconos/classroomDashboard-01.svg" data-toggle="tooltip" data-placement="top" title="<spring:message code="etiq.txtlessons"/>"></div></a></div>
            <div class="col-xs-12 iconosmenulateral"><a href="messages.htm"><div class="center-block"><input type="image" src="recursos/img/iconos/messages-01.svg" data-toggle="tooltip" data-placement="top" title="<spring:message code="etiq.txtmessages"/>"></div></a></div>
            <div class="col-xs-12 iconosmenulateral"><a href="students.htm"><div class="center-block"><input type="image" src="recursos/img/iconos/students-01.svg" data-toggle="tooltip" data-placement="top" title="<spring:message code="etiq.txtstudents"/>"></div></a></div>
            <div class="col-xs-12">
                <hr>
            </div>
            <div class="col-xs-12 iconosmenulateral">
                <div class="col-xs-4 center-block text-center contenedorBandera">
                    <a class="btnBandera" href='datosIdioma.htm?lenguaje=en'><img width="30px" height="20px" src="recursos/img/iconos/flags/flag_en.png" title="<spring:message code="etiq.txtenglish"/>" alt="<spring:message code="etiq.txtenglish"/>"></a>
                </div>
                <div class="col-xs-4 center-block text-center contenedorBandera">
                    <a class="btnBandera" href='datosIdioma.htm?lenguaje=es'><img width="30px" src="recursos/img/iconos/flags/flag_es.png" title="<spring:message code="etiq.txtspanish"/>" alt="<spring:message code="etiq.txtspanish"/>"></a>
                </div>
                <div class="col-xs-4 center-block text-center contenedorBandera">
                    <a class="btnBandera" href='datosIdioma.htm?lenguaje=ar'><img width="30px" src="recursos/img/iconos/flags/flag_ar.png" title="<spring:message code="etiq.txtarabic"/>" alt="<spring:message code="etiq.txtarabic"/>"></a>
                </div>
            </div>
        </nav>    
        </div>
            
 
            <div>
                <!--Include your brand here-->
                <!--<a class="navbar-brand" href="#">Off Canvas Menu</a>-->
                <div class="navbar-header pull-right">
                  <a id="nav-expander" class="nav-expander fixed">
                    <spring:message code="etiq.txtmenu"/>
                  </a>
                </div>
            </div>
        </div>
        <!--MENU LATERAL FINAL-->
    
