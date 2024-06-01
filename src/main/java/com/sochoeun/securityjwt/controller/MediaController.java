package com.sochoeun.securityjwt.controller;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.Media;
import com.sochoeun.securityjwt.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.sochoeun.securityjwt.constant.constant.PHOTO_MEDIA_DIRECTORY;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

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

    @PutMapping("/photo")
    public ResponseEntity<String> uploadMedia(@RequestParam Integer mediaId, @RequestParam MultipartFile file){
        String profile = mediaService.uploadMedia(mediaId, file);
        return ResponseEntity.ok().body(profile);
    }

    @GetMapping(path = "/photo/{filename}",produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE})
    public byte[] getProfile(@PathVariable("filename") String filename) throws Exception{
        return Files.readAllBytes(Paths.get(PHOTO_MEDIA_DIRECTORY + filename));
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<?> deleteMedia(@PathVariable Integer mediaId){
        mediaService.deleteMedia(mediaId);
        baseResponse = new BaseResponse();
        baseResponse.success("Media ID: %s deleted".formatted(mediaId));
        return ResponseEntity.ok(baseResponse);
    }
}
