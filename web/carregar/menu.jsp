<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="ui grey top fixed inverted menu">
    <div class="item">
        <img src="/CMS/img/cms-160x137.png">
    </div>
    
    <a class="item toggle button pusher">
        <i class="list layout icon"></i>Opções
    </a>
    <a href="/CMS/SociedadeController?action=Reserva.RecuperaReserva" class="item ">
        <i class="calendar icon"></i>Agendamento
    </a>
    
    <div class="right menu">

        <div class="item red">
            <i class="user icon"></i>
            Bem vindo, <c:out value="${usuarioAutenticado.nomeUsuario}"></c:out>
        </div>

        <a href="/CMS/ControleAcesso?action=Saida" class="item">
            <i class="sign out icon"></i> Sair
        </a>
    </div>
</nav>
