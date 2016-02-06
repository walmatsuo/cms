<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>
        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/ExcEvento.js"></script>
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
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                        <i class="home icon"></i>
                    </a>
                    <a href="/CMS/AtendimentoController?action=Evento.CadastraEvento" class="item pinfo" data-content="Adicionar evento">
                        <i class="add icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeEvento" placeholder="Procurar eventos...">
                            <button class="ui button" type="submit" name="action" value="Evento.PesquisaEvento">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <div class="ui relaxed divided list segment selection">
                    <c:forEach items="${listaEvento}" var="k">
                        <div class="item" id="linha${k.idEvento}">
                            <div class="right floated content">
                                <div class="ui small basic icon buttons">
                                    <a class="ui button" href="/CMS/AtendimentoController?action=Evento.ListaInscrito&idEvento=${k.idEvento}"><i class="file text outline icon pinfo" data-content="Listar todos os inscritos nesse evento"></i></a>
                                    <a class="ui button" href="/CMS/AtendimentoController?action=Evento.AlteraEvento&idEvento=${k.idEvento}"><i class="edit icon pinfo" data-content="Editar"></i></a>
                                    <a class="ui button btn-apagar" id-del="${k.idEvento}"><i class="trash outline icon pinfo" data-content="Excluir"></i></a>
                                </div>
                            </div>
                            <i class="large ticket middle aligned icon"></i>
                            <div class="content">
                                <div class="header">${k.titulo}</div>
                                <div class="description">${k.local}</div>
                            </div>
                        </div>
                    </c:forEach>      
                </div>
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
        <!--Inicio do modal de exclusao-->
    <jsp:include page="/carregar/modalExclusao.jsp"></jsp:include>
    <!--Final do modal-->
</body>
</html>
