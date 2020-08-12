package com.zph.controller;

import com.github.pagehelper.PageHelper;
import com.zph.domain.Article;
import com.zph.domain.User;
import com.zph.handler.AuthInterceptorHandler;
import com.zph.service.ArticleService;
import com.zph.utils.Msg;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping
    public Msg selectArticle(@Validated(Article.Select.class) Article article){

        PageHelper.startPage(article.getPageNum(), article.getPageSize());
        List<Article> articles = articleService.selectArticle(article);
        return Msg.message(200,"OK").add("list",articles);

    }
    @PostMapping
    public Msg addArticle(@Validated(Article.Add.class) Article article) throws IOException {
        //获取用户信息
        User userInfo = AuthInterceptorHandler.userInfo();
        //设置时间和uid
        article.setCreateAt(new Date());
        article.setUserId(userInfo.getId());
        //添加文章
        int nums = articleService.addArticle(article);
        return Msg.message(200,"OK").add("int",nums);
    }

    @PutMapping(value = "/{id}")
    public Msg updateArticle(@PathVariable(value = "id") int id, @Validated(Article.Update.class) Article article) throws IOException {
        //获取用户信息
        System.out.println(article);
        User userInfo = AuthInterceptorHandler.userInfo();
        article.setId(id);
        article.setUserId(userInfo.getId());
        //修改文章
        int nums = articleService.updateArticleById(article);
        return Msg.message(200,"OK").add("update",nums);
    }

    @DeleteMapping(value = "/{id}")
    public  Msg deleteArticle(@PathVariable(value = "id") int id, @Validated(Article.Update.class) Article article) throws IOException {
        //获取用户信息
        User userInfo = AuthInterceptorHandler.userInfo();
        article.setId(id);
        article.setUserId(userInfo.getId());
        //删除文章
        int nums = articleService.deleteArticleById(article);
        return Msg.message(200,"OK").add("delete",nums);
    }

}
