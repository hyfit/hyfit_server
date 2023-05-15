package com.example.hyfit_server.service.image;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.image.ImageEntity;
import com.example.hyfit_server.domain.image.ImageRepository;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImagePlaceSaveDto;
import com.example.hyfit_server.dto.Image.ImagePostSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.awt.*;

import static com.example.hyfit_server.config.response.BaseResponseStatus.NO_IMAGE_ERROR;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    public ImageDto saveImage(ImagePlaceSaveDto imagePlaceSaveDto, ImagePostSaveDto imagePostSaveDto) throws BaseException {
        ImageEntity result = new ImageEntity();
        // post 이미지 저장
        if(imagePlaceSaveDto == null) {
            result = imageRepository.save((imagePostSaveDto.toEntity()));
        }
        // place 이미지 저장
        else{
            // 이미 해당 place에 저장된 이미지가 있는 경우 -> 새로운 이미지 url로 데이터 업데이트
            if(imageRepository.findByPlaceId(imagePlaceSaveDto.getPlaceId()) != null) {
                result = imageRepository.findByPlaceId(imagePlaceSaveDto.getPlaceId());
                s3Service.deleteFile(result.getImageUrl());
                result.updateImageUrl(imagePlaceSaveDto.getImageUrl());
            }
            // 기존에 저장된 이미지가 없는 place -> 이미지 데이터 저장
            else{
                result = imageRepository.save(imagePlaceSaveDto.toEntity());

            }
        }

        return result.toDto();
    }

    public ImageDto getImageUrl(long placeId) throws BaseException{
        ImageEntity imageEntity = imageRepository.findByPlaceId(placeId);
        if(imageEntity == null || !s3Service.checkExistence(imageEntity.getImageUrl())) {
            throw new BaseException(NO_IMAGE_ERROR);
        }
        return imageEntity.toDto();
    }



    public void deleteImage(long placeId) throws BaseException {
        ImageEntity imageEntity = imageRepository.findByPlaceId(placeId);
        if(imageEntity == null) {
            throw new BaseException(NO_IMAGE_ERROR);
        }

        String imageUrl = imageEntity.getImageUrl();

        s3Service.deleteFile(imageUrl);

        imageRepository.deleteByPlaceId(placeId);
    }

}
