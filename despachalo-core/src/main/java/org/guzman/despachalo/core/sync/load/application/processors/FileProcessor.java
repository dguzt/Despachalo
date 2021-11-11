package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;

import java.io.IOException;

public interface FileProcessor {
    Integer process(DataFrame<Object> dataFrame) throws IOException;
}
