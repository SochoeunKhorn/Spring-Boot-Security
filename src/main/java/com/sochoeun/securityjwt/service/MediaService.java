package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.Media;

import java.util.List;

public interface MediaService {
    Media createMedia(Media request);
    List<Media> getAllByContentId(Integer contentId);

    Media getMedia(String id);

    List<Media> getAllByMediaType(String type);
}
