package br.cms.Controller.Atendimento.Pdf;

import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeraContrato implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;

        String idSocio = req.getParameter("idSocio");

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(Long.parseLong(idSocio));

        SocioDAO socioDAO = new SocioDAO();
        socioOBJ = socioDAO.consultarPorId(socioOBJ);

        if (socioOBJ != null) {

            try {
                //Instancia um novo documento
                Document documentOBJ = new Document();

                //Define uma saida para o documento
                PdfWriter.getInstance(documentOBJ, res.getOutputStream());

                //Abre o documento
                documentOBJ.open();

            //Escreve no documento
                //Formatando a fonte
                Font titulo = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
                Font texto = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                Font texto2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            //Instancia um novo paragrafo
                //Titulo do documento
                Paragraph p1 = new Paragraph("CONTRATO DE PRESTAÇÃO DE SERVIÇOS", titulo);
                p1.setAlignment(Element.ALIGN_CENTER);
                p1.setSpacingAfter(20);
                documentOBJ.add(p1);

                Paragraph p2 = new Paragraph("IDENTIFICAÇÃO DAS PARTES CONTRATANTES", titulo);
                p2.setAlignment(Element.ALIGN_CENTER);
                p2.setSpacingAfter(15);
                documentOBJ.add(p2);

                Paragraph p3 = new Paragraph("CONTRATANTE: " + socioOBJ.getNomeSocio() + ", "
                        + socioOBJ.getNacionalidadeSocio() + ", " + socioOBJ.getEstadoCivilSocio() + ", "
                        + socioOBJ.getProfissao() + ", residente e domiciliado na "
                        + socioOBJ.getLogradouro() + ", número " + socioOBJ.getNumero() + ", bairro "
                        + socioOBJ.getBairro() + ", Cep " + socioOBJ.getCep() + ", Cidade "
                        + socioOBJ.getMunicipio() + ", no Estado de " + socioOBJ.getUf() + ".", texto);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                p3.setSpacingAfter(5);
                documentOBJ.add(p3);

                Paragraph p4 = new Paragraph("CONTRATADO: CMS, com sede em Mogi das Cruzes, "
                        + "na Av. Dr. Cândido Xavier de Almeida Souza, número 20, bairro Mogi das Cruzes, "
                        + "Cep 11222-333, no Estado de SP, inscrita no CNPJ sob número 00.000.000/0000-00, "
                        + "neste ato representado pelo seu diretor Walter Matsuo, Brasileiro, Solteiro, "
                        + "residente e domiciliado na Rua Lorem Inspum, número 20, bairro Braz Cubas, "
                        + "Cep 11222-333, Cidade Mogi das Cruzes, no estado de SP.", texto);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                p4.setSpacingAfter(5);
                documentOBJ.add(p4);

                Paragraph p5 = new Paragraph("As partes acima identificadas têm, entre si, "
                        + "justo e acertado o presente Contrato de Prestação de Serviços, "
                        + "que se regerá pelas cláusulas seguintes e pelas condições de preço, "
                        + "forma e termo de pagamento descritas no presente.", texto2);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                p5.setSpacingAfter(15);
                documentOBJ.add(p5);

                Paragraph p6 = new Paragraph("DO OBJETIVO DO CONTRATO", titulo);
                p6.setAlignment(Element.ALIGN_CENTER);
                p6.setSpacingAfter(15);
                documentOBJ.add(p6);

                Paragraph p7 = new Paragraph("Cláusula 1ª. É objeto do presente contrato a prestação do "
                        + "serviço de (xxx) (Descrever pormenorizadamente o serviço, com todas as suas "
                        + "especificidades, incluindo dados técnicos que possam vir a influir no "
                        + "entendimento do contrato, e, se possível for, dados decorrentes de perícia "
                        + "realizada envolvendo as situações em que serão realizadas o serviço).", texto);
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                p7.setSpacingAfter(15);
                documentOBJ.add(p7);

                Paragraph p8 = new Paragraph("OBRIGAÇÕES DO CONTRATANTE", titulo);
                p8.setAlignment(Element.ALIGN_CENTER);
                p8.setSpacingAfter(15);
                documentOBJ.add(p8);

                Paragraph p9 = new Paragraph("Cláusula 2ª. O CONTRATANTE deverá fornecer ao CONTRATADO "
                        + "todas as informações necessárias à realização do serviço, devendo especificar os "
                        + "detalhes necessários à perfeita consecução do mesmo, e a forma de como ele deve ser "
                        + "entregue.", texto);
                p9.setAlignment(Element.ALIGN_JUSTIFIED);
                p9.setSpacingAfter(5);
                documentOBJ.add(p9);

                Paragraph p10 = new Paragraph("Cláusula 3ª. O CONTRATANTE deverá efetuar o pagamento na forma e "
                        + "condições estabelecidas na cláusula 6ª.", texto);
                p10.setAlignment(Element.ALIGN_JUSTIFIED);
                p10.setSpacingAfter(15);
                documentOBJ.add(p10);

                Paragraph p11 = new Paragraph("OBRIGAÇÕES DO CONTRATADO", titulo);
                p11.setAlignment(Element.ALIGN_CENTER);
                p11.setSpacingAfter(15);
                documentOBJ.add(p11);

                Paragraph p12 = new Paragraph("Cláusula 4ª. É dever do CONTRATADO oferecer ao contratante a cópia "
                        + "do presente instrumento, contendo todas as especificidades da prestação de serviço "
                        + "contratada.", texto);
                p12.setAlignment(Element.ALIGN_JUSTIFIED);
                p12.setSpacingAfter(5);
                documentOBJ.add(p12);

                Paragraph p13 = new Paragraph("Cláusula 5ª. O CONTRATADO deverá fornecer Nota Fiscal de Serviços,"
                        + " referente ao(s) pagamento(s) efetuado(s) pelo CONTRATANTE.", texto);
                p13.setAlignment(Element.ALIGN_JUSTIFIED);
                p13.setSpacingAfter(15);
                documentOBJ.add(p13);

                Paragraph p14 = new Paragraph("DO PREÇO E DAS CONDIÇÕES DE PAGAMENTO", titulo);
                p14.setAlignment(Element.ALIGN_CENTER);
                p14.setSpacingAfter(15);
                documentOBJ.add(p14);

                Paragraph p15 = new Paragraph("Cláusula 6ª. O presente serviço será remunerado pela quantia"
                        + "estabelecida em nota fiscal, referente aos serviços efetivamente prestados, "
                        + "devendo ser pago em dinheiro ou cheque, ou outra forma de pagamento em que ocorra "
                        + "a prévia concordância de ambas as partes.", texto);
                p15.setAlignment(Element.ALIGN_JUSTIFIED);
                p15.setSpacingAfter(15);
                documentOBJ.add(p15);

                Paragraph p16 = new Paragraph("DO INADIMPLEMENTO, DO DESCUMPRIMENTO E DA MULTA", titulo);
                p16.setAlignment(Element.ALIGN_CENTER);
                p16.setSpacingAfter(15);
                documentOBJ.add(p16);

                Paragraph p17 = new Paragraph("Cláusula 7ª. Em caso de inadimplemento por parte do CONTRATANTE "
                        + "quanto ao pagamento do serviço prestado, deverá incidir sobre o valor do presente "
                        + "instrumento, multa pecuniária de 2%, juros de mora de 1% ao mês e "
                        + "correção monetária. ", texto);
                p17.setAlignment(Element.ALIGN_JUSTIFIED);
                p17.setSpacingAfter(5);
                documentOBJ.add(p17);

                Paragraph p18 = new Paragraph(" Parágrafo único. Em caso de cobrança judicial, devem ser "
                        + "acrescidas custas processuais e 20% de honorários advocatícios.", texto);
                p18.setAlignment(Element.ALIGN_JUSTIFIED);
                p18.setSpacingAfter(5);
                documentOBJ.add(p18);

                Paragraph p19 = new Paragraph("Cláusula 8ª. No caso de não haver o cumprimento de qualquer uma "
                        + "das cláusulas, exceto a 6ª, do presente instrumento, a parte que não cumpriu deverá "
                        + "pagar uma multa de 10% do valor do contrato para a outra parte.", texto);
                p19.setAlignment(Element.ALIGN_JUSTIFIED);
                p19.setSpacingAfter(15);
                documentOBJ.add(p19);

                Paragraph p20 = new Paragraph("DA RESCISÃO IMOTIVADA", titulo);
                p20.setAlignment(Element.ALIGN_CENTER);
                p20.setSpacingAfter(15);
                documentOBJ.add(p20);

                Paragraph p22 = new Paragraph("Cláusula 9ª. Poderá o presente instrumento ser rescindido por "
                        + "qualquer uma das partes, em qualquer momento, sem que haja qualquer tipo de motivo "
                        + "relevante, não obstante a outra parte deverá ser avisada previamente por escrito, "
                        + "no prazo de 30 dias.", texto);
                p22.setAlignment(Element.ALIGN_JUSTIFIED);
                p22.setSpacingAfter(5);
                documentOBJ.add(p22);

                Paragraph p23 = new Paragraph("Cláusula 10ª. Caso o CONTRATANTE já tenha realizado o pagamento "
                        + "pelo serviço, e mesmo assim, requisite a rescisão imotivada do presente contrato, "
                        + "terá o valor da quantia paga devolvido, deduzindo-se 2% de taxas "
                        + "administrativas.", texto);
                p23.setAlignment(Element.ALIGN_JUSTIFIED);
                p23.setSpacingAfter(5);
                documentOBJ.add(p23);

                Paragraph p24 = new Paragraph("Cláusula 11ª. Caso seja o CONTRATADO quem requeira a rescisão "
                        + "imotivada, deverá devolver a quantia que se refere aos serviços por ele não prestados "
                        + "ao CONTRATANTE, acrescentado de 2% de taxas administrativas.", texto);
                p24.setAlignment(Element.ALIGN_JUSTIFIED);
                p24.setSpacingAfter(15);
                documentOBJ.add(p24);

                Paragraph p25 = new Paragraph("Por estarem assim justos e contratados, firmam o presente "
                        + "instrumento, em duas vias de igual teor.", texto);
                p25.setAlignment(Element.ALIGN_JUSTIFIED);
                p25.setSpacingAfter(15);
                documentOBJ.add(p25);

                //Fecha o documento após realizar as operações
                documentOBJ.close();
            } catch (DocumentException de) {
                throw new IOException("Não foi possível gerar o contrato do Sócio, notifique o TI!");
            }
        } else {
            pagina = "/principal.jsp";
            
            req.setAttribute("msg", "Não foi possível gerar o contrato, Sócio inexistente !");
        }

        return pagina;
    }

}
