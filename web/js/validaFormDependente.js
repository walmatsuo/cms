/*************************************************
 Validação do formulario de dependente
 ************************************************/
$(document).ready(function() {
    $('#formularioDependente').form({

        nomeDependente: {
            identifier: 'nomeDependente',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite um nome'
                }
            ]
        },
        nascimentoDependente: {
            identifier: 'nascimentoDependente',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione a data de nascimento'
                }
            ]
        },
        nacionalidadeDependente: {
            identifier: 'nacionalidadeDependente',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a nacionalidade'
                }
            ]
        },
        naturalidadeDependente: {
            identifier: 'naturalidadeDependente',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a naturalidade'
                }
            ]
        },
        estadoCivilDependente: {
            identifier: 'estadoCivilDependente',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o estado civil'
                }
            ]
        }
    },
    {
        inline: true,
        on: 'blur'
    });
});