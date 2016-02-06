package br.cms.Controller.Gerencia.Relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GeradorRelatorio {

    public static final String PASTA_JRXML = "C:\\CMS\\src\\java\\br\\cms\\iReport/";

    public void gerarRelatorio(String extensao, String arquivoJrxml, HttpServletResponse response, JRDataSource jds) {

        try {
            // Compilando arquivo jrxml para JasperReport
            JasperReport jr = JasperCompileManager.compileReport(PASTA_JRXML + arquivoJrxml + ".jrxml");

            // Criando e preenchendo arquivo JasperPrint com os dados do ArrayList
            JasperPrint jp = JasperFillManager.fillReport(jr, null, jds);

            // Criando uma ByteArrayOutputStream onde os dados serão escritos
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Exportando Relatorio
            MultiExportador mExportador = new MultiExportador();
            response = mExportador.exportar(extensao, jp, response, baos);

            try {
                // Recuperando OutPutStream
                ServletOutputStream outputStream = response.getOutputStream();

                // Escrevendo o arquivo na ByteArrayOutPutSteam
                baos.writeTo(outputStream);
                // Entregando o arquivo para o usuário
                outputStream.flush();
            } catch (IOException ioe) {
                throw new RuntimeException("Não foi possível exportar o relatório, notifique o TI");
            }
        } catch (JRException jre) {
            throw new RuntimeException("Não foi possível gerar o relatório, notifique o TI!");
        }
    }
}
