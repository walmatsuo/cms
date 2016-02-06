$(document).ready(function() {

//Modal confirm
    $(".btn-apagar").click(function() {

        var id = $(this).attr('id-del');

        $("#apagar-confirm").modal("setting", "closable", false).modal("show");
        
         $("#exc").html("Deseja excluir Dependente?");

        $("#apagar").click(function() {

            $("#apagar-confirm").modal("hide");
            window.location = ("SociedadeController?action=Dependente.RemoveDependente&idDependente=" + id);

        });

        $("#n-apagar").click(function() {
            $("#apagar-confirm").modal("hide");
        });

    });

});