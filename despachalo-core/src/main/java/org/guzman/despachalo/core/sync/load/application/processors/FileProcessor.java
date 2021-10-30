package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;

public interface FileProcessor {
    void process(DataFrame<Object> dataFrame);
}
