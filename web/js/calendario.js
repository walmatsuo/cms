/*************************************************
 JavaScript do Full Calendar (configuração propria)
 ************************************************/
$(document).ready(function() {

    $('.message').fadeOut();

    //Message
    $('.message .close').on('click', function() {
        $(this).closest('.message').fadeOut();
    });

    //Sidebar
    $('.ui.sidebar').sidebar('setting', {
        dimPage: false,
        transition: 'auto',
        mobileTransition: 'auto',
        duration: 500
    }).sidebar('attach events', '.toggle.button');

    //Função para recuperar os Sócios
    function exibirSocio() {
        //Requisição Ajax
        $.ajax({
            url: "/CMS/ReservaController",
            type: 'POST',
            data: {
                action: 'Reserva.BuscaSocio'
            },
            complete: function(e, xhr, result) { //Se ocorrer tudo certo

                var Obj = eval("(" + e.responseText + ")"); //Combo OS

                if (Obj !== null) {

                    //Preenche o combox de Sócio
                    $("#socio option").remove();
                    $("#socio").append("<option value=''>Selecione um Sócio</option>");
                    for (var i = 0; i < Obj.length; i++) {
                        $("#socio").append("<option value=" + Obj[i].idSocio + ">" + Obj[i].nomeSocio + " </option>");
                    }
                }

            }

        });
        //Fim da Requisição
    }
    ;

    //Função para recuperar espaço e hora de funcionamento
    function exibirEspaco() {
        //Requisição Ajax
        $.ajax({
            url: "/CMS/ReservaController",
            type: 'POST',
            data: {
                action: 'Reserva.BuscaEspaco'
            },
            complete: function(e, xhr, result) { //Se ocorrer tudo certo

                var Obj = eval("(" + e.responseText + ")"); //Combo OS

                if (Obj !== null) {

                    //Preenche o combox de Espaço
                    $("#espaco option").remove();
                    $("#espaco").append("<option value=''>Selecione um espaço</option>");
                    for (var i = 0; i < Obj.length; i++) {
                        $("#espaco").append("<option value=" + Obj[i].idEspaco + ">" + Obj[i].nomeEspaco + " </option>");
                    }

                    //Preeenche a hora de inicio e fim de acordo com o espaço escolhido
                    $('#espaco').change(function() {
                        $('#horaInicio option').remove();
                        $('#horaFinal option').remove();
                        var valor = $(this).val();
                        $(this).each(function() {
                            if (valor !== "") {
                                for (var i = 0; i < Obj.length; i++) {
                                    if (Obj[i].idEspaco == valor) {
                                        valor = i;
                                        break;
                                    }
                                }
                                $('#horaInicioFld').show();
                                $('#horaFinalFld').show();

                                for (var i = Obj[valor].abertura; i <= Obj[valor].fechamento; i++) {
                                    $("#horaInicio").append("<option value=" + i + ">" + i + ":00:00</option>");
                                    $("#horaFinal").append("<option value=" + i + ">" + i + ":00:00</option>");
                                }
                            }
                        });
                    });
                }

            }

        });
        //Fim da Requisição
    }
    ;

    // Desvincular dos eventos clicados anteriormente ao clicar no botão OK do modal Visualização
    $('#ok').click(function() {
        $('#cancelarReserva').unbind();
    });
    // Desvincular dos eventos clicados anteriormente ao clicar no botão fechar(x) do modal
    $('.modal .close').click(function() {
        $('#cancelarReserva').unbind();
    });
    $('#cancelarReserva').click(function() {
        $('#cancelarReserva').unbind();
    });

    //Determina o idioma do calendário
    var currentLangCode = 'pt-br';
    //Inicia o fullCalendar
    $('#calendar').fullCalendar({
        //Determina o cabeçalho do Calendário
        header: {
            left: 'prev,next today, title',
            right: 'month,agendaWeek,agendaDay'
        },
        theme: true,
        lang: currentLangCode,
        defaultDate: moment(),
        selectable: true,
        unselectAuto: true,
        eventLimit: true,
        aspectRatio: 2.5,
        eventSources: [{
                url: "/CMS/ReservaController",
                type: "POST",
                data: {
                    action: "Reserva.ListaReserva"
                }}],
        //Inicia o evento de agendar uma nova reserva
        select: function(date) {

            if (date.format("YYYY-MM-DD") < moment().format("YYYY-MM-DD")) {
                $('#continuar-confirm').modal('setting', 'closable', false).modal('show');
                $("#inf").html("Data selecionada é menor que data atual?");
            } else {

                //Limpa todos os campos antes de preenche-los
                $('#titulo').val("");
                $('#data').val("");
                $('#horaInicio').text("");
                $('#horaFinal').text("");
                $('#horaInicioFld').hide();
                $('#horaFinalFld').hide();


                //Chama a função para preencher sócio
                exibirSocio();

                //Chama a função para preencher espaço e horário
                exibirEspaco();

                //Formata o campo data
                $('#data').val(date.format('YYYY-MM-DD'));

                //Abre o modal para realizar o agendamento
                $('#agendamento').modal('setting', 'closable', false).modal('show');

                //Quando o botão enviar é clicado, executa a função
                $('#enviar').click(function() {

                    //Recupera as informações digitadas no formulário
                    var titulo = $('#titulo').val();
                    var start = $('#data').val();
                    var end = $('#data').val();
                    var horaInicio = $('#horaInicio').val();
                    var horaFinal = $('#horaFinal').val();
                    var espaco = $('#espaco').val();
                    var socio = $('#socio').val();

                    //Fecha o modal
                    $('#agendamento').modal('hide');

                    //Validações
                    if (titulo === "" || start === "" || end === "" || horaInicio === "" || horaFinal === "" || espaco === "" || socio === "") {
                        $("#horaLivre").html("Preencha todos os campos");
                        $('.message').fadeIn();
                        $('#agendamento').modal('setting', 'closable', false).modal('show');
                    }

                    else if (horaFinal <= horaInicio) {
                        $("#horaLivre").html("Hora final precisa ser maior que hora inicial");
                        $('.message').fadeIn();
                        $('#agendamento').modal('setting', 'closable', false).modal('show');
                    }

                    else if (titulo !== "" && start !== "" && end !== "" && horaInicio !== "" && horaFinal !== "" && espaco !== "" && socio !== "") {
                        //Inicia a requisição ajax
                        $.ajax({
                            url: "/CMS/ReservaController",
                            type: 'POST',
                            data: {
                                action: ('Reserva.CadastraReserva'),
                                title: titulo,
                                start: start + ' ' + horaInicio + ':00:00',
                                end: end + ' ' + horaFinal + ':00:00',
                                espaco: espaco,
                                socio: socio
                            },
                            success: function(data) {

                                // Recuperando mensagem para o usuário
                                $("#msg").html(data);
                                $('.message').fadeIn();

                                $("#calendar").fullCalendar('refetchEvents');
                                $('#calendar').fullCalendar('unselect');
                            }
                        });
                    }
                    //Renderiza o calendário
                    renderCalendar();
                });
            }
            $('#calendar').fullCalendar('unselect');
        },
        //Inicia o evento de visualizar e alterar o status da reserva
        eventClick: function(calEvent) {

            //Recuperando campos do BD para ser impressos na tela
            var socioReserva = calEvent.socioReserva.idSocio.toString() + " - " + calEvent.socioReserva.nomeSocio.toString();

            $('#socio2').html(socioReserva);
            $('#titulo2').html(calEvent.title);
            $('#data2').html(calEvent.start.format('DD/MM/YYYY'));
            $('#espaco2').html(calEvent.espacoReserva.nomeEspaco);
            $('#horaInicio2').html(calEvent.start.format('HH:mm:ss'));
            $('#horaFinal2').html(calEvent.end.format('HH:mm:ss'));
            $('#can option[value="' + calEvent.statusReserva + '"]').attr({selected: "selected"});

            //Teste de condição para saber o status da reserva
            $('#status i,h1').remove();

            if (calEvent.statusReserva === 'a') {
                $('#cancelarReserva').show();

                $('#status').append('<i class="checkmark icon green"></i> <h1>Confirmada</h1>');
            } else {
                $('#cancelarReserva').hide();

                if (calEvent.statusReserva === 'k') {
                    $('#status').append('<i class="remove icon red"></i> <h1>Cancelada</h1>');
                } else {
                    $('#status').append('<i class="checkmark icon blue"></i> <h1>Concluído</h1>');
                }
            }
            //Abre o modal para ver situção da reserva
            $('#visualizar').modal('setting', 'closable', false).modal('show');

            //Quando o botão atulizar é clicado, executa a função
            $('#cancelarReserva').click(function() {
                //Fecha o modal
                $('#visualizar').modal('hide');

                //Executa requisição  ajax
                $.ajax({
                    url: "/CMS/ReservaController",
                    type: 'POST',
                    data: {
                        action: ('Reserva.CancelaReserva'),
                        id: calEvent.id,
                    },
                    success: function(data) {
                        // Recuperando mensagem para o usuário
                        $("#msg").html(data);
                        $('.message').fadeIn();

                        $("#calendar").fullCalendar('refetchEvents');
                        $('#calendar').fullCalendar('unselect');
                    }
                });

            });

            //Renderiza o calendário
            renderCalendar();

            $('#calendar').fullCalendar('unselect');
        },
        unselect: function() {
            $('#cancelarReserva').unbind();
        }
    });
});
