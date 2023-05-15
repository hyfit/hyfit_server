package com.example.hyfit_server.controller.image;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImagePlaceSaveDto;
import com.example.hyfit_server.service.image.ImageService;
import com.example.hyfit_server.service.image.S3Service;
import com.example.hyfit_server.service.user.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@RequiredArgsConstructor
@RequestMapping("/api/image")
@RestController
public class ImageController {

    private final S3Service s3Service;
    private final ImageService imageService;
    private final UserService userService;

    @Value("https://d14okywu7b1q79.cloudfront.net")
    private String cloudfrontUrl;

    @PostMapping("/place")
    public BaseResponse<ImageDto> uploadPlaceImage(@RequestPart(value="file")MultipartFile file, @RequestPart(value="placeId")long placeId) throws Exception {
        try {
            String imageUrl = s3Service.uploadFile(file, "place/images");

            ImagePlaceSaveDto imagePlaceSaveDto = ImagePlaceSaveDto.builder()
                    .placeId(placeId)
                    .imageUrl(imageUrl)
                    .build();

            ImageDto result = imageService.saveImage(imagePlaceSaveDto, null);
            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/place/{placeId}")
    public BaseResponse<String> getImageUrl(HttpServletRequest request, @PathVariable("placeId") long placeId) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);

            ImageDto imageDto = imageService.getImageUrl(placeId);
            String result = cloudfrontUrl + imageDto.getImageUrl();
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> deletePlaceImage(@RequestParam long placeId) throws BaseException {
        try {
            imageService.deleteImage(placeId);
            String result = "이미지 삭제 완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
