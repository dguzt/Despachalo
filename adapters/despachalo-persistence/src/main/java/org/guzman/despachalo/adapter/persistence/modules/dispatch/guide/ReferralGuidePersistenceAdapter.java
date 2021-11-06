package org.guzman.despachalo.adapter.persistence.modules.dispatch.guide;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.company.CompanyMapper;
import org.guzman.despachalo.adapter.persistence.modules.company.CompanyRepository;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.DispatchRepository;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverMapper;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckMapper;
import org.guzman.despachalo.adapter.persistence.modules.programming.ProgrammedVehicleRepository;
import org.guzman.despachalo.adapter.persistence.modules.sync.client.ClientMapper;
import org.guzman.despachalo.adapter.persistence.modules.sync.client.EndPointMapper;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.programming.application.port.out.GetDataForReferralGuidePort;
import org.guzman.despachalo.core.programming.application.port.out.GetReferralGuidePort;
import org.guzman.despachalo.core.programming.domain.ReferralGuideData;
import org.guzman.despachalo.core.programming.domain.VehicleDetails;
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
    private final ProgrammedVehicleRepository programmedVehicleRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final DriverMapper driverMapper;
    private final TruckMapper truckMapper;
    private final OrderRepository orderRepository;
    private final ClientMapper clientMapper;
    private final EndPointMapper endPointMapper;
    private final DispatchRepository dispatchRepository;

    @Override
    public ReferralGuideData getDataForReferralGuide(Long dispatchId, Long programmedVehicleId, Long orderId) {
        var companyRow = companyRepository.findAll().get(0);
        var company = companyMapper.toCompany(companyRow);

        var programmed = programmedVehicleRepository.findById(programmedVehicleId)
                .orElseThrow(() -> new RuntimeException("Programmed vehicle not found"));

        var orderRow = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        var dispatchRow = dispatchRepository.findById(dispatchId)
                .orElseThrow(() -> new RuntimeException("Dispatch not found"));

        var originPoint = new org.guzman.despachalo.core.sync.points.origin.domain.OriginPoint(1L, "Av. Nicolás de Piérola 189-403, Cercado de Lima", "140101");
        var destinationPoint = endPointMapper.toDestinationPoint(orderRow.getEndPoint());
        var client = clientMapper.toClient(orderRow.getEndPoint().getClient());
        var vehicle = truckMapper.toTruck(programmed.getTruck());
        var driver = driverMapper.toDriver(programmed.getDriver());
        var vehicleDetails = new VehicleDetails(vehicle.getId(), vehicle, driver, null, null);
        var transportedAt = dispatchRow.getDispatchAt();
        var issuedAt = dispatchRow.getCreatedAt();

        return new ReferralGuideData(
                orderId,
                company,
                client,
                300,
                vehicleDetails,
                originPoint,
                destinationPoint,
                issuedAt,
                transportedAt
        );
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
