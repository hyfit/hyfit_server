package com.example.hyfit_server.controller.image;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImageSaveDto;
import com.example.hyfit_server.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/image")
@RestController
public class ImageController {

    private final ImageService imageService;

//    @PostMapping("/save")
//    public BaseResponse<ImageDto> saveImage(@RequestBody ImageSaveDto imageSaveDto) throws BaseException {
//        try {
//
////            return new BaseResponse<>(ImageDto);
//        }
//        catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//    }
//    }
}
