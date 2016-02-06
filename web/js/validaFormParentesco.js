/*************************************************
 Validação do formulário de parentesco
 ************************************************/
$(document).ready(function() {
    $('#formularioParentesco').form({
        
        grauParentesco: {
            identifier: 'grauParentesco',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o grau de parentesco'
                }
            ]
        },
        
        nomeParentesco: {
            identifier: 'nomeParentesco',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o nome correspondente ao tipo de parentesco'
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

