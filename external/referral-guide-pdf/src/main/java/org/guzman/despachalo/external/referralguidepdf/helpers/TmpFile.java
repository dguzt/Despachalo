package org.guzman.despachalo.external.referralguidepdf.helpers;

import org.guzman.despachalo.commons.hexagonal.extra.HelperService;

import java.nio.file.Paths;
import java.util.UUID;

@HelperService
public class TmpFile {
    private static final String TMP_PATH = System.getProperty("java.io.tmpdir");

    public String newTmpFilename(String ext) {
        var filename = String.format("%s.%s", randomFilename(), ext);
        return Paths.get(TMP_PATH, filename).toString();
    }

    private String randomFilename() {
        return UUID.randomUUID().toString();
    }
}
