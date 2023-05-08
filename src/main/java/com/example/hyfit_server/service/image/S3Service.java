package com.example.hyfit_server.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.hyfit_server.config.response.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;
import static javassist.compiler.ast.ASTList.concat;

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

        String contentType ="image/" + fileName.substring(fileName.lastIndexOf(".") + 1);

        metadata.setContentType(contentType);
        metadata.setContentLength(file.getSize());
        metadata.setContentDisposition("inline");

        //        File uploadFile = null;
//        Optional<File> tempFile = convertToFile(file);
//        if(tempFile.isEmpty()) {
//            throw new IllegalArgumentException("파일 변환 실패");
//        }
//        uploadFile = tempFile.get();
//
//        String imageUrl = putS3(uploadFile, fileName);
//        if(imageUrl != null && !imageUrl.isEmpty()) {
//            removeNewFile(uploadFile);
//        }
        return putS3(file, fileName, metadata);
    }

    public void deleteFile(String imageUrl) throws BaseException {
        if("".equals(imageUrl) == false && imageUrl != null) {
            boolean isExistObject = amazonS3Client.doesObjectExist(bucket, imageUrl);

            if(isExistObject == true) {
                amazonS3Client.deleteObject(bucket, imageUrl);
            }
        }
    }


    // file을 s3에 저장 후, cloudfront 이미지 url 반환
    private String putS3(MultipartFile uploadFile, String fileName, ObjectMetadata metadata) throws IOException {


        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.Private)
                         // private 권한으로 업로드 (cloudfront 통해서 접근 가능)
        );
        String imageUrl = amazonS3Client.getUrl(bucket, fileName).getPath();
        return imageUrl;
    }

//    // file 변환 과정에서 생긴 로컬 파일 삭제
//    private void removeNewFile(File targetFile) {
//        if(targetFile.delete()) {
//            log.info("로컬 파일 삭제 완료");
//        } else {
//            log.info("로컬 파일 삭제 실패");
//        }
//    }

//    private Optional<File> convertToFile(MultipartFile file) throws IOException {
//        File convertedFile = new File(file.getOriginalFilename());
//        if(convertedFile.createNewFile()) {
//            try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
//                fos.write(file.getBytes());
//                fos.close();
//            }
//            return Optional.of(convertedFile);
//        }
//        return Optional.empty();
//    }


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
