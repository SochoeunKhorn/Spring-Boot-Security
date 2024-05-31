package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.Slide;

import java.util.List;

public interface SlideService {
    Slide createSlide(Slide request);
    List<Slide> getAllSlides();
    Slide getSlide(Integer slideId);
    Slide updateSlide(Integer slideId,Slide request);
    void deleteSlide(Integer slideId);
}