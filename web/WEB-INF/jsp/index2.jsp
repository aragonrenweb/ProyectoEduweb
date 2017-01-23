<%@ include file="header.jsp" %>
    <head>
         <title>A Drill down report for all subjects in Grade 1- Quarter 2</title>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
         <script type="text/javascript" src="https://www.google.com/jsapi"></script>
         <script type="text/javascript">

            var queryObject="";

            var queryObjectLen="";

            $.ajax({

                type : 'POST',

                url : 'getdata.jsp',

                dataType:'json',

                success : function(data) {

                    queryObject = eval('(' + JSON.stringify(data) + ')');

                    queryObjectLen = queryObject.empdetails.length;

                },

                    error : function(xhr, type) {

                    alert('server error occoured')

                }

            });

            google.load("visualization", "1", {packages:["corechart"]});

            google.setOnLoadCallback(drawChart);

            function drawChart() {

           var data = new google.visualization.DataTable();

                data.addColumn('string', 'name');

                data.addColumn('number', 'empid');

                for(var i=0;i<queryObjectLen;i++)

                {

                    var name = queryObject.empdetails[i].name;

                    var empid = queryObject.empdetails[i].empid;

                    data.addRows([

                        [name,parseInt(empid)]

                    ]);

                }

                var options = {

                    title: 'Employee Information',

                };

  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));




 chart.draw(data,options);

 }
        </script>
         </head>
         <body>
               <div id="chart_div"></div>
          </body>
    
<%@ include file="footer.jsp" %>
