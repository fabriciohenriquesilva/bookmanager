package br.com.bookmanager.core;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] createReport(String reportPath, Map<String, Object> parameters, List<?> dataSource) {

        try {
            InputStream reportStream = getClass().getResourceAsStream(reportPath);
            if (reportStream == null) {
                throw new IllegalArgumentException("Arquivo de relatório não encontrado: " + reportPath);
            }

            JasperReport jasperReport;

            if (reportPath.endsWith(".jrxml")) {
                jasperReport = JasperCompileManager.compileReport(reportStream);
            } else {
                jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
            }

            JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório Jasper: " + e.getMessage(), e);
        }
    }
}
