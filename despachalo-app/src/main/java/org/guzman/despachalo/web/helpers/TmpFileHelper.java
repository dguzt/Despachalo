package org.guzman.despachalo.web.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class TmpFileHelper {

    private static final String TMP_PATH = System.getProperty("java.io.tmpdir");

    /**
     * Get a temporal file, stored in a tmp directory in the OS,
     * from a multipart file. The temporal filename will be generated
     * randomly to avoid path injection attacks:
     * https://rules.sonarsource.com/java/RSPEC-2083
     *
     * @param multipart file as multipart
     * @param fileExtension extension to add in the filename generated
     * @return convFile file stored in a tmp directory
     */
    public File multipartToTmpFile(@NotNull MultipartFile multipart, String fileExtension) throws IOException {
        var filename = randomFilename() + fileExtension;
        var pathname = Paths.get(TMP_PATH, filename).toString();
        var convFile = new File(pathname);

        multipart.transferTo(convFile);
        return convFile;
    }

    /**
     * Get a temporal file, stored in a tmp directory in the OS,
     * from a multipart file. The temporal filename will be generated
     * randomly to avoid path injection attacks:
     * https://rules.sonarsource.com/java/RSPEC-2083
     *
     * @param multipart file as multipart
     * @return convFile file stored in a tmp directory
     */
    public File multipartToTmpFile(@NotNull MultipartFile multipart) throws IOException {
        return multipartToTmpFile(multipart, "");
    }

        private String randomFilename() {
        return UUID.randomUUID().toString();
    }
}
