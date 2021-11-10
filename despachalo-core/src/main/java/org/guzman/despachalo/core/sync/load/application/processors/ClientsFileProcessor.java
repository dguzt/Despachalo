package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.MapperDataFrame;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterClientsPort;
import org.guzman.despachalo.core.sync.load.domain.ClientToRegister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static org.guzman.despachalo.core.sync.load.application.identifier.ClientColumns.*;

@Service
@RequiredArgsConstructor
public class ClientsFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final MapperDataFrame mapperDataFrame;
    private final RegisterClientsPort clientsPort;

    public Integer process(DataFrame<Object> dataFrame) {
        var codeClean = dataFrame.select(emptyFilter.filterNoEmptyStrings(CLIENT_CODE));
        var rucClean = codeClean.select(emptyFilter.filterNoEmptyStrings(RUC));
        var businessNameClean = rucClean.select(emptyFilter.filterNoEmptyStrings(BUSINESS_NAME));

        var uniqueCodes =  uniqueFilter.filterUnique(businessNameClean, CLIENT_CODE);
        var clients = mapperDataFrame.mapToObj(uniqueCodes, mapToClient());
        clientsPort.registerClients(clients);
        return clients.size();
    }

    private Function<List<Object>, ClientToRegister> mapToClient() {
        return (row) -> ClientToRegister.builder()
                .code(        (String) row.get(CLIENT_CODE))
                .ruc(                  row.get(RUC).toString())
                .businessName((String) row.get(BUSINESS_NAME))
                .build();
    }
}
