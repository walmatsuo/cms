/*************************************************
 Validação do formulario de usuario
 ************************************************/
$(document).ready(function() {
    $('#formularioUsuario').form({
        
        nomeUsuario: {
            identifier: 'nomeUsuario',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite um nome'
                }
            ]
        },
        
        categoriaUsuario: {
            identifier: 'categoriaUsuario',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Selecione uma categoria'
                }
            ]
        },
        email: {
            identifier: 'email',
            rules: [
                {
                    type: 'email',
                    prompt: 'Digite um e-mail válido'
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

