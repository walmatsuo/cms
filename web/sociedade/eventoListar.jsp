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
            <form action="SociedadeController" method="POST">


                <div class="ui labeled icon menu">
                    <a href="/CMS/principal.jsp" class="item pinfo" data-content="Ir para o início">
                        <i class="home icon"></i>
                    </a>
                    <a href="SociedadeController?action=AreaSocio.ListaEventoInscrito" class="item pinfo" data-content="Visualizar eventos em que estou cadastrado">
                        <i class="unhide icon"></i>
                    </a>
                    <div class="right menu">
                        <div class="ui transparent action input">
                            <input type="text" name="nomeEvento" placeholder="Procurar eventos...">
                            <button class="ui button" type="submit" name="action" value="AreaSocio.PesquisaEvento">Pesquisar</button>
                        </div>
                    </div>
                </div>

                <div class="ui cards">
                    <c:forEach items="${listaEvento}" var="k">
                        <div class="card">
                            <div class="content">
                                <div class="header">${k.titulo}</div>
                                <div class="description">
                                    <p><fmt:formatDate pattern="dd/MM/yyyy" value="${k.data}" /></p>
                                    <p>${k.descricao}</p>
                                </div>
                            </div>
                            <a href="SociedadeController?action=AreaSocio.VerificaDadosInscricao&idEvento=${k.idEvento}" class="ui bottom attached button">
                                <i class="location arrow icon"></i>
                                Inscreva-se
                            </a>
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
</body>
</html>
