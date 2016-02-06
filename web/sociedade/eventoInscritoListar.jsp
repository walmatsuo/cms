<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


            <!--Inicio da listagem-->
            <form action="/CMS/SociedadeController" method="POST">


                <div class="ui labeled icon menu">
                    <a href="/CMS/SociedadeController?action=AreaSocio.ListaEvento" class="item pinfo" data-content="Voltar">
                        <i class="arrow left icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeEventoInscrito" placeholder="Procurar eventos...">
                            <input type="hidden" name="idSocio" value= "${socioInscrito.idSocio}">
                            <button class="ui button" type="submit" name="action" value="AreaSocio.PesquisaInscrito">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <table class="ui attached celled table segment">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Evento</th>
                            <th>Data</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaEvento}" var="k">
                            <tr>
                                <td>${k.evento.idEvento}</td>
                                <td>${k.evento.titulo}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${k.evento.data}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


            </form>
        </div>
    </div>
    <!--Fim da listagem de categorias-->


    <!--Inicio do footer-->
    <jsp:include page="/carregar/footer.jsp"></jsp:include>
        <!--Final do footer-->
        <!--Inicio do footer-->
    <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
    <!--Final do footer-->
</body>
</html>
