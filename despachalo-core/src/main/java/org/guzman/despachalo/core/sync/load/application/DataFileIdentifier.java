package org.guzman.despachalo.core.sync.load.application;

import org.guzman.despachalo.core.sync.load.domain.DataType;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DataFileIdentifier {
    public String identify(File file) {
        return DataType.CLIENT;
    }
}
