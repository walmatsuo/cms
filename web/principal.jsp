<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

            <c:if test="${(msg != null)}">
                <div class="ui info message">
                    <i class="close icon"></i>
                    <div class="header">
                        <c:out value="${msg}"></c:out>
                        </div>
                    </div>
            </c:if>


            <!--Verifica que categoria de usuario está logado-->
            <div class="ui four cards">
                <c:choose>
                    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia')}">

                        <jsp:include page="/gerencia/gerencia.jsp"></jsp:include>
                        <jsp:include page="/atendimento/atendimento.jsp"></jsp:include>

                    </c:when>
                    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Atendimento')}">

                        <jsp:include page="/atendimento/atendimento.jsp"></jsp:include>

                    </c:when>
                    <c:otherwise>

                        <jsp:include page="/sociedade/sociedade.jsp"></jsp:include>

                    </c:otherwise>
                </c:choose>
            </div>
            <!--Fim da verificação-->

            <!--Inicio do footer-->
            <jsp:include page="/carregar/footer.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do footer-->
            <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
            <!--Final do footer-->
        </div>
    </body>
</html>
