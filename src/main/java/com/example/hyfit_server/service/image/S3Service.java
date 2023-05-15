package com.example.hyfit_server.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.example.hyfit_server.config.response.BaseException;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class S3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file, String dirName) throws Exception {
        isValidFile(file.getOriginalFilename());
        String fileName = dirName + "/" + createNewFileName(file.getOriginalFilename());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(Mimetypes.getInstance().getMimetype(fileName));
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        metadata.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(bytes);

        return putS3(byteArrayIs, fileName, metadata);
    }

    public void deleteFile(String imageUrl) throws BaseException {
        if("".equals(imageUrl) == false && imageUrl != null) {
            String key = imageUrl.substring(imageUrl.indexOf("/") + 1);
            boolean isExistObject = amazonS3Client.doesObjectExist(bucket, key);
            if(isExistObject == true) {
                amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
            }
            else {
                throw new BaseException(NO_IMAGE_ERROR);
            }
        }
    }

    public boolean checkExistence(String imageUrl) throws BaseException {
        String key = imageUrl.substring(imageUrl.indexOf("/") + 1);
        return amazonS3Client.doesObjectExist(bucket, key);
    }


    // file을 s3에 저장 후, 이미지 path(/key) 반환
    private String putS3(ByteArrayInputStream byteArrayIs, String fileName, ObjectMetadata metadata) throws IOException {

        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, byteArrayIs, metadata)
                        .withCannedAcl(CannedAccessControlList.Private)
                         // private 권한으로 업로드 (cloudfront 통해서 접근 가능)
        );
        String imageUrl = amazonS3Client.getUrl(bucket, fileName).getPath();
        return imageUrl;
    }

    // 파일 이름에 의한 중복 방지를 위해 파일 이름 앞에 랜덤 문자열 삽입
    private String createNewFileName(String orgName) {
        String newName = UUID.randomUUID().toString() + "_" + orgName;
        return newName;
    }

    private void isValidFile(String fileName) throws BaseException {
        ArrayList<String> validExtension = new ArrayList<>();
        validExtension.add(".jpg");
        validExtension.add(".jpeg");
        validExtension.add(".png");
        validExtension.add(".JPG");
        validExtension.add(".JPEG");
        validExtension.add(".PNG");
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        if(!validExtension.contains(fileExtension)) {
            throw new BaseException(NOT_SUPPORTED_IMAGE_FORMAT);
        }
    }

}
