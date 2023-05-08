package com.example.hyfit_server.service.image;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.image.ImageEntity;
import com.example.hyfit_server.domain.image.ImageRepository;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImagePlaceSaveDto;
import com.example.hyfit_server.dto.Image.ImagePostSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        if(imagePlaceSaveDto == null) {
            result = imageRepository.save((imagePostSaveDto.toEntity()));
        }
        else{
            result = imageRepository.save(imagePlaceSaveDto.toEntity());
        }


        return result.toDto();
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
