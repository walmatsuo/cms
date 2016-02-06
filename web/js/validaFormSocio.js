/*************************************************
 Validação do formulario de Socio
 ************************************************/
$(document).ready(function() {
    $('#formularioSocio').form({
        nomeSocio: {
            identifier: 'nomeSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o nome do sócio'
                }
            ]
        },
        cpfSocio: {
            identifier: 'cpfSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira o cpf do sócio'
                }
            ]
        },
        categoriaSocio: {
            identifier: 'categoriaSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione uma categoria'
                }
            ]
        },
        telefone1: {
            identifier: 'telefone1',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira um telefone'
                }
            ]
        },
        estadoCivilSocio: {
            identifier: 'estadoCivilSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione o estado civil'
                }
            ]
        },
        email: {
            identifier: 'email',
            rules: [
                {
                    type: 'email',
                    prompt: 'Insira um e-mail válido'
                }
            ]
        },
        profissao: {
            identifier: 'profissao',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a profissão do sócio'
                }
            ]
        },
        sexoSocio: {
            identifier: 'sexoSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione o sexo'
                }
            ]
        },
        nacionalidadeSocio: {
            identifier: 'nacionalidadeSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite a nacionalidade'
                }
            ]
        },
        naturalidadeSocio: {
            identifier: 'naturalidadeSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o lugar em que o sócio nasceu'
                }
            ]
        },
        nascimentoSocio: {
            identifier: 'nascimentoSocio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione a data de nascimento'
                }
            ]
        },
        logradouro: {
            identifier: 'logradouro',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite o logradouro'
                }
            ]
        },
        numero: {
            identifier: 'numero',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira o número do endereço'
                }
            ]
        },
        cep: {
            identifier: 'cep',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira um CEP válido'
                }
            ]
        },
        uf: {
            identifier: 'uf',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione um estado'
                }
            ]
        },
        municipio: {
            identifier: 'municipio',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira um município'
                }
            ]
        },
        bairro: {
            identifier: 'bairro',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Insira o bairro'
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


