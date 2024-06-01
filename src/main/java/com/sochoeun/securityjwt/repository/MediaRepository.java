package com.sochoeun.securityjwt.repository;

import com.sochoeun.securityjwt.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media,String> {
    // findAllByMediaType: Image or Video
    List<Media> findAllByMediaType(String type);
    // findAllByContentId
    List<Media> findAllByContentId(Integer contentId);
}
