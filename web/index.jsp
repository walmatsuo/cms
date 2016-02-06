<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="carregar/head.jsp"></jsp:include>
        </head>

        <body class="logP">
            <div class="ui middle aligned center aligned grid">
                <div class="column tlogP">
                    <div class="ui teal image header">
                        <img src="/CMS/img/cms-160x137.png" class="image">
                        <div class="content">
                            Acesso ao CMS
                        </div>
                    </div>
                    <form id="formularioLogin" class="ui large form" action="/CMS/ControleAcesso" method="POST">
                        <div class="ui grey inverted tall stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="inverted circular user icon"></i>
                                    <input id="login" data-content="Digite apenas números" type="text" name="login" placeholder="Login">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="inverted circular lock icon"></i>
                                    <input id="senha" data-content="Digite apenas números e letras" type="password" name="senha" placeholder="Senha">
                                </div>
                            </div>
                            <button class="ui fluid large teal submit button" type="submit" value="Acessa" name="action" >Login</button>
                        </div>
                    </form>
                <c:if test="${(msg != null)}">
                    <div class="ui tertiary inverted orange segment"><c:out value="${msg}"></c:out></div>
                </c:if>
                <div class="ui message">
                    <a href="/CMS/ControleAcesso?action=RecuperaSenha">Esqueceu a senha ?</a>
                </div>
            </div>
        </div>
    </body>

</html>