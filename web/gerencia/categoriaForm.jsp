<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>

            <script type="text/javascript" src="/CMS/js/validaFormCategoria.js"></script>
            <script type="text/javascript">
                function valida_form() {
                    if (document.getElementById("lotacaoMaxima").value < document.getElementById("lotacaoMinima").value) {
                        $('#continuar-confirm').modal('setting', 'closable', false).modal('show');
                        $("#inf").html("Lotação maxima precisa ser maior que lotação minima");
                        document.getElementById("lotacaoMaxima").focus();
                        return false;
                    }
                }
            </script>

        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->

                <form onsubmit="return valida_form()" id="formularioCategoria" class="ui form segment" action="/CMS/GerenciaController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="block layout icon"></i>
                        Gerenciar Categorias
                    </h4>

                    <input type="hidden" name="idCategoriaSocio" value="${categoria.idCategoriaSocio}"/>

                <div class="sixteen wide required field">
                    <label for="nomeCategoria">Categoria</label>
                    <input type="text" value="${categoria.nomeCategoriaSocio}" name="nomeCategoria" id="nomeCategoria">
                </div>

                <div class="fields">
                    <div class="eight wide required field">
                        <label for="valorTitulo">Valor do Titulo</label>
                        <div class="ui left icon input">
                            <i class="dollar icon"></i>
                            <input type="text" value="${categoria.valorTitulo}" name="valorTitulo" id="valorTitulo">
                        </div>
                    </div>

                    <div class="eight wide required field">
                        <label for="valorMensalidade">Valor da Mensalidade</label>
                        <div class="ui left icon input">
                            <i class="dollar icon"></i>
                            <input type="text" value="${categoria.valorMensalidade}" name="valorMensalidade" id="valorMensalidade">
                        </div>
                    </div>
                </div>

                <div class="fields">
                    <div class="eight wide required field">
                        <label for="lotacaoMinima">Lotação Minima</label>
                        <input type="number" min="1" value="${categoria.lotacaoMinima}" name="lotacaoMinima" id="lotacaoMinima">
                    </div>

                    <div class="eight wide required field">
                        <label for="lotacaoMaxima">Lotação Maxima</label>
                        <input type="number" min="1" value="${categoria.lotacaoMaxima}" name="lotacaoMaxima" id="lotacaoMaxima">
                    </div>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="CategoriaSocio.SalvaCategoriaSocio">Salvar</button>
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
                <!--Inicio do modal de cancelar-->
            <jsp:include page="/carregar/modalInfo2.jsp"></jsp:include>
            <!--Final do modal de cancelar-->


        </div>
    </body>
</html>
