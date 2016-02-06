<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS</title>

        <jsp:include page="/carregar/head.jsp"></jsp:include>
            <script type="text/javascript" src="/CMS/js/validaFormSocio.js"></script>

        </head>

        <body>
            <div class="ui container">

                <!--Barra de navegação-->
            <jsp:include page="/carregar/menu.jsp"></jsp:include>
                <!--Fim da barra de navegação-->

                <!--Inicio do formulário-->
                <form id="formularioSocio" class="ui form segment" action="/CMS/AtendimentoController" method="POST">
                    <h4 class="ui horizontal header divider">
                        <i class="smile icon"></i>
                        Visualiza Sócios
                    </h4>

                    <input type="hidden" name="idSocio" value="${socio.idSocio}"/>


                <div class="fields">
                    <div class="eight wide field required">
                        <label>Sócio</label>
                        <input type="text" value="${socio.nomeSocio}" name="nomeSocio" id="nomeSocio" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>Categoria Sócio</label>
                        <select class="ui dropdown" name="categoriaSocio" id="categoriaSocio" disabled="disabled">
                            <option value="">Selecione categoria</option>
                            <c:forEach items="${listaCategoriaSocio}" var="categoriaSocio">
                                <option value="${categoriaSocio.idCategoriaSocio}" ${socio.categoriaSocio.idCategoriaSocio == categoriaSocio.idCategoriaSocio ? 'selected':''}>${categoriaSocio.nomeCategoriaSocio}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="four wide field">
                        <label>Membro desde:</label>
                        <input type="text" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${socio.inclusaoSocio}" />" name="inclusaoSocio" id="inclusaoSocio" disabled="disabled" placeholder="Preenchimento automático">
                    </div>

                </div>

                <div class="fields">

                    <div class="five wide field required">
                        <label>CPF</label>
                        <input type="text" value="${socio.cpfSocio}" name="cpfSocio" id="cpf" disabled="disabled">
                    </div>

                    <div class="three wide field required">
                        <label>Nascimento</label>
                        <input type="date" value="${socio.nascimentoSocio}" name="nascimentoSocio" id="nascimentoSocio" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>Sexo</label>
                        <select class="ui dropdown" name="sexoSocio" disabled="disabled">
                            <option value="">Selecione sexo</option>
                            <option value="f" ${socio.sexoSocio == 'f' ? 'selected':''}>Feminino</option>
                            <option value="m" ${socio.sexoSocio == 'm' ? 'selected':''}>Masculino</option>
                        </select>
                    </div>

                    <div class="four wide field required">
                        <label>Estado Civil</label>
                        <select class="ui dropdown" name="estadoCivilSocio" id="estadoCivilSocio" disabled="disabled">
                            <option value="">Estado Civil</option>
                            <option value="so" ${socio.estadoCivilSocio == 'so' ? 'selected':''}>Solteiro(a)</option>
                            <option value="ca" ${socio.estadoCivilSocio == 'ca' ? 'selected':''}>Casado(a)</option>
                            <option value="se" ${socio.estadoCivilSocio == 'se' ? 'selected':''}>Separado(a)</option>
                            <option value="di" ${socio.estadoCivilSocio == 'di' ? 'selected':''}>Divorciado(a)</option>
                            <option value="vi" ${socio.estadoCivilSocio == 'vi' ? 'selected':''}>Viúvo(a)</option>
                        </select>
                    </div>

                </div>

                <div class="fields">

                    <div class="four wide field required">
                        <label>Telefone Principal</label>
                        <input type="text" value="${socio.telefone1}" name="telefone1" id="telefone1" disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Telefone Alternativo 1</label>
                        <input type="text" value="${socio.telefone2}" name="telefone2" disabled="disabled">
                    </div>

                    <div class="four wide field">
                        <label>Telefone Alternativo 2</label>
                        <input type="text" value="${socio.telefone3}" name="telefone3" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>E-mail</label>
                        <input type="text" value="${socio.usuarioSocio.email}" name="email" id="email" disabled="disabled">
                    </div>

                </div>

                <div class="fields">

                    <div class="four wide field required">
                        <label>Profissão</label>
                        <input type="text" value="${socio.profissao}" name="profissao" id="profissao" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>Nacionalidade</label>
                        <input type="text" value="${socio.nacionalidadeSocio}" name="nacionalidadeSocio" id="nacionalidadeSocio" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>Naturalidade</label>
                        <input type="text" value="${socio.naturalidadeSocio}" name="naturalidadeSocio" id="naturalidadeSocio" disabled="disabled">
                    </div>

                </div>

                <div class="fields">

                    <div class="eight wide field required">
                        <label>Logradouro</label>
                        <input type="text" value="${socio.logradouro}" name="logradouro" id="logradouro" disabled="disabled">
                    </div>

                    <div class="two wide field required">
                        <label>Número</label>
                        <input type="text" value="${socio.numero}" name="numero" id="numero" disabled="disabled">
                    </div>

                    <div class="two wide field required">
                        <label>CEP</label>
                        <input type="text" value="${socio.cep}" name="cep" id="cep" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>UF</label>
                        <select class="ui dropdown" name="uf" id="uf" disabled="disabled">
                            <option value="">UF</option>
                            <option value="ac" ${socio.uf == 'ac' ? 'selected':''}>Acre (AC)</option>
                            <option value="al" ${socio.uf == 'al' ? 'selected':''}>Alagoas (AL)</option>
                            <option value="am" ${socio.uf == 'am' ? 'selected':''}>Amazonas (AM)</option>
                            <option value="ap" ${socio.uf == 'ap' ? 'selected':''}>Amapá (AP)</option>
                            <option value="ba" ${socio.uf == 'ba' ? 'selected':''}>Bahia (BA)</option>
                            <option value="ce" ${socio.uf == 'ce' ? 'selected':''}>Ceará (CE)</option>
                            <option value="df" ${socio.uf == 'df' ? 'selected':''}>Distrito Federal (DF)</option>
                            <option value="es" ${socio.uf == 'es' ? 'selected':''}>Espírito Santo (ES)</option>
                            <option value="go" ${socio.uf == 'go' ? 'selected':''}>Goiás (GO)</option>
                            <option value="ma" ${socio.uf == 'ma' ? 'selected':''}>Maranhão (MA)</option>
                            <option value="mt" ${socio.uf == 'mt' ? 'selected':''}>Mato Grosso (MT)</option>
                            <option value="ms" ${socio.uf == 'ms' ? 'selected':''}>Mato Grosso do Sul (MS)</option>
                            <option value="mg" ${socio.uf == 'mg' ? 'selected':''}>Minas Gerais (MG)</option>
                            <option value="pa" ${socio.uf == 'pa' ? 'selected':''}>Pará (PA)</option>
                            <option value="pb" ${socio.uf == 'pb' ? 'selected':''}>Paraíba (PB)</option>
                            <option value="pr" ${socio.uf == 'pr' ? 'selected':''}>Paraná (PR)</option>
                            <option value="pe" ${socio.uf == 'pe' ? 'selected':''}>Pernambuco (PE)</option>
                            <option value="pi" ${socio.uf == 'pi' ? 'selected':''}>Piauí (PI)</option>
                            <option value="rj" ${socio.uf == 'rj' ? 'selected':''}>Rio de Janeiro (RJ)</option>
                            <option value="rn" ${socio.uf == 'rn' ? 'selected':''}>Rio Grande do Norte (RN)</option>
                            <option value="rs" ${socio.uf == 'rs' ? 'selected':''}>Rio Grande do Sul (RS)</option>
                            <option value="ro" ${socio.uf == 'ro' ? 'selected':''}>Rondônia (RO)</option>
                            <option value="rr" ${socio.uf == 'rr' ? 'selected':''}>Roraima (RR)</option>
                            <option value="sc" ${socio.uf == 'sc' ? 'selected':''}>Santa Catarina (SC)</option>
                            <option value="sp" ${socio.uf == 'sp' ? 'selected':''}>São Paulo (SP)</option>
                            <option value="se" ${socio.uf == 'se' ? 'selected':''}>Sergipe (SE)</option>
                            <option value="to" ${socio.uf == 'to' ? 'selected':''}>Tocantins (TO)</option>
                        </select>
                    </div>

                </div>

                <div class="fields">

                    <div class="four wide field required">
                        <label>Município</label>
                        <input type="text" value="${socio.municipio}" name="municipio" id="municipio" disabled="disabled">
                    </div>

                    <div class="four wide field required">
                        <label>Bairro</label>
                        <input type="text" value="${socio.bairro}" name="bairro" id="bairro" disabled="disabled">
                    </div>

                    <div class="eight wide field">
                        <label>Complemento</label>
                        <input type="text" value="${socio.complemento}" name="complemento" disabled="disabled">
                    </div>

                </div>

                <button class="ui grey submit button" type="submit" name="action" value="Socio.ListaSocio">Voltar</button>

                <c:choose> 
                    <c:when test="${(usuarioAutenticado.categoriaUsuario == 'Gerencia' || usuarioAutenticado.categoriaUsuario == 'Atendimento')}">
                        <button class="ui orange submit button" type="submit" name="action" value="Socio.AlteraSocio">Alterar</button>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </form>
            <!--Fim do formulário-->


            <!--Inicio do footer-->
            <jsp:include page="/carregar/footer.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do footer-->
            <jsp:include page="/carregar/sidebar.jsp"></jsp:include>
                <!--Final do footer-->
                <!--Inicio do modal de cancelar-->
            <jsp:include page="/carregar/modalCancelar.jsp"></jsp:include>
            <!--Final do modal de cancelar-->
        </div>
    </body>
</html>
