<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5; /CMS/principal.jsp">
        <title>Error 404</title>

        <jsp:include page="carregar/head.jsp"></jsp:include>
    </head>
    <body class="pge">
        <div class="ui page statistics centered grid topY">

            <div class="statistic">
                <div class="value"> <img src="img/cms-160x137.png" class="ui circular inline image"> </div>
                <div class="label">Página não encontrada <br/>
                    404<br/>
                    Você será redirecionado automaticamente em 5 segundos
                </div>
            </div>

        </div>
    </body>
</html>
