<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormAlteraSenha.js"></script>
            
        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
                <nav class="ui grey top fixed inverted menu">
                    <div class="item">
                        <img src="img/cms-160x137.png">
                    </div>
                    <div class="right menu">

                        <div class="item red">
                            <i class="user icon"></i>
                            Bem vindo, <c:out value="${usuarioAutenticado.nomeUsuario}"></c:out>
                        </div>

                        <a href="/CMS/ControleAcesso?action=Saida" class="item">
                            <i class="sign out icon"></i> Sair
                        </a>
                    </div>
                </nav>
                <nav class="ui sidebar left fixed vertical menu">
                </nav>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->
                <form id="formularioAlterarSenha" class="ui form segment" action="/CMS/ControleAcesso" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="key layout icon"></i>
                        CMS - Defina uma nova senha !
                    </h4>

                    <div class="five wide field">
                        <label>Senha</label>
                        <input type="password" name="senha" id="senha">
                    </div>
                    <div class="five wide field">
                        <label>Confirme Senha</label>
                        <input type="password" name="senhaConfirm" id="senhaConfirm">
                    </div>
                    <button class="ui submit button" type="submit" name="action" value="DefineNovaSenha">Salvar</button>
                </form>
                <!--Fim do formulário-->
            </div>

            <!--Inicio do footer-->
        <jsp:include page="carregar/footer.jsp"></jsp:include>
        <!--Final do footer-->
    </body>
</html>
