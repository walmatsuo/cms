/*************************************************
 Validação do formulario de agendamento
 ************************************************/
$(document).ready(function() {
    $('#agendamento').form({
        
        socio: {
            identifier: 'socio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione um sócio'
                }
            ]
        },
        
        espaco: {
            identifier: 'espaco',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione um espaço'
                }
            ]
        },
        titulo: {
            identifier: 'titulo',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite uma descrição para o evento'
                }
            ]
        }
    },
    //Apresentar erro para o usuário
    {
        inline: true,
        on: 'blur'
    });
});




