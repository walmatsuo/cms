<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui modal form" id="visualizar">
    <i class="close icon"></i>
    <div class="header">
        <i class="calendar icon"></i>
        Acompanhe sua reserva
    </div>
    <div class="image content">
        <div class="ui medium image" id="status">
        </div>
        <div class="description">
            <c:choose>
                <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia' || usuarioAutenticado.categoriaUsuario == 'Atendimento')}">
                    <h3 class="ui header">
                        <i class="users icon"></i>
                        <div class="content">
                            Sócio
                            <div class="sub header" id="socio2"></div>
                        </div>
                    </h3> 
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <h3 class="ui header">
                <i class="puzzle icon"></i>
                <div class="content">
                    Espaço
                    <div class="sub header" id="espaco2"></div>
                </div>
            </h3>
            <h3 class="ui header">
                <i class="write icon"></i>
                <div class="content">
                    Descrição
                    <div class="sub header" id="titulo2"></div>
                </div>
            </h3>
            <h3 class="ui header">
                <i class="calendar outline icon"></i>
                <div class="content">
                    Data
                    <div class="sub header" id="data2"></div>
                </div>
            </h3>
            <h3 class="ui header">
                <i class="wait icon"></i>
                <div class="content">
                    Hora de início
                    <div class="sub header" id="horaInicio2"></div>
                </div>
            </h3>
            <h3 class="ui header">
                <i class="wait icon"></i>
                <div class="content">
                    Hora de término
                    <div class="sub header" id="horaFinal2"></div>
                </div>
            </h3>
        </div>
    </div>
    <div class="actions">
        <div class="ui red button" id="cancelarReserva">
            Cancelar Reserva
        </div>
        <div class="ui positive right labeled icon button" id="ok">
            Ok
            <i class="checkmark icon"></i>
        </div>
    </div>
</div>
