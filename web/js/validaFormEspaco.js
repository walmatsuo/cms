/*************************************************
 Validação do formulario de Espaço
 ************************************************/
$(document).ready(function() {
    $('#formularioEspaco').form({
        nomeEspaco: {
            identifier: 'nomeEspaco',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o nome do espaço'
                }
            ]
        },
        abertura: {
            identifier: 'abertura',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira a hora de abertura deste espaço'
                }
            ]
        },
        fechamento: {
            identifier: 'fechamento',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira a hora de fechamento deste espaço'
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


