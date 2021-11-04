package org.guzman.despachalo.adapter.persistence.modules.dispatch.guide;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.GetReferralGuidePort;
import org.guzman.despachalo.external.referralguidepdf.*;
import org.guzman.despachalo.external.referralguidepdf.export.pdf.PdfGuideExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReferralGuidePersistenceAdapter implements GetReferralGuidePort {
    private final Logger logger = LoggerFactory.getLogger(ReferralGuidePersistenceAdapter.class);
    private final PdfGuideExporter pdfGuideExporter;

    @Override
    public File getReferralGuide() {
        try {
            var now = LocalDateTime.now();

            var company = new SendingCompany("Despáchalo Pe", "12345678902");
            var client = new TargetClient("Tiendas Gutierrez", "1234567801");
            var transport = new Transport(TransportType.PRIVATE, now, now, TransportReason.SALE, "300");
            var originPoint = new OriginPoint("Av San Martín 120", "12345");
            var destinationPoint = new DestinationPoint("Av Julio Carranza 340", "12345");
            var vehicles = Collections.<TransportVehicle>emptyList();

            var guideData = ReferralGuide.builder()
                    .id("EG01-2")
                    .company(company)
                    .client(client)
                    .transport(transport)
                    .originPoint(originPoint)
                    .destinationPoint(destinationPoint)
                    .vehicles(vehicles)
                    .build();
            return pdfGuideExporter.generatePdf(guideData);
        } catch (Exception e) {
            logger.error("Cannot build the referral guide pdf file: {}", e.getMessage());
            throw new RuntimeException("Cannot build the referral guide pdf file");
        }
    }
}
