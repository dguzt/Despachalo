package org.guzman.despachalo.external.referralguidepdf.export.pdf;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.guzman.despachalo.commons.hexagonal.extra.HelperService;
import org.guzman.despachalo.external.referralguidepdf.ReferralGuide;
import org.guzman.despachalo.external.referralguidepdf.helpers.TmpFile;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@HelperService
@RequiredArgsConstructor
public class PdfGuideExporter {
    private final TmpFile tmpFile;

    public File generatePdf(ReferralGuide referralGuide) throws JRException {
        var vehicles = referralGuide.getVehicles();
        var dsVehicles = new JRBeanArrayDataSource(vehicles.toArray());

        var parameters = parameters(referralGuide, dsVehicles);
        var guideJasperStream = getClass().getResourceAsStream("/referral-guide.jrxml");

        var jasperReport = JasperCompileManager.compileReport(guideJasperStream);
        var jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dsVehicles);
        var filename = tmpFile.newTmpFilename("pdf");

        exportToTmp(jasperPrint, filename);
        return new File(filename);
    }

    private void exportToTmp(JasperPrint jasperPrint, String filename) throws JRException {
        JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
    }

    private Map<String, Object> parameters(ReferralGuide guide, JRBeanArrayDataSource dsVehicles) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var parameters = new HashMap<String, Object>();

        parameters.put("D_DS_VEHICLES_ROWS", dsVehicles);
        parameters.put("D_COMPANY_NAME", guide.getCompany().getName());
        parameters.put("D_COMPANY_RUC", guide.getCompany().getRuc());
        parameters.put("D_GUIDE_ID", guide.getId());

        parameters.put("D_DATA_ISSUED_AT", guide.getTransport().getIssuedAt().format(dateFormatter));
        parameters.put("D_DATA_TRANSPORTED_AT", guide.getTransport().getTransportedAt().format(dateFormatter));
        parameters.put("D_DATA_TRANSFER_REASON", guide.getTransport().getReason().getValue());
        parameters.put("D_DATA_TRANSPORT_TYPE", guide.getTransport().getType().getValue());
        parameters.put("D_DATA_TOTAL_WEIGHT", guide.getTransport().getTotalWeight());

        parameters.put("D_CLIENT_NAME", guide.getClient().getName());
        parameters.put("D_CLIENT_RUC", guide.getClient().getRuc());

        parameters.put("D_POINTS_ORIGIN_ADDRESS", guide.getOriginPoint().getAddress());
        parameters.put("D_POINTS_DESTINY_ADDRESS", guide.getDestinationPoint().getAddress());

        return parameters;
    }
}
