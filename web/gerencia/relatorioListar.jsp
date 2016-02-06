<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/ExcSocio.js"></script>

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


            <div class="ui labeled icon menu">
                <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                    <i class="home icon"></i>
                </a>
            </div>

            <h2 class="ui center aligned icon header">
                <i class="file outline icon"></i>
                <div class="content">Relatórios <div class="sub header"></div>
                </div>
            </h2>
            
            <div class="ui horizontal divider"><i class="caret down icon"></i></div>

            <div class="ui cards">
                <div class="card">
                    <div class="content">
                        <div class="header">${k.titulo}</div>
                        <div class="description">
                            <p>Escolha uma categoria e veja todos os sócios que estão associados a ela.</p>
                        </div>
                    </div>
                    <a href="/CMS/GerenciaController?action=Relatorio.PreparaSocioPorCategoria" class="ui bottom attached button">
                        <i class="file outline icon"></i>
                        Sócio por categoria
                    </a>
                </div>

                <div class="card">
                    <div class="content">
                        <div class="header">${k.titulo}</div>
                        <div class="description">
                            <p>Faz uma listagem detalhada de todos os sócios cadastrados no mês escolhido.</p>
                        </div>
                    </div>
                    <a href="/CMS/gerencia/relatorioSocioPorMes.jsp" class="ui bottom attached button">
                        <i class="file outline icon"></i>
                        Sócio por mês
                    </a>
                </div>

                <div class="card">
                    <div class="content">
                        <div class="header">${k.titulo}</div>
                        <div class="description">
                            <p>Saiba quais dependentes estão associados a um determinado parentesco.</p>
                        </div>
                    </div>
                    <a href="/CMS/GerenciaController?action=Relatorio.PreparaDependentePorParentesco" class="ui bottom attached button">
                        <i class="file outline icon"></i>
                        Dependente por parentesco
                    </a>
                </div>
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
    </div>
</body>
</html>
