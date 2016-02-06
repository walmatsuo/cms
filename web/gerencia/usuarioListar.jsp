<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="../carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/ExcUsuario.js"></script>
            <script type="text/javascript" src="/CMS/js/resetaUsuario.js"></script>
        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="../carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

            <c:if test="${(msg != null)}">
                <div class="ui info message">
                    <i class="close icon"></i>
                    <div class="header">
                        <c:out value="${msg}"></c:out>
                        </div>
                    </div>
            </c:if>

            <!--Inicio da listagem de usuarios-->
            <form action="GerenciaController" method="POST">
                <div class="ui labeled icon menu">
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o inicio">
                        <i class="home icon"></i>
                    </a>
                    <a href="GerenciaController?action=Usuario.CadastraUsuario" class="item pinfo" data-content="Adicionar usuário">
                        <i class="add icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeUsuario" placeholder="Procurar usuarios...">
                            <button class="ui button" type="submit" name="action" value="Usuario.PesquisaUsuario">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <div class="ui four cards">
                    <c:forEach items="${listaUsuario}" var="k">
                        <div class="card">
                            <div class="content">
                                <img class="right floated mini ui image" src="/CMS/img/user.png">
                                <div class="header">
                                    ${k.nomeUsuario}
                                </div>
                                <div class="meta">
                                    ${k.categoriaUsuario}
                                </div>
                                <div class="description">
                                    ${k.email}
                                </div>
                            </div>
                            <div class="extra content">
                                <div class="ui basic icon buttons">
                                    <a href="GerenciaController?action=Usuario.AlteraUsuario&idUsuario=${k.idUsuario}" class="ui basic button pinfo" data-content="Editar"><i class="edit icon"></i></a>
                                    <a id-del="${k.idUsuario}" class="ui basic button pinfo btn-apagar" data-content="Excluir"><i class="trash icon"></i></a>
                                    <a id-cont="${k.idUsuario}" class="ui basic button pinfo btn-continuar" data-content="Resetar Senha"><i class="adjust icon"></i></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </form>
        </div>
        <!--Fim da listagem-->

        <!--Inicio do footer-->
        <jsp:include page="/carregar/footer.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do footer-->
        <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do modal de exclusao-->
        <jsp:include page="/carregar/modalExclusao.jsp"></jsp:include>
            <!--Final do modal-->
            <!--Inicio do modal de resete-->
        <jsp:include page="/carregar/modalInfo.jsp"></jsp:include>
        <!--Final do modal-->

    </body>
</html>
