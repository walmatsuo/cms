<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui basic modal" id="continuar-confirm">
    <i class="close icon"></i>
    <div class="header">
        Confirmação
    </div>
    <div class="image content">
        <div class="image">
            <i class="warning sign icon"></i>
        </div>
        <div class="description">
            <p id="inf"></p>
        </div>
    </div>
    <div class="actions">
        <div class="fluid ui inverted buttons">
            <div class="ui green basic inverted button confirm positive" id="continuar">
                <i class="checkmark icon"></i>
                Ok
            </div>
        </div>
    </div>
</div>


<div class="ui modal form" id="agendamento">
    <i class="close icon"></i>
    <div class="header">
        <i class="calendar icon"></i>
        Agendamento
    </div>
    <div class="image content">
        <div class="description">
            <div class="fields">
                <c:choose>
                    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia' || usuarioAutenticado.categoriaUsuario == 'Atendimento')}">
                        <div class="eight wide required field">
                            <label>Sócio</label>
                            <select class="ui dropdown" id="socio">
                            </select>
                        </div>

                        <div class="eight wide required field">
                            <label>Espaço</label>
                            <select class="ui dropdown" id="espaco">
                            </select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="sixteen wide required field">
                            <label>Espaço</label>
                            <select class="ui dropdown" id="espaco">
                            </select>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="fields">
                <div class="eight wide required field">
                    <label>Descrição</label>
                    <input type="text"  id="titulo">
                </div>

                <div class="eight wide required field">
                    <label>Data</label>
                    <input type="date" id="data">
                </div>
            </div>

            <div class="fields">
                <div class="eight wide required field" id="horaInicioFld">
                    <label>Hora de início</label>
                    <select class="ui dropdown" id="horaInicio">
                    </select>
                </div>

                <div class="eight wide required field" id="horaFinalFld">
                    <label>Hora de término</label>
                    <select class="ui dropdown" id="horaFinal">
                    </select>
                </div>
            </div>

            <div class="ui negative message">
                <i class="close icon"></i>
                <div id="horaLivre" class="header"> </div>
            </div>
            
        </div>
    </div>
    <div class="actions">
        <button class="ui button" id="enviar">Salvar</button>
    </div>
</div>
