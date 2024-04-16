package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class S3Service {
    @Value("${sample-spring-boot-project}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> listObjects() {
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }
}
