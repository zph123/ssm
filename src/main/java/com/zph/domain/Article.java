package com.zph.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

public class Article {
    public interface Select {
    }

    public interface Add {
    }

    public interface Update {
    }

    @NotNull(groups = {Update.class})
    @Null(groups = {Add.class})
    private Integer id;
    @NotNull(groups = {Add.class})
    @Length(min = 6, max = 20, message = "标题长度为6~20", groups = {Add.class, Update.class})
    private String title;
    @NotNull(groups = {Add.class})
    @Length(min = 1, max = 65535, message = "内容长度为1~65535", groups = {Add.class, Update.class})
    private String content;

    private Date createAt;

    private Integer userId;

    private transient Integer tagId;
    private transient User user;

    private transient List<Tag> tags;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient Date startCreateAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient Date endCreateAt;

    @Min(value = 1, groups = Select.class)
    private transient Integer pageNum = 1;
    @Min(value = 1, groups = Select.class)
    @Max(value = 100, groups = Select.class)
    private transient Integer pageSize = 10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tag) {
        this.tags = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStartCreateAt(Date startCreateAt) {
        this.startCreateAt = startCreateAt;
    }

    public void setEndCreateAt(Date endCreateAt) {
        this.endCreateAt = endCreateAt;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Transient
    public Integer getPageNum() {
        return pageNum;
    }

    @Transient
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", userId=" + userId +
                '}';
    }
}
