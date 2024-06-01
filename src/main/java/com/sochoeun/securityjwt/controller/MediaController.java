package com.sochoeun.securityjwt.controller;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.Media;
import com.sochoeun.securityjwt.service.MediaService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private BaseResponse baseResponse;

    @PostMapping
    public ResponseEntity<?> createMedia(@RequestBody Media reques){
        Media newMedia = mediaService.createMedia(reques);
        baseResponse = new BaseResponse();
        baseResponse.success(newMedia);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<?> getAllMediaByContentId(@PathVariable Integer contentId){
        List<Media> allByContentId = mediaService.getAllByContentId(contentId);
        baseResponse = new BaseResponse();
        baseResponse.success(allByContentId);
        return ResponseEntity.ok(allByContentId);
    }

    @GetMapping("/mediaType/{type}")
    public ResponseEntity<?> getAllMediaByMediaType(@PathVariable String type){
        List<Media> allByMediaType = mediaService.getAllByMediaType(type);
        baseResponse = new BaseResponse();
        baseResponse.success(allByMediaType);
        return ResponseEntity.ok(baseResponse);
    }
}
