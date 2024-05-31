package com.sochoeun.securityjwt.controller;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.Slide;
import com.sochoeun.securityjwt.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slides")
@RequiredArgsConstructor
public class SlideController {
    private final SlideService slideService;
    private BaseResponse baseResponse;

    @PostMapping
    public ResponseEntity<?> createSlide(@RequestBody Slide request){
        Slide slide = slideService.createSlide(request);
        baseResponse = new BaseResponse();
        baseResponse.success(slide);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllSlides(){
        List<Slide> allSlides = slideService.getAllSlides();
        baseResponse = new BaseResponse();
        baseResponse.success(allSlides);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/{slideId}")
    public ResponseEntity<?> getSlide(@PathVariable Integer slideId){
        Slide slide = slideService.getSlide(slideId);
        baseResponse = new BaseResponse();
        baseResponse.success(slide);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping("/{slideId}")
    public ResponseEntity<?> updateSlide(@PathVariable Integer slideId,@RequestBody Slide slide){
        Slide updated = slideService.updateSlide(slideId, slide);
        baseResponse = new BaseResponse();
        baseResponse.success(updated);
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("/{slideId}")
    public ResponseEntity<?> deleteSlide(@PathVariable Integer slideId){
        slideService.deleteSlide(slideId);
        baseResponse = new BaseResponse();
        baseResponse.success("Deleted");
        return ResponseEntity.ok(baseResponse);
    }
}
