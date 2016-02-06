<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>

            <script type="text/javascript" src=""></script>
        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->

                <form id="" class="ui form segment" action="/CMS/AtendimentoController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="ticket icon"></i>
                        Gerenciar Eventos
                    </h4>

                    <input type="hidden" name="idEvento" value="${evento.idEvento}"/>

                <div class="fields">
                    <div class="eight wide required field">
                        <label for="data">Data</label>
                        <input type="date" value="${evento.data}" name="data" id="data">
                    </div>

                    <div class="eight wide required field">
                        <label for="titulo">Titulo</label>
                        <input type="text" value="${evento.titulo}" name="titulo" id="titulo">
                    </div>
                </div>

                <div class="sixteen wide required field">
                    <label for="local">Local</label>
                    <input type="text" value="${evento.local}" name="local" id="local">
                </div>

                <div class="sixteen wide required field">
                    <label for="descricao">Descrição</label>
                    <textarea name="descricao" id="descricao">${evento.descricao}</textarea>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Evento.SalvaEvento">Salvar</button>
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
