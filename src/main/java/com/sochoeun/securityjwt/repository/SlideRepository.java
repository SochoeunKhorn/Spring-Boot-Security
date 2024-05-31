package com.sochoeun.securityjwt.repository;

import com.sochoeun.securityjwt.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide,Integer> {
}
