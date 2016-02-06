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
                    Relatório - Sócio por Mês
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

                    <div class="four wide field">
                        <label>Ano</label>
                        <select class="ui dropdown" name="ano">
                            <option value="">Selecione o ano</option>
                            <option value="2014">2014</option>
                            <option value="2015">2015</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                        </select>
                    </div>

                    <div class="four wide field">
                        <label>Mês</label>
                        <select class="ui dropdown" name="mes">
                            <option value="">Selecione o mês</option>
                            <option value="0">Janeiro</option>
                            <option value="1">Fevereiro</option>
                            <option value="2">Março</option>
                            <option value="3">Abril</option>
                            <option value="4">Maio</option>
                            <option value="5">Junho</option>
                            <option value="6">Julho</option>
                            <option value="7">Agosto</option>
                            <option value="8">Setembro</option>
                            <option value="9">Outubro</option>
                            <option value="10">Novembro</option>
                            <option value="11">Dezembro</option>
                        </select>
                    </div>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Relatorio.SocioPorMes">Gerar</button>
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
