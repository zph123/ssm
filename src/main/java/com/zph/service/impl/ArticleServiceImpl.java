package com.zph.service.impl;

import com.zph.dao.ArticleAndTagDao;
import com.zph.dao.ArticleDao;
import com.zph.domain.Article;
import com.zph.domain.ArticleAndTag;
import com.zph.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
/**
 * 事务控制
 */
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Resource
    private ArticleAndTagDao articleAndTagDao;

    @Override
    public int addArticle(Article article) {
        int nums = articleDao.addArticle(article);
        ArticleAndTag articleAndTag = new ArticleAndTag();
        if (article.getTagId() == null || "".equals(article.getTagId())){
        }else{
            articleAndTag.setArticleId(article.getId());
            articleAndTag.setTagId(article.getTagId());
            articleAndTagDao.addArticleAndTag(articleAndTag);
        }
        System.out.println(article.getId());
        return nums;
    }

    @Override
    public List<Article> selectArticle(Article article) {
        List<Article> articles = articleDao.selectArticle(article);
        return articles;
    }

    @Override
    public int updateArticleById(Article article) {
        int nums = articleDao.updateArticleById(article);
        return nums;
    }

    @Override
    public int deleteArticleById(Article article) {
        int nums = articleDao.deleteArticleById(article);
        return nums;
    }
}
