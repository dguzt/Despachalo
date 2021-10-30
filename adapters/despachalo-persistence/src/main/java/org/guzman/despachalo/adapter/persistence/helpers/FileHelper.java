package org.guzman.despachalo.adapter.persistence.helpers;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileHelper {
    private static final String TMP_PATH = System.getProperty("java.io.tmpdir");

    public File inputStreamToTmpFile(@NotNull InputStream inputStream) throws IOException {
        var pathname = Paths.get(TMP_PATH, randomFilename()).toString();
        Files.copy(inputStream, Paths.get(pathname), StandardCopyOption.REPLACE_EXISTING);
        return new File(pathname);
    }

    private String randomFilename() {
        return UUID.randomUUID().toString();
    }
}
