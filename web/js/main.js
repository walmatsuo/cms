/*************************************************
 Configurações gerais em JavaScript da aplicação
 ************************************************/
$(document).ready(function() {

    //Speical cards
    $('.special.cards .image').dimmer({
        on: 'hover'
    });

//Link active
    $('a.item').click(function() {
        $('.item').removeClass('active');
        $(this).addClass('active');
    });

//Dropdown
    $('.dropdown').dropdown({
        transition: 'drop'
    });

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

//Popup
    $('#login,#senha')
            .popup({
                inline: true,
                on: 'focus'
            });

    $('.pinfo')
            .popup({
                inline: true,
                hoverable: true,
                position: 'bottom left',
                delay: {
                    show: 300,
                    hide: 500
                }
            });

    //Bloquear enter
    $('input').keypress(function(e) {
        if (e.which === 13) {
            $('#submit').click();
        }
    });

    //Botao cancelar
    $('#btn-cancelar').click(function() {

        $("#cancelar-confirm").modal("setting", "closable", false).modal("show");

        $('#cancelar').click(function() {

            $("#cancelar-confirm").modal("hide");
            window.location = ("/CMS/principal.jsp");

        });

        $("#n-cancelar").click(function() {
            $("#cancelar-confirm").modal("hide");
        });
    });

});









  