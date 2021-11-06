package org.guzman.despachalo.adapter.persistence.modules.dispatch.guide;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.programming.application.port.out.GetDataForReferralGuidePort;
import org.guzman.despachalo.core.programming.application.port.out.GetReferralGuidePort;
import org.guzman.despachalo.core.programming.domain.ReferralGuideData;
import org.guzman.despachalo.external.referralguidepdf.*;
import org.guzman.despachalo.external.referralguidepdf.export.pdf.PdfGuideExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReferralGuidePersistenceAdapter implements GetDataForReferralGuidePort, GetReferralGuidePort {
    private final Logger logger = LoggerFactory.getLogger(ReferralGuidePersistenceAdapter.class);
    private final PdfGuideExporter pdfGuideExporter;

    @Override
    public ReferralGuideData getDataForReferralGuide(Long dispatchId, Long programmedVehicleId, Long orderId) {
        return null;
    }

    @Override
    public File getReferralGuide(ReferralGuideData data) {
        try {
            var company = new SendingCompany(data.getCompany().getBusinessName(), data.getCompany().getRuc());
            var client = new TargetClient(data.getClient().getBusinessName(), data.getClient().getRuc());
            var transport = new Transport(TransportType.PRIVATE, data.getIssuedAt(), data.getTransportedAt(), TransportReason.SALE, data.getTotalWeight().toString());
            var originPoint = new OriginPoint(data.getOriginPoint().getAddress(), data.getOriginPoint().getGeoCode());
            var destinationPoint = new DestinationPoint(data.getDestinationPoint().getAddress(), data.getDestinationPoint().getGeoCode());
            var vehicles = Collections.<TransportVehicle>emptyList();

            var guideData = ReferralGuide.builder()
                    .id(String.format("EG01-%d", data.getId()))
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
