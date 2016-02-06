<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormUsuario.js"></script>

        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->
                <form id="formularioUsuario" class="ui form segment" action="/CMS/GerenciaController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="user icon"></i>
                        Gerenciar Usuários
                    </h4>

                    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}"/>

                <div class="fields">
                    <div class="eight wide field">
                        <label>Nome Completo</label>
                        <input type="text" value="${usuario.nomeUsuario}" name="nomeUsuario" id="nomeUsuario">
                    </div>

                    <div class="eight wide field">
                        <label>Categoria</label>
                        <select class="ui dropdown" name="categoriaUsuario" id="categoriaUsuario">
                            <option value="">Selecione categoria</option>
                            <option value="Atendimento" ${usuario.categoriaUsuario.toString().equals('Atendimento') ? 'selected':''}>Atendimento</option>
                            <option value="Gerencia" ${usuario.categoriaUsuario.toString().equals('Gerencia') ? 'selected':''}>Gerencia</option>
                        </select>
                    </div>
                </div>

                <div class="fields">
                    <div class="eight wide field">
                        <label>Login</label>
                        <input type="text" value="${usuario.login}" name="login" id="login" disabled="disabled" placeholder="Preenchimento automático">
                    </div>

                    <div class="eight wide field">
                        <label>Email</label>
                        <input type="email" value="${usuario.email}" name="email" id="emal">
                    </div>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Usuario.SalvaUsuario">Salvar</button>
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
