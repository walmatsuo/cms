<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormDependente.js"></script>

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
                        Gerenciar Dependentes
                    </h4>

                    <input type="hidden" name="idDependente" value="${dependente.idDependente}" >
                <input type="hidden" name="idSocio" value="${socioDependente.idSocio}" >


                <div class="fields">
                    <div class="eight wide field required">
                        <label>Dependente</label>
                        <input type="text" value="${dependente.nomeDependente}" name="nomeDependente">
                    </div>

                    <div class="four wide field required">
                        <label>Parentesco</label>
                        <select class="ui dropdown" name="parentesco">
                            <option value="">Selecione parentesco</option>
                            <c:forEach items="${listaParentesco}" var="parentesco">
                                <option value="${parentesco.idParentesco}" ${dependente.parentescoDependente.idParentesco == parentesco.idParentesco ? 'selected':''}>${parentesco.nomeParentesco}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="four wide field required">
                        <label>Sexo</label>
                        <select class="ui dropdown" name="sexoDependente">
                            <option value="">Selecione sexo</option>
                            <option value="f" ${dependente.sexoDependente == 'f' ? 'selected':''}>Feminino</option>
                            <option value="m" ${dependente.sexoDependente == 'm' ? 'selected':''}>Masculino</option>
                        </select>
                    </div>
                </div>
                <div class="fields">
                    <div class="four wide field required">
                        <label>Nascimento</label>
                        <input type="date" value="${dependente.nascimentoDependente}" name="nascimentoDependente">
                    </div>

                    <div class="four wide field required">
                        <label>Nacionalidade</label>
                        <input type="text" value="${dependente.nacionalidadeDependente}" name="nacionalidadeDependente">
                    </div>

                    <div class="four wide field required">
                        <label>Naturalidade</label>
                        <input type="text" value="${dependente.naturalidadeDependente}" name="naturalidadeDependente">
                    </div>

                    <div class="four wide field required">
                        <label>Estado Civil</label>
                        <select class="ui dropdown" name="estadoCivilDependente">
                            <option value="">Estado Civil</option>
                            <option value="so" ${dependente.estadoCivilDependente == 'so' ? 'selected':''}>Solteiro(a)</option>
                            <option value="ca" ${dependente.estadoCivilDependente == 'ca' ? 'selected':''}>Casado(a)</option>
                            <option value="se" ${dependente.estadoCivilDependente == 'se' ? 'selected':''}>Separado(a)</option>
                            <option value="di" ${dependente.estadoCivilDependente == 'di' ? 'selected':''}>Divorciado(a)</option>
                            <option value="vi" ${dependente.estadoCivilDependente == 'vi' ? 'selected':''}>Viúvo(a)</option>
                        </select>
                    </div>

                </div>

                <button class="ui inverted green submit button" id="submit" type="submit" name="action" value="Dependente.SalvaDependente">Salvar</button>
                <div class="ui inverted red button" id="btn-cancelar">Cancelar</div>
                <div class="ui inverted blue clear button">Limpar</div>
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
