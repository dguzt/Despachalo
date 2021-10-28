package org.guzman.despachalo.external.awss3;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.extra.ExternalService;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@ExternalService
@RequiredArgsConstructor
public class AwsStorageExternalService {
    private final AwsStorageConfig awsStorageConfig;

    private AmazonS3 getS3Client() {
        var awsCredentials = new BasicAWSCredentials(
                awsStorageConfig.getCredentials().getAccessKey(),
                awsStorageConfig.getCredentials().getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withRegion(awsStorageConfig.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public String saveFile(File file, String path) {
        var bucketName = awsStorageConfig.getBucket().getName();
        var s3Client = this.getS3Client();

        var fileKey = Paths.get(path, file.getName()).toString();
        var request = new PutObjectRequest(bucketName, fileKey, file)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(request);
        return fileKey;
    }

    public String getUrlFile(String fileKey) {
        var bucketName = awsStorageConfig.getBucket().getName();
        var s3Client = this.getS3Client();

        return s3Client
                .getUrl(bucketName, fileKey)
                .toString();
    }

    public URL generatePresignedUrlForObject(String fileKey) {
        var bucketName = awsStorageConfig.getBucket().getName();
        var s3Client = getS3Client();
        var availableMinutes = awsStorageConfig.getMinutesPresigned();

        var expirationTime = LocalDateTime.now()
                .plusMinutes(availableMinutes)
                .toInstant(ZoneOffset.UTC);

        var presignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileKey)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(Date.from(expirationTime));

        return s3Client.generatePresignedUrl(presignedUrlRequest);
    }
}
