package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.exception.NotFoundException;
import com.sochoeun.securityjwt.model.Article;
import com.sochoeun.securityjwt.model.Category;
import com.sochoeun.securityjwt.model.request.ArticleRequest;
import com.sochoeun.securityjwt.repository.ArticleRepository;
import com.sochoeun.securityjwt.service.ArticleService;
import com.sochoeun.securityjwt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    @Override
    public Article createArticle(ArticleRequest request) {
        Category category = categoryService.getCategory(request.getCategoryId());

        Article getRequest = Article.builder()
                .name(request.getName())
                .category(category)
                .build();
        return articleRepository.save(getRequest);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Integer articleId) {
        return articleRepository.findById(articleId).orElseThrow(()->new NotFoundException("Article",articleId));
    }

    @Override
    public Article updateArticle(Integer articleId, ArticleRequest request) {
        Article article = getArticle(articleId);
        Category category = categoryService.getCategory(request.getCategoryId());

        article.setName(request.getName());
        article.setCategory(category);
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        getArticle(articleId);
        articleRepository.deleteById(articleId);
    }
}
