/*************************************************
 Validação do formulario de Recuperação de senha
 ************************************************/
$(document).ready(function() {
    $('#formularioRecuperarSenha').form({
        
        login: {
            identifier: 'login',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite um login'
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

