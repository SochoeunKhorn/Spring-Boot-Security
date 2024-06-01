package com.sochoeun.securityjwt.controller;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.Content;
import com.sochoeun.securityjwt.model.request.ContentRequest;
import com.sochoeun.securityjwt.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private BaseResponse baseResponse;

    @PostMapping
    public ResponseEntity<?> createContent(@RequestBody ContentRequest request){
        Content content = contentService.createContent(request);
        baseResponse = new BaseResponse();
        baseResponse.success(content);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllContent(){
        List<Content> allContent = contentService.getAllContent();
        baseResponse = new BaseResponse();
        baseResponse.success(allContent);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<?> getContent(@PathVariable Integer contentId){
        Content content = contentService.getContent(contentId);
        baseResponse = new BaseResponse();
        baseResponse.success(content);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping("/{contentId}")
    public ResponseEntity<?> getContent(@PathVariable Integer contentId,@RequestBody ContentRequest request){
        Content content = contentService.updateContent(contentId,request);
        baseResponse = new BaseResponse();
        baseResponse.success(content);
        return ResponseEntity.ok(baseResponse);
    }
}
