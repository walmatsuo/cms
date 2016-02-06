<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/ExcCategoria.js"></script>

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

            <!--Inicio da listagem de categorias-->
            <form action="GerenciaController" method="POST">
                <div class="ui labeled icon menu">
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                        <i class="home icon"></i>
                    </a>
                    <a href="GerenciaController?action=CategoriaSocio.CadastraCategoriaSocio" class="item pinfo" data-content="Adicionar categoria">
                        <i class="add icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeCategoria" placeholder="Procurar categorias...">
                            <button class="ui button" type="submit" name="action" value="CategoriaSocio.PesquisaCategoriaSocio">Pesquisar</button>
                        </div>
                    </div>
                </div>


                <div class="ui relaxed divided list segment selection">
                    <c:forEach items="${listaCategoria}" var="k">
                        <div class="item" id="linha${k.idCategoriaSocio}">
                            <div class="right floated content">
                                <div class="ui small basic icon buttons">
                                    <a class="ui button" href="GerenciaController?action=CategoriaSocio.AlteraCategoriaSocio&idCategoriaSocio=${k.idCategoriaSocio}"><i class="edit icon"></i></a>
                                    <a class="ui button btn-apagar" id-del="${k.idCategoriaSocio}"><i class="trash outline icon"></i></a>
                                </div>
                            </div>
                            <i class="large block layout middle aligned icon"></i>
                            <div class="content">
                                <div class="header">${k.nomeCategoriaSocio}</div>
                                <div class="description">${k.valorTitulo} / ${k.valorMensalidade}</div>
                            </div>
                        </div>
                    </c:forEach>      
                </div>
            </form>
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
        </div>

    </body>
</html>

