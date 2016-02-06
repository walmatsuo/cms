$(document).ready(function() {

//Modal confirm
    $(".btn-continuar").click(function() {

        var id = $(this).attr('id-cont');

        $("#continuar-confirm").modal("setting", "closable", false).modal("show");
        
        $("#inf").html("Deseja realmente resetar a senha deste usuário?")

        $("#continuar").click(function() {

            $("#continuar-confirm").modal("hide");
            
            window.location = ("GerenciaController?action=Usuario.ResetaUsuario&idUsuario=" + id);
        });

        $("#n-continuar").click(function() {
            $("#continuar-confirm").modal("hide");
        });

    });

});


