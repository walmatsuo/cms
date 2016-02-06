<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormDependente.js"></script>

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


            <!--Inicio do formulário-->
            <form id="formularioSocioPorMes" class="ui form segment" action="/CMS/GerenciaController" method="POST">
                <h4 class="ui horizontal header divider">
                    <i class="users icon"></i>
                    Relatório - Sócio por Categoria
                </h4>

                

                <div class="fields">
                    <div class="field">
                        <div class="ui radio checkbox">
                            <input type="radio" name="extensao" value="pdf" checked="checked">
                            <label><i class="file pdf outline icon large"></i></label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui radio checkbox">
                            <input type="radio" name="extensao" value="xlsx">
                            <label><i class="file excel outline icon large"></i></i></label>
                        </div>
                    </div>

                    <div class="four wide field required">
                        <label>Categoria Sócio</label>
                        <select class="ui dropdown" name="categoriaSocio" id="categoriaSocio">
                            <option value="">Selecione categoria</option>
                            <c:forEach items="${listaCategoriaSocio}" var="categoriaSocio">
                                <option value="${categoriaSocio.idCategoriaSocio}" ${socio.categoriaSocio.idCategoriaSocio == categoriaSocio.idCategoriaSocio ? 'selected':''}>${categoriaSocio.nomeCategoriaSocio}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Relatorio.SocioPorCategoria">Gerar</button>
                <div class="ui inverted red button" id="btn-cancelar">Cancelar</div>
            </form>

            <!--Fim do formulário-->

        </div>

        <!--Inicio do footer-->
        <jsp:include page="/carregar/footer.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do footer-->
        <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do modal de cancelar-->
        <jsp:include page="/carregar/modalCancelar.jsp"></jsp:include>
        <!--Final do modal de cancelar-->
    </body>
</html>
