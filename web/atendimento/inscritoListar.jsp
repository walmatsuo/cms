<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="/CMS/AtendimentoController" method="POST">

                <div class="ui labeled icon menu">
                    <a href="/CMS/AtendimentoController?action=Evento.ListaEvento" class="item pinfo" data-content="Voltar">
                        <i class="arrow left icon"></i>
                    </a>
                    <a href="/CMS/AtendimentoController?action=Pdf.ListaInscrito&idEvento=${eventoInscrito.idEvento}" target='new' class="item pinfo" data-content="Imprimir lista">
                        <i class="file pdf outline icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeInscrito" placeholder="Procurar inscritos...">
                            <input type="hidden" name="idEvento" value= "${eventoInscrito.idEvento}">
                            <button class="ui button" type="submit" name="action" value="Evento.PesquisaInscrito">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <table class="ui attached celled table segment">
                    <thead>
                        <tr>
                            <th>Título Nº</th>
                            <th>Sócio</th>
                            <th>CPF</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaInscrito}" var="k">
                            <tr>
                                <td>${k.socio.idSocio}</td>
                                <td>${k.socio.nomeSocio}</td>
                                <td>${k.socio.cpfSocio}</td>
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
