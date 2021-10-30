package org.guzman.despachalo.core.sync.load.application.port.out;

import java.io.File;
import java.io.IOException;

public interface GetLoadFilePort {
    File getLoadFile(String uri) throws IOException;
}
