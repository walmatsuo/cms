$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
         $("#exc").html("Deseja excluir usuário?");

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            window.location = ("GerenciaController?action=Usuario.RemoveUsuario&idUsuario=" + id);
        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});