package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.Article;
import com.sochoeun.securityjwt.model.request.ArticleRequest;

import java.util.List;

public interface ArticleService {
    Article createArticle(ArticleRequest request);
    List<Article> getAllArticle();
    Article getArticle(Integer articleId);
    Article updateArticle(Integer articleId,ArticleRequest request);
    void deleteArticle(Integer articleId);
}
