package com.sochoeun.securityjwt.repository;

import com.sochoeun.securityjwt.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    // findAllArticleByCategoryId

    //
}
