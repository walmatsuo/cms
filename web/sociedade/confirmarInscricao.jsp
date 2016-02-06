<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                <!--Inicio da listagem-->
                <form action="/CMS/SociedadeController" method="POST" class="ui segment">
                    <input type="hidden" name="idEvento" value="${evento.idEvento}" >

                <div class="ui horizontal divider">
                    <h2 class="ui header">
                        <div class="content">
                            Confirme sua inscrição
                        </div>
                    </h2>
                </div>

                <div class="ui two column very relaxed grid">
                    <div class="ui column">
                        <div class="ui piled segments">
                            <div class="ui center aligned segment">
                                <h2 class="ui icon header">
                                    <i class="ticket icon"></i>
                                    <div class="content"> ${evento.titulo} <div class="sub header"> ${evento.descricao}</div>
                                    </div>
                                </h2>
                            </div>
                            <div class="ui right aligned segment">
                                <div class="ui labeled button" tabindex="0">
                                    <div class="ui basic grey button"><i class="calendar icon"></i>  <fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data}" /> </div>
                                    
                                    <a class="ui basic left pointing grey label">
                                        <i class="marker icon"></i>
                                        ${evento.local}
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="ui column">
                        <div class="ui piled segments">
                            <div class="ui center aligned segment">
                                <h3>Dados do sócio</h3>
                            </div>
                            <div class="ui segment">
                                <h3 class="ui header">
                                    <i class="write icon"></i>
                                    <div class="content">
                                        Número do título
                                        <div class="sub header">${socio.idSocio}</div>
                                    </div>
                                </h3>
                                <h3 class="ui header">
                                    <i class="user icon"></i>
                                    <div class="content">
                                        Nome do sócio
                                        <div class="sub header">${socio.nomeSocio}</div>
                                    </div>
                                </h3>
                                <h3 class="ui header">
                                    <i class="barcode icon"></i>
                                    <div class="content">
                                        CPF
                                        <div class="sub header">${socio.cpfSocio}</div>
                                    </div>
                                </h3>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="ui right aligned segment">
                    <c:choose>
                        <c:when test="${(status == 'a')}">
                            <button class="ui inverted green submit button disabled" >Você já está inscrito neste evento</button>
                        </c:when>
                        <c:otherwise>
                            <button class="ui inverted green submit button" type="submit" name="action" value="AreaSocio.RegistraInscricao">Confirmar Inscrição</button> 
                            <div class="ui inverted red button" id="btn-cancelar">Cancelar</div>
                        </c:otherwise>
                    </c:choose>
                </div>



            </form>
        </div>
        <!--Fim da listagem de categorias-->


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

