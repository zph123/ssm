package com.zph.service;

import com.zph.domain.Article;

import javax.validation.Valid;
import java.util.List;

public interface ArticleService {
    int addArticle(Article article);
    List<Article> selectArticle(Article article);
    int updateArticleById(Article article);
    int deleteArticleById(Article article);
}
