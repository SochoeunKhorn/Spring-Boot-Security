package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.exception.NotFoundException;
import com.sochoeun.securityjwt.model.Slide;
import com.sochoeun.securityjwt.repository.SlideRepository;
import com.sochoeun.securityjwt.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepository;

    // CRUD
    @Override
    public Slide createSlide(Slide request) {
        return slideRepository.save(request);
    }

    @Override
    public List<Slide> getAllSlides() {
        return slideRepository.findAll();
    }

    @Override
    public Slide getSlide(Integer slideId) {
        return slideRepository.findById(slideId).orElseThrow(()->new NotFoundException("Slide",slideId));
    }

    @Override
    public Slide updateSlide(Integer slideId, Slide request) {
        // id = 1 -> update
        Slide updateSlide = getSlide(slideId);  // ->updateSlide: id=1
        updateSlide.setName(request.getName());
        updateSlide.setDescription(request.getDescription());
        updateSlide.setImageUrl(request.getImageUrl());

        return slideRepository.save(updateSlide);
    }

    @Override
    public void deleteSlide(Integer slideId) {
        getSlide(slideId);
        slideRepository.deleteById(slideId);
    }
}
