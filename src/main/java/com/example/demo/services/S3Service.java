package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class S3Service {
	private final S3Client s3Client;
    private final String bucketName;

    public S3Service(S3Client s3Client, @Value("${bucketName}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public List<String> listObjects() {
        ListObjectsResponse response = s3Client.listObjects(builder -> builder.bucket(bucketName));
        return response.contents().stream().map(S3Object::key).collect(Collectors.toList());
    }
}
