package com.zph.dao;

import com.zph.domain.Article;

import java.util.List;

public interface ArticleDao {
    int addArticle(Article article);
    List<Article> selectArticle(Article article);
    int updateArticleById(Article article);
    int deleteArticleById(Article article);
}
