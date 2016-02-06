$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
         $("#exc").html("Deseja excluir parentesco?");

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            $(".segment").addClass("loading");
            window.location = ("GerenciaController?action=Parentesco.RemoveParentesco&idParentesco=" + id);
        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});