<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>

            <script type="text/javascript" src="/CMS/js/validaFormEspaco.js"></script>

            <script type="text/javascript">
                function valida_form() {
                    if (document.getElementById("fechamento").value < document.getElementById("abertura").value) {
                        $('#continuar-confirm').modal('setting', 'closable', false).modal('show');
                        $("#inf").html("Hora de fechamento precisa ser maior que a hora de abertura");
                        document.getElementById("fechamento").focus();
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

                <form onsubmit="return valida_form()" id="formularioEspaco" class="ui form segment" action="/CMS/GerenciaController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="puzzle icon"></i>
                        Gerenciar Espaços
                    </h4>

                    <input type="hidden" name="idEspaco" value="${espaco.idEspaco}"/>

                <div class="sixteen wide required field">
                    <label for="nomeEspaco">Espaço</label>
                    <input type="text" value="${espaco.nomeEspaco}" name="nomeEspaco" id="nomeEspaco">
                </div>

                <div class="fields">

                    <div class="eight wide field">
                        <label for="abertura">Abertura</label>
                        <select class="ui dropdown" name="abertura" id="abertura">
                            <option value="">Selecione horário</option>
                            <option value="00" ${espaco.abertura == '00' ? 'selected':''}>00:00:00</option>
                            <option value="01" ${espaco.abertura == '01' ? 'selected':''}>01:00:00</option>
                            <option value="02" ${espaco.abertura == '02' ? 'selected':''}>02:00:00</option>
                            <option value="03" ${espaco.abertura == '03' ? 'selected':''}>03:00:00</option>
                            <option value="04" ${espaco.abertura == '04' ? 'selected':''}>04:00:00</option>
                            <option value="05" ${espaco.abertura == '05' ? 'selected':''}>05:00:00</option>
                            <option value="06" ${espaco.abertura == '06' ? 'selected':''}>06:00:00</option>
                            <option value="07" ${espaco.abertura == '07' ? 'selected':''}>07:00:00</option>
                            <option value="08" ${espaco.abertura == '08' ? 'selected':''}>08:00:00</option>
                            <option value="09" ${espaco.abertura == '09' ? 'selected':''}>09:00:00</option>
                            <option value="10" ${espaco.abertura == '10' ? 'selected':''}>10:00:00</option>
                            <option value="11" ${espaco.abertura == '11' ? 'selected':''}>11:00:00</option>
                            <option value="12" ${espaco.abertura == '12' ? 'selected':''}>12:00:00</option>
                            <option value="13" ${espaco.abertura == '13' ? 'selected':''}>13:00:00</option>
                            <option value="14" ${espaco.abertura == '14' ? 'selected':''}>14:00:00</option>
                            <option value="15" ${espaco.abertura == '15' ? 'selected':''}>15:00:00</option>
                            <option value="16" ${espaco.abertura == '16' ? 'selected':''}>16:00:00</option>
                            <option value="17" ${espaco.abertura == '17' ? 'selected':''}>17:00:00</option>
                            <option value="18" ${espaco.abertura == '18' ? 'selected':''}>18:00:00</option>
                            <option value="19" ${espaco.abertura == '19' ? 'selected':''}>19:00:00</option>
                            <option value="20" ${espaco.abertura == '20' ? 'selected':''}>20:00:00</option>
                            <option value="21" ${espaco.abertura == '21' ? 'selected':''}>21:00:00</option>
                            <option value="22" ${espaco.abertura == '22' ? 'selected':''}>22:00:00</option>
                            <option value="23" ${espaco.abertura == '23' ? 'selected':''}>23:00:00</option>
                        </select>
                    </div>

                    <div class="eight wide field">
                        <label for="abertura">Fechamento</label>
                        <select class="ui dropdown" name="fechamento" id="fechamento">
                            <option value="">Selecione horário</option>
                            <option value="01" ${espaco.fechamento == '01' ? 'selected':''}>01:00:00</option>
                            <option value="02" ${espaco.fechamento == '02' ? 'selected':''}>02:00:00</option>
                            <option value="03" ${espaco.fechamento == '03' ? 'selected':''}>03:00:00</option>
                            <option value="04" ${espaco.fechamento == '04' ? 'selected':''}>04:00:00</option>
                            <option value="05" ${espaco.fechamento == '05' ? 'selected':''}>05:00:00</option>
                            <option value="06" ${espaco.fechamento == '06' ? 'selected':''}>06:00:00</option>
                            <option value="07" ${espaco.fechamento == '07' ? 'selected':''}>07:00:00</option>
                            <option value="08" ${espaco.fechamento == '08' ? 'selected':''}>08:00:00</option>
                            <option value="09" ${espaco.fechamento == '09' ? 'selected':''}>09:00:00</option>
                            <option value="10" ${espaco.fechamento == '10' ? 'selected':''}>10:00:00</option>
                            <option value="11" ${espaco.fechamento == '11' ? 'selected':''}>11:00:00</option>
                            <option value="12" ${espaco.fechamento == '12' ? 'selected':''}>12:00:00</option>
                            <option value="13" ${espaco.fechamento == '13' ? 'selected':''}>13:00:00</option>
                            <option value="14" ${espaco.fechamento == '14' ? 'selected':''}>14:00:00</option>
                            <option value="15" ${espaco.fechamento == '15' ? 'selected':''}>15:00:00</option>
                            <option value="16" ${espaco.fechamento == '16' ? 'selected':''}>16:00:00</option>
                            <option value="17" ${espaco.fechamento == '17' ? 'selected':''}>17:00:00</option>
                            <option value="18" ${espaco.fechamento == '18' ? 'selected':''}>18:00:00</option>
                            <option value="19" ${espaco.fechamento == '19' ? 'selected':''}>19:00:00</option>
                            <option value="20" ${espaco.fechamento == '20' ? 'selected':''}>20:00:00</option>
                            <option value="21" ${espaco.fechamento == '21' ? 'selected':''}>21:00:00</option>
                            <option value="22" ${espaco.fechamento == '22' ? 'selected':''}>22:00:00</option>
                            <option value="23" ${espaco.fechamento == '23' ? 'selected':''}>23:00:00</option>
                            <option value="24" ${espaco.fechamento == '24' ? 'selected':''}>24:00:00</option>
                        </select>
                    </div>
                </div>

                <div class="fields">
                    <div class="eight wide field">
                        <label for="descricaoEspaco">Descrição</label>
                        <input type="text" value="${espaco.descricaoEspaco}" name="descricaoEspaco" id="descricaoEspaco">
                    </div>
                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Espaco.SalvaEspaco">Salvar</button>
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
