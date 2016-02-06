/*************************************************
 Validação do formulario de categoria
 ************************************************/
$(document).ready(function() {
    $('#formularioCategoria').form({

        nomeCategoria: {
            identifier: 'nomeCategoria',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Informe o nome da categoria'
                }
            ]
        },
        valorTitulo: {
            identifier: 'valorTitulo',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira o valor referente ao titulo'
                }
            ]
        },
        valorMensalidade: {
            identifier: 'valorMensalidade',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira o valor da mensalidade'
                }
            ]
        },
        lotacaoMinima: {
            identifier: 'lotacaoMinima',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a quantidade minima de pessoas para o pacote'
                }
            ]
        },
        lotacaoMaxima: {
            identifier: 'lotacaoMaxima',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a quantidade maxima de pessoas para o pacote'
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


