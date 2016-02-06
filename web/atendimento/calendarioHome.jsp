<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                <!--Inicio do formulário-->
                <form id="formularioReserva" class="ui form segment" action="/CMS/ReservaController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="users icon"></i>
                        Gerenciar Reservas
                    </h4>
                    <div class="field">
                        <div class="eight wide field">
                            <label>Espaço</label>
                            <select class="ui dropdown" name="espaco">
                                <option value="">Selecione o espaço</option>
                            <c:forEach items="${listaEspaco}" var="espaco">
                                <option value="${espaco.idEspaco}">${espaco.nomeEspaco}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <button class="ui submit button" id="submit" type="submit" name="action" value="Reserva.FiltraReserva">Carregar</button>
            </form>



            <!--Fim do formulário-->


            <!--Inicio do footer-->
            <jsp:include page="/carregar/footer.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do footer-->
            <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
            <!--Final do footer-->
    </body>
</html>
