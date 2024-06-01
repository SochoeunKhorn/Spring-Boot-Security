package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.exception.NotFoundException;
import com.sochoeun.securityjwt.model.Media;
import com.sochoeun.securityjwt.repository.MediaRepository;
import com.sochoeun.securityjwt.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public Media createMedia(Media request) {
        return mediaRepository.save(request);
    }

    @Override
    public List<Media> getAllByContentId(Integer contentId) {
        return mediaRepository.findAllByContentId(contentId);
    }

    @Override
    public Media getMedia(String id) {
        return mediaRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public List<Media> getAllByMediaType(String type) {
        return mediaRepository.findAllByMediaType(type);
    }
}
