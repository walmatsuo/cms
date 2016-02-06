<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/ExcDependente.js"></script>
        </head>
        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio da listagem-->

            <c:if test="${(msg != null)}">
                <div class="ui info message">
                    <i class="close icon"></i>
                    <div class="header">
                        <c:out value="${msg}"></c:out>
                        </div>
                    </div>
            </c:if>

            <form action="/CMS/SociedadeController" method="POST">


                <div class="ui labeled icon menu">
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                        <i class="home icon"></i>
                    </a>
                    <a href="/CMS/SociedadeController?action=Dependente.CadastraDependente" class="item pinfo" data-content="Adicionar dependente">
                        <i class="add icon"></i>
                    </a>

                    <a class="browse item pinfo">
                        <i class="info icon"></i>
                    </a>

                    <div class="ui flowing popup bottom left transition hidden">
                        <div class="ui two column divided grid">
                            <div class="column">
                                <h4 class="ui header">Título: </h4>
                                <p>${socioDependente.idSocio}</p>
                            </div>
                            <div class="column">
                                <h4 class="ui header">Nome:</h4>
                                <p>${socioDependente.nomeSocio}</p>
                            </div>
                        </div>
                    </div>

                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeDependente" placeholder="Procurar dependentes...">
                            <input type="hidden" name="idSocio" value= "${socioDependente.idSocio}">
                            <button class="ui button" type="submit" name="action" value="Dependente.PesquisaDependente">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <div class="ui special four grid cards">
                    <c:forEach items="${listaDependente}" var="k">
                        <div class="card column">
                            <div class="blurring dimmable image">
                                <div class="ui dimmer">
                                    <div class="content">
                                        <div class="center">
                                            <c:choose> 
                                                <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia' || usuarioAutenticado.categoriaUsuario == 'Atendimento')}">
                                                    
                                                    <c:choose> 
                                                        <c:when test="${(k.statusDependente == 'd' || k.statusDependente == 'b')}">
                                                            <a href="/CMS/SociedadeController?action=Dependente.ValidaDependente&idDependente=${k.idDependente}"><i class="unlock icon large green"></i></a>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <a href="/CMS/AtendimentoController?action=Pdf.GeraCarteirinhaDependente&idDependente=${k.idDependente}" target='new'><i class="payment icon large teal"></i></a>
                                                            <a href="/CMS/SociedadeController?action=Dependente.BloqueiaDependente&idDependente=${k.idDependente}"><i class="lock icon large yellow"></i></a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    <a href="/CMS/SociedadeController?action=Dependente.AlteraDependente&idDependente=${k.idDependente}"><i class="edit icon large orange"></i></a>
                                                    <a href="/CMS/SociedadeController?action=Dependente.VisualizaDependente&idDependente=${k.idDependente}"><i class="eye icon large blue"></i></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose> 
                                                            <c:when test="${(k.statusDependente == 'a')}">
                                                            <a href="/CMS/SociedadeController?action=Dependente.BloqueiaDependente&idDependente=${k.idDependente}"><i class="lock icon large yellow"></i></a>    
                                                            </c:when>
                                                            <c:otherwise>
                                                            <i class="disabled lock icon large"></i></a></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <a href="/CMS/SociedadeController?action=Dependente.VisualizaDependente&idDependente=${k.idDependente}"><i class="eye icon large blue"></i></a>
                                                    </c:otherwise>
                                                </c:choose>

                                            <a class="btn-apagar" id-del="${k.idDependente}"><i class="trash outline icon large red"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${(k.sexoDependente == 'm')}">
                                        <img src="/CMS/img/patrick.png">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/CMS/img/lindsay.png">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="content">
                                <a class="header">${k.nomeDependente}</a>
                                <div class="meta">
                                    <span class="date">${k.parentescoDependente.nomeParentesco}</span>
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    <c:choose>
                                        <c:when test="${(k.statusDependente == 'a')}">
                                            <i class="unlock icon"></i>
                                            Ativo(a)
                                        </c:when>
                                        <c:when test="${(k.statusDependente == 'b')}">
                                            <i class="unlock icon"></i>
                                            Bloqueado(a)
                                        </c:when>
                                        <c:otherwise>
                                            <i class="lock icon"></i>
                                            Documentação pendente 
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </form>

            <!--Fim da listagem de categorias-->

        </div>

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
