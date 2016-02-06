<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia')}">

        <div class="ui inverted grey labeled icon left inline vertical sidebar menu">
            <a href="/CMS/principal.jsp" class="item">
                <i class="home icon"></i>
                Home
            </a>
            <a href="/CMS/GerenciaController?action=CategoriaSocio.ListaCategoriaSocio" class="item">
                <i class="block layout icon"></i>
                Categorias
            </a>
            <a href="/CMS/GerenciaController?action=Parentesco.ListaParentesco" class="item">
                <i class="child icon"></i>
                Parentescos
            </a>
            <a href="/CMS/GerenciaController?action=Espaco.ListaEspaco" class="item">
                <i class="puzzle icon"></i>
                Espaços
            </a>
            <a href="/CMS/GerenciaController?action=Usuario.ListaUsuario" class="item">
                <i class="user icon"></i>
                Usuários
            </a>
            <a href="/CMS/AtendimentoController?action=Socio.ListaSocio" class="item">
                <i class="users icon"></i>
                Sócios
            </a>
            
            <a href="/CMS/gerencia/relatorioListar.jsp" class="item">
                <i class="file outline icon"></i>
                Relatórios
            </a>
            
            <a href="/CMS/AtendimentoController?action=Evento.ListaEvento" class="item">
                <i class="ticket icon"></i>
                Eventos
            </a>
        </div>

    </c:when>
    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Atendimento')}">

        <div class="ui inverted grey labeled icon left inline vertical sidebar menu">
            <a href="/CMS/principal.jsp" class="item">
                <i class="home icon"></i>
                Home
            </a>

            <a href="/CMS/AtendimentoController?action=Socio.ListaSocio" class="item">
                <i class="users icon"></i>
                Sócios
            </a>
            
            <a href="/CMS/AtendimentoController?action=Evento.ListaEvento" class="item">
                <i class="ticket icon"></i>
                Eventos
            </a>
        </div>

    </c:when>
    <c:otherwise>

        <div class="ui inverted grey labeled icon left inline vertical sidebar menu">
            <a href="/CMS/principal.jsp" class="item">
                <i class="home icon"></i>
                Home
            </a>

            <a href="/CMS/SociedadeController?action=Dependente.ListaDependente" class="item">
                <i class="users icon"></i>
                Dependentes
            </a>
            
            <a href="/CMS/SociedadeController?action=AreaSocio.ListaEvento" class="item">
                <i class="ticket icon"></i>
                Eventos
            </a>
        </div>

    </c:otherwise>
</c:choose>






