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
            <form action="/CMS/AtendimentoController" method="POST">


                <div class="ui labeled icon menu">
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                        <i class="home icon"></i>
                    </a>
                    <a href="/CMS/AtendimentoController?action=Socio.CadastraSocio" class="item pinfo" data-content="Adicionar sócio">
                        <i class="add icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeSocio" placeholder="Procurar sócios...">
                            <button class="ui button" type="submit" name="action" value="Socio.PesquisaSocio">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <div class="ui divided items segment">
                    <c:forEach items="${listaSocio}" var="k">
                        <div class="item">
                            <div class="ui tiny circular image">
                                <c:choose>
                                    <c:when test="${(k.sexoSocio == 'm')}">
                                        <img src="/CMS/img/elliot.jpg">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/CMS/img/stevie.jpg"> 
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="content">
                                <a class="header">${k.idSocio} - ${k.nomeSocio}</a>
                                <div class="meta">
                                    <span class="cinema"><b>Membro desde: </b><fmt:formatDate pattern="dd/MM/yyyy" value="${k.inclusaoSocio}" /> | <b>Categoria:</b> ${k.categoriaSocio.nomeCategoriaSocio}</span>
                                </div>
                                <div class="description">
                                    <p></p>
                                </div>
                                <div class="extra">
                                    <a class="ui label teal pinfo"><i class="info icon"></i>Informações</a>

                                    <div class="ui flowing popup bottom left transition hidden">
                                        <div class="ui two column divided grid">
                                            <div class="column">
                                                <h4 class="ui header">Status do sócio </h4>
                                                <p>
                                                    <c:choose>
                                                        <c:when test="${(k.statusSocio == 'a')}">
                                                            Ativo(a)
                                                        </c:when>
                                                        <c:otherwise>
                                                            Bloqueado(a) 
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                            <div class="column">
                                                <h4 class="ui header">Status do usuário </h4>
                                                <p>
                                                    <c:choose>
                                                        <c:when test="${(k.usuarioSocio.statusUsuario == 'a')}">
                                                            Ativo(a)
                                                        </c:when>
                                                        <c:otherwise>
                                                            Aguardando primeiro acesso 
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </div>
                                    </div>

                                    <c:choose>
                                        <c:when test="${(k.statusSocio == 'a')}">
                                            <a class="ui label teal grey"><i class="unlock icon disabled"></i>Ativar</i></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/CMS/AtendimentoController?action=Socio.ValidaSocio&idSocio=${k.idSocio}" class="ui label teal"><i class="unlock icon"></i>Ativar</i></a>
                                        </c:otherwise>
                                    </c:choose>

                                    <a href="/CMS/AtendimentoController?action=Pdf.GeraContrato&idSocio=${k.idSocio}" target='new' class="ui label teal"><i class="file pdf outline icon"></i>Contrato</a>
                                    <a href="/CMS/AtendimentoController?action=Pdf.GeraCarteirinhaSocio&idSocio=${k.idSocio}" target='new' class="ui label teal"><i class="payment icon"></i>Carteirinha</a>
                                    <a href="/CMS/SociedadeController?action=Dependente.ListaDependente&idSocio=${k.idSocio}" class="ui label teal"><i class="smile icon"></i>Dependentes</a>
                                    <a href="/CMS/AtendimentoController?action=Socio.AlteraSocio&idSocio=${k.idSocio}" class="ui label orange"><i class="edit icon"></i>Editar</a>
                                    <a href="/CMS/AtendimentoController?action=Socio.VisualizaSocio&idSocio=${k.idSocio}" class="ui label blue"><i class="eye icon"></i>Visualizar</a>
                                    <a id-del="${k.idSocio}" class="ui label red btn-apagar"><i class="trash icon"></i>Excluir</a>
                                </div>
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
</div>
</body>
</html>
