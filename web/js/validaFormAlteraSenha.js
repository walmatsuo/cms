/*************************************************
 Validação do formulario de Alteração de senha
 ************************************************/
$(document).ready(function() {
    $('#formularioAlterarSenha').form({
        
        senha: {
            identifier: 'senha',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Digite uma senha'
                }
            ]
        },
        
        match: {
            identifier: 'senhaConfirm',
            rules: [
                {
                    type: 'match[senha]',
                    prompt: 'Digite a senha exatamente igual'
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

