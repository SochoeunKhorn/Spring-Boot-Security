package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.Content;
import com.sochoeun.securityjwt.model.request.ContentRequest;

import java.util.List;

public interface ContentService {
    Content createContent(ContentRequest request);
    List<Content> getAllContent();
    Content getContent(Integer contentId);
    Content updateContent(Integer contentId,ContentRequest request);
    void deleteContent(Integer contentId);
}
