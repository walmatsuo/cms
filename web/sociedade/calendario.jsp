<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>
        <!--CSS do FullCalendar-->
        <link href='/CMS/fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='/CMS/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

        <!--CSS do Semantic UI-->
        <link rel="stylesheet" href="/CMS/semantic/semantic.min.css">

        <!--JavaScript do FullCalendar-->
        <script src='/CMS/fullcalendar/lib/moment.min.js'></script>
        <script src='/CMS/fullcalendar/lib/jquery.min.js'></script>
        <script src='/CMS/fullcalendar/fullcalendar.min.js'></script>
        <script src='/CMS/fullcalendar/lang-all.js'></script>

        <!--JavaScript do Semantic UI-->
        <script type="text/javascript" src="/CMS/semantic/semantic.min.js"></script>

        <!--CSS do FullCalendar (estilo proprio)-->
        <link href='/CMS/css/calendario.css' rel='stylesheet' />
        <link href='/CMS/jquery-ui/jquery-ui.min.css' rel='stylesheet' />

        <!--JavaScript do FullCalendar (configuração propria)-->
        <script src='/CMS/js/calendario.js'></script>


    </head>
    <body>
        <jsp:include page="/carregar/menu.jsp"></jsp:include>

            <div class="ui container">
                <div class="ui info message">
                    <i class="close icon"></i>
                    <div class="header" id="msg">
                    </div>
                </div>
                <div id="calendar">
                </div>
            </div>


        <jsp:include page="/carregar/calendarioAgendamento.jsp"></jsp:include>

        <jsp:include page="/carregar/calendarioVisualizacao.jsp"></jsp:include>

        <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
    </body>
</html>


