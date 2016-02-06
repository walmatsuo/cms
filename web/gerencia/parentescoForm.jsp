<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>

            <script type="text/javascript" src="/CMS/js/validaFormParentesco.js"></script>
        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->

                <form id="formularioParentesco" class="ui form segment" action="/CMS/GerenciaController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="child icon"></i>
                        Gerenciar Parentescos
                    </h4>

                    <input type="hidden" name="idParentesco" value="${parentesco.idParentesco}"/>

                <div class="fields">
                    <div class="four wide field">
                        <label for="grauParentesco">Grau</label>
                        <select class="ui dropdown" name="grauParentesco" id="grauParentesco">
                            <option value="">Selecione o grau de parentesco</option>
                            <option value="1" ${parentesco.grauParentesco == '1' ? 'selected':''}>Grau 1</option>
                            <option value="2" ${parentesco.grauParentesco == '2' ? 'selected':''}>Grau 2</option>
                            <option value="3" ${parentesco.grauParentesco == '3' ? 'selected':''}>Grau 3</option>
                            <option value="4" ${parentesco.grauParentesco == '4' ? 'selected':''}>Grau 4</option>
                            <option value="5" ${parentesco.grauParentesco == '5' ? 'selected':''}>Grau 5</option>
                        </select>
                    </div>

                    <div class="twelve wide required field">
                        <label for="nomeParentesco">Parentesco</label>
                        <input type="text" value="${parentesco.nomeParentesco}" name="nomeParentesco" id="nomeParentesco">
                    </div>

                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Parentesco.SalvaParentesco">Salvar</button>
                <div class="ui inverted red button" id="btn-cancelar">Cancelar</div>
                <div class="ui inverted blue clear button">Limpar</div>
            </form>

            <!--Fim do formulário-->

            <!--Inicio do footer-->
            <jsp:include page="/carregar/footer.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do footer-->
            <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do modal de cancelar-->
            <jsp:include page="/carregar/modalCancelar.jsp"></jsp:include>
            <!--Final do modal de cancelar-->

        </div>
    </body>
</html>
