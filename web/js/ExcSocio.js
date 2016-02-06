$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
         $("#exc").html("Deseja excluir socio?");
        

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            $(".segment").addClass("loading");
            window.location = ("AtendimentoController?action=Socio.RemoveSocio&idSocio=" + id);
        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});