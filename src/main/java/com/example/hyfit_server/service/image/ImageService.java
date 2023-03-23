package com.example.hyfit_server.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class ImageService {
//    private final AmazonS3Client amazonS3Client;
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;

    //public String upload(MultipartFile multipartFile, String dir)
}
