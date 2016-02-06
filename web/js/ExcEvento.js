$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
         $("#exc").html("Deseja excluir evento?");

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            $(".segment").addClass("loading");
            window.location = ("AtendimentoController?action=Evento.RemoveEvento&idEvento=" + id);
        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});