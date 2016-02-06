<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>

        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->
                <form id="formularioDependente" class="ui form segment" action="/CMS/SociedadeController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="users icon"></i>
                        Visualiza Dependente
                    </h4>

                    <input type="hidden" name="idDependente" value="${dependente.idDependente}" >
                <input type="hidden" name="idSocio" value="${socioDependente.idSocio}" >


                <div class="fields">
                    <div class="eight wide field">
                        <label>Dependente</label>
                        <input type="text" value="${dependente.nomeDependente}" name="nomeDependente"disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Parentesco</label>
                        <select class="ui dropdown" name="parentesco" disabled="disabled">
                            <option value="">Selecione parentesco</option>
                            <c:forEach items="${listaParentesco}" var="parentesco">
                                <option value="${parentesco.idParentesco}" ${dependente.parentescoDependente.idParentesco == parentesco.idParentesco ? 'selected':''}>${parentesco.nomeParentesco}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="four wide field">
                        <label>Sexo</label>
                        <select class="ui dropdown" name="sexoDependente" disabled="disabled">
                            <option value="">Selecione sexo</option>
                            <option value="f" ${dependente.sexoDependente == 'f' ? 'selected':''}>Feminino</option>
                            <option value="m" ${dependente.sexoDependente == 'm' ? 'selected':''}>Masculino</option>
                        </select>
                    </div>
                </div>
                <div class="fields">
                    <div class="four wide field">
                        <label>Nascimento</label>
                        <input type="date" value="${dependente.nascimentoDependente}" name="nascimentoDependente" disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Nacionalidade</label>
                        <input type="text" value="${dependente.nacionalidadeDependente}" name="nacionalidadeDependente" disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Naturalidade</label>
                        <input type="text" value="${dependente.naturalidadeDependente}" name="naturalidadeDependente" disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Estado Civil</label>
                        <select class="ui dropdown" name="estadoCivilDependente" disabled="disabled">
                            <option value="">Estado Civil</option>
                            <option value="so" ${dependente.estadoCivilDependente == 'so' ? 'selected':''}>Solteiro(a)</option>
                            <option value="ca" ${dependente.estadoCivilDependente == 'ca' ? 'selected':''}>Casado(a)</option>
                            <option value="se" ${dependente.estadoCivilDependente == 'se' ? 'selected':''}>Separado(a)</option>
                            <option value="di" ${dependente.estadoCivilDependente == 'di' ? 'selected':''}>Divorciado(a)</option>
                            <option value="vi" ${dependente.estadoCivilDependente == 'vi' ? 'selected':''}>Viúvo(a)</option>
                        </select>
                    </div>
                </div>

                <button class="ui grey submit button" type="submit" name="action" value="Dependente.ListaDependente">Voltar</button>

                <c:choose> 
                    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia' || usuarioAutenticado.categoriaUsuario == 'Atendimento')}">
                        <button class="ui orange submit button" type="submit" name="action" value="Dependente.AlteraDependente">Alterar</button>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </form>

            <!--Fim do formulário-->

        </div>

        <!--Inicio do footer-->
        <jsp:include page="/carregar/footer.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do footer-->
        <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
            <!--Final do footer-->
            <!--Inicio do modal de cancelar-->
        <jsp:include page="/carregar/modalCancelar.jsp"></jsp:include>
        <!--Final do modal de cancelar-->
    </body>
</html>
