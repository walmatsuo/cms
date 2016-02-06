$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
        $("#exc").html("Deseja excluir categoria?");

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            $(".segment").addClass("loading");
            window.location = ("GerenciaController?action=CategoriaSocio.RemoveCategoriaSocio&idCategoriaSocio=" + id);
        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});