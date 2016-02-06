package br.cms.Controller.Gerencia.Relatorio;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class MultiExportador {

    public static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String CONTENT_TYPE_PDF = "application/pdf";

    public static final String EXTENSAO_EXCEL_XLSX = "xlsx";
    public static final String EXTENSAO_PDF = "pdf";

    public static final String NOME_ARQUIVO = "CMS_Relatorio";

    public HttpServletResponse exportar(String extensao, JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos) {

        Date dataReport = new Date();

        DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataReportSt = df.format(dataReport);

        if (extensao.equalsIgnoreCase(EXTENSAO_EXCEL_XLSX)) {
            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

            try {
                exporter.exportReport();
            } catch (JRException e) {
                throw new RuntimeException("Não foi possível gerar o arquivo XLSX, notifique o TI");
            }

            String nomeArquivoExtensao = dataReportSt + "_" + NOME_ARQUIVO + "." + EXTENSAO_EXCEL_XLSX;
            response.setHeader("Content-Disposition", "inline; filename=" + nomeArquivoExtensao);

            response.setContentType(CONTENT_TYPE_XLSX);
            response.setContentLength(baos.size());

        } else if (extensao.equalsIgnoreCase(EXTENSAO_PDF)) {
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            try {
                exporter.exportReport();
            } catch (JRException e) {
                throw new RuntimeException("Não foi possível gerar o arquivo PDF, notifique o TI");
            }

            String nomeArquivoExtensao = dataReportSt + "_" + NOME_ARQUIVO + "." + EXTENSAO_PDF;
            response.setHeader("Content-Disposition", "inline; filename=" + nomeArquivoExtensao);

            response.setContentType(CONTENT_TYPE_PDF);
            response.setContentLength(baos.size());
        }

        return response;
    }
}
