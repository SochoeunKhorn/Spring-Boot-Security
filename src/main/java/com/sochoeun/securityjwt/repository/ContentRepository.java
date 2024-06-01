package com.sochoeun.securityjwt.repository;

import com.sochoeun.securityjwt.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
}
