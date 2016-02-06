<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormRecuperaSenha.js"></script>

        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
                <nav class="ui grey top fixed inverted menu">
                    <div class="item">
                        <img src="/CMS/img/cms-160x137.png">
                    </div>

                    <div class="right menu">
                        <a href="/CMS" class="item">
                            <i class="home icon"></i> Login
                        </a>
                    </div>
                </nav>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->
                <form id="formularioRecuperarSenha" class="ui form segment" action="/CMS/ControleAcesso" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="key layout icon"></i>
                        CMS - Recuperação de senha!
                    </h4>

                    <div class="five wide field">
                        <label>Login</label>
                        <input type="text" name="login" id="login">
                    </div>
                    <div class="five wide field">
                        <label>E-mail</label>
                        <input type="email" name="email" id="email">
                    </div>
                    <button class="ui submit button" type="submit" name="action" value="EnviaSenha">Confirmar</button>
                </form>
                <!--Fim do formulário-->
            </div>

            <!--Inicio do footer-->
        <jsp:include page="carregar/footer.jsp"></jsp:include>
        <!--Final do footer-->
    </body>
</html>
