<%-- 
    Document   : buttonFragment
    Created on : 08-may-2016, 20:28:20
    Author     : shahadbawi
--%>





<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Graficas</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="img/logo.ico">
        <link rel="shortcut icon" href="img/logo.ico">
        <link href="recursos/css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="recursos/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="recursos/css/bootstrap.min.css">
        <link rel="stylesheet" href="recursos/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="recursos/css/main.css">
        
        <!--Load the AJAX API-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
    
    google.charts.load('current', {'packages': ['corechart', 'bar']});
    
    // Barras verticales
        google.charts.setOnLoadCallback(graficaBarras);
        function graficaBarras() {

            var data = google.visualization.arrayToDataTable([
                ['Signature', 'Cumulative Average', {role: 'style'}],
                ['English', 71.0, '#b87333'], // RGB value
                ['Mathematics', 81.5, 'silver'], // English color name
                ['Science', 83.0, 'green'],
                ['Social\n\Studies', 80.6, 'blue'],
                ['Art/Drama', 77.7, 'purple'],
                ['Arabic', 82.6, '#FAECAF'],
                ['Islamic', 88.2, 'black'],
                ['Quran', 84.7, '#AFFAF2'],
                ['Computer', 94.5, 'grey'],
                ['Music', 87.8, '#ED9C2B'],
                ['Physical\n\Education', 81.8, 'gold']
            ]);


            var options = {
                legend: {position: 'none'},
                title: 'A Drill down report for all subjects in Grade 1- Quarter 2',
                bar: {groupWidth: "75%"},
                hAxis: {
                    title: 'Signature'
                },
                vAxis: {
                    title: 'Cumulative Average'
                }
            };

            var chart = new google.visualization.ColumnChart(
                    document.getElementById('graficaBarras'));

            chart.draw(data, options);
        }

    // Grafica sectores
        google.charts.setOnLoadCallback(graficaSectores);
        function graficaSectores() {
            var data = google.visualization.arrayToDataTable([
                ['Signature', 'Cumulative Average'],
                ['English', 10.0], // RGB value
                ['Mathematics', 4],
                ['Science', 5],
                ['Social Studies', 1],
                ['Art/Drama', 5],
                ['Arabic', 0],
                ['Islamic', 1],
                ['Quran', 1],
                ['Computer', 0],
                ['Music', 0],
                ['Physical Education', 0]
            ]);
            var options = {
                title: 'Number of students not meeting expectation (below 50%) for all subjects Grade 1- Quarter 2',
                colors: ['#b87333', 'silver', 'green', 'blue', 'purple', '#FAECAF', 'black', '#AFFAF2', 'grey', '#ED9C2B', 'gold']
            };

            var sectores = new google.visualization.PieChart(document.getElementById('graficaSectores'));
            sectores.draw(data, options);
        }    
    //Graficas lineas reading
        google.charts.setOnLoadCallback(lineasReading);
        function lineasReading() {
        var data = google.visualization.arrayToDataTable([
          ['Subject', 'Fall 2014 RIT', 'Spring 2015 RIT'],
          ['Class Average',  158.40, 190.00],
          ['International Average',  165.00, 199.00]
        ]);

        var options = {
          title: 'Reading Trend',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasReading = new google.visualization.LineChart(document.getElementById('graficaReading'));

        lineasReading.draw(data, options);
      }
    //Graficas lineas language
        google.charts.setOnLoadCallback(lineasLanguage);
        function lineasLanguage() {
        var data = google.visualization.arrayToDataTable([
          ['Subject', 'Fall 2014 RIT', 'Spring 2015 RIT'],
          ['Class Average',  157.63, 191.00],
          ['International Average',  166.00, 200.00]
        ]);

        var options = {
          title: 'Language Trend',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasLanguage = new google.visualization.LineChart(document.getElementById('graficaLanguage'));

        lineasLanguage.draw(data, options);
      };
    //Graficas lineas mathematics  
        google.charts.setOnLoadCallback(lineasMathematics);
        function lineasMathematics() {
        var data = google.visualization.arrayToDataTable([
          ['Subject', 'Fall 2014 RIT', 'Spring 2015 RIT'],
          ['Class Average',  163.12, 192.00],
          ['International Average',  171.10, 203.00]
        ]);

        var options = {
          title: 'Mathematics Trend',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasMathematics = new google.visualization.LineChart(document.getElementById('graficaMathematics'));

        lineasMathematics.draw(data, options);
      };
      
    //Grafica Report grado3

        google.charts.setOnLoadCallback(lineasGrado3);
        function lineasGrado3() {
        var data = google.visualization.arrayToDataTable([
          ['RIT', 'Reading', 'Language','Mathematics'],
          ['Fall 2014 RIT',  159.83, 160.42, 164.28],
          ['Spring 2015 RIT', 166.64, 168.35, 174.45]
        ]);

        var options = {
          title: 'Grade 3',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasGrado3 = new google.visualization.LineChart(document.getElementById('graficaReportGrade3'));

        lineasGrado3.draw(data, options);
      };
      
    //Grafica Report grado6

        google.charts.setOnLoadCallback(lineasGrado6);
        function lineasGrado6() {
        var data = google.visualization.arrayToDataTable([
          ['RIT', 'Reading', 'Language','Mathematics'],
          ['Fall 2014 RIT',  159.83, 160.42, 164.28],
          ['Spring 2015 RIT', 166.64, 168.35, 174.45]
        ]);

        var options = {
          title: 'Grade 6',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasGrado6 = new google.visualization.LineChart(document.getElementById('graficaReportGrade6'));

        lineasGrado6.draw(data, options);
      };
      
    //Grafica Report grado9

        google.charts.setOnLoadCallback(lineasGrado9);
        function lineasGrado9() {
        var data = google.visualization.arrayToDataTable([
          ['RIT', 'Reading', 'Language','Mathematics'],
          ['Fall 2014 RIT',  203.73, 205.55, 213.32],
          ['Spring 2015 RIT', 195.55, 205.15, 214.76]
        ]);

        var options = {
          title: 'Grade 9',
          curveType: 'function',
          legend: { position: 'bottom' }
          
        };

        var lineasGrado9 = new google.visualization.LineChart(document.getElementById('graficaReportGrade9'));

        lineasGrado9.draw(data, options);
      };
    
    // Barras agrupadas Grupo3

        google.charts.setOnLoadCallback(graficaBarrasAnidadasGrado3);
        function graficaBarrasAnidadasGrado3() {
          
        var data = google.visualization.arrayToDataTable([
            ['Asignature',     'Average RIT Score Fall 2014 RIT', {role: 'style'},{ role: 'annotation' },    'Average RIT Score Spring 2015 RIT', {role: 'style'}, { role: 'annotation' }],
            ['Reading',        159.83, 'blue',      'Fall',                                                     166.64, 'cyan', 'Spring'],
            ['Language',       160.42, 'yellow',    'Fall',                                                     168.35, 'gold', 'Spring'],                
            ['Math',           164.28, 'green',     'Fall',                                                     174.45, 'greenyellow', 'Spring'],
            ['',               0, 'black',     '',                                                     0, '',     ''],
            ['Reading',        187.31, 'blue',      'Fall',                                                     190.97, 'cyan', 'Spring'],
            ['Language',       185.61, 'yellow',    'Fall',                                                     197.53, 'gold', 'Spring'],
            ['Math',           190.72, 'green',     'Fall',                                                     199.69, 'greenyellow', 'Spring'],
            ['',               , 'black',     '',                                                     0, '',     ''],
            ['Reading',        203.73, 'blue',      'Fall',                                                     195.55, 'cyan', 'Spring'],
            ['Language',       205.55, 'yellow',    'Fall',                                                     205.15, 'gold', 'Spring'],
            ['Math',           213.32, 'green',     'Fall',                                                     214.76, 'greenyellow', 'Spring']
             
         ]);
         var options = {
                legend: {position: 'none'},
                
                bar: {
                    groupWidth: "65%",
                    width: "80%"
                },
                
                hAxis: {
                    gridlines:{color: 'black', count: 2},
                    minorGridlines:{count: 4}
                },
                vAxis: {
                    viewWindowMode:'explicit',
                    gridlines: { count: 14 }, 
                    viewWindow: {
                      max:220,
                      min:150
                        }
            
            }
            
           }; 
            
            var el = document.getElementById('graficaBarrasAnidadasGrado3');
        
            var graficaBarrasAnidadasGrado3 = new google.visualization.ColumnChart(el);
            
            graficaBarrasAnidadasGrado3.draw(data, options);
            
        };
        
        //Grafica combo
        //
       google.charts.setOnLoadCallback(graficaCombo);


      function graficaCombo() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
         ['Grade', 'Average RIT Score Reading Fall 2014 RIT', 'Average RIT Score Reading Spring 2015 RIT', 'Average RIT Score Language Fall 2014 RIT', 'Average RIT Score Language Spring 2015 RIT', 'Average RIT Score Mathematics Fall 2014 RIT', 'Average RIT Score Mathematics Spring 2015 RIT', 'International Average RIT Score Reading Fall 2014 RIT', 'International Average RIT Score Reading Spring 2015 RIT', 'International Average RIT Score Language Fall 2014 RIT', 'International Average RIT Score Language Spring 2015 RIT', 'International Average RIT Score Mathematics Fall 2014 RIT', 'International Average RIT Score Mathematics Spring 2015 RIT' ],
         ['Grade3',  159.83,                                    166.64,                                     160.42,                                     168.35,                                         164.28,                                         174.45,                                          190.00,                                                199.00,                                                     191.00,                                                 200.00,                                                     192.00,                                                       203.00],
         ['Grade6',  187.31,                                    190.97,                                     185.61,                                     197.53,                                         190.72,                                         199.69,                                          212.00,                                                216.00,                                                     212.00,                                                 216.00,                                                     220.00,                                                       226.00],
         ['Grade9',  203.73,                                    195.55,                                     205.55,                                     205.15,                                         213.32,                                         214.76,                                          221.00,                                                223.00,                                                     221.00,                                                 222.00,                                                     234.00,                                                       236.00]
         ]);

    var options = {
      bar: {
                    groupWidth: "65%",
                    width: "80%"
                },
      vAxis: {      title: 'Cups',
                    viewWindowMode:'explicit',
                    gridlines: { count: 14 }, 
                    viewWindow: {
                      max:240,
                      min:150
                        }},
      hAxis: {title: 'Month'},
      seriesType: 'bars',
      series: {6: {type: "line"}, 7: {type: "line"}, 8: {type: "line"}, 9: {type: "line"},10: {type: "line"}, 11: {type: "line"}}

    };

    var chart = new google.visualization.ComboChart(document.getElementById('graficaCombo'));
    chart.draw(data, options);
  } 
        // Barras agrupadas Grupo6
//        google.charts.setOnLoadCallback(graficaBarrasAnidadasGrado6);
//        function graficaBarrasAnidadasGrado6() {
//          
//        var data = google.visualization.arrayToDataTable([
//         ['Asignature',     'Fall', {role: 'style'},    'Spring', {role: 'style'}],
//         ['Reading',        187.31, 'blue',             190.97, 'cyan'],
//         ['Language',       185.61, 'yellow',           197.53, 'gold'],
//         ['Math',           190.72, 'green',            199.69, 'greenyellow']
//         
//
//         ]);
//            var options = {
//                legend: {position: 'bottom'},
//                title: 'Grade 6',
//                bar: {groupWidth: "75%"},
//                
//            };
//
//            var graficaBarrasAnidadasGrado6 = new google.visualization.ColumnChart(
//                    document.getElementById('graficaBarrasAnidadasGrado6'));
//
//            graficaBarrasAnidadasGrado6.draw(data, options);
//        };
        // Barras agrupadas Grupo9
//        google.charts.setOnLoadCallback(graficaBarrasAnidadasGrado9);
//        function graficaBarrasAnidadasGrado9() {
//          
//        var data = google.visualization.arrayToDataTable([
//         ['Asignature',     'Fall', {role: 'style'},    'Spring', {role: 'style'}],
//         ['Reading',        203.73, 'blue',             195.55, 'cyan'],
//         ['Language',       205.55, 'yellow',           205.15, 'gold'],
//         ['Math',           213.32, 'green',            214.76, 'greenyellow']
//         
//
//         ]);
//            var options = {
//                legend: {position: 'none'},
//                title: 'Grade 9',
//                bar: {groupWidth: "75%"},
//                
//            };
//
//            var graficaBarrasAnidadasGrado9 = new google.visualization.ColumnChart(
//                    document.getElementById('graficaBarrasAnidadasGrado9'));
//
//            graficaBarrasAnidadasGrado9.draw(data, options);
//        };
        
    $(window).resize(function(){
        graficaBarras();
        graficaSectores();
        lineasReading();
        lineasLanguage();
        lineasMathematics();
        lineasGrado3();
        lineasGrado6();
        lineasGrado9();
        graficaBarrasAnidadasGrado3();
        graficaCombo();
//        graficaBarrasAnidadasGrado6();
//        graficaBarrasAnidadasGrado9();
    });
        
    //Jquery boton volver arriba
    var username = "colegio2";
    $(document).ready(function () {
        if (username === 'colegio1') {
            $('head').append('<link rel="stylesheet" href="recursos/css/colegio1.css" type="text/css" />');
        } else {
            $('head').append('<link rel="stylesheet" href="recursos/css/colegio2.css" type="text/css" />');
        }
    });
    
        </script>

    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#" style="margin: -15px;"><div class="banner img-responsive"></div></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li role="separator" class="divider"></li>
                        <li class="active"><a href="#">schedules<span class="sr-only">(current)</span></a></li>
                        <li><a href="#">absences</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">graphic<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="graphic.htm">Drilldown Report Q2 (ES)</a></li>
                                <li><a href="#panel3">MAP report 2014-2015</a></li>
                                <li><a href="#">Achievement Trend in English and Mathematics across Q1,Q2 (ES)</a></li>
                                <li><a href="#">Intervention Report Q2_ Maths & English_(ES)</a></li>
                                <li><a href="#">Top Rankers Subject Toppers Q2 (ES)</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">RRT_ QTR2_GRADE1A_AY2015-2016</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="lessons.htm">Extras</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search signature">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Links <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="http://www.cbskuwait.com/">Home</a></li>
                                <li><a href="http://www.moe.edu.kw/">Ministry of Education</a></li>
                                <li><a href="http://www.eduwebgroup.com/">EduWeb Group</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Contact Us</a></li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- Main jumbotron for a primary marketing message or call to action -->
        <a href="#" class="back-to-top" style="display: inline;">
           <i class="glyphicon glyphicon-chevron-up"></i>
        </a>


        <div class="col-lg-12">
            
            <div class="col-xs-12 table-responsive">
                <table class="table table-striped table-hover table-condensed">
                    <caption class="caption"><h3 class="text-center">A Drill down report for all subjects in Grade 1- Quarter 2</h3>
                        <div class="dropdown">
                            <button class="btn btn-red dropdown-toggle" type="button" data-toggle="dropdown">
                                Select the Grade 
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Grade 1</a></li>
                                <li><a href="#">Grade 2</a></li>
                                <li><a href="#">Grade 3</a></li>
                                <li><a href="#">Grade 4</a></li>
                                <li><a href="#">Grade 5</a></li>
                                <li><a href="#">Grade 6</a></li>
                            </ul>
                        </div>
                    </caption>
                    <thead>
                        <tr>
                            <th></th>
                            <th>English</th>
                            <th>Mathematics</th>
                            <th>Science</th>
                            <th>Social Studies</th>
                            <th>Art/Drama</th>
                            <th>Arabic</th>
                            <th>Islamic</th>
                            <th>Quran</th>
                            <th>Computer</th>
                            <th>Music</th>
                            <th>Physical Education</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>GRADE 1A</td>
                            <td>69,8</td>
                            <td>88,0</td>
                            <td>87,3</td>
                            <td>79,6</td>
                            <td>80,9</td>	
                            <td>83,7</td>	
                            <td>88,5</td>
                            <td>82,2</td>
                            <td>95,4</td>
                            <td>86,2</td>	
                            <td>81,7</td>
                        </tr>
                        <tr>
                            <td>GRADE 1B</td>
                            <td>67,8</td>
                            <td>75,3</td>
                            <td>81,5</td>
                            <td>82,8</td>
                            <td>81,3</td>
                            <td>85,3</td>
                            <td>88,1</td>
                            <td>86,0</td>
                            <td>93,0</td>
                            <td>89,3</td>
                            <td>81,0</td>
                        </tr>
                        <tr>
                            <td>GRADE 1C</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>GRADE 1D</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>GRADE 1E</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Cumulative Average</td>
                            <td>71,0</td>	
                            <td>81,5</td>
                            <td>83,0</td>
                            <td>80,6</td>
                            <td>77,7</td>
                            <td>82,6</td>
                            <td>88,2</td>
                            <td>84,7</td>
                            <td>94,5</td>
                            <td>87,0</td>
                            <td>81,8</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-xs-12">
                <div class="panel panel-default" id="panel1">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-target="#grafica1" href="#grafica1">Mostrar Gráfica</a>
                        </h4>
                    </div>
                    <div id="grafica1" class="panel-collapse collapse in">
                        <div class="panel-body center-block">
                            <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                <div id="graficaBarras" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            


            <div class="col-xs-12 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption" style="text-align: center;"><h3>Number of students not meeting expectation (below 50%) for all subjects Grade 1- Quarter 2</h3></caption>
                        <thead>
                            <tr>
                                <th></th>
                                <th>English</th>
                                <th>Mathematics</th>
                                <th>Science</th>
                                <th>Social Studies</th>
                                <th>Art/Drama</th>
                                <th>Arabic</th>
                                <th>Islamic</th>
                                <th>Quran</th>
                                <th>Computer</th>
                                <th>Music</th>
                                <th>Physical Education</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>GRADE 1A</td>
                                <td>4</td>
                                <td></td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>	
                                <td></td>	
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>	
                                <td></td>
                            </tr>
                            <tr>
                                <td>GRADE 1B</td>
                                <td>4</td>
                                <td>2</td>
                                <td>1</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>GRADE 1C</td>
                                <td>1</td>
                                <td>1</td>
                                <td></td>
                                <td></td>
                                <td>2</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>GRADE 1D</td>
                                <td>1</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>2</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>GRADE 1E</td>
                                <td></td>
                                <td>1</td>
                                <td>3</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>1</td>
                                <td>1</td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Total No. of students not meeting expectations.</td>
                                <td>10</td>	
                                <td>4</td>
                                <td>5</td>
                                <td>1</td>
                                <td>5</td>
                                <td></td>
                                <td>1</td>
                                <td>1</td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
            </div>  
            <div class="col-xs-12">
                    <div class="panel panel-default" id="panel2">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-target="#grafica2" href="#grafica2">Mostrar Gráfica</a>
                            </h4>
                        </div>
                        <div id="grafica2" class="panel-collapse collapse in">
                            <div class="panel-body center-block">
                                <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                    <div id="graficaSectores" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            <!-- MAP REPORT 2014-2015 -->
            <div class="col-xs-12"><h3 class="text-center">Classwise report for Grade 3A</h3></div>
            <!-- Tabla seleccion grado-->
            <div class="col-xs-12 col-lg-6 col-md-6 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption cabecera">
                            <div class="col-lg-6">
                                <h3 style="text-align: right;">Average RIT Score -</h3>
                            </div>
                            <div class="col-lg-6">
                                <div class="dropdown" style="margin-top: 20px; margin-bottom: 10px;">
                            <button class="btn btn-red dropdown-toggle" type="button" data-toggle="dropdown">
                                Select the Grade 
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Grade 3A</a></li>
                                <li><a href="#">Grade 3B</a></li>
                                <li><a href="#">Grade 3C</a></li>
                                <li><a href="#">Grade 6A</a></li>
                                <li><a href="#">Grade 9A</a></li>
                            </ul>
                            </div>
                        
                        <thead>
                            <tr>
                                <th>Subject</th>
                                <th>Fall 2014 RIT</th>
                                <th>Spring 2015 RIT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Reading</td>
                                <td>158,40</td>
                                <td>165,00</td>
                            </tr>
                            <tr>
                                <td>Language</td>
                                <td>157,63</td>
                                <td>166,00</td>
                            </tr>
                            <tr>
                                <td>Mathematics</td>
                                <td>163,12</td>
                                <td>171,10</td>
                            </tr>
                        </tbody>
                    </table>
            </div>
            <div class="col-xs-12 col-lg-6 col-md-6 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption" style="text-align: center;"><h3>International Average RIT Score- Grade 3</h3>
                        
                        <thead>
                            <tr>
                                <th>Subject</th>
                                <th>Fall 2014 RIT</th>
                                <th>Spring 2015 RIT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Reading</td>
                                <td>190,00</td>
                                <td>199,00</td>
                            </tr>
                            <tr>
                                <td>Language</td>
                                <td>191,00</td>
                                <td>200,00</td>
                            </tr>
                            <tr>
                                <td>Mathematics</td>
                                <td>192,00</td>
                                <td>203,00</td>
                            </tr>
                        </tbody>
                    </table>
            </div>
            <!-- graficas lineas-->            
            <div class="col-xs-12">
                    <div class="panel panel-default" id="panel3">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-target="#grafica3" href="#grafica3">Mostrar Gráficas</a>
                            </h4>
                        </div>
                        <div id="grafica3" class="panel-collapse collapse in">
                            <div class="panel-body center-block">
                                <!-- colum graf uno -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaReading" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                <!-- colum graf dos -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaLanguage" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                <!-- colum graf tres -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaMathematics" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            <!-- Tabla todos los grados-->
            <div class="col-lg-12">
                <div class="col-xs-12"><h3 class="text-center">Class Wise Average RIT Score - Table view</h3></div>
            
            <!--Reading-->
            <div class="col-xs-12 col-lg-4 col-md-4 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption cabecera">
                            <h3 style="text-align: right;">Reading - Average RIT Score</h3>
                        </caption>
                        <thead>
                            <tr>
                                <th>Grade</th>
                                <th>Fall 2014 RIT</th>
                                <th>Spring 2015 RIT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Grade 3A</td>
                                <td>159,41</td>
                                <td>168,34</td>
                            </tr>
                            <tr>
                                <td>Grade 3B</td>
                                <td>158,40</td>
                                <td>165,00</td>
                            </tr>
                            <tr>
                                <td>Grade 3C</td>
                                <td>161,68</td>
                                <td>166,57</td>
                            </tr>
                            <tr>
                                <td>Grade 6A</td>
                                <td>187,31</td>
                                <td>190,97</td>
                            </tr>
                            <tr>
                                <td>Grade 9A</td>
                                <td>203,73</td>
                                <td>195,55</td>
                            </tr>
                        </tbody>
                    </table>
            </div>
            <!--Language-->
            <div class="col-xs-12 col-lg-4 col-md-4 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption cabecera">
                            <h3 style="text-align: right;">Language - Average RIT Score</h3>
                        </caption>
                        <thead>
                            <tr>
                                <th>Grade</th>
                                <th>Fall 2014 RIT</th>
                                <th>Spring 2015 RIT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Grade 3A</td>
                                <td>162,59,41</td>
                                <td>168,34</td>
                            </tr>
                            <tr>
                                <td>Grade 3B</td>
                                <td>158,40</td>
                                <td>165,00</td>
                            </tr>
                            <tr>
                                <td>Grade 3C</td>
                                <td>161,68</td>
                                <td>166,57</td>
                            </tr>
                            <tr>
                                <td>Grade 6A</td>
                                <td>187,31</td>
                                <td>190,97</td>
                            </tr>
                            <tr>
                                <td>Grade 9A</td>
                                <td>203,73</td>
                                <td>195,55</td>
                            </tr>
                        </tbody>
                    </table>
            </div>
            <!--Mathematics-->
            <div class="col-xs-12 col-lg-4 col-md-4 table-responsive">
                    <table class="table table-striped table-hover">
                        <caption class="caption cabecera">
                            <h3 style="text-align: right;">Reading - Average RIT Score</h3>
                        </caption>
                        <thead>
                            <tr>
                                <th>Grade</th>
                                <th>Fall 2014 RIT</th>
                                <th>Spring 2015 RIT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Grade 3A</td>
                                <td>159,41</td>
                                <td>168,34</td>
                            </tr>
                            <tr>
                                <td>Grade 3B</td>
                                <td>158,40</td>
                                <td>165,00</td>
                            </tr>
                            <tr>
                                <td>Grade 3C</td>
                                <td>161,68</td>
                                <td>166,57</td>
                            </tr>
                            <tr>
                                <td>Grade 6A</td>
                                <td>187,31</td>
                                <td>190,97</td>
                            </tr>
                            <tr>
                                <td>Grade 9A</td>
                                <td>203,73</td>
                                <td>195,55</td>
                            </tr>
                        </tbody>
                    </table>
            </div>
            </div>
            <div class="col-xs-12"><h3 class="text-center">MAP Report for 2014-2015</h3></div>
            																	
            <!-- graficas lineas x grado-->            
            <div class="col-xs-12">
                    <div class="panel panel-default" id="panel4">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-target="#grafica3" href="#grafica3">Mostrar Gráficas</a>
                            </h4>
                        </div>
                        <div id="grafica3" class="panel-collapse collapse in">
                            <div class="panel-body center-block">
                                <!-- colum graf uno -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaReportGrade3" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                <!-- colum graf dos -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaReportGrade6" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                <!-- colum graf tres -->
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaReportGrade9" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            <!--Graficas barras anidadas-->
            <div class="col-xs-12">
                    <div class="panel panel-default" id="panel4">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-target="#grafica4" href="#grafica4">Mostrar Gráficas</a>
                            </h4>
                        </div>
                        <div id="grafica3" class="panel-collapse collapse in">
                            <div class="col-lg-6 col-lg-offset-3 text-center"><h4>School Growth Report</h4></div>
                            <div class="panel-body center-block">
                                <!-- colum graf uno -->
                                <div class="col-lg-12">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaBarrasAnidadasGrado3" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
<!--                                </div>
                                 colum graf dos 
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaBarrasAnidadasGrado6" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                 colum graf tres 
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaBarrasAnidadasGrado9" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </div>
            </div>
            </div>
            <!--Grafica combo-->
            <div class="col-xs-12">
                    <div class="panel panel-default" id="panel4">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-target="#grafica5" href="#grafica4">Mostrar Gráfica Combo</a>
                            </h4>
                        </div>
                        <div id="grafica3" class="panel-collapse collapse in">
                            <div class="col-lg-6 col-lg-offset-3 text-center"><h4></h4></div>
                            <div class="panel-body center-block">
                                <!-- colum graf uno -->
                                <div class="col-lg-12">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaCombo" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
<!--                                </div>
                                 colum graf dos 
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaBarrasAnidadasGrado6" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>
                                 colum graf tres 
                                <div class="col-lg-4">
                                    <div id="chart_wrap" style="position: relative;padding-bottom: 65%;height:0;overflow:hidden;">
                                        <div id="graficaBarrasAnidadasGrado9" style="position: absolute;top: 0;left: 0;width:100%;height:100%;"></div>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </div>
            </div>
            </div>
                <hr>
                <div class="col-xs-12">
                <footer>
                    <p>&copy; Company 2016</p>
        </footer>
                    </div>
        </div> <!-- /container -->
        
           
            <script src="recursos/js/bootstrap.min.js"></script>

            <script src="recursos/js/main.js"></script>

            <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
            <script>
    (function (b, o, i, l, e, r) {
        b.GoogleAnalyticsObject = l;
        b[l] || (b[l] =
                function () {
                    (b[l].q = b[l].q || []).push(arguments)
                });
        b[l].l = +new Date;
        e = o.createElement(i);
        r = o.getElementsByTagName(i)[0];
        e.src = '//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e, r)
    }(window, document, 'script', 'ga'));
    ga('create', 'UA-XXXXX-X', 'auto');
    ga('send', 'pageview');
            </script>
    </body>
</html>


